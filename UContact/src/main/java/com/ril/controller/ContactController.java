package com.ril.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ril.SendEmail;
import com.ril.classes.CDCChaine;
import com.ril.classes.Traitement;
import com.ril.dao.DonneeDao;
import com.ril.dao.TemplateDao;
import com.ril.entity.Categories;
import com.ril.entity.Champ;
import com.ril.entity.Contact;
import com.ril.entity.ContactezNousForm;
import com.ril.entity.Donnee;
import com.ril.entity.Mail;
import com.ril.entity.Template;
import com.ril.entity.User;
import com.ril.entity.UserForm;
import com.ril.entity.UserFormConnexion;
import com.ril.entity.UserFormInscription;

import com.ril.dao.ChampDao;
import com.ril.dao.ContactDao;
import com.ril.dao.UserDao;

@Controller
public class ContactController {
	@Autowired
	UserDao userDao;

	@Autowired

	ContactDao contactDao;

	@Autowired
	DonneeDao donneeDao;

	@Autowired
	TemplateDao templateDao;

	@Autowired
	ChampDao champDao;

	ContactDao contact_repository;

	// _____ BLOC FONCTIONS --> _____\\

	public User getUserConnected(HttpSession session, HttpServletRequest request) {
		// Si une session existe
		if (session.getAttribute("iduser") != null) {

			// On récupère l'utilisateur grâce à son id
			User u = userDao.findByIduser((Long) session.getAttribute("iduser"));

			if (u == null) {
				session.removeAttribute("iduser");
			} else {
				return u;
			}

		} else {

			Cookie[] cookies = request.getCookies();

			// Si des cookies existent
			if (cookies != null) {

				Long iduser = 0L;
				byte[] key = null;

				for (Cookie cookie : cookies) {

					// Si le cookie est celui contenant l'id, on le transforme en Long
					if (cookie.getName().equals("iduser")) {
						iduser = Long.parseLong(cookie.getValue());

						// Si le cookie est celui contenant la clé, on la transforme en byte, puis on la
						// hache
					} else if (cookie.getName().equals("key")) {

						key = Base64.getDecoder().decode(cookie.getValue());
						try {
							MessageDigest digest = MessageDigest.getInstance("SHA-256");
							key = digest.digest(key);

						} catch (NoSuchAlgorithmException e) {
							e.printStackTrace();
						}
					}
				}

				// On récupère l'utilisateur grâce à son id et à sa clé
				if (iduser != 0 && key != null) {

					User u = userDao.findByIduserAndEncryptedkey(iduser, key);

					if (u != null) {
						session.setAttribute("iduser", u.getIduser());
						return u;
					}
				}
			}
		}
		return null;
	}

	// Envoyer mail
	public void envoyerMail(Mail mail, String adresseMail, String sujet, String contenu) {
		// Envoi de mail de validation de compte
		SendEmail envoimail = new SendEmail(mail);
		mail.setHost("smtp.gmail.com");
		mail.setUser("quentinpetit52@gmail.com");
		mail.setPass("Gqlmp1602&*");
		mail.setTo(adresseMail);
		mail.setFrom("Support");
		mail.setSubject(sujet);
		mail.setMessageText(contenu);
		System.out.println(contenu);
		envoimail.send();
	}

	// Envoyer mail de validation de compte
	public void mailValidationAccount(User user, Mail mail, String page) throws Exception {

		// Génération de la validationkey
		SecureRandom rand = SecureRandom.getInstanceStrong();
		
		byte[] key = new byte[32];
		rand.nextBytes(key);

		// Ajout de la validationkey à l'objet de type user
		user.setValidationkey(key);
		userDao.Save(user);
		
		String validationkey = org.apache.tomcat.util.codec.binary.Base64.encodeBase64URLSafeString(key);
		String sujet = "";
		String contenu = "";

		if (page == "inscription") {
			sujet = "Activation de votre compte U-Contact";
			contenu = "Bonjour, \n\nFélicitation, vous êtes inscrit sur le site U-Contact. \n\nPour activer votre compte, veuillez cliquer sur le lien suivant http://localhost:8080/validation/"
					+ user.getIdEncrypt() + "/" + validationkey + "\n\nCordialement, \n\nL'équipe U-Contact";
			
		} else if (page == "modification-email") {
			sujet = "Modification de votre email sur U-Contact";
			contenu = "Bonjour, \n\nVous avez modifié votre adresse email sur le site U-Contact. \n\nPour valider ce changement, veuillez cliquer sur le lien suivant http://localhost:8080/validation/"
					+ user.getIdEncrypt() + "/" + validationkey + "\n\nCordialement, \n\nL'équipe U-Contact";	
		}

		envoyerMail(mail, user.getLogin(), sujet, contenu);
	}

	public void reinitialiserMotdepasse(User u, Mail mail) throws Exception {

		// Génération d'une clé de validation de modification de mot de passe non chiffrée
		SecureRandom rand = SecureRandom.getInstanceStrong();
		
		byte[] key = new byte[32];
		rand.nextBytes(key);
		
		String encryptedkeypwd = org.apache.tomcat.util.codec.binary.Base64.encodeBase64URLSafeString(key);

		// Hashage de la clé
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			key = digest.digest(key);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String sujet = "Modification mot de passe - U-Contact";
		String contenu = "Bonjour, \n\nAfin de modifier votre mot de passe pour vous connecter au site U-Contact, veuillez cliquer sur le lien suivant : http://localhost:8080/modification-mot-de-passe/"
				+ u.getIdEncrypt() + "/" + encryptedkeypwd + "\n\nCordialement, \n\nL'équipe U-Contact";

		envoyerMail(mail, u.getLogin(), sujet, contenu);

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		// Ajout du timestamp au moment de l'envoi du mail
		u.setEncryptedkeypwd(key);
		u.setTimestampModifPwd(ts);

		// Enregistrement en base de la clé de validation chiffrée
		userDao.Save(u);
	}
	// _____ <-- BLOC FONCTIONS _____\\

	// _____ BLOC MÉTHODES --> _____\\
	// Affichage de la page index
	@RequestMapping("/")
	public String accueil(Model model, HttpSession session, HttpServletRequest request) throws Exception {

		User u = getUserConnected(session, request);

		// Si l'utilisateur est logué
		if (u != null)
			model.addAttribute("user", u);

		/*
		 * User u2=userDao.findByIduser(2); if (u2==null) System.out.println("u2 null");
		 * else System.out.println("u2: "+u2);
		 * 
		 * 
		 * UserFormConnexion uc=new UserFormConnexion();
		 * 
		 * if (uc==null) System.out.println("uc null"); else
		 * System.out.println("uc: "+uc);
		 * 
		 * uc.setHashedPassword("user2");
		 * 
		 * System.out.println(uc.getHashedPassword().toString());
		 * 
		 * u2.setHashedPassword(uc.getHashedPassword());
		 * 
		 * u2.setValidaccount(true); userDao.Save(u2);
		 */

		return "index";
	}

	// Affichage de la page d'inscription
	@RequestMapping(value = { "/inscription", "/inscription/{retour}" })
	public String inscription(@Valid @ModelAttribute UserFormInscription userForm, BindingResult result, Model model,
			@PathVariable("retour") Optional<String> retour, Mail mail, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws Exception {

		User u = getUserConnected(session, request);

		// Si l'utilisateur n'est pas encore logué
		if (u == null) {

			// S'il y a un paramètre GET
			if (retour.isPresent()) {
				model.addAttribute("retour", retour.get());
			} else {
				model.addAttribute("retour", null);
			}

			// Si le formulaire a été rempli
			if (!result.hasErrors()) {

				User user = userDao.findByLogin(userForm.getLogin());

				// Si l'email renseigné dans le formulaire est déjà présent en base
				if (user != null) {
					response.sendRedirect("/inscription/erreur-compte-existant");
					return null;

				} else {

					// On vérifie si les 2 mots de passe entrés sont bien identiques
					if (userForm.getPassword().equals(userForm.getConfirmPassword())) {

						user = new User();
						user.setLogin(userForm.getLogin());
						userForm.setHashedPassword(userForm.getPassword());
						user.setHashedPassword(userForm.getHashedPassword());
						user.setDtcreation(Date.valueOf(LocalDate.now()));
						user.setActif(true);

						// Ajout en base de l'utilisateur (enregistrement des modifications)
						user.setIduser(userDao.Save(user));

						mailValidationAccount(user, mail, "inscription");

						// Redirection vers la page attente-validation
						response.sendRedirect("/attente-validation");

						return null;

					} else {
						// Si les mots de passe sont différents on est redirigé vers la page inscription
						response.sendRedirect("/inscription/erreur");
						return null;
					}
				}
			} else {
				return "inscription";
			}

			// Si l'utilisateur est déjà logué, on le redirige vers sa page de contacts
		} else {
			response.sendRedirect("/contacts");
			return null;
		}
	}

	// Affichage de la page attente-validation
	@RequestMapping("/attente-validation")
	public String attenteValidation(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User u = getUserConnected(session, request);

		// Si l'utilisateur n'est pas encore logué
		if (u == null) {
			return "attente-validation";

			// Si l'utilisateur est déjà logué, on le redirige vers sa page de contacts
		} else {

			response.sendRedirect("/contacts");
			return null;
		}
	}

	// Affichage de la page attente-motdepasse-oublie
	@RequestMapping("/attente-motdepasse-oublie")
	public String attenteMotdepasseOublie(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User u = getUserConnected(session, request);

		// Si l'utilisateur n'est pas encore logué
		if (u == null) {
			return "attente-motdepasse-oublie";

			// Si l'utilisateur est déjà logué, on le redirige vers sa page de contacts
		} else {

			response.sendRedirect("/contacts");
			return null;
		}
	}

	// Traitement de la validation du compte de l'utilisateur
	@RequestMapping(value = { "/validation/{idEncrypt}/{validationkey}" }, method = RequestMethod.GET)
	public void validationCompte(Model model, @PathVariable("idEncrypt") String idEncrypt,
			@PathVariable("validationkey") String validationkey, HttpServletResponse response) throws Exception {

		
		byte[] key = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(validationkey);
		
		Long iduser = Long.parseLong(CDCChaine.Decrypter(idEncrypt));
		User u = userDao.findByIduserAndValidationkey(iduser, key, true);

		if (u != null) {

			u.setValidaccount(true);
			u.setValidationkey(null);
			userDao.Save(u);
			response.sendRedirect("/connexion/compte-valide");

		} else {

			response.sendRedirect("/connexion/erreur-validation-compte");
		}
	}

	// Affichage de la page motdepasse-oublie
	@RequestMapping(value = { "/mot-de-passe-oublie", "/mot-de-passe-oublie/{retour}" })
	public String motdepasseOublie(@Valid @ModelAttribute UserForm userForm, BindingResult result, Mail mail,
			Model model, @PathVariable("retour") Optional<String> retour, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws Exception {

		User u = getUserConnected(session, request);

		// Si l'utilisateur n'est pas encore logué
		if (u == null) {

			// S'il y a un paramètre GET
			if (retour.isPresent()) {
				model.addAttribute("retour", retour.get());
			} else {
				model.addAttribute("retour", null);
			}

			// Si le formulaire a été rempli
			if (!result.hasErrors()) {

				u = userDao.findByLogin(userForm.getLogin());

				if (u != null) {

					// Si l'utilisateur a validé son compte
					if (u.getValidaccount()) {

						reinitialiserMotdepasse(u, mail);

						// Redirection vers la page attente-motdepasse-oublie
						response.sendRedirect("/attente-motdepasse-oublie");
						return null;

					} else {

						mailValidationAccount(u, mail, "inscription");

						// Redirection vers la page attente-validation
						response.sendRedirect("/attente-validation");
						return null;
					}
				} else {

					response.sendRedirect("/mot-de-passe-oublie/erreur");
					return null;
				}

			} else {

				return "motdepasse-oublie";
			}

			// Si l'utilisateur est déjà logué, on le redirige vers sa page de contacts
		} else {
			response.sendRedirect("/contacts");
			return null;
		}
	}

	// Traitement après le clic sur le lien de l'email mot de passe oublié
	@RequestMapping(value = { "/modification-mot-de-passe/{idEncrypt}/{encryptedkeypwd}" }, method = RequestMethod.GET)
	public String modificationMotDePasse(@PathVariable("idEncrypt") String idEncrypt,
			@PathVariable("encryptedkeypwd") String encryptedkeypwd, Mail mail, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		User u = getUserConnected(session, request);

		// Si l'utilisateur n'est pas encore logué
		if (u == null) {

			byte[] key = null;

			// Hashage de la clé
			key = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(encryptedkeypwd);
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				key = digest.digest(key);

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			// On vérifie les informations présentes dans l'URL en recherchant un
			// utilisateur par rapport à l'id et encryptedkeypwd
			Long iduser = Long.parseLong(CDCChaine.Decrypter(idEncrypt));
			u = userDao.findByIduserAndEncryptedkeypwd(iduser, key);

			if (u != null) {

				Timestamp tsmdp = u.getTimestampModifPwd();
				Timestamp tsnow = new Timestamp(System.currentTimeMillis());

				// Si la différence est de plus de 24h alors
				if ((tsnow.getTime() - tsmdp.getTime()) > 86400000) {

					// On renvoie le mail de réinitialisation du mot de passe
					reinitialiserMotdepasse(u, mail);
					return "attente-delai-depasse";

				} else {

					// On enregistre son id en session
					session.setAttribute("iduser", u.getIduser());

					// On redirige vers le formulaire de modification du mot de passe
					response.sendRedirect("/modifier-motdepasse");
					return null;
				}

			} else {

				// Redirection vers la page attente-validation
				response.sendRedirect("/connexion/erreur-validation-compte");
				return null;
			}

			// Si l'utilisateur est déjà logué, on le redirige vers sa page de contacts
		} else {
			response.sendRedirect("/contacts");
			return null;
		}
	}

	@RequestMapping(value = { "/modifier-motdepasse", "/modifier-motdepasse/{retour}" })
	public String modifierMotdepasse(@Valid @ModelAttribute UserFormInscription userForm, BindingResult result,
			Model model, @PathVariable("retour") Optional<String> retour, HttpSession session,
			HttpServletResponse response, HttpServletRequest request) throws Exception {

		User u = getUserConnected(session, request);

		// Si l'utilisateur est logué comme il doit l'être sur cette page
		if (u != null) {

			// S'il y a un paramètre GET
			if (retour.isPresent()) {
				model.addAttribute("retour", retour.get());
			} else {
				model.addAttribute("retour", null);
			}

			// Si le formulaire a été rempli
			if (!result.hasErrors()) {

				// On vérifie si les 2 mots de passe entrés sont bien identiques
				if (userForm.getPassword().equals(userForm.getConfirmPassword())) {

					userForm.setHashedPassword(userForm.getPassword());
					u.setHashedPassword(userForm.getHashedPassword());

					// Ajout en base de l'utilisateur (enregistrement des modifications)
					userDao.Save(u);

					response.sendRedirect("/compte/motdepasse-modifie");

					return null;

				} else {
					// Si les mots de passe sont différents on est redirigé vers la page inscription
					response.sendRedirect("modifier-motdepasse/erreur");
					return null;
				}

			} else {
				model.addAttribute("user", u);
				return "modification-motdepasse";
			}

			// Si l'utilisateur arrive sur cette page sans avoir été identifié
		} else {
			response.sendRedirect("/connexion");
			return null;
		}
	}

	// Affichage de la page Contactez-nous
	@RequestMapping(value = { "/contactez-nous", "/contactez-nous/{retour}" })
	public String contactezNous(@Valid @ModelAttribute ContactezNousForm contactezNousForm, BindingResult result,
			Model model, Mail mail, @PathVariable("retour") Optional<String> retour, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws IOException {

		User u = getUserConnected(session, request);

		// Si l'utilisateur est logué
		if (u != null)
			model.addAttribute("user", u);
		model.addAttribute("categories", EnumSet.allOf(Categories.class));

		if (retour.isPresent()) {
			model.addAttribute("retour", retour.get());
		} else {
			model.addAttribute("retour", null);
		}

		// Si le formulaire a été rempli
		if (!result.hasErrors()) {

			String contenu = "Bonjour, \n\nUn nouveau message a été envoyé sur le site U-Contact. \nEmail de l'utilisateur : "
					+ contactezNousForm.getEmail() + "\nCatégorie : " + contactezNousForm.getCategorie() + "\nSujet : "
					+ contactezNousForm.getSujet() + "\nMessage : " + contactezNousForm.getMessage();
			envoyerMail(mail, "quentinpetit52@gmail.com", "Un nouveau message a été envoyé sur le site U-Contact",
					contenu);
			response.sendRedirect("contactez-nous/succes");
			return null;

		} else {
			return "contactez-nous";
		}
	}

	@RequestMapping(value = { "/connexion", "/connexion/{retour}" })
	public String connexion(@Valid @ModelAttribute UserFormConnexion userForm, BindingResult result, Model model,
			@PathVariable("retour") Optional<String> retour, HttpSession session, HttpServletResponse response,
			HttpServletRequest request, Mail mail) throws Exception {

		User u = getUserConnected(session, request);

		// Si l'utilisateur n'est pas encore logué
		if (u == null) {

			// S'il y a un paramètre GET
			if (retour.isPresent()) {
				model.addAttribute("retour", retour.get());
			} else {
				model.addAttribute("retour", null);
			}

			// Si le formulaire a été rempli
			if (!result.hasErrors()) {
				User user;

				// On récupère l'utilisateur grâce à son login et à son mot de passe
				userForm.setHashedPassword(userForm.getPassword());
				user = userDao.findByLoginAndHashedPassword(userForm.getLogin(), userForm.getHashedPassword(), true);

				// Si on ne récupère pas l'utilisateur
				if (user == null) {
					session.removeAttribute("iduser");
					response.sendRedirect("/connexion/erreur-connexion");
					return null;

				} else {

					// Si le compte a été validé
					if (user.getValidaccount()) {

						// On enregistre son id en session
						session.setAttribute("iduser", user.getIduser());

						// Si l'utilisateur veut qu'on se souvienne de lui
						if (userForm.isRemember()) {
							// On stocke son id et une clé hashée dans 2 cookies, et on enregistre la clé en
							// bdd
							Cookie cookie = new Cookie("iduser", "" + user.getIduser());
							cookie.setMaxAge(2147483647);
							response.addCookie(cookie);
							byte[] key = new byte[32];

							try {
								SecureRandom.getInstanceStrong().nextBytes(key);
								cookie = new Cookie("key", Base64.getEncoder().encodeToString(key));
								cookie.setMaxAge(2147483647);
								response.addCookie(cookie);
								MessageDigest digest = MessageDigest.getInstance("SHA-256");
								byte[] encodedHash = digest.digest(key);
								user.setEncryptedkey(encodedHash);
								userDao.Save(user);
							} catch (NoSuchAlgorithmException e) {
								e.printStackTrace();
							}
						}
						// Une fois l'utilisateur logué, on le redirige vers sa page de contacts
						response.sendRedirect("/contacts");
						return null;

					} else {

						mailValidationAccount(user, mail, "inscription");

						// Redirection vers la page attente-validation
						response.sendRedirect("/attente-validation");
						return null;
					}
				}
			} else {
				return "connexion";
			}

			// Si l'utilisateur est déjà logué, on le redirige vers sa page de contacts
		} else {
			response.sendRedirect("/contacts");
			return null;
		}
	}

	@RequestMapping("/contacts")
	public String affichageContacts(@ModelAttribute("templates") ArrayList<Template> templates, HttpSession session,
			Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		User u = getUserConnected(session, request);

		// Si l'utilisateur est logué
		if (u != null) {

			java.util.Date date = new java.util.Date();

			Timestamp t = new Timestamp(date.getTime());

			// recherche du template
			templates = (ArrayList<Template>) templateDao.getTemplates(0, 0, true);
			long idtemplate = 0;

			/*** s'il y a q'un template, il est sélectionné ***/
			if (templates.size() == 1) {
				templates.get(0).setCheck(true);
				session.setAttribute("idtemplate", templates.get(0).getIdtemplate());
				idtemplate = templates.get(0).getIdtemplate();

				templates.get(0).getChamps().forEach(x -> {
					System.out.println(x);
				});

			}

			// System.out.println(templates.size());

			/*** **************************************** ***/

			List<Contact> contacts = contactDao.findByIduserAndIdTemplate(u.getIduser(), idtemplate, true, t);

			System.out.println(contacts + "; " + session.getAttribute("iduser"));

			contacts.forEach(x -> {
				System.out.println(x);
			});

			model.addAttribute(u);
			model.addAttribute("templates", templates);
			model.addAttribute("contacts", contacts);

			return "contacts";

		} else {
			response.sendRedirect("/connexion");
			return null;
		}
	}

	@GetMapping("/modifier-contact/{idcontactEncrypt}")
	public String modifierContact(@PathVariable("idcontactEncrypt") String idcontactEncrypt, HttpSession session,
			Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
 
		System.out.println(idcontactEncrypt+" modifié");
		User u = getUserConnected(session, request);
		// System.out.println("modifier contact "+ u);
		// System.out.println("id contact "+ stringIdContact);

		// Si l'utilisateur est logué
		if (u != null) {

			long idcontact = 0;

			//si idcontactEncrypt = 0 ==> Création d'un nouveau contact
			
			if (idcontactEncrypt.compareTo("0") != 0) {
				try {
					idcontact = Long.parseLong(CDCChaine.Decrypter(idcontactEncrypt));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					idcontact = -1;
				}
			}

			// retourne à la page Contacts s'il il y a erreur sur le décryptage de
			// idcontactEncrypt
			if (idcontact == -1) {
				response.sendRedirect("/connexion");
				return null;
			}
			
			//si les variables de session ne sont pas trouvé, retourne à la page connexion
			if (session.getAttribute("idtemplate") == null || session.getAttribute("idtemplate").equals("") ||
					session.getAttribute("iduser") == null || session.getAttribute("iduser").equals(""))
			{
				response.sendRedirect("/connexion");
				return null;
			}
				
			System.out.println(idcontact + " modifié");

			long idtemplate = (long) session.getAttribute("idtemplate");
			long iduser = (long) session.getAttribute("iduser");

			List<Template> templates = templateDao.getTemplates(iduser, idtemplate, true);
			Template t = null;

			// System.out.println(templates.size());

			// récupère qu'un template : le template standard, pas encore de gestion de
			// template personnalisé
			if (templates.size() == 1) {
				t = templates.get(0);
			}

			if (templates.size() == 0) {
				// System.out.println("template empty");
				return "contacts";
			}

			/*
			 * t.getChamps().forEach(x -> { System.out.println(x); });
			 */

			// renseigne les données du contact
			Contact c = contactDao.findByIdcontactAndIdTemplate(idcontact, idtemplate, true,
					new Timestamp(new java.util.Date().getTime()));
			System.out.println("le contact " + c);
			if (c != null) {

				// vérifie si c'est le contact de l'utilisateur: si non renvoie sur la page
				// liste des contacts
				if (c.getIduser() != u.getIduser())
					response.sendRedirect("/contacts");
				// -----------------------------------------------------------------------------------------------/

				for (Champ cTemplate : t.getChamps()) {
					for (Donnee dContact : c.getDonnees()) {
						if (cTemplate.getIdchamp() == dContact.getIdchamp()) {
							cTemplate.setDonnee(dContact);
						}
					}
				}
			}

			model.addAttribute(u);
			model.addAttribute("template", t);
			model.addAttribute("idcontact", idcontact);

			return "modifier-contact";

		} else {
			System.out.println("user null");
			response.sendRedirect("/connexion");
			return null;
		}
	}

	@PostMapping("/enregistrer-contact")
	public void enregistrerContact(@ModelAttribute("template") Template template,
			@ModelAttribute("idcontactEncrypt") String idcontactEncrypt, Model model, HttpSession session,
			HttpServletResponse response, @RequestParam("file") MultipartFile myFile)
			throws IOException, URISyntaxException {

		long idtemplate = (long) session.getAttribute("idtemplate");

		// récuère le contact passé en paramètre
		long idcontact = 0;
		try {
			idcontact = Long.parseLong(CDCChaine.Decrypter(idcontactEncrypt));
		} catch (Exception e) {
		}

		System.out.println(idcontact + " enregistré");

		User u = userDao.findByIduser((long) session.getAttribute("iduser"));

		if (u == null)
			response.sendRedirect("/modifier-contact");
		else {

			Contact c = contactDao.findByIdcontactAndIdTemplate(idcontact, idtemplate, true,
					new Timestamp(new java.util.Date().getTime()));

			if (c == null) {
				// Enregistrer un contact
				c = new Contact();
				c.setIduser(u.getIduser());
				c.setDtcreation(Date.valueOf(LocalDate.now()));
				c.setFavoris(false);
				c.setActif(true);

				idcontact = contactDao.Save(c);
				System.out.println("id contact créé: " + idcontact);
			}

			final long idcontact_donnee = idcontact;

			java.util.Date date = new java.util.Date();
			final Timestamp now = new Timestamp(date.getTime());

			// System.out.println("Template: " + template);
			
			if (idcontact > 0) { // Enregistrer les données du contact
				template.getChamps().forEach(x -> {

					System.out.println("champ: " + x);

					x.getDonnee().setDtenregistrement(now);
					x.getDonnee().setIdcontact(idcontact_donnee);
					x.getDonnee().setIdchamp(x.getIdchamp());

					// enregistre les données en base
					try {
						// sauvegarde la photo
						if (myFile != null && myFile.getOriginalFilename().compareTo("")!=0 &&  x.getDatatype().getLibelle().compareTo("PHOTO") == 0) {
					
							Traitement.Photo(myFile, idcontactEncrypt);
							
							x.getDonnee().setValeur(myFile.getOriginalFilename());
						}

						donneeDao.Save(x.getDonnee());

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			}

			response.sendRedirect("/contacts");
		}
	}
	
	@ResponseBody
	@GetMapping("/changer-favori/{idcontactEncrypt}/{favori}")
	public boolean changerFavori(@PathVariable("idcontactEncrypt") String idcontactEncrypt, @PathVariable("favori") boolean favori) throws Exception {

		long idcontact = 0;
		
		if (favori == true) {
			favori = false;
		} else {
			favori = true;
		}
		

		try {
			idcontact = Long.parseLong(CDCChaine.Decrypter(idcontactEncrypt));
		} catch (Exception e) {
		}

		contactDao.ChangeFavoriByIdContact(idcontact, favori);

		return true;
	}

	@ResponseBody
	@GetMapping("/supprimer-contact/{idcontactEncrypt}")
	public boolean supprimerUnContact(@PathVariable("idcontactEncrypt") String idcontactEncrypt) throws Exception {

		long idcontact = 0;

		try {
			idcontact = Long.parseLong(CDCChaine.Decrypter(idcontactEncrypt));
		} catch (Exception e) {
		}

		contactDao.ActiverDesactiverByIdContact(idcontact, false);

		return true;
	}

	// Déconnexion de l'utilisateur
	@RequestMapping("/deconnexion")
	public void deconnexion(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// Suppression de la requête en cours
		session.removeAttribute("iduser");

		// Suppression des cookies
		Cookie[] cookies = request.getCookies();

		// Si des cookies existent
		if (cookies != null) {

			for (Cookie cookie : cookies) {

				// Si le cookie est celui contenant l'id, on le supprime
				if (cookie.getName().equals("iduser")) {
					cookie.setMaxAge(0);

					// Si le cookie est celui contenant la clé, on le supprime
				} else if (cookie.getName().equals("key")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		response.sendRedirect("/");
	}
	
	@RequestMapping(value = { "/compte", "/compte/{retour}" })
	public String compte(HttpSession session, @PathVariable("retour") Optional<String> retour, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		User u = getUserConnected(session, request);

		// Si l'utilisateur est logué
		if (u != null) {

			// S'il y a un paramètre GET
			if (retour.isPresent()) {
				model.addAttribute("retour", retour.get());
			} else {
				model.addAttribute("retour", null);
			}
			
			model.addAttribute(u);

			return "compte";

		} else {
			response.sendRedirect("/connexion");
			return null;
		}
	}
	
	@RequestMapping(value = { "/modifier-email", "/modifier-email/{retour}" })
	public String modifierEmail(@Valid @ModelAttribute UserForm userForm, BindingResult result, Mail mail, Model model, 
			@PathVariable("retour") Optional<String> retour, HttpSession session, HttpServletResponse response, HttpServletRequest request) throws Exception {

		User u = getUserConnected(session, request);

		// Si l'utilisateur est logué comme il doit l'être sur cette page
		if (u != null) {
			
			// S'il y a un paramètre GET
			if (retour.isPresent()) {
				model.addAttribute("retour", retour.get());
			} else {
				model.addAttribute("retour", null);
			}

			// Si le formulaire a été rempli
			if (!result.hasErrors()) {
				
				User user = userDao.findByLogin(userForm.getLogin());

				// Si l'email renseigné dans le formulaire est déjà présent en base
				if (user != null) {
					response.sendRedirect("/modifier-email/erreur-compte-existant");
					return null;
				}

				u.setLogin(userForm.getLogin());
				u.setValidaccount(false);
				userDao.Save(u);
				mailValidationAccount(u, mail, "modification-email");

				response.sendRedirect("/compte/email-modifie");

				return null;

			} else {
				model.addAttribute("user", u);
				return "modification-email";
			}

			// Si l'utilisateur arrive sur cette page sans avoir été identifié
		} else {
			response.sendRedirect("/connexion");
			return null;
		}
	}
}
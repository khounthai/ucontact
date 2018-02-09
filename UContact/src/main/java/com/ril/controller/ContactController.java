package com.ril.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
import org.springframework.web.bind.support.SessionStatus;
import com.ril.SendEmail;
import com.ril.dao.DonneeDao;
import com.ril.dao.TemplateDao;
import com.ril.entity.Categories;
import com.ril.entity.Champ;
import com.ril.entity.Contact;
import com.ril.entity.Donnee;
import com.ril.entity.Mail;
import com.ril.entity.Template;
import com.ril.entity.User;
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

	public User getUserConnected(HttpSession session, SessionStatus session_status, HttpServletRequest request) {
		// Si une session existe
		if (session.getAttribute("iduser") != null) {

			// On récupère l'utilisateur grâce à son id
			User u = userDao.findByIduser((Long) session.getAttribute("iduser"), true);

			if (u == null) {
				session_status.setComplete();
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

					User u = userDao.findByIduserAndEncryptedkey(iduser, key, true);

					if (u != null) {
						session.setAttribute("iduser", u.getIduser());
						return u;
					}
				}
			}
		}
		return null;
	}

    @RequestMapping(value={"/connexion","/connexion/{retour}"})
    public String connexion(Model model, @PathVariable("retour") Optional<String> retour, User user, HttpSession session, SessionStatus session_status, HttpServletResponse response) throws Exception {	
    	
    	// S'il y a un paramètre GET
    	if (retour.isPresent()) {
    		model.addAttribute("retour", retour.get());
    	} else {
    		model.addAttribute("retour", null);
    	}
    	
    	// Si le formulaire a été rempli
    	if (user.getLogin() != null && user.getPassword() != null) {
   
	    	// On récupère l'utilisateur grâce à son login et à son mot de passe
	    	User u = userDao.findByLoginAndPassword(user.getLogin(), user.getPassword(),true);
	    	
	    	// Si on ne récupère pas l'utilisateur
	    	if (u == null) {
	    		session_status.setComplete();
	    		
	    		response.sendRedirect("/connexion/erreur");
	    		return null;
	    		
	    	// Si on récupère l'utilisateur
	    	} else {
	    		
	    		// On enregistre son id en session
	    		session.setAttribute("iduser", u.getIduser());
	    		
	    		// Si l'utilisateur veut qu'on se souvienne de lui
	    		if(user.getRemember()) {
	
	    			// On stocke son id et une clé hashée dans 2 cookies, et on enregistre la clé en bdd
	    			Cookie cookie = new Cookie("iduser", ""+u.getIduser());
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
		    			u.setEncryptedkey(encodedHash);
		    			
		    			userDao.Save(u);    			
		    			
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}    			    			
	    		}
	    		
	    		// Une fois l'utilisateur logué, on le redirige vers sa page de contacts
	    		response.sendRedirect("/contacts");
	    		return null;
	    	}
    	} else {
    		return "connexion";
    	}
    }
    
    @RequestMapping("/attente-validation")
    public String attentevalidation(Model model, User user) {    	
    	return "attente-validation";    }

	@RequestMapping("/")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "index";
	}

	@PostMapping("/contactez-nous-form")
	public String contactezNousForm(@ModelAttribute("mail") Mail mail) {

		SendEmail envoimail = new SendEmail(mail);
		mail.setHost("smtp-mail.outlook.com");
		mail.setUser("khounthai.houang@viacesi.fr");
		mail.setPass("Gypab516");
		mail.setTo("khounthai.houang@viacesi.fr");
		mail.setFrom("Support");
		mail.setSubject(mail.getCategorie() + " - " + mail.getSubject());

		boolean bok = envoimail.send();
		if (bok)
			return "contactez-nous/succes";
		return "contactez-nous/erreur";
	}

	@RequestMapping(value = { "/contactez-nous", "/contactez-nous/{retour}" }, method = RequestMethod.GET)
	public String contactezNous(Model model, @PathVariable("retour") Optional<String> retour,
			@ModelAttribute("mail") Mail mail) {

		model.addAttribute("mail", new Mail());
		model.addAttribute("categories", EnumSet.allOf(Categories.class));
		if (retour.isPresent()) {
			model.addAttribute("retour", retour.get());
		} else {
			model.addAttribute("retour", null);
		}
		return "contactez-nous";
	}

	@RequestMapping("/contacts")
	public String affichageContacts(@ModelAttribute("templates") ArrayList<Template> templates, HttpSession session,
			Model model,SessionStatus session_status, HttpServletRequest request) throws Exception {
		
		User u = getUserConnected(session, session_status, request);

		if (u != null) {
			/*java.util.Date d = new java.util.Date();
			SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			java.util.Date date = parser.parse("08/02/2018 09:40:00");
			Timestamp t = new Timestamp(date.getTime());*/
			
			java.util.Date date =new java.util.Date();
			
			Timestamp t = new Timestamp(date.getTime());
			
			//recherche du template
			templates = (ArrayList<Template>) templateDao.getTemplates(0, 0, true);
			long idtemplate=0;

			/*** s'il y a q'un template, il est sélectionné ***/
			if (templates.size() == 1) {
				templates.get(0).setCheck(true);
				session.setAttribute("idtemplate", templates.get(0).getIdtemplate());
				idtemplate=templates.get(0).getIdtemplate();
			}
			
			System.out.println(templates.size());
			/*** **************************************** ***/
			
			List<Contact> contacts = contactDao.findByIduserAndIdTemplate(u.getIduser(),idtemplate, true, t);
			
			System.out.println(contacts + "; " + session.getAttribute("iduser"));

			contacts.forEach(x -> {
				System.out.println(x);
			});

			
			model.addAttribute("templates", templates);
			model.addAttribute("contacts", contacts);

			return "contacts";
		} else {			
			return "connexion";
		}
	}

	@GetMapping("/fiche-contact-form/{idcontact}")
	public String ficheContactForm(@PathVariable("idcontact") String stringIdContact, HttpSession session, Model model)
			throws Exception {
		
		long idcontact = 0;

		try {
			idcontact = Long.parseLong(stringIdContact);
		} catch (Exception e) {
		}

		System.out.println("fiche-contact-form: idcontact=" + idcontact);

		long idtemplate = (long) session.getAttribute("idtemplate");
		long iduser = (long) session.getAttribute("iduser");

		List<Template> templates = templateDao.getTemplates(iduser, idtemplate, true);
		Template t = null;

		System.out.println(templates.size());

		//récupère qu'un template : le template standard, pas encore de gestion de template personnalisé 
		if (templates.size() == 1) {
			t = templates.get(0);
		}

		if (templates.size() == 0) {
			System.out.println("template empty");
			return "contacts";
		}

		t.getChamps().forEach(x -> {
			System.out.println(x);
		});

		// renseigne les données du contact
		Contact c = contactDao.findByIdcontactAndIdTemplate(idcontact,idtemplate, true, new Timestamp(new java.util.Date().getTime()));
		if (c != null) {
			for (Champ item1 : t.getChamps()) {

				for (Donnee item2 : c.getDonnees()) {
					if (item1.getIdchamp() == item2.getIdchamp()) {
						item1.setDonnee(item2);
					}
				}
			}
		}

		model.addAttribute("template", t);
		model.addAttribute("idcontact", idcontact);
		return "fiche-contact-form";
	}

	@PostMapping("/enregistrer-contact")
	public void enregistrerContact(@ModelAttribute("template") Template template,
			@ModelAttribute("idcontact") String strIdContact, Model model, HttpSession session,
			HttpServletResponse response) throws IOException {
		
		long idtemplate = (long) session.getAttribute("idtemplate");
		
		// récuère le contact passé en paramètre
		long idcontact = 0;
		try {
			idcontact = Long.parseLong(strIdContact);
		} catch (Exception e) {
		}

		
		User u = userDao.findByIduser((long) session.getAttribute("iduser"), true);

		if (u == null)
			response.sendRedirect("/fiche-contact-form");
		else {

			Contact c = contactDao.findByIdcontactAndIdTemplate(idcontact,idtemplate, true, new Timestamp(new java.util.Date().getTime()));

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

			if (idcontact > 0) { // Enregistrer les données du contact
				template.getChamps().forEach(x -> {
					java.util.Date date = new java.util.Date();
					Timestamp now = new Timestamp(date.getTime());

					x.getDonnee().setDtenregistrement(now);
					x.getDonnee().setIdcontact(idcontact_donnee);
					x.getDonnee().setIdchamp(x.getIdchamp());

					// enregistre les données en base
					try {
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

	@GetMapping("/supprimer-contact/{idcontact}")
	public void supprimerUnContact(@PathVariable("idcontact") String stringIdContact,HttpServletResponse response)
			throws Exception {

		long idcontact = 0;

		try {
			idcontact = Long.parseLong(stringIdContact);
		} catch (Exception e) {
		}

		System.out.println("supprimer-contact: idcontact=" + idcontact);
		contactDao.ActiverDesactiverByIdContact(idcontact,false);		
		response.sendRedirect("/contacts");
	}
	
	@RequestMapping("/inscription")
	public String inscription(Model model, User user) {
		return "inscription";
	}

	@PostMapping("/inscription-form")
	public void inscriptionForm(@ModelAttribute("user") User user, Model model, HttpSession session,
			HttpServletResponse response, Mail mail) throws Exception {

		String password1 = user.getPassword();
		String password2 = user.getConfirmpassword();
		String useracomparer = user.getLogin();
		User userref = userDao.findByLogin(user.getLogin(), true);

		// Si l'email est déjà présent en base
		if (useracomparer != null && userref != null && useracomparer.equals(userref.getLogin())) {

		} else {
			if (password1.equals(password2)) {
				// Chiffrement du mot de passe
				Random rand = new Random();
				String validationkey = "";
				for (int i = 0; i < 20; i++) {
					char c = (char) (rand.nextInt(26) + 97);
					validationkey += c;
					System.out.print(c + " ");
				}

				user.setValidationkey(validationkey);

				// Ajout en base de l'utilisateur
				long result = userDao.Save(user);
				User u = userDao.findByIduser(result, true);

				if (u == null) {
					response.sendRedirect("/");
				} else {

					// Envoi de mail de validation de compte
					SendEmail envoimailvalidation = new SendEmail(mail);
					mail.setHost("smtp.gmail.com");
					mail.setUser("quentinpetit52@gmail.com");
					mail.setPass("qlmp1602");
					mail.setTo(u.getLogin());
					mail.setFrom("Support");
					mail.setSubject("Activation de votre compte U-Contact");
					mail.setMessageText(
							"Bonjour, \n\nVotre compte n'est pas encore activé. Afin d'activer votre compte, veuillez cliquer suivant http://localhost:8080/validation/"
									+ u.getIduser() + "/" + validationkey);

					envoimailvalidation.send();
					response.sendRedirect("/attente-validation");
				}

			} else {

				// Cas ou les mots de passe sont différents
				response.sendRedirect("/inscription");
			}

		}

	}

	@RequestMapping(value = { "/validation/{iduser}/{validationkey}" }, method = RequestMethod.GET)
	public String validationCompte(Model model, @PathVariable("iduser") Long iduser,
			@PathVariable("validationkey") String validationkey) throws Exception {

		User u = userDao.findByIduserAndValidationkey(iduser, validationkey, true);
		System.out.println(u + " " + iduser);
		if (u != null) {
			u.setValidaccount(true);
			u.setValidationkey(null);
			userDao.Save(u);
			return "/connexion/compte-valide";

		} else {

			return "/connexion";
		}
	}
}
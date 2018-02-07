package com.ril.controller;

import java.io.IOException;
import java.time.LocalDate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.ril.entity.Contact;
import com.ril.entity.Mail;
import com.ril.entity.Template;
import com.ril.entity.User;
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
	
	public User getUserConnected(HttpSession session, SessionStatus session_status, HttpServletRequest request) {		
		// Si une session existe
		if(session.getAttribute("iduser") != null) {
			
			// On récupère l'utilisateur grâce à son id
			User u = userDao.findByIduser((Long)session.getAttribute("iduser")); 
			
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
					
					// Si le cookie est celui contenant la clé, on la transforme en byte, puis on la hache
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
	
    @RequestMapping("/connexion")
    public String connexion(Model Model) { 	
    	User u = new User();
    	Model.addAttribute("user", u);
    	
    	return "connexion";
    }
    
    @RequestMapping("/attente-validation")
    public String attentevalidation(Model model, User user) {    	
    	return "attente-validation";    }
    
    @RequestMapping("/connexion-form")
    public void connexionForm(@ModelAttribute("user") User user, HttpSession session, SessionStatus session_status, HttpServletResponse response) throws IOException {
    	//User u = userDao.findByLoginAndHashedPassword(user.getLogin(), user.getHashedPassword());
    	User u = userDao.findByLoginAndPassword(user.getLogin(), user.getPassword());

    	if (u == null) {
    		session_status.setComplete();
    		
    		response.sendRedirect("/connexion");
    	} else {
    		session.setAttribute("iduser", u.getIduser());
    		
    		if(user.getRemember()) {

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
    		
    		response.sendRedirect("/contacts");
    	}
    }
    

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
    	mail.setSubject(mail.getCategorie()+" - "+mail.getSubject());
    	
    	boolean bok = envoimail.send();
    	if(bok) return "contactez-nous/succes";
    	return "contactez-nous/erreur";
   }
    
    @RequestMapping(value={"/contactez-nous","/contactez-nous/{retour}"}, method=RequestMethod.GET)
    public String contactezNous(Model model, @PathVariable("retour") Optional<String> retour, @ModelAttribute("mail") Mail mail) {
    	
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
			Model model) {

		long iduser = (long) session.getAttribute("iduser");
		User u = userDao.findByIduser(iduser);

		if (u != null) {			
			List<Contact> contacts = contactDao.findByIduser(iduser);
			model.addAttribute("contacts", contacts);
			System.out.println(contacts+"; "+session.getAttribute("iduser"));
			
			contacts.forEach(x->{
				System.out.println(x);
			});
			
			templates = (ArrayList<Template>) templateDao.getTemplates(0, 0);

			// s'il y a q'un template, il est sélectionné
			if (templates.size() == 1) {
				templates.get(0).setCheck(true);
				session.setAttribute("idtemplate", templates.get(0).getIdtemplate());
			}

			System.out.println(templates.size());
			model.addAttribute("templates", templates);
		}

		return "contacts";
	}


	@GetMapping("/fiche-contact-form")
	public String ficheContactForm(HttpSession session, Model model) {

		long idtemplate = (long) session.getAttribute("idtemplate");
		long iduser = (long) session.getAttribute("iduser");

		List<Template> templates = templateDao.getTemplates(iduser, idtemplate);
		Template t = null;

		System.out.println(templates.size());

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

		model.addAttribute("template", t);
		return "fiche-contact-form";
	}

	@PostMapping("/enregistrer-contact")
	public void enregistrerContact(@ModelAttribute("template") Template template, Model model, HttpSession session,
			HttpServletResponse response) throws IOException {
		System.out.println("template: " + template);

		if (template == null)
			System.out.println("template est null");
		else if (template.getChamps() == null)
			System.out.println("champs est null");

		System.out.println("champs:" + template.getChamps().size());

		template.getChamps().forEach(x -> {
			System.out.println("t:c= "+x);
		});

		User u = userDao.findByIduser((long) session.getAttribute("iduser"));
		if (u == null)
			response.sendRedirect("/fiche-contact-form");
		else {
			// Enregistrer un contact
			Contact c = new Contact();
			c.setIduser(u.getIduser());
			c.setdtcreation(LocalDate.now());
			c.setFavoris(false);
			
			if (contactDao==null)
				System.out.println("contactDao null");

			if (c == null)
				System.out.println("Contact c=null");
			
			long idcontact = contactDao.Save(c);
			System.out.println("id contact créé: " +idcontact);
			if (idcontact>0)
			{			// Enregistrer les données du contact
				template.getChamps().forEach(x -> {
					x.getDonnee().setDtenregistrement(LocalDate.now());
					x.getDonnee().setIdcontact(idcontact);
					x.getDonnee().setIdchamp(x.getIdchamp());
					
					long r = donneeDao.Save(x.getDonnee());
					
					System.out.println("donnee sauvegardée: " + r);
				});
			}

			response.sendRedirect("/contacts");
		}
    }
    
    @RequestMapping("/inscription")
    public String inscription(Model model, User user) {
    	
    	return "inscription";	
    }
    
    @PostMapping("/inscription-form")
    public void inscriptionForm(@ModelAttribute("user") User user, Model model ,HttpSession session, HttpServletResponse response, Mail mail) throws IOException {
    	
    	String password1 = user.getPassword();
    	String password2 = user.getConfirmpassword();
    	String useracomparer = user.getLogin();    	
    	User userref = userDao.findByLogin(user.getLogin());
    	
    	//Si l'email est déjà présent en base
    	if(useracomparer!=null && userref!= null && useracomparer.equals(userref.getLogin())) {
    		
    	} 
    	else {    			
	    	if (password1.equals(password2)) {	    		
	    		//Chiffrement du mot de passe
	    		Random rand = new Random();
        		String validationkey="";
        		for(int i = 0 ; i < 20 ; i++){
        		  char c = (char)(rand.nextInt(26) + 97);
        		  validationkey += c;
        		  System.out.print(c+" ");
        		}
        		
        		user.setValidationkey(validationkey);
        		
	    		// Ajout en base de l'utilisateur
        		long result = userDao.Save(user);       	
        		User u=userDao.findByIduser(result);
        		
	        	if (u == null) {	        		
	        		response.sendRedirect("/");
	        	} else {
	        		
	        		//Envoi de mail de validation de compte 
		    		SendEmail envoimailvalidation = new SendEmail(mail);
		        	mail.setHost("smtp.gmail.com");
		        	mail.setUser("quentinpetit52@gmail.com");
		        	mail.setPass("qlmp1602");
		        	mail.setTo(u.getLogin());
		        	mail.setFrom("Support");
		        	mail.setSubject("Activation de votre compte U-Contact");
		        	mail.setMessageText("Bonjour, \n\nVotre compte n'est pas encore activé. Afin d'activer votre compte, veuillez cliquer suivant http://localhost:8080/validation/"+u.getIduser()+"/"+validationkey);
		        	
		        	envoimailvalidation.send();
	        		response.sendRedirect("/attente-validation");
	        	}
	    		
	    	} else {    		
	    		
	    		// Cas ou les mots de passe sont différents
	    		response.sendRedirect("/inscription");
	    	}
	    	
    	}
    	
    }
    
    @RequestMapping(value={"/validation/{iduser}/{validationkey}"}, method=RequestMethod.GET)
    public String validationCompte(Model model, @PathVariable("iduser") Long iduser, @PathVariable("validationkey") String validationkey) {
    	
    	User u = userDao.findByIduserAndValidationkey(iduser, validationkey);
    	 System.out.println(u+" "+iduser);
    	if (u != null) {
    		u.setValidaccount(true);
    		u.setValidationkey(null);
    		userDao.Save(u);
    		return"/connexion/compte-valide";    		

    	} else {
    		
    		return "/connexion";
    	}   
    }
    
}
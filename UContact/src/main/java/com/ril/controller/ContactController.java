package com.ril.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.EnumSet;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ril.SendEmail;
import com.ril.entity.Categories;
import com.ril.entity.Contact;
import com.ril.entity.Mail;
import com.ril.entity.User;
import com.ril.model.ContactDao;
import com.ril.model.UserDao;

@Controller
public class ContactController {
	
	@Autowired
	UserDao user_repository;
	
	@Autowired
	ContactDao contact_repository;	
	
	public User getUserConnected(HttpSession session, SessionStatus session_status, HttpServletRequest request) {
		
		// Si une session existe
		if(session.getAttribute("iduser") != null) {
			
			// On récupère l'utilisateur grâce à son id
			User u = user_repository.findByIduser((Long)session.getAttribute("iduser")); 
			
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
    				
    				User u = user_repository.findByIduserAndEncryptedkey(iduser, key);  
    			
	    	    	if (u != null) {
	    	    		session.setAttribute("iduser", u.getIduser());
	    	    		return u;
	    	    	}
    			}
			}
		}
		return null;
	}

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
    
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
    
    @RequestMapping("/connexion")
    public String connexion(Model model) { 	
    	User u = new User();
    	model.addAttribute("user", u);
    	
    	return "connexion";
    }
    
    @RequestMapping("/connexion-form")
    public void connexionForm(@ModelAttribute("user") User user, HttpSession session, SessionStatus session_status, HttpServletResponse response) throws IOException {
    	User u = user_repository.findByLoginAndPassword(user.getLogin(), user.getPassword());
    	
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
	    			
	    			user_repository.save(u);    			
	    			
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}    			    			
    		}
    		
    		response.sendRedirect("/contacts");
    	}
    }
    
    @RequestMapping("/contacts")
    public String affichageContacts(Model model, HttpServletResponse response, HttpSession session, SessionStatus session_status, HttpServletRequest request) throws IOException {
    	
    	User u = getUserConnected(session, session_status, request);

    	if (u == null) {

    		response.sendRedirect("/connexion");
    		return null;
    	} else {    	
    		ArrayList<Contact> c = contact_repository.findByiduser(u.getIduser());
    		model.addAttribute("liste", c);
    
    		return "contacts";
    	}
    }

    @PostMapping("/contactez-nous-form")
    public String contactezNousForm(@ModelAttribute("mail") Mail mail) {
    	
    	SendEmail envoimail = new SendEmail(mail);
    	mail.setHost("smtp.gmail.com");
    	mail.setUser("quentinpetit52@gmail.com");
    	mail.setPass("qlmp1602");
    	mail.setTo("quentin.petit@yahoo.fr");
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
}
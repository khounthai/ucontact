package com.ril.controller;

import java.util.EnumSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.ril.SendEmail;
import com.ril.entity.Categories;
import com.ril.entity.Mail;
import com.ril.entity.User;
import com.ril.model.UserDao;

@Controller
public class ContactController {
	
	@Autowired
	UserDao repository;

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
    public String connexion(Model theModel) { 	
    	
    	User user = new User();
    	theModel.addAttribute("user", user);
    	
    	return "connexion";
    }
    
    @RequestMapping("/connexion-form")
    public String connexionForm(@ModelAttribute("user") User user, HttpSession session, SessionStatus session_status) {
    	
    	User u = repository.findByLoginAndPassword(user.getLogin(), user.getPassword());
    	
    	if (u == null) {
    		session_status.setComplete();
    		
    		return "connexion";
    	} else {
    		session.setAttribute("is_user", user.getId_user());
    		
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
    	if(bok) return "messageenvoye";
    	return "contactez-nous/erreur";
   
    	
   }
    
    @RequestMapping("/contactez-nous")
    public String contactezNous(Model model) {
    	
    	model.addAttribute("mail", new Mail());
    	model.addAttribute("categories", EnumSet.allOf(Categories.class));
        return "contactez-nous";
        
    }
}
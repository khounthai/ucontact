package com.ril.controller;

import java.util.ArrayList;
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
import com.ril.entity.Contact;
import com.ril.entity.User;
import com.ril.model.ContactDao;
import com.ril.model.UserDao;

@Controller
public class QuentinController {
	
		
	@Autowired
	ContactDao contact_repository;

    
    
    
    @RequestMapping("/contact")
    public String affichageContacts(HttpSession session,Model model) {
    	System.out.println("mmmmm=");
    	Long id = (Long) session.getAttribute("iduser");
    	System.out.println("id="+id);
    	ArrayList<Contact> c = contact_repository.findByiduser(1L);
    			model.addAttribute("liste", c);
    			
    			System.out.println("liste="+c.size());
    
    		return "contacts";
    	
    }

  

}

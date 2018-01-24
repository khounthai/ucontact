package com.ril.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

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
    
    @RequestMapping("/connexionForm")
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
}

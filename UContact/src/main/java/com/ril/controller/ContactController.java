package com.ril.controller;

import java.util.EnumSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ril.SendEmail;
import com.ril.entity.Categories;
import com.ril.entity.Mail;

@Controller
public class ContactController {

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
    
    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/SendMail")
    public String SendMail(@ModelAttribute("mail") Mail mail) {
    	
    	SendEmail envoimail = new SendEmail(mail);
    	mail.setHost("smtp.gmail.com");
    	mail.setUser("quentinpetit52@gmail.com");
    	mail.setPass("qlmp1602");
    	mail.setTo("khounthai.houang@viacesi.fr");
    	mail.setFrom("Support");
    	mail.setSubject(mail.getCategorie()+" - "+mail.getSubject());
    	
    	boolean bok = envoimail.send();
    	if(bok) {
    		
    		return "messageenvoye";
    	}
    	else return "SendMail";
   
    	
   }
    
    @RequestMapping("/contacteznousform")
    public String contacteznousform(Model model) {
    	
    	model.addAttribute("mail", new Mail());
    	model.addAttribute("categories", EnumSet.allOf(Categories.class));
        return "contacteznousform";
    }


    
}

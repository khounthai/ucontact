package com.ril.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import com.ril.SendEmail;
import com.ril.dao.TemplateDao;
import com.ril.entity.Categories;
import com.ril.entity.Champ;
import com.ril.entity.Contact;
import com.ril.entity.Donnee;
import com.ril.entity.Mail;
import com.ril.entity.Template;
import com.ril.entity.User;
import com.ril.model.ContactDao;
import com.ril.model.UserDao;

@Controller
public class ContactController {

	@Autowired
	UserDao repository;

	@Autowired
	ContactDao contact_repository;

	@RequestMapping("/")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
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
	public void connexionForm(@ModelAttribute("user") User user, HttpSession session, SessionStatus session_status,
			HttpServletResponse response,Model model) throws IOException {
		
		try {
		User u = repository.findByLoginAndPassword(user.getLogin(), user.getPassword());
		System.out.println(u);
		if (u == null) {
			session_status.setComplete();

			response.sendRedirect("/connexion");
		} else {
			session.setAttribute("id_user", u.getIduser());
			System.out.println("redirect contact");
			response.sendRedirect("/contact");
		}
		}catch(Exception ex)
		{
			model.addAttribute("message", ex.getMessage());
			System.out.println( ex.getMessage());
			
			response.sendRedirect("/connexion");

		}
	}

	@Autowired
	TemplateDao templateDao;
	
	@RequestMapping("/contact")
	public String affichageContacts(@ModelAttribute("templates") ArrayList<Template> templates,HttpSession session, Model model) {
		
		ArrayList<Contact> c = contact_repository.findByiduser((long) session.getAttribute("id_user"));
		model.addAttribute("liste", c);
		
		long iduser=(Long) session.getAttribute("id_user");
		User u = repository.findByIduser(iduser);
		
		if (u != null) {
			templates=(ArrayList<Template>) templateDao.getTemplates(0,0);
			
			//si il y a q'un template, il est sélectionné
			if (templates.size()==1)
			{
				templates.get(0).setCheck(true);
				session.setAttribute("idtemplate", templates.get(0).getIdtemplate());
			}
			
			System.out.println(templates.size());
			model.addAttribute("templates",templates);
		}

		return "contacts";

	}

	@PostMapping("/contactez-nous-form")
	public String contactezNousForm(@ModelAttribute("mail") Mail mail) {

		SendEmail envoimail = new SendEmail(mail);
		mail.setHost("smtp.gmail.com");
		mail.setUser("quentinpetit52@gmail.com");
		mail.setPass("qlmp1602");
		mail.setTo("quentin.petit@yahoo.fr");
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
	
	@PostMapping("/fiche-contact-form")
	public ModelAndView ficheContactForm(HttpSession session) {
		
		List<Champ> champs=null;
		ModelAndView model=new ModelAndView();
		
		long idtemplate=(Long) session.getAttribute("idtemplate");
		long iduser=(Long) session.getAttribute("id_user");
		
		List<Template> templates=templateDao.getTemplates(iduser, idtemplate);
		
		System.out.println(templates.size());
		
		for (Template item : templates) {			
				System.out.println(item);				
				champs=item.getChamps();
				
				//initialise Donnee pour chaque champ
				champs.forEach(x-> {
					x.setDonnee(new Donnee());
				});			
		}
	
		if (templates.size()==0l) {
			model.setViewName("contacts");			
		}
		
		model.setViewName("fiche-contact-form");
		model.addObject("champs", champs);
					
		return model;
	}
}
package com.ril.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

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
			HttpServletResponse response, Model model) throws IOException {

		try {
			User u = userDao.findByLoginAndPassword(user.getLogin(), user.getPassword());
			System.out.println(u);
			if (u == null) {
				session_status.setComplete();

				response.sendRedirect("/connexion");
			} else {
				session.setAttribute("id_user", u.getIduser());
				System.out.println("redirect contact");
				response.sendRedirect("/contact");
			}
		} catch (Exception ex) {
			model.addAttribute("message", ex.getMessage());
			System.out.println(ex.getMessage());

			response.sendRedirect("/connexion");
		}
	}

	@RequestMapping("/contact")
	public String affichageContacts(@ModelAttribute("templates") ArrayList<Template> templates, HttpSession session,
			Model model) {

		List<Contact> contacts = contactDao.findByIduser((long) session.getAttribute("id_user"));
		model.addAttribute("contacts", contacts);

		long iduser = (long) session.getAttribute("id_user");
		User u = userDao.findByIduser(iduser);

		if (u != null) {
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

	@GetMapping("/fiche-contact-form")
	public String ficheContactForm(HttpSession session, Model model) {

		long idtemplate = (long) session.getAttribute("idtemplate");
		long iduser = (long) session.getAttribute("id_user");

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

		User u = userDao.findByIduser((long) session.getAttribute("id_user"));
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

}
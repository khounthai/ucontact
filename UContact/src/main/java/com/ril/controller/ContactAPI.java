package com.ril.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ril.dao.ChampDao;
import com.ril.dao.ContactDao;
import com.ril.dao.DonneeDao;
import com.ril.dao.TemplateDao;
import com.ril.dao.UserDao;
import com.ril.entity.Champ;
import com.ril.entity.Contact;
import com.ril.entity.Donnee;
import com.ril.entity.Template;
import com.ril.entity.User;
import com.ril.entity.UserFormConnexion;

@Controller
public class ContactAPI {
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

	@RequestMapping(value = { "/api-liste-contacts/{login}/{pwd}" })
	@ResponseBody
	public List<Contact> GetContacts(@PathVariable("login") String login, @PathVariable("pwd") String pwd) throws Exception {
		String result = "";
		List<Contact> contacts = null;

		// On récupère l'utilisateur grâce à son login et à son mot de passe
		UserFormConnexion uc = new UserFormConnexion();
		uc.setHashedPassword(pwd);

		User u = userDao.findByLoginAndHashedPassword(login, uc.getHashedPassword(), true);

		// Si l'utilisateur existe
		if (u != null) {
			System.out.println(u);

			java.util.Date date = new java.util.Date();
			Timestamp t = new Timestamp(date.getTime());

			// recherche le template
			List<Template> templates = (ArrayList<Template>) templateDao.getTemplates(0, 0, true);
			long idtemplate = 0;

			/*** s'il y a q'un template, il est sélectionné ***/
			if (templates.size() == 1) {
				templates.get(0).setCheck(true);
				idtemplate = templates.get(0).getIdtemplate();
				templates.get(0).getChamps().forEach(x -> {
					System.out.println(x);
				});
			}

			/*** **************************************** ***/
			contacts = contactDao.findByIduserAndIdTemplate(u.getIduser(), idtemplate, true, t);

			// sérialise la liste des contatcs en XML
			JacksonXmlModule xmlModule = new JacksonXmlModule();
			xmlModule.setDefaultUseWrapper(false);
			ObjectMapper objectMapper = new XmlMapper(xmlModule);

			result = objectMapper.writeValueAsString(contacts);
		}

		System.out.println(result);

		return contacts;
	}
}

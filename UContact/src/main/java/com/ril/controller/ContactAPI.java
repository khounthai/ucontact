package com.ril.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ril.dao.ChampDao;
import com.ril.dao.ContactDao;
import com.ril.dao.DonneeDao;
import com.ril.dao.TemplateDao;
import com.ril.dao.UserDao;
import com.ril.entity.Contact;
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

	@RequestMapping(value = { "/api-liste-contacts"},method = RequestMethod.POST)
	@ResponseBody
	public List<Contact> GetContacts(@RequestBody User u)
			throws Exception {
		
		String result = "";
		List<Contact> contacts = null;
		// Si l'utilisateur existe
		if (u != null) {
			
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
		}

		System.out.println(result);

		return contacts;
	}

	@RequestMapping(value = { "/api-template"} ,method = RequestMethod.POST)
	@ResponseBody
	public Template GetTemplate(@RequestBody User u) throws Exception {

		Template template = null;
		
		System.out.println(u);
		
		// recherche le template
		List<Template> templates = (ArrayList<Template>) templateDao.getTemplates(u.getIduser(), 0, true);
		long idtemplate = 0;

		/*** s'il y a q'un template, il est sélectionné ***/
		if (templates.size() == 1) {
			templates.get(0).setCheck(true);
			idtemplate = templates.get(0).getIdtemplate();
			template=templates.get(0);
		}
		
		System.out.println(template);
		return template;
	}
	
	@RequestMapping(value = {"/api-get-user/{login}/{pwd}"})
	@ResponseBody
	public User GetUser(@PathVariable("login") String login,@PathVariable("pwd") String pwd) throws Exception {
	
		UserFormConnexion uc = new UserFormConnexion();
		uc.setHashedPassword(pwd);

		User u = userDao.findByLoginAndHashedPassword(login, uc.getHashedPassword(), true);
				
		System.out.println(u);
		
		return u;
	}
	
	
}

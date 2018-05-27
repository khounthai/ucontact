package com.ril.controller;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ril.dao.UserDao;
import com.ril.entity.User;
import com.ril.entity.UserAPI;
import com.ril.entity.UserFormConnexion;

@RestController
public class APIContact {
	@Autowired
	UserDao userDao;
	
	@RequestMapping(value = { "/api/authentification"}, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> Authentification(@RequestParam("login") String login, @RequestParam("pwd") String pwd) throws Exception {
		
		// Récupération du login et du mot de passe
		UserFormConnexion uf = new UserFormConnexion();
		uf.setLogin(login);
		uf.setHashedPassword(pwd);
		
		// Déclaration du header et du status
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		HttpStatus userStatus = HttpStatus.NOT_FOUND;
		
		// Si l'utilisateur a été trouvé avec son login et son mot de passe
		User u = userDao.findByLoginAndHashedPassword(uf.getLogin(), uf.getHashedPassword(), true);
		if (u != null) {
			
			userStatus = HttpStatus.OK;
			
			// Création du token et du timestamp
			SecureRandom rand = SecureRandom.getInstanceStrong();
			byte[] key = new byte[32];
			rand.nextBytes(key);
			u.setToken_api(key);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			u.setTimestamp_api(ts);
			userDao.Save(u);
			
			String token = Base64.getEncoder().encodeToString(key);
			
			// Création des données qui seront envoyées à l'utilisateur
			UserAPI uapi = new UserAPI();
			uapi.setLogin(u.getLogin());
			uapi.setDtcreation(u.getDtcreation());
			uapi.setToken_api(token);

			return new ResponseEntity<UserAPI>(uapi, headers, userStatus);
		}
		
		return new ResponseEntity(headers, userStatus);
	}
	
	/*
	@RequestMapping(value = { "/api/contacts"}, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> ListeContacts(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		// Déclaration du header et du status
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		HttpStatus userStatus = HttpStatus.UNAUTHORIZED;
		
		// Est-ce qu'on a bien un token dans le header
		String header = request.getHeader("token");
		if (header == null || !header.startsWith("Bearer ")) {
			return new ResponseEntity(headers, userStatus);
			
        } else {
        	// On retransforme le token en bytes
        	String token = request.getHeader("token").substring(8);
        	byte[] key = Base64.getDecoder().decode(token);
        	
        	// Si on trouve un utilisateur avec ce token
        	User u = userDao.findByTokenAPI(key);
        	if (u != null) {
        		userStatus = HttpStatus.OK;
        		
        	} else {
        		userStatus = HttpStatus.NOT_FOUND;
        	}
        }
	}
	*/
}

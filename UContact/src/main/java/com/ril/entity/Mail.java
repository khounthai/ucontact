package com.ril.entity;

import org.springframework.stereotype.Component;

public class Mail {
	
	public Mail() {}
	
	private String host;
	private String user;
	private String pass;
	private String to;
	private String from;
	private String subject;
	private String messageText;
	private String categorie;
		
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Mail(String host, String user, String pass, String to, String from, String subject,
			String messageText, String categorie) {
		super();
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.messageText = messageText;
		this.categorie = categorie;
	}


}

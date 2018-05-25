package com.ril.entity;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

import com.ril.classes.CDCChaine;

public class UserAPI {
	private String login;	
	private Date dtcreation;
	private String token_api;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Date getDtcreation() {
		return dtcreation;
	}
	public void setDtcreation(Date dtcreation) {
		this.dtcreation = dtcreation;
	}
	public String getToken_api() {
		return token_api;
	}
	public void setToken_api(String token_api) {
		this.token_api = token_api;
	}	
}
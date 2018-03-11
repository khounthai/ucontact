package com.ril.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserForm {
	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	protected String login;	
	
	public UserForm() {
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}	
}
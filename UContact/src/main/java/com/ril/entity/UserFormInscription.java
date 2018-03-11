package com.ril.entity;

import org.hibernate.validator.constraints.NotEmpty;

public class UserFormInscription extends UserFormConnexion {
	@NotEmpty
	private String confirmPassword;
	
	public UserFormInscription() {
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
package com.ril.entity;

public class User {
	
	private long iduser;
	private String login;
	private String password;
	private byte[] encryptedkey;
	private String validationkey; //clé de validation de compte	
	private Boolean validaccount = false;	
	private boolean remember;
	private String confirmpassword;
	private String role;
	
	public User() {
	}

	public User(long iduser, String login, String password, String role,byte[] encryptedkey,String validationkey,boolean validaccount ) {		
		this.iduser = iduser;
		this.login = login;
		this.password = password;
		this.encryptedkey = encryptedkey;
		this.role = role;
		this.remember = false;
		this.validationkey=validationkey;
		this.validaccount=validaccount;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getValidationkey() {
		return validationkey;
	}

	public void setValidationkey(String validationkey) {
		this.validationkey = validationkey;
	}

	public Boolean getValidaccount() {
		return validaccount;
	}

	public void setValidaccount(Boolean validaccount) {
		this.validaccount = validaccount;
	}

	public boolean getRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public byte[] getEncryptedkey() {
		return encryptedkey;
	}

	public void setEncryptedkey(byte[] encryptedkey) {
		this.encryptedkey = encryptedkey;
	}

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
}
package com.ril.entity;

public class User {
	
	private long iduser;
	private String login;
	private String password;

	private byte[] encrypted_key;
	private String role;
	
	private boolean remember;

	public User() {
	}

	public User(long iduser, String login, String password, String role,byte[] encrypted_key ) {		
		this.iduser = iduser;
		this.login = login;
		this.password = password;
		this.encrypted_key = encrypted_key;
		this.role = role;
		this.remember = false;
	}
	
	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	public long getIduser() {
		return iduser;
	}
	
	public String getLogin() {
		return login;
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

	public boolean getRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	public byte[] getEncrypted_key() {
		return encrypted_key;
	}

	public void setEncrypted_key(byte[] encrypted_key) {
		this.encrypted_key = encrypted_key;
	}


	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
}
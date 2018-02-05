package com.ril.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_user;
	private String login;
	private String password;
	
	@Column(columnDefinition="BINARY(32)", nullable=true)
	private byte[] encrypted_key;
	private String role;
	@Transient 
	private boolean remember;
	@Transient
	private String confirm_password;
	
	public User() {}
	
	public User(String login, String password, String role) {
		this.login=login;
		this.password=password;
		this.role=role;
	}

	public Long getId_user() {
		return id_user;
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
	
	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}

	@Override
	public String toString() {
		return "User [iduser=" + id_user + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
}

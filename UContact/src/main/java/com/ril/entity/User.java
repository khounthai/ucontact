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
	private Long iduser;
	private String login;
	private String password;
	
	@Column(columnDefinition="BINARY(32)", nullable=true)
	private byte[] encryptedkey;
	private String role;
	@Transient
	private boolean remember;
	
	public User() {}
	
	public User(String login, String password, String role) {
		this.login=login;
		this.password=password;
		this.role=role;
	}

	public Long getIduser() {
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

	
	public byte[] getEncryptedkey() {
		return encryptedkey;
	}

	public void setEncryptedkey(byte[] encryptedkey) {
		this.encryptedkey = encryptedkey;
	}

	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
}

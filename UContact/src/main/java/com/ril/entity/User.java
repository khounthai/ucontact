package com.ril.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id_user;
	private String login;
	private String password;
	private String role;
	
	public User() {}
	
	public User(String login, String password, String role) {
		this.login=login;
		this.password=password;
		this.role=role;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
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

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
}

package com.ril.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long iduser;
	private String login;
	private String password;
	private String role;

	public User() {
	}

	public User(String login, String password, String role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long id_user) {
		this.iduser = iduser;
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

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "lienusertemplate", joinColumns = { @JoinColumn(name = "iduser") }, inverseJoinColumns = {
			@JoinColumn(name = "idtemplate") })
	private Set<Template> templates = new HashSet<Template>();

	public Set<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(Set<Template> templates) {
		this.templates = templates;
	}

	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
}

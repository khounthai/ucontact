package com.ril.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long iduser;
	private String login;
	private String password;
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	private Date dtcreation;
	private String role;

	public User() {
	}


	public User(long iduser, String login, String password, Date dtcreation, String role) {
		super();
		this.iduser = iduser;
		this.login = login;
		this.password = password;
		this.dtcreation = dtcreation;
		this.role = role;
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
	
	 public Date getDtcreation() {
		return dtcreation;
	}

	public void setDtcreation(Date dtcreation) {
		this.dtcreation = dtcreation;
	}


	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", login=" + login + ", password=" + password + ", role=" + role
				+ ", dtcreation=" + dtcreation  + "]";
	}

}

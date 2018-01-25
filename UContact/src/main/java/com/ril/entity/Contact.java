package com.ril.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idcontact;
	private Date dt_creation;
	private Boolean favoris;
	private Long iduser;
	
	public Contact() {}

	public Contact(Long idcontact, Date dt_creation, Boolean favoris, Long iduser) {
		this.idcontact = idcontact;
		this.dt_creation = dt_creation;
		this.favoris = favoris;
		this.iduser = iduser;
	}

	

	public Long getIdcontact() {
		return idcontact;
	}

	public void setIdcontact(Long idcontact) {
		this.idcontact = idcontact;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public Date getDt_creation() {
		return dt_creation;
	}

	public void setDt_creation(Date dt_creation) {
		this.dt_creation = dt_creation;
	}

	public Boolean getFavoris() {
		return favoris;
	}

	public void setFavoris(Boolean favoris) {
		this.favoris = favoris;
	}

	@Override
	public String toString() {
		return "Contact [idcontact=" + idcontact + ", dt_creation=" + dt_creation + ", favoris=" + favoris + ", iduser="
				+ iduser + "]";
	}

	

	
	

}

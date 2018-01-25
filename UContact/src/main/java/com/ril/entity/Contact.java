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
	private long idcontact;
	private Date dtcreation;
	private Boolean favoris;
	private Long iduser;
	
	public Contact() {}

	public Contact(long idcontact, Date dtcreation, Boolean favoris, Long iduser) {
		this.idcontact = idcontact;
		this.dtcreation = dtcreation;
		this.favoris = favoris;
		this.iduser = iduser;
	}


	public long getIdcontact() {
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

	public Date getdtcreation() {
		return dtcreation;
	}

	public void setdtcreation(Date dtcreation) {
		this.dtcreation = dtcreation;
	}

	public Boolean getFavoris() {
		return favoris;
	}

	public void setFavoris(Boolean favoris) {
		this.favoris = favoris;
	}

	@Override
	public String toString() {
		return "Contact [idcontact=" + idcontact + ", dtcreation=" + dtcreation + ", favoris=" + favoris + ", iduser="
				+ iduser + "]";
	}

}

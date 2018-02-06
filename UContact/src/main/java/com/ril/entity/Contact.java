package com.ril.entity;

import java.time.LocalDate;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


public class Contact {
	private long idcontact;
	private LocalDate dtcreation;
	private Boolean favoris;
	private long iduser;
	
	public Contact() {}

	public Contact(long idcontact, LocalDate dtcreation, Boolean favoris, long iduser) {
		this.idcontact = idcontact;
		this.dtcreation = dtcreation;
		this.favoris = favoris;
		this.iduser = iduser;
	}

	public long getIdcontact() {
		return idcontact;
	}

	public void setIdcontact(long idcontact) {
		this.idcontact = idcontact;
	}

	public LocalDate getDtcreation() {
		return dtcreation;
	}

	public void setDtcreation(LocalDate dtcreation) {
		this.dtcreation = dtcreation;
	}

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	public LocalDate getdtcreation() {
		return dtcreation;
	}

	public void setdtcreation(LocalDate dtcreation) {
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

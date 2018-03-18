package com.ril.entity;

import java.sql.Date;
import java.util.List;


public class Contact {
	
	private long idcontact;	
	private Date dtcreation;	
	private Boolean favoris;	
	private long iduser;	
	private boolean actif;	
	private List<Donnee> donnees;

	public Contact() {
	}

	public Contact(long idcontact, Date dtcreation, Boolean favoris, long iduser, boolean actif, List<Donnee> donnees) {
		this.idcontact = idcontact;
		this.dtcreation = dtcreation;
		this.favoris = favoris;
		this.iduser = iduser;
		this.donnees = donnees;
		this.actif = actif;
	}

	public long getIdcontact() {
		return idcontact;
	}

	public void setIdcontact(long idcontact) {
		this.idcontact = idcontact;
	}

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	public Boolean getFavoris() {
		return favoris;
	}

	public void setFavoris(Boolean favoris) {
		this.favoris = favoris;
	}

	public List<Donnee> getDonnees() {
		return donnees;
	}

	public void setDonnees(List<Donnee> donnees) {
		this.donnees = donnees;
	}

	public boolean getActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	
	public Date getDtcreation() {
		return dtcreation;
	}

	public void setDtcreation(Date dtcreation) {
		this.dtcreation = dtcreation;
	}

	@Override
	public String toString() {
		return "Contact [idcontact=" + idcontact + ", dtcreation=" + dtcreation + ", favoris=" + favoris + ", iduser="
				+ iduser + ", actif=" + actif + ", donnees=" + donnees + "]";
	}

}

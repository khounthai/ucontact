package com.ril.entity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.util.List;

import com.ril.classes.CDCChaine;


public class Contact {
	
	private long idcontact;	
	private Date dtcreation;	
	private Boolean favoris;	
	private long iduser;	
	private boolean actif;	
	private List<Donnee> donnees;
	private String idcontactEncrypt;
	
	public Contact() {
	}

	public Contact(long idcontact, Date dtcreation, Boolean favoris, long iduser, boolean actif, List<Donnee> donnees) {
		this.idcontact = idcontact;
		this.dtcreation = dtcreation;
		this.favoris = favoris;
		this.iduser = iduser;
		this.donnees = donnees;
		this.actif = actif;
		if (idcontact==0)
			this.idcontactEncrypt="";
		else			
			this.idcontactEncrypt = CDCChaine.crypter(Long.toString(idcontact));
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
	
	public String getIdcontactEncrypt() {		
		return this.idcontactEncrypt;
	}
	
	public void setIdcontactEncrypt(String idcontactEncrypt) {
		this.idcontactEncrypt =  idcontactEncrypt;
	}

	@Override
	public String toString() {
		return "Contact [idcontact=" + idcontact + ", dtcreation=" + dtcreation + ", favoris=" + favoris + ", iduser="
				+ iduser + ", actif=" + actif + ", donnees=" + donnees + ", idcontactEncrypt=" + idcontactEncrypt + "]";
	}

}

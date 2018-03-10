package com.ril.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Donnee {	
	private long iddonnee;
	private long idchamp;
	private long idcontact;
	private String valeur;
	private long ordre;	
	private Timestamp dtenregistrement;
	private boolean accueil;
	
	public Donnee() {}

	public Donnee(long iddonnee, long idchamp, long idcontact, String valeur, Timestamp dtenregistrement,long ordre,boolean accueil) {
		super();
		this.iddonnee = iddonnee;
		this.idchamp = idchamp;
		this.idcontact = idcontact;
		this.valeur = valeur;
		this.dtenregistrement = dtenregistrement;
		this.ordre=ordre;
		this.accueil=accueil;
	}

	public long getIddonnee() {
		return iddonnee;
	}

	public void setIddonnee(long iddonnee) {
		this.iddonnee = iddonnee;
	}

	public long getIdchamp() {
		return idchamp;
	}

	public void setIdchamp(long idchamp) {
		this.idchamp = idchamp;
	}

	public long getIdcontact() {
		return idcontact;
	}

	public void setIdcontact(long idcontact) {
		this.idcontact = idcontact;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public Timestamp getDtenregistrement() {
		return dtenregistrement;
	}

	public void setDtenregistrement(Timestamp dtenregistrement) {
		this.dtenregistrement = dtenregistrement;
	}

	
	public long getOrdre() {
		return ordre;
	}

	public void setOrdre(long ordre) {
		this.ordre = ordre;
	}

	public boolean isAccueil() {
		return accueil;
	}

	public void setAccueil(boolean accueil) {
		this.accueil = accueil;
	}

	@Override
	public String toString() {
		return "Donnee [iddonnee=" + iddonnee + ", idchamp=" + idchamp + ", idcontact=" + idcontact + ", valeur="
				+ valeur + ", ordre=" + ordre + ", dtenregistrement=" + dtenregistrement + ", accueil=" + accueil + "]";
	}

	
	
}

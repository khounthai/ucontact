package com.ril.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Component

public class Donnee {	

	private long iddonnee;
	private long idchamp;
	private long idcontact;
	private String valeur;
	private long ordre;	
	private Timestamp dtenregistrement;
	private boolean accueil;
	private String libellechamp;
	
	public Donnee() {}

	public Donnee(long iddonnee, long idchamp, long idcontact, String valeur, Timestamp dtenregistrement,long ordre,boolean accueil,String libellechamp) {		
		this.iddonnee = iddonnee;
		this.idchamp = idchamp;
		this.idcontact = idcontact;
		this.valeur = valeur;
		this.dtenregistrement = dtenregistrement;
		this.ordre=ordre;
		this.accueil=accueil;
		this.libellechamp=libellechamp;
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
	
	public String getLibellechamp() {
		return libellechamp;
	}

	public void setLibellechamp(String libellechamp) {
		this.libellechamp = libellechamp;
	}

	@Override
	public String toString() {
		return "Donnee [iddonnee=" + iddonnee + ", idchamp=" + idchamp + ", idcontact=" + idcontact + ", valeur="
				+ valeur + ", ordre=" + ordre + ", dtenregistrement=" + dtenregistrement + ", accueil=" + accueil
				+ ", libellechamp=" + libellechamp + "]";
	}

}

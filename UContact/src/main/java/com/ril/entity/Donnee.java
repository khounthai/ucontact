package com.ril.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/*@JsonPropertyOrder({"idcontact", "dtcreation", "favoris","iduser","actif",""})
public class Person {
@JsonProperty("_id")
private String id;

private String name;

private int age;

@JsonIgnore
private String note;
}*/

@Component
@JsonPropertyOrder({"iddonnee", "idchamp", "idcontact","valeur","ordre","dtenregistrement","accueil"})
public class Donnee {	
	@JsonProperty("iddonnee")
	private long iddonnee;
	@JsonProperty("idchamp")
	private long idchamp;
	@JsonProperty("idcontact")
	private long idcontact;
	@JsonProperty("valeur")
	private String valeur;
	@JsonProperty("ordre")
	private long ordre;	
	@JsonProperty("dtenregistrement")
	private Timestamp dtenregistrement;
	@JsonProperty("accueil")
	private boolean accueil;
	
	public Donnee() {}

	public Donnee(long iddonnee, long idchamp, long idcontact, String valeur, Timestamp dtenregistrement,long ordre,boolean accueil) {		
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

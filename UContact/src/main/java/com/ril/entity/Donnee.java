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
	
	private Timestamp dtenregistrement;
	
	public Donnee() {}

	public Donnee(long iddonnee, long idchamp, long idcontact, String valeur, Timestamp dtenregistrement) {
		super();
		this.iddonnee = iddonnee;
		this.idchamp = idchamp;
		this.idcontact = idcontact;
		this.valeur = valeur;
		this.dtenregistrement = dtenregistrement;
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

	@Override
	public String toString() {
		return "Donnee [iddonnee=" + iddonnee + ", idchamp=" + idchamp + ", idcontact=" + idcontact + ", valeur="
				+ valeur + ", dtenregistrement=" + dtenregistrement + "]";
	}

}

package com.ril.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

public class Champ {
	private long idchamp;
	private String libelle;
	private boolean multivaleur;
	private long iddatatype;
	private Donnee donnee;
	
	public Champ() {}
	
	public Champ(long idchamp, String libelle, boolean multivaleur,long iddatatype,Donnee donnee) {
		this.idchamp = idchamp;
		this.libelle = libelle;
		this.multivaleur = multivaleur;
		this.iddatatype = iddatatype;
		this.donnee=donnee;
	}

	public long getIdchamp() {
		return idchamp;
	}

	public void setIdchamp(long idchamp) {
		this.idchamp = idchamp;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public boolean getMultivaleur() {
		return multivaleur;
	}

	public void setMultivaleur(boolean multivaleur) {
		this.multivaleur = multivaleur;
	}


	public long getIddatatype() {
		return iddatatype;
	}

	public void setIddatatype(long iddatatype) {
		this.iddatatype = iddatatype;
	}
		
	public Donnee getDonnee() {
		if (this.donnee==null) {
			this.donnee=new Donnee();
			this.donnee.setIdchamp(idchamp);
			}
		
		return donnee;
	}
		
	public void setDonnee(Donnee donnee) {
		this.donnee = donnee;
	}

	@Override
	public String toString() {
		return "Champ [idchamp=" + idchamp + ", libelle=" + libelle + ", multivaleur=" + multivaleur + 
				", iddatatype=" + iddatatype + ", donnee=" + donnee + "]";
	}

}

package com.ril.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

public class Champ {
	private long idchamp;
	private String libelle;
	private int multivaleur;
	private boolean actif;
	private long iddatatype;
	private Donnee donnee;
	
	public Champ() {}
	
	public Champ(long idchamp, String libelle, int multivaleur, boolean actif, long iddatatype,Donnee donnee) {
		this.idchamp = idchamp;
		this.libelle = libelle;
		this.multivaleur = multivaleur;
		this.actif = actif;
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

	public int getMultivaleur() {
		return multivaleur;
	}

	public void setMultivaleur(int multivaleur) {
		this.multivaleur = multivaleur;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
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


	@Override
	public String toString() {
		return "Champ [idchamp=" + idchamp + ", libelle=" + libelle + ", multivaleur=" + multivaleur + ", actif="
				+ actif + ", iddatatype=" + iddatatype + ", donnee=" + donnee + "]";
	}

}

package com.ril.entity;

import org.springframework.stereotype.Component;

@Component
public class Champ {
	private long idchamp;
	private String libelle;
	private int multivaleur;
	private boolean actif;
	private long iddatatype;
	private Donnee donnee;
	private String valeur;
	
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
		return donnee;
	}

	public void setDonnee(Donnee donnee) {
		this.donnee = donnee;
	}
	
	
	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	@Override
	public String toString() {
		return "Champ [idchamp=" + idchamp + ", libelle=" + libelle + ", multivaleur=" + multivaleur + ", actif="
				+ actif + ", iddatatype=" + iddatatype + ", donne=" + donnee + "]";
	}

}

package com.ril.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

public class Champ {
	private long idchamp;
	private String libelle;
	private boolean multivaleur;
	private long iddatatype;
	private Donnee donnee;
	private DataType datatype;
	private List<String> preselection;
	
	public Champ() {}
	
	public Champ(long idchamp, String libelle, boolean multivaleur, long iddatatype, Donnee donnee, DataType datatype,
			List<String> preselection) {
		super();
		this.idchamp = idchamp;
		this.libelle = libelle;
		this.multivaleur = multivaleur;
		this.iddatatype = iddatatype;
		this.donnee = donnee;
		this.datatype = datatype;
		this.preselection = preselection;
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
	
	public DataType getDatatype() {
		return datatype;
	}

	public void setDatatype(DataType datatype) {
		this.datatype = datatype;
	}

	public List<String> getPreselection() {
		return preselection;
	}

	public void setPreselection(List<String> preselection) {
		this.preselection = preselection;
	}

	@Override
	public String toString() {
		return "Champ [idchamp=" + idchamp + ", libelle=" + libelle + ", multivaleur=" + multivaleur + ", iddatatype="
				+ iddatatype + ", donnee=" + donnee + ", datatype=" + datatype + ", preselection=" + preselection + "]";
	}

	
}

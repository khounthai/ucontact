package com.ril.entity;

import java.util.List;

public class Champ {
	private long idchamp;
	private String libelle;
	private boolean multivaleur;
	private long iddatatype;
	private Donnee donnee;
	private DataType datatype;
	private List<String> preselection;
	private int preselectionsize;
	private boolean accueil;
	
	public Champ() {}
	
	public Champ(long idchamp, String libelle, boolean multivaleur, long iddatatype, Donnee donnee, DataType datatype,
			List<String> preselection,boolean accueil) {
		
		this.idchamp = idchamp;
		this.libelle = libelle;
		this.multivaleur = multivaleur;
		this.iddatatype = iddatatype;
		this.donnee = donnee;
		this.datatype = datatype;
		this.preselection = preselection;
		this.accueil=accueil;
	}
	
	/*public Champ(long idchamp, String libelle, boolean multivaleur, long iddatatype,DataType datatype,
			List<String> preselection,boolean accueil) {
		
		this.idchamp = idchamp;
		this.libelle = libelle;
		this.multivaleur = multivaleur;
		this.iddatatype = iddatatype;
		this.datatype = datatype;
		this.preselection = preselection;
		this.accueil=accueil;
	}*/

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

	
	public int getPreselectionsize() {
		return preselection.size();
	}

	public boolean isAccueil() {
		return accueil;
	}

	public void setAccueil(boolean accueil) {
		this.accueil = accueil;
	}

	@Override
	public String toString() {
		return "Champ [idchamp=" + idchamp + ", libelle=" + libelle + ", multivaleur=" + multivaleur + ", iddatatype="
				+ iddatatype + ", datatype=" + datatype + ", preselection=" + preselection
				+ ", preselectionsize=" + preselectionsize + ", accueil=" + accueil + "]";
	}

	
}

package com.ril.entity;

import org.springframework.stereotype.Component;

@Component
public class Preselection {
	private long id;
	private String valeur;
	private long idchamp;

	public Preselection() {}
	
	public Preselection(long id, String valeur, long idchamp) {
		super();
		this.id = id;
		this.valeur = valeur;
		this.idchamp = idchamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public long getIdchamp() {
		return idchamp;
	}

	public void setIdchamp(long idchamp) {
		this.idchamp = idchamp;
	}

	@Override
	public String toString() {
		return "Preselection [id=" + id + ", valeur=" + valeur + ", idchamp=" + idchamp + "]";
	}
}

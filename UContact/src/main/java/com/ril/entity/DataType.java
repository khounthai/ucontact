package com.ril.entity;

import org.springframework.stereotype.Component;

@Component
public class DataType {
	private long id;
	private String libelle;
	
	public DataType(){}

	public DataType(long id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "DataType [id=" + id + ", libelle=" + libelle + "]";
	}
	
}

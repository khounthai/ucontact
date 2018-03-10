package com.ril.entity;

import org.springframework.stereotype.Component;

@Component
public class DataType {
	private long id;
	private String libelle;
	private String regex;
	
	public DataType(){}

	public DataType(long id, String libelle,String regex) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.regex=regex;
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

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	@Override
	public String toString() {
		return "DataType [id=" + id + ", libelle=" + libelle + ", regex=" + regex + "]";
	}

	

}

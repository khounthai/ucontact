package com.ril.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="template")	
public class Template {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long idtemplate;
	private String libelle;

    @ManyToMany(mappedBy = "templates")
    private Set<User> users = new HashSet<User>();

	
	public Template() {};
	
	public Template(long idtemplate, String libelle) {
		this.idtemplate = idtemplate;
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Template [idtemplate=" + idtemplate + ", libelle=" + libelle + "]";
	}

	public long getIdtemplate() {
		return idtemplate;
	}

	public void setIdtemplate(long idtemplate) {
		this.idtemplate = idtemplate;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
}

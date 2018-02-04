package com.ril.entity;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Template {
	
	private long idtemplate;
	private String libelle;
	private long iduser;	
	private List<Champ> champs;
	private boolean check;

	public Template() {
	};

	public Template(long idtemplate, String libelle, long iduser, List<Champ> champs) {
		this.idtemplate = idtemplate;
		this.libelle = libelle;
		this.iduser = iduser;
		this.champs = champs;
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

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	public List<Champ> getChamps() {
		return champs;
	}

	public void setChamps(List<Champ> champs) {
		this.champs = champs;
	}
	
	
	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "Template [idtemplate=" + idtemplate + ", libelle=" + libelle + ", iduser=" + iduser + ", champs="
				+ champs + ", isCheck=" +check + "]";
	}

}

package com.ril.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
/*public class Person {
    @JsonProperty("_id")
    private String id;

    private String name;

    private int age;

    @JsonIgnore
    private String note;
}*/

@JsonPropertyOrder({"idcontact", "dtcreation", "favoris","iduser","actif",""})
public class Contact {
	
	@JsonProperty("idcontact")
	private long idcontact;
	@JsonProperty("dtcreation")
	private Date dtcreation;
	@JsonProperty("favoris")
	private Boolean favoris;
	@JsonProperty("iduser")
	private long iduser;
	@JsonProperty("actif")
	private boolean actif;
	@JsonProperty("donnees")
	private List<Donnee> donnees;

	public Contact() {
	}

	public Contact(long idcontact, Date dtcreation, Boolean favoris, long iduser, boolean actif, List<Donnee> donnees) {
		this.idcontact = idcontact;
		this.dtcreation = dtcreation;
		this.favoris = favoris;
		this.iduser = iduser;
		this.donnees = donnees;
		this.actif = actif;
	}

	public long getIdcontact() {
		return idcontact;
	}

	public void setIdcontact(long idcontact) {
		this.idcontact = idcontact;
	}

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	public Boolean getFavoris() {
		return favoris;
	}

	public void setFavoris(Boolean favoris) {
		this.favoris = favoris;
	}

	public List<Donnee> getDonnees() {
		return donnees;
	}

	public void setDonnees(List<Donnee> donnees) {
		this.donnees = donnees;
	}

	public boolean getActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	
	public Date getDtcreation() {
		return dtcreation;
	}

	public void setDtcreation(Date dtcreation) {
		this.dtcreation = dtcreation;
	}

	@Override
	public String toString() {
		return "Contact [idcontact=" + idcontact + ", dtcreation=" + dtcreation + ", favoris=" + favoris + ", iduser="
				+ iduser + ", donnees=" + donnees + "]";
	}

}

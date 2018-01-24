package com.ril.entity;

public enum Categories {
	
	
	MDP ("Mot de passe oublié"),
	INSCR ("Problème d'inscription"),
	UTLI ("Problème d'utilisation"),
	SUPR ("Supprimer mon compte"),
	RPRT ("Rapport de bug"),
	QST ("Question sur l'utilisation de l'application"),
	ATR ("Autre");
	
	private String name ="";

	private Categories(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;		
	}
	
}

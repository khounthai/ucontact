package com.ril.entity;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class User {
	private long iduser;
	private String login;	
	private byte[] encryptedkey;
	private String validationkey; //clé de validation de compte	
	private Boolean validaccount = false;	
	private byte[] hashedPassword;
	private Date dtcreation;
	private String role;
	private boolean actif;
	private Timestamp timestampModifPwd;
	private byte[] encryptedkeypwd;
	
	public User() {
	}
	
	public User(long iduser, String login, byte[] encryptedkey, String validationkey, Boolean validaccount,
			byte[] hashedPassword, String role, boolean actif, Timestamp timestampModifPwd,
			byte[] encryptedkeypwd, Date dtcreation) {
		
		this.iduser = iduser;
		this.login = login;
		this.encryptedkey = encryptedkey;
		this.validationkey = validationkey;
		this.validaccount = validaccount;
		this.hashedPassword = hashedPassword;
		this.dtcreation = dtcreation;
		this.role = role;
		this.actif = actif;
		this.timestampModifPwd = timestampModifPwd;
		this.encryptedkeypwd = encryptedkeypwd;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setHashedPassword(byte[] hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public byte[] getHashedPassword() {
		return hashedPassword;
	}

	public Date getDtcreation() {
		return dtcreation;
	}

	public void setDtcreation(Date dtcreation) {
		this.dtcreation = dtcreation;
	}	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getValidationkey() {
		return validationkey;
	}

	public void setValidationkey(String validationkey) {
		this.validationkey = validationkey;
	}

	public Boolean getValidaccount() {
		return validaccount;
	}

	public void setValidaccount(Boolean validaccount) {
		this.validaccount = validaccount;

	}

	public byte[] getEncryptedkey() {
		return encryptedkey;
	}

	public void setEncryptedkey(byte[] encryptedkey) {
		this.encryptedkey = encryptedkey;
	}

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	public String getLogin() {
		return login;
	}
	
	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
	
	public Timestamp getTimestampModifPwd() {
		return timestampModifPwd;
	}

	public void setTimestampModifPwd(Timestamp timestampModifPwd) {
		this.timestampModifPwd = timestampModifPwd;
	}

	public byte[] getEncryptedkeypwd() {
		return encryptedkeypwd;
	}

	public void setEncryptedkeypwd(byte[] encryptedkeypwd) {
		this.encryptedkeypwd = encryptedkeypwd;
	}

	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", login=" + login + ", encryptedkey=" + Arrays.toString(encryptedkey)
				+ ", validationkey=" + validationkey + ", validaccount=" + validaccount
				+ ", hashedPassword=" + Arrays.toString(hashedPassword) + ", role=" + role
				+", dtcreation=" + dtcreation + ", actif=" + actif + ", timestampModifPwd=" + timestampModifPwd
				+ ", encryptedkeypwd=" + Arrays.toString(encryptedkeypwd) + "]";
	}


}
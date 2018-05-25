package com.ril.entity;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

import com.ril.classes.CDCChaine;

public class User {
	private long iduser;
	private String login;	
	private byte[] encryptedkey;
	private byte[] validationkey; //clé de validation de compte	
	private Boolean validaccount = false;	
	private byte[] hashedPassword;
	private Date dtcreation;
	private String role;
	private boolean actif;
	private Timestamp timestampModifPwd;
	private byte[] encryptedkeypwd;
	private String idEncrypt;
	private byte[] token_api;
	private Timestamp timestampAPI;

	public User() {
	}
	
	public User(long iduser, String login, byte[] encryptedkey, byte[] validationkey, Boolean validaccount,
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
		if (iduser==0)
			this.idEncrypt="";
		else			
			this.idEncrypt = encryptId(iduser);
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

	public byte[] getValidationkey() {
		return validationkey;
	}

	public void setValidationkey(byte[] validationkey) {
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
		setIdEncrypt(iduser);
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

	public String getIdEncrypt() {
		return idEncrypt;
	}

	public void setIdEncrypt(long id) {
		this.idEncrypt = encryptId(id);
	}
	
	public byte[] getToken_api() {
		return token_api;
	}

	public void setToken_api(byte[] token_api) {
		this.token_api = token_api;
	}

	public Timestamp getTimestampAPI() {
		return timestampAPI;
	}

	public void setTimestampAPI(Timestamp timestampAPI) {
		this.timestampAPI = timestampAPI;
	}

	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", login=" + login + ", encryptedkey=" + Arrays.toString(encryptedkey)
				+ ", validationkey=" + validationkey + ", validaccount=" + validaccount
				+ ", hashedPassword=" + Arrays.toString(hashedPassword) + ", role=" + role
				+", dtcreation=" + dtcreation + ", actif=" + actif + ", timestampModifPwd=" + timestampModifPwd
				+ ", encryptedkeypwd=" + Arrays.toString(encryptedkeypwd) + "]";
	}
	
	public String encryptId(long id) {
		
		return CDCChaine.crypter(Long.toString(id));
	}
}
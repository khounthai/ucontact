package com.ril.entity;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
	private boolean remember;
	private byte[] hashedPassword;
	private String confirmpassword;
	private String role;
	private static final byte[] salt = Base64.getDecoder().decode("wA1AIEqxQeWY+FgwfUTtBqHmVdrC69Op");
	private boolean actif;
	private Timestamp timestampModifPwd;
	private byte[] encryptedkeypwd;
	private String password;
	
	public User() {
	}
	
	public User(long iduser, String login, byte[] encryptedkey, String validationkey, Boolean validaccount,
			boolean remember, byte[] hashedPassword, String confirmpassword, String role, boolean actif,
			Timestamp timestampModifPwd, byte[] encryptedkeypwd) {
		
		this.iduser = iduser;
		this.login = login;
		this.encryptedkey = encryptedkey;
		this.validationkey = validationkey;
		this.validaccount = validaccount;
		this.remember = remember;
		this.hashedPassword = hashedPassword;
		this.confirmpassword = confirmpassword;
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

	public boolean comparePassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		return Arrays.equals(hashedPassword, hashPassword(password));
	}
	
	private byte [] hashPassword(String pwd) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		if (pwd==null)
			return null;
		PBEKeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 10000, 32 * 8);
    	SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHMacSHA256");
    	return skf.generateSecret(spec).getEncoded();
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
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

	public void setRemember(boolean remember) {
		this.remember = remember;
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

	public boolean isRemember() {
		return remember;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

		this.hashedPassword = hashPassword(password);

		this.password = password;

	}

	@Override
	public String toString() {
		return "User [iduser=" + iduser + ", login=" + login + ", encryptedkey=" + Arrays.toString(encryptedkey)
				+ ", validationkey=" + validationkey + ", validaccount=" + validaccount + ", remember=" + remember
				+ ", hashedPassword=" + Arrays.toString(hashedPassword) + ", confirmpassword=" + confirmpassword
				+ ", role=" + role + ", actif=" + actif + ", timestampModifPwd=" + timestampModifPwd
				+ ", encryptedkeypwd=" + Arrays.toString(encryptedkeypwd) + "]";
	}


}
package com.ril.entity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long iduser;
	private String login;
	@Column(columnDefinition="BINARY(32)", nullable=false)
	private byte[] hashedPassword;
	private String validationkey; //clé de validation de compte
	@Column(nullable=false)
	private Boolean validaccount = false; 
	
	@Transient
	private String password;
	

	@Transient
	private String confirmpassword;
	
	private static final byte[] salt = Base64.getDecoder().decode("wA1AIEqxQeWY+FgwfUTtBqHmVdrC69Op"); 

	@Column(columnDefinition="BINARY(32)", nullable=true)
	private byte[] encryptedkey;
	private String role;
	@Transient
	private boolean remember;

	
	public User() {}
	
	public User(String login, String password, String role) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.login=login;
		setPassword(password);
		this.role=role;
	}

	public Long getIduser() {
		return iduser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public byte[] getHashedPassword() {
		return hashedPassword;
	}

	public void setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.hashedPassword = hashPassword(password);
		this.password = password;
	}
	
	public String getPassword() {
		return password;
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
	
	public boolean getRemember() {
		return remember;
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

	@Override
	public String toString() {
		
		return "User [iduser=" + iduser + ", login=" + login + ", password=" + password + ", role=" + role + "]";

	}
}

package com.ril.entity;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {
	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	protected String login;	
	
	@NotNull
	protected String password;
	
	protected byte[] hashedPassword;
	protected boolean remember;
	protected static final byte[] salt = Base64.getDecoder().decode("wA1AIEqxQeWY+FgwfUTtBqHmVdrC69Op");
	
	public UserForm() {
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getHashedPassword() {
		return hashedPassword;
	}
	
	public void setHashedPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.hashedPassword = hashPassword(password);
	}

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
	protected byte [] hashPassword(String pwd) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		if (pwd==null) return null;
		PBEKeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 10000, 32 * 8);
    	SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHMacSHA256");
    	return skf.generateSecret(spec).getEncoded();
	}
}
package com.ril.classes;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.validation.constraints.NotNull;

public class CrypterDecrypterChaine {
	
	protected String password;
	
	protected byte[] hashedPassword;	

	protected static final byte[] salt = Base64.getDecoder().decode("wA1AIEqxQeWY+FgwfUTtBqHmVdrC69Op");
	
	public byte [] crypter(String chaine) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		if (chaine==null) return null;
		
		PBEKeySpec spec = new PBEKeySpec(chaine.toCharArray(), salt, 10000, 32 * 8);
    	SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHMacSHA256");
    	return skf.generateSecret(spec).getEncoded();
	}
	
	public String Decrypter(byte chaineCryper[]) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		return "";
    }
}

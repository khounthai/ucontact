package com.ril.classes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.validation.constraints.NotNull;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.FixedByteArraySaltGenerator;
import org.springframework.stereotype.Component;


public class CDCChaine {
	private static String seed = "idcontact";

	private static final String STRING_OUTPUT_TYPE_BASE64 = "base64";
	private static final String STRING_OUTPUT_TYPE_HEXADECIMAL = "hexadecimal";
	private static FixedByteArraySaltGenerator salt;
	private static CDCChaine instance ;
	
	private CDCChaine () {
		salt = new FixedByteArraySaltGenerator();
		salt.setSalt(Base64.getDecoder().decode("wA1AIEqxQeWY+FgwfUTtBqHmVdrC69Op"));
	}

	public static String crypter(String chaineClaire) {
		
		if (instance==null)
			instance=new CDCChaine();
			
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setSaltGenerator(salt);
		encryptor.setPassword(seed);
		encryptor.setStringOutputType(STRING_OUTPUT_TYPE_HEXADECIMAL);
		String encrypted = encryptor.encrypt(chaineClaire);
		
		return encrypted;
	}

	public static String Decrypter(String chaineCryptee) throws NoSuchAlgorithmException, InvalidKeySpecException {
		if (instance==null)
			instance=new CDCChaine();
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setSaltGenerator(salt);
		encryptor.setPassword(seed);
		encryptor.setStringOutputType(STRING_OUTPUT_TYPE_HEXADECIMAL);
		String decrypted = encryptor.decrypt(chaineCryptee);

		return decrypted;
	}

}

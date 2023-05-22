package com.app.moviePilot.controller.register;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.app.moviePilot.model.register.RegisterData;
/**
 * 
 * @author Marino Burillo
 *
 */
public class RegisterValidator {
	public JsonElement getRegisterData(final Map<String,String> registerData) {	
		JsonElement errorText =JsonParser.parseString("{\"error\":\"Incorrect registration data\"}").getAsJsonObject();
		if(!registerData.containsKey("username") || !registerData.get("username").matches("\\w{6,18}")) return errorText;
		if(!registerData.containsKey("password") || !registerData.get("password").matches("^(?=.*\\d).{8,}$")) return errorText;
		if(!registerData.containsKey("email") || !registerData.get("email").matches("\\b[a-zA-Z](\\d|\\w|\\.)*@(\\w*\\.\\w{2,}\\.\\w{2,}|\\w*\\.\\w{2,})\\b")) return errorText;
		
		return encryptData(new RegisterData(registerData.get("username"),registerData.get("password"),registerData.get("email")));
	}

	public JsonElement encryptData(RegisterData d) {
		d.setEmail(stringToEncrypted(d.getEmail(), "e"));
		d.setPassword(stringToEncrypted(d.getPassword(),"e"));
		return null;
	}

	private String stringToEncrypted(final String toEncrypt, final String secretKey) {
	    try { 
	        SecretKey secret = generateSecret(secretKey);
	        byte[] iv;
	        do {
	        	SecureRandom random = new SecureRandom();
		        iv = new byte[12];
		        random.nextBytes(iv);
	        } while(Base64.getEncoder().encodeToString(iv).contains(System.getProperty("line.separator")));	        
	        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);     
	        if (KeyStorer.saveEncryptedSecretToProperties(Base64.getEncoder().encodeToString(iv),secretKey)) {
	            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
	            cipher.init(Cipher.ENCRYPT_MODE, secret, gcmParameterSpec);
	            byte[] encryptedData = cipher.doFinal(toEncrypt.getBytes(StandardCharsets.UTF_8));
	            return Base64.getEncoder().encodeToString(encryptedData);
	        } 
	    }catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

	        return null;
	    }
	private static SecretKey generateSecret(final String secretKey) {		
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
	        byte[] hashedKey = digest.digest(keyBytes);
	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), hashedKey, 65536, 256);
	        SecretKey tmp = factory.generateSecret(spec);
	        return new SecretKeySpec(tmp.getEncoded(), "AES");
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}
}

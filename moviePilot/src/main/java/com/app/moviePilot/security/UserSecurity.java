package com.app.moviePilot.security;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.app.moviePilot.model.register.UserRegisterDTO;
import com.app.moviePilot.model.user.ActiveUser;

@Component
public class UserSecurity {
	
	public UserRegisterDTO encryptData(UserRegisterDTO d) {
		d.setPassword(stringToEncrypted(d.getPassword(), d.getUsername()));
		return d;
	}
	public String stringToEncrypted(final String toEncrypt, final String secretKey) {
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
	
	public String decryptString(final ActiveUser u) {
	    try {
	        SecretKey secret = generateSecret(u.getUsername());
	        String ivString = KeyStorer.getEncryptedSecretFromProperties(u.getUsername());
	        byte[] iv = Base64.getDecoder().decode(ivString);
	        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv);

	        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secret, gcmParameterSpec);
            byte[] encryptedData = cipher.doFinal(u.getPassword().getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedData);
	    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
	            IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
}

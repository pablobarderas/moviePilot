package com.app.moviePilot.security;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.Properties;

import com.app.moviePilot.model.user.ActiveUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * 
 * @author Marino Burillo
 *
 */

public abstract class JwtUtils {
	private static final long EXPIRATION_TIME = 86400000;
	private static final String FILE_NAME = "jwt.properties";
	private static final String FILE_PATH = "src/main/resources";

	public static String generateToken(final ActiveUser u) {
	    Date now = new Date();
	    Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);
	    return Jwts.builder()
	            .setSubject(u.getUsername())
	            .setClaims(generateClaims(u))
	            .setIssuedAt(now)
	            .setExpiration(expirationDate)
	            .signWith(Keys.hmacShaKeyFor(getSecretKey().getBytes()))
	            .compact();
	    
	}

	private static Claims generateClaims(final ActiveUser u) {
	    Claims userClaims = Jwts.claims();
	    userClaims.put("username", u.getUsername());
	    userClaims.put("email", u.getEmail());
	    return userClaims;
	}


	public static boolean verifyAndExtractData(final String token) {
	    try {
	        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(getSecretKey().getBytes()))
	                .build().parseClaimsJws(token);
	        Claims claims = claimsJws.getBody();
	        String username = claims.get("username").toString();
	        if(claims.getExpiration().after(new Date())) {
	        	return true;
	        }
	    } catch (NullPointerException | JwtException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	private static String getSecretKey() {		 
		  try {
			Properties props = new Properties();
			Path filePath = Path.of(FILE_PATH, FILE_NAME);
			InputStream inputStream = Files.newInputStream(filePath, StandardOpenOption.READ);
			props.load(inputStream);
			return props.getProperty("secretKey");
		} catch (IOException e) {
			e.printStackTrace();
		}
	      return null;
	}
	
}
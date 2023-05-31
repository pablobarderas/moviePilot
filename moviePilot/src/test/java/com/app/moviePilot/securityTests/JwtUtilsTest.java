package com.app.moviePilot.securityTests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.app.moviePilot.security.JwtUtils;
import com.app.moviePilot.model.user.ActiveUser;

import io.jsonwebtoken.Claims;

import org.junit.jupiter.api.Test;
/**
 * 
 * @author Marino Burillo
 *
 */
class JwtUtilsTest {

	@Test
	void getSecretKeyTest() {
		Method privateMethod;
		String result = null;
		try {
			privateMethod = JwtUtils.class.getDeclaredMethod("getSecretKey");
	        privateMethod.setAccessible(true);
	        result = (String) privateMethod.invoke(JwtUtils.class);
	        privateMethod.setAccessible(false);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		assertNotNull(result);
	}

	@Test
	void generateClaimsTest() {
		 ActiveUser user = new ActiveUser();
		 user.setUsername("exampleUsername");
		 user.setEmail("validEmail@gmail.co");
		Method privateMethod;
		Claims result = null;
		try {
			privateMethod = JwtUtils.class.getDeclaredMethod("generateClaims", ActiveUser.class);
	        privateMethod.setAccessible(true);
	        result = (Claims) privateMethod.invoke(JwtUtils.class, user);
	        privateMethod.setAccessible(false);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		assertNotNull(result);
	}
	@Test 
	void generateJwtTokenTest() {
		ActiveUser user = new ActiveUser();
		user.setUsername("exampleUsername");
		user.setEmail("validEmail@gmail.co");
		assertNotNull(JwtUtils.generateToken(user));
	}
	@Test 
	void verifyJwtTokenTest() {
		ActiveUser user = new ActiveUser();
		user.setUsername("exampleUsername");
		user.setEmail("validEmail@gmail.co");
		String token = JwtUtils.generateToken(user);
		assertNotNull(token);
		assertTrue(JwtUtils.verifyAndExtractData(token));
	}
}

package com.app.moviePilot.registerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.register.UserController;
import com.app.moviePilot.model.register.UserRegisterDTO;

/**
 * 
 * @author Marino Burillo
 *
 */
class RegisterValidatorTest {
	UserController vr;
	final String errorMessage = "{\"error\":\"Incorrect registration data\"}";
	@BeforeEach
	void setUp(){
		vr = new UserController();
	}
	@Test
	void createValidatorTest() {
		assertNotNull(vr);
	}

	@Test
	void validValuesTest() {		
		String username = "thisValidTestUser";
		String password = "hasToBeAtLeast8CharsLong";
		String email = "valid.email@gmail.co";
		assertNotNull(vr.getRegisterData(new UserRegisterDTO(username,password, email)));
		email = "valid.email@gmail.co.uk";
		assertNotNull(vr.getRegisterData(new UserRegisterDTO(username,password, email)));
	}
	@Test
	void invalidUsernameTest() {		
		String username = "a";
		String password = "hasToBeAtLeast8CharsLong";
		String email = "valid.email@gmail.co";
		assertEquals(errorMessage,vr.getRegisterData(new UserRegisterDTO(username,password, email)));
	}
	@Test
	void invalidPasswordTest() {
		String username = "testUser";
		String password = "short";
		String email = "valid.email@gmail.co";
		assertEquals(errorMessage,vr.getRegisterData(new UserRegisterDTO(username,password, email)));
		password = "notShortNoNumbers";
		assertEquals(errorMessage,vr.getRegisterData(new UserRegisterDTO(username,password, email)));
	}
	@Test
	void invalidEmailTest() {
		String username = "testUser";
		String password = "short";
		String email = "invalid.email@gmail";
		assertEquals(errorMessage,vr.getRegisterData(new UserRegisterDTO(username,password, email)));
		email = "@gmail.co";
		assertEquals(errorMessage,vr.getRegisterData(new UserRegisterDTO(username,password, email)));
		email = "a@gmail.c.uk";
		assertEquals(errorMessage,vr.getRegisterData(new UserRegisterDTO(username,password, email)));
		email = "a@gmail.co.k";
		assertEquals(errorMessage,vr.getRegisterData(new UserRegisterDTO(username,password, email)));
	}

}

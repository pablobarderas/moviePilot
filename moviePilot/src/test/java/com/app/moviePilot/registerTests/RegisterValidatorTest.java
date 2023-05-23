package com.app.moviePilot.registerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.register.RegisterValidator;

/**
 * 
 * @author Marino Burillo
 *
 */
class RegisterValidatorTest {
	RegisterValidator vr;
	final String errorMessage = "{\"error\":\"Incorrect registration data\"}";
	@BeforeEach
	void setUp(){
		vr = new RegisterValidator();
	}
	@Test
	void createValidatorTest() {
		assertNotNull(vr);
	}

	@Test
	void validValuesTest() {		
		String username = "testUser";
		String password = "hasToBeAtLeast8CharsLong";
		String email = "valid.email@gmail.co";
		assertNotNull(vr.getRegisterData(Map.of("username",username,"password",password,"email",email)) );
		email = "valid.email@gmail.co.uk";
		assertNotNull(vr.getRegisterData(Map.of("username",username,"password",password,"email",email)) );
	}
	@Test
	void invalidUsernameTest() {		
		String username = "a";
		String password = "hasToBeAtLeast8CharsLong";
		String email = "valid.email@gmail.co";
		assertEquals(errorMessage,vr.getRegisterData(Map.of("username",username,"password",password,"email",email)).toString());
	}
	@Test
	void invalidPasswordTest() {
		String username = "testUser";
		String password = "short";
		String email = "valid.email@gmail.co";
		assertEquals(errorMessage,vr.getRegisterData(Map.of("username",username,"password",password,"email",email)).toString());
		password = "notShortNoNumbers";
		assertEquals(errorMessage,vr.getRegisterData(Map.of("username",username,"password",password,"email",email)).toString());
	}
	@Test
	void invalidEmailTest() {
		String username = "testUser";
		String password = "short";
		String email = "invalid.email@gmail";
		assertEquals(errorMessage,vr.getRegisterData(Map.of("username",username,"password",password,"email",email)).toString());
		email = "@gmail.co";
		assertEquals(errorMessage,vr.getRegisterData(Map.of("username",username,"password",password,"email",email)).toString());
		email = "a@gmail.c.uk";
		assertEquals(errorMessage,vr.getRegisterData(Map.of("username",username,"password",password,"email",email)).toString());
		email = "a@gmail.co.k";
		assertEquals(errorMessage,vr.getRegisterData(Map.of("username",username,"password",password,"email",email)).toString());
	}
	@Test
	void missingParametersTest() {
		String username = "testUser";
		String password = "validPassword2";
		String email = "valid.email@gmail.co";
		assertEquals(errorMessage,vr.getRegisterData(Map.of("username",username,"password",password)).toString());
		assertEquals(errorMessage,vr.getRegisterData(Map.of("password",password)).toString());
		assertEquals(errorMessage,vr.getRegisterData(Map.of()).toString());
		assertEquals(errorMessage,vr.getRegisterData(Map.of("email",email)).toString());
		
	}
}

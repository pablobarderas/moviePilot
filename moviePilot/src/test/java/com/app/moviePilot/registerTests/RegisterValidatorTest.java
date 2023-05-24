package com.app.moviePilot.registerTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.register.RegisterValidator;
import com.app.moviePilot.model.register.UserRegisterDTO;
import com.app.moviePilot.restControllers.UserController;

/**
 * 
 * @author Marino Burillo
 *
 */
class RegisterValidatorTest {
	

	@Test
	void validValuesTest() {
		UserRegisterDTO registerData = new UserRegisterDTO("validUsername", "atLeast8Chars", "validEmail@gmail.co");
		assertNotNull(RegisterValidator.checkRegex(registerData));
		registerData.setEmail("another.valid@gmail.co.uk");
		assertNotNull(RegisterValidator.checkRegex(registerData));
	}
	@Test
	void invalidUsernameTest() {
		UserRegisterDTO registerData = new UserRegisterDTO("short", "atLeast8Chars", "validEmail@gmail.co");
		assertNull(RegisterValidator.checkRegex(registerData));
		registerData.setUsername("	 ");
		assertNull(RegisterValidator.checkRegex(registerData));
	}
	@Test
	void invalidPasswordTest() {
		UserRegisterDTO registerData = new UserRegisterDTO("validUsername", "short1", "validEmail@gmail.co");
		assertNull(RegisterValidator.checkRegex(registerData));
		registerData.setPassword("	 ");
		assertNull(RegisterValidator.checkRegex(registerData));
		registerData.setPassword("longButNoNumbers");
		assertNull(RegisterValidator.checkRegex(registerData));
	}
	@Test
	void invalidEmailTest() {
		UserRegisterDTO registerData = new UserRegisterDTO("validUsername", "atLeast8Chars", "@gmail.co");
		assertNull(RegisterValidator.checkRegex(registerData));
		registerData.setEmail("	 ");
		assertNull(RegisterValidator.checkRegex(registerData));
		registerData.setEmail("a@gmail.c");
		assertNull(RegisterValidator.checkRegex(registerData));
		registerData.setEmail("a@gmail.co.a");
		assertNull(RegisterValidator.checkRegex(registerData));
		registerData.setEmail("a@.com");
		assertNull(RegisterValidator.checkRegex(registerData));
	}
}

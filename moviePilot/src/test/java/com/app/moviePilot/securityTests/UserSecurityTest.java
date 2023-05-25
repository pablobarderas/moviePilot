package com.app.moviePilot.securityTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.register.UserRegisterDTO;
import com.app.moviePilot.security.UserSecurity;
/**
 * 
 * @author Marino Burillo
 *
 */
class UserSecurityTest {

	@Test
	void creationTest() {
		assertNotNull(new UserSecurity());
	}
	@Test
	void encryptionTest() {
		assertNotNull(new UserSecurity().encryptData(new UserRegisterDTO("notEmpty", "notEmpty", "notEmpty")));
	}
}

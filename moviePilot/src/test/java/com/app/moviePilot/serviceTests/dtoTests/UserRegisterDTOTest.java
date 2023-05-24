package com.app.moviePilot.serviceTests.dtoTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.register.UserRegisterDTO;
/**
 * 
 * @author Marino Burillo
 *
 */
class UserRegisterDTOTest {

	@Test
	void creationTest() {
		assertNotNull(new UserRegisterDTO("username", "password", "email"));
	}

}

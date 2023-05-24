package com.app.moviePilot.serviceTests.dtoTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.register.UserRegisterDTO;
import com.app.moviePilot.model.register.UserUpdateDTO;
/**
 * 
 * @author Marino Burillo
 *
 */
class UserUpdateDTOTest {

	@Test
	void creationTest() {
		assertNotNull(new UserUpdateDTO("username", "password", "email"));
	}

}

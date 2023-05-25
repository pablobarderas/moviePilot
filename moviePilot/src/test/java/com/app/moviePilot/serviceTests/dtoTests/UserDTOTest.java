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
class UserDTOTest {

	@Test
	void creationRegisterTest() {
		assertNotNull(new UserRegisterDTO("username", "password", "email"));
	}
	@Test
	void creationUpdateTest() {
		assertNotNull(new UserUpdateDTO("username", "password", "email"));
	}
}

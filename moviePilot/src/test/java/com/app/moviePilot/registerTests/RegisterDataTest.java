package com.app.moviePilot.registerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.register.UserRegisterDTO;

class RegisterDataTest {

	@Test
	void creationTest() {
		assertNotNull(new UserRegisterDTO("test","passwordTest","email"));
	}

}

package com.app.moviePilot.registerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.register.RegisterData;

class RegisterDataTest {

	@Test
	void creationTest() {
		assertNotNull(new RegisterData("test","passwordTest","email"));
	}

}

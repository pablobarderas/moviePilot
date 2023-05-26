package com.app.moviePilot.configTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.moviePilot.services.UserService;
/**
 * 
 * @author Marino Burillo
 *
 */
@SpringBootTest
class JpaConfigurationTest {
	@Autowired
	private UserService userService;
	@Test
	void isAdminCreatedwithDbCreationTest() {
		assertNotNull(userService.getUser("admin123"));
	}

}

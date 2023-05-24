package com.app.moviePilot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoviePilotApplicationTests {

	@Test
	void contextLoads() {
		assertNotNull(MoviePilotApplication.class);
	}

}

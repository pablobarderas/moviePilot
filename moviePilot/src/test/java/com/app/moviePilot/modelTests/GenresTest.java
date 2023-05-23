package com.app.moviePilot.modelTests;

import static org.junit.jupiter.api.Assertions.*;
import com.app.moviePilot.model.enums.Genres;
import org.junit.jupiter.api.Test;

class GenresTest {

	@Test
	void testNotNull() {
		Genres gen = new Genres();
		assertNotNull(gen);
	}

}

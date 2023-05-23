package com.app.moviePilot.modelTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.app.moviePilot.model.film.Languages;
class LanguagesTest {

	@Test
	void testNotNull() {
		Languages lan = new Languages();
		assertNotNull(lan);
	}
}

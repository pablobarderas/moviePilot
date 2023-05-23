package com.app.moviePilot.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.film.Film;
import com.app.moviePilot.model.visualContent.VisualContent;


/**
 * 
 * @author Pablo Barderas
 *
 */
class TestFilm {
	
	VisualContent movie;

	@BeforeEach
	void setup() {
		movie = new Film();
	}
	
	@Test
	void testObject() {
		assertNotNull(movie);
	}

}

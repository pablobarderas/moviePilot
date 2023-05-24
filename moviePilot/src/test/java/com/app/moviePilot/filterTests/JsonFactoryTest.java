package com.app.moviePilot.filterTests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.filter.JsonFactory;
/**
 * 
 * @author Marino Burillo
 *
 */
class JsonFactoryTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void creationTest() {
		assertNotNull(new JsonFactory());
	}
	@Test
	void searchTvTest() {
		assertNotNull(new JsonFactory().getResponse("https://api.themoviedb.org/3/discover/tv?language=es&with_genres=27&api_key=6cacd119a397de0ec8845d760efdb7ab"));
	}
	@Test
	void searchMovieTest() {
		assertNotNull(new JsonFactory().getResponse("https://api.themoviedb.org/3/discover/movie?language=es&with_genres=27&api_key=6cacd119a397de0ec8845d760efdb7ab"));

	}
}

package com.app.moviePilot.parserTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.parser.FilmParser;
import com.google.gson.JsonElement;

/**
 * 
 * @author Pablo Barderas
 *
 */
class JsonFilmTest {

	protected JsonElement filmJson;
	protected boolean isFilm;
	protected FilmParser filmParser;
	
	@BeforeEach
	void setup() {
		filmParser = new FilmParser();
	}
	
	@Test
	void testJsonArrayCorrect() {
		filmJson = filmParser.getJson("https://api.themoviedb.org/3/discover/movie?language=es&release_date.gte=2000-01-01&vote_average.lte=3&api_key=6cacd119a397de0ec8845d760efdb7ab");
		isFilm = filmParser.isCorrectFormat(filmJson);
		assertTrue(isFilm);
	}

	@Test
	void testJsonObjectCorrect() {
		filmJson = filmParser.getJson("https://api.themoviedb.org/3/movie/550?language=en&api_key=6cacd119a397de0ec8845d760efdb7ab");
		assertTrue(filmParser.isCorrectFormat(filmJson));
	}

	@Test
	void testJsonInorrect() {
		filmJson = filmParser.getJson("https://api.themoviedb.org/3/movie/27593487?language=en&api_key=6cacd119a397de0ec8845d760efdb7ab");
		isFilm = filmParser.isCorrectFormat(filmJson);
		assertFalse(isFilm);
		assertNull(filmJson);
	}
	
	
}

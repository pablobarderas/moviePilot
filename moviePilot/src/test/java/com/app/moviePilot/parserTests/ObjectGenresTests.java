package com.app.moviePilot.parserTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.parser.GenresParser;
import com.app.moviePilot.model.enums.Genres;
import com.google.gson.JsonElement;

/**
 * 
 * @author Pablo Barderas
 *
 */
public class ObjectGenresTests {

	protected static JsonElement genresJson;
	protected static Set<Genres> genreList;
	protected static GenresParser genresParser;
	protected static Genres genre;

	@BeforeAll
	static void setup() {
		genresParser = new GenresParser();
		genresJson = genresParser
				.getJson("https://api.themoviedb.org/3/genre/movie/list?api_key=6cacd119a397de0ec8845d760efdb7ab&with_genres=16");
		genreList = genresParser.getGenresList(genresJson);
	}
	
	
	@Test
	void testAttributesNotNull() {
		
		assertNotNull(genreList);
		
		for (Genres genres : genreList) {
			assertNotNull(genres.getId());
			assertNotNull(genres.getName());
		}
	}
	
}

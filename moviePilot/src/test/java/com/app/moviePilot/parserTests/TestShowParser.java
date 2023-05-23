package com.app.moviePilot.parserTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.parser.ShowParser;
import com.app.moviePilot.model.show.Show;
import com.app.moviePilot.model.visualContent.VisualContent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 
 * @author Arismendy Castillo
 *
 */
class TestShowParser {
	
	VisualContent show;
	ShowParser sParser;
	
	@BeforeEach
	void setup() {
		sParser = new ShowParser();
		show = sParser.fetchShow((JsonObject) sParser.getJson("https://api.themoviedb.org/3/tv/1399?api_key=6cacd119a397de0ec8845d760efdb7ab&language=en-US"));
	}//end method
	
	@Test
	void testCorrectFormat() {
		assertNotNull(show);
		assertEquals("Game of Thrones", show.getTitle());
	}//end test

	@Test
	void testFetchedShow() {
		assertNotNull(show);
		assertEquals("Game of Thrones", show.getTitle());
	}//end test
	
	@Test
	void testFetchOnePage() {
		List<Show> allShows = sParser.getShowsFromPage(1);
		assertNotNull(allShows);
		assertEquals(20, allShows.size());
	}//end test
	
	@Test
	void testFetchShowsFromURL() {
		List<Show> allShows = sParser.getAllShows("https://api.themoviedb.org/3/discover/tv?language=en&first_air_date.lte=2002-01-01&vote_average.gte=8.5&api_key=6cacd119a397de0ec8845d760efdb7ab");
		assertNotNull(allShows);
		//assertEquals(20, allShows.size());
	}//end test
	
	@Test
	void testObjectToJson() {
		System.out.println(sParser.toJson((Show) show));
	}//end test
	
	@Test
	void testGetJsonElement() {
		JsonElement showElement = sParser.getJson("https://api.themoviedb.org/3/discover/tv?language=es&api_key=6cacd119a397de0ec8845d760efdb7ab");
		assertNotNull(showElement);
	}//end test
	

}//end class
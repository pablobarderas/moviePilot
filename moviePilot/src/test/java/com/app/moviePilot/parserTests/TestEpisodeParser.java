package com.app.moviePilot.parserTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.moviePilot.controller.parser.EpisodeParser;
import com.app.moviePilot.model.season.Episode;


@SpringBootTest
class TestEpisodeParser {

	private final String URL = "https://api.themoviedb.org/3/tv/";
	private final String API_KEY= "?api_key=6cacd119a397de0ec8845d760efdb7ab";
	
	@Autowired
	EpisodeParser episodeParser;
	
//	@BeforeEach
//	public void setup() {
//		episodeParser = new EpisodeParser();
//	}
	
	@Test
	public void testIsCorrect() {
		//negative case
		assertFalse(episodeParser.isCorrectFormat(episodeParser.getJson(URL + 12088 + "/season/" + 0 + "/episode/" + 1 + API_KEY)));
	}
	@Test
	public void testGetJson() {
		//positive case
		
		//negative case (wrong URL)
		assertNull(episodeParser.getJson(URL + 12088 + "/season/" + 0 + "/episode/" + 1 + API_KEY));
	}
	
	
	@Test
	void testGteObjectValidURL() {
		
		//First positive test case
		Episode episode = episodeParser.getObject(episodeParser.getJson(URL + 100088 + "/season/" + 1 + "/episode/" + 1 + API_KEY));
		
		String overview = "2003. As a parasitic fungal outbreak begins to ravage the country "
				+ "and the world, Joel Miller attempts to escape "
				+ "the escalating chaos with his daughter and brother. "
				+ "Twenty years later, a now hardened Joel and his partner "
				+ "Tess fight to survive under a totalitarian regime, while the insurgent "
				+ "Fireflies harbor a teenage girl with a unique gift.";
		
		assertEquals("\"When You're Lost in the Darkness\"", episode.getName());
		assertEquals(81, episode.getRuntime());
		assertEquals(1, episode.getSeasonNumber());
		assertEquals(1, episode.getEpisodeNumber());
		assertEquals(overview, episode.getOverview());
		assertEquals(episode.getStillPath(), episode.getStillPath());
		//vote count and vote average may change very easily, can't be tested 
		assertNotNull(episode.getVoteAverageApi());
		assertNotNull(episode.getVoteCount());
		
		
		//Second positive test case
		episode = episodeParser.getObject(episodeParser.getJson(URL + 456 + "/season/" + 13 + "/episode/" + 9 + API_KEY));
		
		String overview2 = "A jaw injury from colliding with a new town statue turns Homer "
				+ "into a better listener while recuperating with his jaws wired shut, "
				+ "but once the wires come off, Homer does not go back to being loud and "
				+ "obnoxious and Marge becomes starved for thrills.";
		assertEquals("\"Jaws Wired Shut\"", episode.getName());
		assertEquals(23, episode.getRuntime());
		assertEquals(13, episode.getSeasonNumber());
		assertEquals(9, episode.getEpisodeNumber());
		assertEquals(overview2, episode.getOverview());
		assertEquals("\"/iRy0OWf2WyxYVtemoaNe9DPSSla.jpg\"", episode.getStillPath());
		//vote count and vote average may change very easily, can't be tested 
		assertNotNull(episode.getVoteAverageApi());
		assertNotNull(episode.getVoteCount());
		
	}
	
	
	//Negative cases
	@Test
	void testInvalidId() {
		assertNull(
		episodeParser.getObject(episodeParser.getJson(URL + 0 + "/season/" + 999 + "/episode/" + 1 + API_KEY))
		);
	}
	
	@Test
	void testInvalidSeason() {
		assertNull(
        episodeParser.getObject(episodeParser.getJson(URL + 100088 + "/season/" + 999 + "/episode/" + 1 + API_KEY))
        );
	}
	
	@Test
	void testInvalidEpisode() {
		assertNull(
		episodeParser.getObject(episodeParser.getJson(URL + 100088 + "/season/" + 1 + "/episode/" + 999 + API_KEY))
		);
	}

}

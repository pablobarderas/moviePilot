package com.app.moviePilot.parserTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.parser.SeasonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * 
 * @author Alberto Johnson
 *
 */
public class testSeasonParser {
	
	static final String VALIDURL = "https://api.themoviedb.org/3/tv/1/season/1?api_key=6cacd119a397de0ec8845d760efdb7ab";
	static final String INVALIDURL = "https://api.themoviedb.org/3/movie/11?api_key=6cacd119a397de0ec8845d760efdb7ab";

	static Client client= null;
	static JsonElement elTvInfo = null;
	SeasonParser sp = new SeasonParser();
	@BeforeAll
	static void setup() {
		client = javax.ws.rs.client.ClientBuilder.newClient();
	}
	
	@Test
	void testIdCorrect() {
		WebTarget webTarget = client.target(VALIDURL);
		String json = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		JsonElement el = JsonParser.parseString(json);
		assertTrue(new SeasonParser().isCorrectFormat(el));
	}
	
	@Test
	void testIdIncorrect() {
		WebTarget webTarget = client.target(INVALIDURL);
		String json = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		JsonElement el = JsonParser.parseString(json);
		assertFalse(new SeasonParser().isCorrectFormat(el));
	}
	
	@Test
	void testGetCorrectJson() {
		String jsonString = "{\"_id\":\"52532e2119c2957940000047\"";
//		String json = new SeasonParser().getJson(VALIDURL).getAsJsonObject().toString();
		System.out.println(sp.getJson(VALIDURL).getAsJsonObject().get("episodes").getAsJsonArray().get(0));
//		assertTrue(json.startsWith(jsonString));
	}
	
	@Test
	void testGetIncorrectJson(){
		assertNull(new SeasonParser().getJson(INVALIDURL));
	}
}

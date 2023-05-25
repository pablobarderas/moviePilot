package com.app.moviePilot.parserTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.parser.NetworksParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class TestNetworkParser {
	final static String VALIDURL = "https://api.themoviedb.org/3/watch/providers/movie?api_key=6cacd119a397de0ec8845d760efdb7ab";
	final static String INVALIDURL = "https://api.themoviedb.org/3/tv/11?api_key=6cacd119a397de0ec8845d760efdb7ab";
	static NetworksParser np;
	static Client client;
	
	@BeforeAll
	static void setup() {
		np = new NetworksParser();
		client = ClientBuilder.newClient();
	}
	
	@Test
	void testFormat() {
		WebTarget service = client.target(VALIDURL);
		String jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
		assertTrue(np.isCorrectFormat(JsonParser.parseString(jsonResponse)));
		service = client.target(INVALIDURL);
		jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
		assertFalse(np.isCorrectFormat(JsonParser.parseString(jsonResponse)));
	}
	
	@Test
	void testJson() {
		JsonElement el = np.getJson(VALIDURL);
		assertTrue(el.toString().contains("/peURlLlr8jggOwK53fJ5wdQl05y.jpg"));
		System.out.println(el);
	}
	
}

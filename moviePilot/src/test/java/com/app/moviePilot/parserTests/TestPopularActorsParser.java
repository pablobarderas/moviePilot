package com.app.moviePilot.parserTests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.parser.PopularActorsParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
/**
 * 
 * @author Alberto Johnson
 *
 */
public class TestPopularActorsParser {
	final static String VALIDURL = "https://api.themoviedb.org/3/person/popular?api_key=6cacd119a397de0ec8845d760efdb7ab";
	final static String INVALIDURL = "https://api.themoviedb.org/3/tv/11?api_key=6cacd119a397de0ec8845d760efdb7ab";
	static PopularActorsParser pp;
	static Client client;
	@BeforeAll
	static void setup() {
		pp = new PopularActorsParser();
		client = ClientBuilder.newClient();
	}
	
	@Test
	void getJson() {
		JsonElement correctJson = pp.getJson(VALIDURL);
		JsonElement incorrectJson = pp.getJson(INVALIDURL);
		assertTrue(correctJson.toString().contains("\"id\":\"58021\""));
		assertNull(incorrectJson);
	}
	
	@Test
	void formatValidator() {
		WebTarget service = client.target(VALIDURL);
		String jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
		assertTrue(pp.isCorrectFormat(JsonParser.parseString(jsonResponse)));
		service = client.target(INVALIDURL);
		jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
		assertFalse(pp.isCorrectFormat(JsonParser.parseString(jsonResponse)));
	}
}

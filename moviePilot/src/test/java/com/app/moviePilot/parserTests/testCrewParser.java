package com.app.moviePilot.parserTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.app.moviePilot.controller.parser.CastParser;
import com.app.moviePilot.controller.parser.CrewParser;
import com.app.moviePilot.model.mediaPersonnel.CrewMember;
/**
 * 
 * @author Alberto Johnson
 *
 */
public class testCrewParser {
	static final String VALIDURL = "https://api.themoviedb.org/3/tv/1/credits?api_key=6cacd119a397de0ec8845d760efdb7ab";
	static final String INVALIDURL = "https://api.themoviedb.org/3/movie/11?api_key=6cacd119a397de0ec8845d760efdb7ab";
	
	JsonElement infoCrew;
	CrewParser crewParser = new CrewParser();
	static Client client = null;
	
	@BeforeAll
	static void setup() {
		client = javax.ws.rs.client.ClientBuilder.newClient();
	}
	
	@Test
	void testIsCorrectFormat() {
		WebTarget webTarget = client.target(VALIDURL);
		String json = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		JsonElement el = JsonParser.parseString(json);
		assertTrue(crewParser.isCorrectFormat(el));
	}
	
	@Test
	void testIncorrectFormat() {
		WebTarget webTarget = client.target(INVALIDURL);
		String json = webTarget.request(MediaType.APPLICATION_JSON).get(String.class);
		JsonElement el = JsonParser.parseString(json);
		assertFalse(crewParser.isCorrectFormat(el));
	}
	
	@Test
	void getCorrectJson() {
		String json = crewParser.getJson(VALIDURL).toString();
		assertTrue(json.startsWith("[{\"adult\":false,\"gender\":2,\"id\":1560850,"));
	}
	
	@Test
	void getIncorrectJson() {
		assertNull(crewParser.getJson(INVALIDURL));
	}
	
	@Test
	void getCastList() {
		Set<CrewMember> crewList = crewParser.getObject(crewParser.getJson(VALIDURL));
		int i= 0;
		Iterator<CrewMember> it = crewList.iterator();

		assertEquals(2164205,it.next().getId());
	}
}

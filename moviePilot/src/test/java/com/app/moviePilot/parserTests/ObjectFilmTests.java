package com.app.moviePilot.parserTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.model.film.Film;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author Pablo Barderas
 *
 */
class ObjectFilmTests {

	protected static JsonElement filmJson;
	protected static Film film;
	protected static FilmParser filmParser;

	@BeforeAll
	static void setup() {
		filmParser = new FilmParser();
		filmJson = filmParser
				.getJson("https://api.themoviedb.org/3/movie/550?api_key=6cacd119a397de0ec8845d760efdb7ab");
		film = filmParser.getObject(filmJson);
	}

	@Test
	void testNotNull() {
		assertNotNull(filmJson);
		assertNotNull(film);
	}

	@Test
	void testAttributes() {

		// This test can change
		assertEquals(550, film.getIdApi());
		assertEquals("Fight Club", film.getTitle());
		assertEquals(
				"A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
				film.getOverview());
		assertFalse(film.getAdult());
		assertEquals("/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", film.getPosterPath());
		assertEquals("1999-10-15", film.getReleaseDate().toString());
		assertEquals(139, film.getRuntime());

		// Class Attributes
		assertEquals(3, film.getGenres().size());
		assertEquals(1, film.getLanguagesFilm().size());

	}

	@Test
	void testAttributesNotNull() {

		// This test can change
		assertNotNull(film.getIdApi());
		assertNotNull(film.getTitle());
		assertNotNull(film.getOverview());
		assertNotNull(film.getAdult());
		assertNotNull(film.getPosterPath());
		assertNotNull(film.getReleaseDate());
		assertNotNull(film.getRuntime());

		// Class Attributes
		assertNotNull(film.getGenres().size());
		assertNotNull(film.getLanguagesFilm().size());

	}

	@Test
	void testObjectToJson() {

		// Check that original json is equals after it parser
		// error en localdate con el año
		JsonElement jsonToSend = filmParser.toJson(film);
		assertNotNull(jsonToSend);

		// TESTS ATTRIBUTES
		assertEquals(jsonToSend.getAsJsonObject().get("idApi"), filmJson.getAsJsonObject().get("id"));
		assertEquals(jsonToSend.getAsJsonObject().get("title"), filmJson.getAsJsonObject().get("original_title"));
		assertEquals(jsonToSend.getAsJsonObject().get("overview"), filmJson.getAsJsonObject().get("overview"));
		assertEquals(jsonToSend.getAsJsonObject().get("adult"), filmJson.getAsJsonObject().get("adult"));
		assertEquals(jsonToSend.getAsJsonObject().get("genres").getAsJsonArray().size(),
				filmJson.getAsJsonObject().get("genres").getAsJsonArray().size(), " ");
		assertEquals(jsonToSend.getAsJsonObject().get("posterPath"), filmJson.getAsJsonObject().get("poster_path"));
		assertEquals(jsonToSend.getAsJsonObject().get("releaseDate"), filmJson.getAsJsonObject().get("release_date"));
		assertEquals(jsonToSend.getAsJsonObject().get("runtime"), filmJson.getAsJsonObject().get("runtime"));
		assertEquals(jsonToSend.getAsJsonObject().get("voteAverageApi"),
				filmJson.getAsJsonObject().get("vote_average"));
		assertEquals(jsonToSend.getAsJsonObject().get("voteCount"), filmJson.getAsJsonObject().get("vote_count"));
		assertEquals(jsonToSend.getAsJsonObject().get("status"), filmJson.getAsJsonObject().get("status"));

		// CHECK LANGUAGES BEFORE AND AFTER THAN IT HAD PARSED
		List<String> sendLanguages = new ArrayList<>();
		JsonArray languagesJson = jsonToSend.getAsJsonObject().get("spokenLanguages").getAsJsonArray();
		for (JsonElement lan : languagesJson) {
			String name = lan.getAsJsonObject().get("name").getAsString();
			// Haz algo con el nombre obtenido
			sendLanguages.add(name);
		}
		Collections.sort(sendLanguages);

		List<String> beforeLanguages = new ArrayList<>();
		JsonArray beforeLanguagesJson = filmJson.getAsJsonObject().get("spoken_languages").getAsJsonArray();
		for (JsonElement lan : beforeLanguagesJson) {
			String name = lan.getAsJsonObject().get("name").getAsString();
			// Haz algo con el nombre obtenido
			beforeLanguages.add(name);
		}
		Collections.sort(beforeLanguages);
		assertArrayEquals(sendLanguages.toArray(), beforeLanguages.toArray());

		// CHECK GENRES BEFORE AND AFTER THAN IT HAD PARSED
		List<String> sendGenres = new ArrayList<>();
		JsonArray genresJson = jsonToSend.getAsJsonObject().get("genres").getAsJsonArray();
		for (JsonElement gen : genresJson) {
			String name = gen.getAsJsonObject().get("name").getAsString();
			// Haz algo con el nombre obtenido
			sendGenres.add(name);
		}
		Collections.sort(sendGenres);
		
		List<String> beforeGenres = new ArrayList<>();
		JsonArray beforeGenresJson = filmJson.getAsJsonObject().get("genres").getAsJsonArray();
		for (JsonElement gen : beforeGenresJson) {
			String name = gen.getAsJsonObject().get("name").getAsString();
			// Haz algo con el nombre obtenido
			beforeGenres.add(name);
		}
		Collections.sort(beforeGenres);
		assertArrayEquals(sendGenres.toArray(), beforeGenres.toArray());
	}

	@Test
	void testListObjectByPage() {

		int page = 1;
		List<Film> listFilmToCompare = new ArrayList<>();
		List<Film> listFilm = filmParser.getFilmsFromPage("discover/movie", page);

		assertNotNull(listFilm);

		// GET SAME DATA FROM API TO COMPARE WITH METHOD
		Client client = ClientBuilder.newClient();
		String url = "http://api.themoviedb.org/3/discover/movie?api_key=6cacd119a397de0ec8845d760efdb7ab" + "&page="
				+ page;
		Response response = client.target(url).request(MediaType.APPLICATION_JSON).get();
		String json = response.readEntity(String.class);
		JsonObject pageObject = JsonParser.parseString(json).getAsJsonObject();
		JsonArray array = pageObject.getAsJsonArray("results");

		for (JsonElement showData : array) {
			JsonObject showObject = showData.getAsJsonObject();
			listFilmToCompare.add(filmParser.getObject(showObject));
		}

		// GET ID'S AND COMPARE IT
		assertEquals(listFilmToCompare.size(), listFilm.size());
		List<Long> idsToCompare = listFilmToCompare.stream()
				.map(Film::getIdApi)
				.collect(Collectors.toList());
		
		List<Long> ids = listFilm.stream()
				.map(Film::getIdApi)
				.collect(Collectors.toList());

		assertArrayEquals(idsToCompare.toArray(), ids.toArray());

	}

}

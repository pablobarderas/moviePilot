package com.app.moviePilot.controller.parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.visualContent.VisualContent;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author Pablo Barderas
 *
 */
public class GenresParser extends DataParser {

	@Override
	public boolean isCorrectFormat(JsonElement object) {
		return true;
	}

	@Override
	public JsonElement getJson(String url) {

		Client client = ClientBuilder.newClient();
		Response response = client.target(url).request(MediaType.APPLICATION_JSON).get();
		String json = response.readEntity(String.class);
		JsonObject genresJson = JsonParser.parseString(json).getAsJsonObject();
		//JsonArray genresArray = genresJson.getAsJsonArray("genres");

		return genresJson;
	}

	// CONVERT JSON TO FILM OBJECT
	public Genres getObject(JsonElement genreJson) {

		Genres genre = new Genres();

		try {

			// SET ATTRIBUTES
			if (genreJson.getAsJsonObject().get("id") != null) {
				genre.setId(genreJson.getAsJsonObject().get("id").getAsLong());
			}
			if (genreJson.getAsJsonObject().get("name") != null) {
				genre.setName(genreJson.getAsJsonObject().get("name").getAsString());
			}

		} catch (Exception e) {
			return genre;
		}

		return genre;

	}

	public Set<Genres> getGenresList(JsonElement genreJson) {
		JsonArray genresArray = genreJson.getAsJsonObject().get("genres").getAsJsonArray();
		Set<Genres> genresSet = new HashSet<>();

		// Save all Genres found on Set
		for (JsonElement element : genresArray) {
			String name = element.getAsJsonObject().get("name").getAsString();
			Long id = element.getAsJsonObject().get("id").getAsLong();
			Genres genre = new Genres(name, id);
			genresSet.add(genre);
		}
		return genresSet;
	}

	@Override
	public List<VisualContent> getVisualContentFromPage(String endPoints, String params, int page) {
		return null;
	}

}

package com.app.moviePilot.controller.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.bytebuddy.implementation.bind.annotation.SuperCall;

import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.film.Film;
import com.app.moviePilot.model.film.Languages;

/**
 * 
 * @author Pablo Barderas
 *
 */
public class FilmParser extends DataParser {

	public FilmParser() {
	}

	// solucionar comprobando si es un array, hacer tests
	@Override
	public boolean isCorrectFormat(JsonElement filmJson) {
		if (filmJson != null) {
			JsonObject jsonObject = filmJson.getAsJsonObject();

			if (jsonObject.has("results")) {
				JsonArray filmArray = jsonObject.getAsJsonArray("results");
				for (JsonElement film : filmArray) {
					if (!film.getAsJsonObject().has("original_title")) {
						return false;
					}
				}
				return true;
			} else {
				return jsonObject.has("original_title");
			}
		}

		return false;
	}

	// GET JSON FILM
	public JsonElement getJson(String URL) {

		// Get request
		Client client = ClientBuilder.newClient();
		JsonObject filmJson = new JsonObject();
		JsonArray filmJsonArray = new JsonArray();

		try {
			WebTarget service = client.target(URL);
			// Get json and check format
			String stringResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
			filmJson = JsonParser.parseString(stringResponse).getAsJsonObject();

			// If json is array, return it
			if (isCorrectFormat(filmJson)) {
				if (filmJson.isJsonArray()) {
					filmJsonArray = filmJson.getAsJsonArray();
					return filmJsonArray;
				} else if (filmJson.isJsonObject()) {
					return filmJson;
				}
			}

		} catch (Exception e) {
			return null;
		}

		return null;

	}

	// CONVERT JSON TO FILM OBJECT
	public Film getObject(JsonElement filmJson) {

		Film film = new Film();

		try {

			if (isCorrectFormat(filmJson)) {

				// SET ATTRIBUTES
				if (filmJson.getAsJsonObject().get("original_title") != null) {
					film.setTitle(filmJson.getAsJsonObject().get("original_title").getAsString());
				}
				if (filmJson.getAsJsonObject().get("id") != null) {
					film.setIdApi(filmJson.getAsJsonObject().get("id").getAsLong());
				}
				if (filmJson.getAsJsonObject().get("overview") != null) {
					film.setOverview(filmJson.getAsJsonObject().get("overview").getAsString());
				}
				if (filmJson.getAsJsonObject().get("poster_path") != null) {
					film.setPosterPath(filmJson.getAsJsonObject().get("poster_path").getAsString());
				}
				if (filmJson.getAsJsonObject().get("runtime") != null) {
					film.setRuntime(filmJson.getAsJsonObject().get("runtime").getAsInt());
				}
				if (filmJson.getAsJsonObject().get("vote_average") != null) {
					film.setVoteAverageApi(filmJson.getAsJsonObject().get("vote_average").getAsDouble());
				}
				if (filmJson.getAsJsonObject().get("vote_count") != null) {
					film.setVoteCount(filmJson.getAsJsonObject().get("vote_count").getAsInt());
				}
				if (filmJson.getAsJsonObject().get("status") != null) {
					film.setStatus(filmJson.getAsJsonObject().get("status").getAsString());
				}

				// SET releaseDate
				if (filmJson.getAsJsonObject().get("release_date") != null) {
					LocalDate localDate = LocalDate.parse(filmJson.getAsJsonObject().get("release_date").getAsString());
					film.setReleaseDate(localDate);
				}

				// SET GENRES SET
				if (filmJson.getAsJsonObject().get("genres") != null) {
					JsonArray genresArray = filmJson.getAsJsonObject().get("genres").getAsJsonArray();
					Set<Genres> genresSet = new HashSet<>();

					// Save all Genres found on Set
					for (JsonElement element : genresArray) {
						String name = element.getAsJsonObject().get("name").getAsString();
						int id = element.getAsJsonObject().get("id").getAsInt();
						Genres genre = new Genres(name, id);
						genresSet.add(genre);
					}

					film.setGenres(genresSet);
				}

				// SET LANGUAGES SET
				if (filmJson.getAsJsonObject().get("spoken_languages") != null) {
					JsonArray languagesArray = filmJson.getAsJsonObject().get("spoken_languages").getAsJsonArray();
					Set<Languages> languagesSet = new HashSet<>();

					// Save all Languages found on Set
					for (JsonElement element : languagesArray) {
						String iso = element.getAsJsonObject().get("iso_639_1").getAsString();
						String name = element.getAsJsonObject().get("name").getAsString();
						Languages language = new Languages(iso, name);
						languagesSet.add(language);
					}
					film.setLanguagesFilm(languagesSet);
				}

				// SET COMMENTS
			}

		} catch (Exception e) {
			e.printStackTrace();
			return film;
		}

		return film;

	}

	// GET LIST OF FILMS BY PAGE NUMBER
	public List<Film> getFilmsFromPage(String params, int page) {
		List<Film> films = new ArrayList<>();
		Client client = ClientBuilder.newClient();
		String url = super.getUrl() + params + super.getApikey() + "&page=" + page;
		Response response = client.target(url).request(MediaType.APPLICATION_JSON).get();
		String json = response.readEntity(String.class);
		JsonObject pageObject = JsonParser.parseString(json).getAsJsonObject();
		JsonArray array = pageObject.getAsJsonArray("results");

		for (JsonElement showData : array) {
			JsonObject showObject = showData.getAsJsonObject();
			films.add(getObject(showObject));
		}

		return films;
	}// end method
	
}

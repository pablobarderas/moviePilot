package com.app.moviePilot.controller.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.network.Network;
import com.app.moviePilot.model.show.Show;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Class that constructs Show objects from Jsons
 * and viceversa.
 * @author Arismendy Castillo
 *
 */
public class ShowParser extends DataParser {

	public ShowParser() {
	}// end constructor

	@Override
	public boolean isCorrectFormat(JsonElement showJson) {
		if (showJson != null) {
			JsonObject jsonObject = showJson.getAsJsonObject();

			if (jsonObject.has("results")) {
				JsonArray showArray = jsonObject.getAsJsonArray("results");
				for (JsonElement show : showArray) {
					if (!show.getAsJsonObject().has("name")) {
						return false;
					}
				}
				return true;
			} else {
				return jsonObject.has("name");
			}
		}

		return false;
	}//end method


	/**
	 * Takes an integer as a parameter and returns
	 * a JsonObject with all the information from the
	 * TV Show with that integer as ID.
	 * @param id The ID of the show we want to fetch.
	 * @return The JsonObject with the info.
	 */
	public JsonObject getJsonShow(int id) {

		// Json format failed
		String formatFail = "{\"error\":\"The show with doesn't exist\"}";
		JsonObject formatFailJson = new JsonObject();

		// Get request
		Client client = ClientBuilder.newClient();
		JsonObject showJson = new JsonObject();

		try {
			WebTarget service = client.target(super.getUrl() + "tv/" + id + super.getApikey());

			// Get json and check format
			String stringResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
			showJson = JsonParser.parseString(stringResponse).getAsJsonObject();
			formatFailJson = JsonParser.parseString(formatFail).getAsJsonObject();

		} catch (Exception e) {
			return formatFailJson;
		}

		// Return correct json
		if (isCorrectFormat(showJson)) {
			return showJson;
		}

		return formatFailJson;
	}// end method
	
	/**
	 * Takes an String as a parameter and returns
	 * a JsonObject with all the information from the
	 * TV Show with that integer as ID.
	 * @param url The url of the show we want to fetch.
	 * @return The JsonObject with the info.
	 */
	@Override
	public JsonElement getJson(String URL) {

		// Get request
		Client client = ClientBuilder.newClient();
		JsonObject showJson = new JsonObject();
		JsonArray showJsonArray = new JsonArray();

		try {
			WebTarget service = client.target(URL);
			// Get json and check format
			String stringResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
			showJson = JsonParser.parseString(stringResponse).getAsJsonObject();

			// If json is array, return it
			if (isCorrectFormat(showJson)) {
				if (showJson.isJsonArray()) {
					showJsonArray = showJson.getAsJsonArray();
					return showJsonArray;
				} else if (showJson.isJsonObject()) {
					return showJson;
				}
			}

		} catch (Exception e) {
			return null;
		}

		return null;

	}// end method

	/**
	 * Takes a JsonObject as a parameter and returns
	 * a Show object.
	 * 
	 * @param jsonShow The JsonObject to parse.
	 * @return The Show object constructed.
	 */
	public Show fetchShow(JsonObject jsonShow) {
	    Show show = new Show();
	    
	    try {
	        if (isCorrectFormat(jsonShow)) {
	            show.setTitle(getStringValue(jsonShow, "name"));
	            show.setIdApi(getLongValue(jsonShow, "id"));
	            show.setOverview(getStringValue(jsonShow, "overview"));
	            show.setAdult(getBooleanValue(jsonShow, "adult"));
	            show.setInProduction(getBooleanValue(jsonShow, "in_production"));
	            show.setPosterPath(getStringValue(jsonShow, "poster_path"));
	            show.setVoteAverageApi(getDoubleValue(jsonShow, "vote_average"));
	            show.setVoteCount(getIntValue(jsonShow, "vote_count"));
	            show.setStatus(getStringValue(jsonShow, "status"));
	            show.setNumberOfEpisodes(getIntValue(jsonShow, "number_of_episodes"));
	            show.setNumberOfSeasons(getIntValue(jsonShow, "number_of_seasons"));
	            show.setOriginalLanguage(getStringValue(jsonShow, "original_language"));
	            
	            LocalDate firstAirDate = getDateValue(jsonShow, "first_air_date");
	            show.setFirstAirDate(firstAirDate);
	            
	            LocalDate lastAirDate = getDateValue(jsonShow, "last_air_date");
	            show.setLastAirDate(lastAirDate);
	            
	            Set<Genres> genresSet = getGenresSet(jsonShow);
	            show.setGenres(genresSet);
	            
	            Set<Network> networkSet = getNetworksSet(jsonShow);
	            show.setNetworks(networkSet);
	            
	            Set<String> countriesSet = getOriginCountriesSet(jsonShow);
	            show.setOriginCountry(countriesSet);
	            
	            int runtime = getIntValueFromArray(jsonShow, "episode_run_time", 0);
	            show.setRuntime(runtime);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return show;
	    }
	    
	    return show;
	}//end method

	private String getStringValue(JsonObject jsonObject, String key) {
	    JsonElement element = jsonObject.get(key);
	    return (element != null && !element.isJsonNull()) ? element.getAsString() : null;
	}

	private int getIntValue(JsonObject jsonObject, String key) {
	    JsonElement element = jsonObject.get(key);
	    return (element != null && element.isJsonPrimitive()) ? element.getAsInt() : 0;
	}
	
	private Long getLongValue(JsonObject jsonObject, String key) {
	    JsonElement element = jsonObject.get(key);
	    return (element != null && element.isJsonPrimitive()) ? element.getAsLong() : 0;
	}

	private double getDoubleValue(JsonObject jsonObject, String key) {
	    JsonElement element = jsonObject.get(key);
	    return (element != null && element.isJsonPrimitive()) ? element.getAsDouble() : 0.0;
	}

	private boolean getBooleanValue(JsonObject jsonObject, String key) {
	    JsonElement element = jsonObject.get(key);
	    return (element != null && element.isJsonPrimitive()) ? element.getAsBoolean() : false;
	}

	private LocalDate getDateValue(JsonObject jsonObject, String key) {
	    JsonElement element = jsonObject.get(key);
	    if (element != null && element.isJsonPrimitive()) {
	        try {
	            return LocalDate.parse(element.getAsString());
	        } catch (Exception e) {
	            // Handle parsing exception
	        }
	    }
	    return null;
	}

	private Set<Genres> getGenresSet(JsonObject jsonObject) {
	    JsonArray genresArray = jsonObject.getAsJsonArray("genres");
	    Set<Genres> genresSet = new HashSet<>();
	    if (genresArray != null) {
	        for (JsonElement element : genresArray) {
	            JsonObject genreObject = element.getAsJsonObject();
	            String name = getStringValue(genreObject, "name");
	            int id = getIntValue(genreObject, "id");
	            if (name != null && id != 0) {
	                Genres genre = new Genres(name, id);
	                genresSet.add(genre);
	            }
	        }
	    }
	    return genresSet;
	}

	private Set<Network> getNetworksSet(JsonObject jsonObject) {
	    JsonArray networkArray = jsonObject.getAsJsonArray("networks");
	    Set<Network> networkSet = new HashSet<>();
	    if (networkArray != null) {
	        for (JsonElement element : networkArray) {
	            JsonObject networkObject = element.getAsJsonObject();
	            String name = getStringValue(networkObject, "name");
	            Long id = getLongValue(networkObject, "id");
	            String logoPath = getStringValue(networkObject, "logo_path");
	            if (name != null && id != 0 && logoPath != null) {
	                Network network = new Network(id, name, logoPath);
	                networkSet.add(network);
	            }
	        }
	    }
	    return networkSet;
	}

	private Set<String> getOriginCountriesSet(JsonObject jsonObject) {
	    JsonArray countriesArray = jsonObject.getAsJsonArray("origin_country");
	    Set<String> countriesSet = new HashSet<>();
	    if (countriesArray != null) {
	        for (JsonElement element : countriesArray) {
	            String country = element.getAsString();
	            if (country != null) {
	                countriesSet.add(country);
	            }
	        }
	    }
	    return countriesSet;
	}

	private int getIntValueFromArray(JsonObject jsonObject, String key, int index) {
	    JsonArray array = jsonObject.getAsJsonArray(key);
	    if (array != null && index >= 0 && index < array.size()) {
	        JsonElement element = array.get(index);
	        return (element != null && element.isJsonPrimitive()) ? element.getAsInt() : 0;
	    }
	    return 0;
	}


	/**
	 * Fetches all the shows in the specified URL,
	 *  looping through every page.
	 * 
	 * @return A list with all the shows from the URL call.
	 */
	public List<Show> getAllShows(String url) {
		List<Show> shows = new ArrayList<>();
		Client client = ClientBuilder.newClient();
		int page = 1;
		int totalPages = 1;
		while (page <= totalPages) {
			Response response = client.target(url).request(MediaType.APPLICATION_JSON).get();
			String json = response.readEntity(String.class);
			JsonObject pageObject = JsonParser.parseString(json).getAsJsonObject();
			JsonArray array = pageObject.getAsJsonArray("results");
			
			if (page == 1) {
	            totalPages = pageObject.get("total_pages").getAsInt();
	        }

			for (JsonElement showData : array) {
				JsonObject showObject = showData.getAsJsonObject();
				shows.add(fetchShow(showObject));
			}
			page++;
		}
		return shows;
	}// end method

	/**
	 * Fetches all the shows in the API, from the
	 * specified page.
	 * 
	 * @return A list with all the shows from that page
	 */
	public List<Show> getShowsFromPage(int page) {
		List<Show> shows = new ArrayList<>();
		Client client = ClientBuilder.newClient();
		String url = super.getUrl() + "/tv/popular" + super.getApikey() + "&language=en-US&page=" + page;
		Response response = client.target(url).request(MediaType.APPLICATION_JSON).get();
		String json = response.readEntity(String.class);
		JsonObject pageObject = JsonParser.parseString(json).getAsJsonObject();
		JsonArray array = pageObject.getAsJsonArray("results");

		for (JsonElement showData : array) {
			JsonObject showObject = showData.getAsJsonObject();
			//int id = showObject.get("id").getAsInt();
			shows.add(fetchShow(showObject));
		}

		return shows;
	}// end method
	

}// end class
package com.app.moviePilot.controller.parser;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.moviePilot.model.film.Film;
import com.app.moviePilot.model.visualContent.VisualContent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 
 * @author Pablo Barderas, Alberto Johnson
 *
 */
public abstract class DataParser {

	private final String URL = "https://api.themoviedb.org/3/";
	private final String API_KEY = "?api_key=6cacd119a397de0ec8845d760efdb7ab";

	public DataParser() {
	}

//	public <T> T getObject(JsonObject object) {
//		return null;
//	}
//	
	public abstract boolean isCorrectFormat(JsonElement object);

	public abstract JsonElement getJson(String url);

	/**
	 * Returns the param object that we want to parse and returns it as a
	 * JsonElement.
	 * 
	 * @param show The show object we want to parse.
	 * @return The JsonElement that represents the Json with the desired
	 *         information.
	 */
	public <T> JsonElement toJson(T object) {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
			private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			@Override
			public JsonElement serialize(LocalDate localDate, Type type,
					JsonSerializationContext jsonSerializationContext) {
				return new JsonPrimitive(formatter.format(localDate));
			}
		}).registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
			private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			@Override
			public JsonElement serialize(LocalDateTime localDateTime, Type type,
					JsonSerializationContext jsonSerializationContext) {
				return new JsonPrimitive(formatter.format(localDateTime));
			}
		}).create();

		return gson.toJsonTree(object);
	}// end method

	public abstract List<VisualContent>getVisualContentFromPage(String endPoints, String params, int page);

	protected String getUrl() {
		return URL;
	}

	protected String getApikey() {
		return API_KEY;
	}

}

package com.app.moviePilot.controller.filter;

import com.google.gson.JsonElement;

import com.app.moviePilot.controller.parser.DataParser;
import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.controller.parser.ShowParser;

/**
 * 
 * @author Marino Burillo
 *
 */
public class JsonFactory {

	private static final DataParser[] parsers = { new FilmParser(), new ShowParser() };

	public static JsonElement getResponse(String Url) {
		for (DataParser parser : parsers) {
			JsonElement response = parser.getJson(Url);
			if (response != null) return response;
		}
		return null;
	}
}

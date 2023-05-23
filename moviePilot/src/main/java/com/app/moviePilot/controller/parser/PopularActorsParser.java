package com.app.moviePilot.controller.parser;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

public class PopularActorsParser extends DataParser{

	@Override
	public boolean isCorrectFormat(JsonElement object) {
		return object.getAsJsonObject().get("results")!=null;
	}

	@Override
	public JsonElement getJson(String url) {
		Client client = ClientBuilder.newClient();
		JsonArray castJson = new JsonArray();
		JsonObject newJson = new JsonObject();
		try {
			WebTarget service = client.target(url);
			String jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
			if(!this.isCorrectFormat(JsonParser.parseString(jsonResponse))) return null;
			JsonArray arrayActors = new JsonArray();
			for(JsonElement o: JsonParser.parseString(jsonResponse).getAsJsonObject().get("results").getAsJsonArray()) {
				JsonObject actorAux = new JsonObject();
				actorAux.addProperty("id", o.getAsJsonObject().get("id").toString());
				actorAux.addProperty("name", o.getAsJsonObject().get("name").toString());
				actorAux.addProperty("profile_path", o.getAsJsonObject().get("profile_path").toString());
				arrayActors.add(actorAux);
			}
			newJson.add("result", arrayActors);
		}catch(Exception e) {
			return null;
		}
		return newJson;
	}

}

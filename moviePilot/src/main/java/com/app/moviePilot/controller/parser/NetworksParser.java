package com.app.moviePilot.controller.parser;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NetworksParser extends DataParser{

	@Override
	public boolean isCorrectFormat(JsonElement object) {
		try{
			return object.getAsJsonObject().get("results").getAsJsonArray().get(0).getAsJsonObject().get("display_priorities")!=null;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public JsonElement getJson(String url) {
		Client client = ClientBuilder.newClient();
		JsonObject newJson = new JsonObject();
		try {
			WebTarget service = client.target(url);
			String jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
			if(!this.isCorrectFormat(JsonParser.parseString(jsonResponse))) return null;
			JsonArray arrayNetworks = new JsonArray();
			for(JsonElement o: JsonParser.parseString(jsonResponse).getAsJsonObject().get("results").getAsJsonArray()) {
				JsonObject networkAux = new JsonObject();
				networkAux.addProperty("id", o.getAsJsonObject().get("provider_id").toString());
				networkAux.addProperty("name", o.getAsJsonObject().get("provider_name").toString());
				networkAux.addProperty("logoPath", o.getAsJsonObject().get("logo_path").toString());
				arrayNetworks.add(networkAux);
			}
			newJson.add("result", arrayNetworks);
		}catch(Exception e) {
			return null;
		}
		return newJson;
	}

}

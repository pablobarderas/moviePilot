package com.app.moviePilot.controller.parser;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.app.moviePilot.model.mediaPersonnel.CastMember;
import com.app.moviePilot.model.visualContent.VisualContent;
import com.google.gson.*;

public class PopularActorsParser extends DataParser{

	@Override
	public boolean isCorrectFormat(JsonElement object) {
		return object.getAsJsonObject().get("results")!=null;
	}

	@Override
	public JsonElement getJson(String url) {
		Client client = ClientBuilder.newClient();
		JsonObject newJson = new JsonObject();
		JsonArray arrayActors = new JsonArray();
		try {
			WebTarget service = client.target(url);
			String jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
			if(!this.isCorrectFormat(JsonParser.parseString(jsonResponse))) return null;
			for(JsonElement o: JsonParser.parseString(jsonResponse).getAsJsonObject().get("results").getAsJsonArray()) {
				JsonObject actorAux = new JsonObject();
				actorAux.addProperty("id", o.getAsJsonObject().get("id").getAsString());
				actorAux.addProperty("name", o.getAsJsonObject().get("name").getAsString());
				JsonElement pathAux = o.getAsJsonObject().get("profile_path");
				if(pathAux!=null) actorAux.addProperty("profile_path", pathAux.getAsString());
				else actorAux.addProperty("profile_path", "");
				arrayActors.add(actorAux);
			}
			newJson.add("result", arrayActors);
		}catch(Exception e) {
			newJson.add("result", arrayActors);
			return newJson;
		}
		return newJson;
	}
	
	public List<CastMember> toList(JsonElement el){
		List<CastMember> popularList = new LinkedList<>();
		for(JsonElement e: el.getAsJsonObject().get("result").getAsJsonArray()) {
			JsonObject o = e.getAsJsonObject();
			popularList.add(new CastMember(o.get("id").getAsLong(), o.get("name").getAsString(), o.get("profile_path").getAsString(), null));
		}
		
		return popularList;
	}

	@Override
	public List<VisualContent> getVisualContentFromPage(String endPoints, String params, int page) {
		// TODO Auto-generated method stub
		return null;
	}

}

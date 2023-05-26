package com.app.moviePilot.controller.parser;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import com.app.moviePilot.model.mediaPersonnel.CastMember;
import com.app.moviePilot.model.visualContent.VisualContent;
/**
 * 
 * @author Alberto Johnson
 *
 */
public class CastParser extends DataParser{
	
	//checks if the json its about to check is a tvshow credits json
	@Override
	public boolean isCorrectFormat(JsonElement el) {
		return 	el.getAsJsonObject().get("cast")!=null;
	}
	
	//creates a cast json
	public JsonElement getJson(String url) {
		Client client = ClientBuilder.newClient();
		JsonArray castJson = new JsonArray();
		
		try {
			WebTarget service = client.target(url);
			String jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
			if(!this.isCorrectFormat(JsonParser.parseString(jsonResponse).getAsJsonObject())) return null;
			castJson = JsonParser.parseString(jsonResponse).getAsJsonObject().get("cast").getAsJsonArray();
			}catch(Exception ex) {
			return null;
		}
		return castJson;
	}
	
	//creates a list of castmembers from a cast json
	public Set<CastMember> getObject(JsonElement el) {
		Set<CastMember> castList = new HashSet<>();
		JsonArray castArray = el.getAsJsonArray();
		try {
			for(JsonElement elem: castArray) {
				JsonObject obAux = elem.getAsJsonObject();
				CastMember castMemberAux = new CastMember();
				JsonElement id = obAux.get("id");
				JsonElement name = obAux.get("name");
				JsonElement profilePath = obAux.get("profile_path");
				JsonElement character = obAux.get("character");
				
				if(!id.isJsonNull()) castMemberAux.setId(id.getAsLong());
				if(!name.isJsonNull()) castMemberAux.setName(name.toString());
				if(!profilePath.isJsonNull()) castMemberAux.setProfilePath(profilePath.toString());
				if(!character.isJsonNull()) castMemberAux.setCharacterName(character.toString());
				
				castList.add(castMemberAux);
			}
		}catch(Exception e) {
			return castList;
		}
		return castList;
	}
	
	public <CastMember> JsonElement toJson(CastMember object) {
		Gson gson = new GsonBuilder()
	            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
	                private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	                @Override
	                public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
	                    return new JsonPrimitive(formatter.format(localDate));
	                }
	            })
	            .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
	                private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	                @Override
	                public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
	                    return new JsonPrimitive(formatter.format(localDateTime));
	                }
	            })
	            .create();

	    return gson.toJsonTree(object);
	}

	@Override
	public List<VisualContent> getVisualContentFromPage(String endPoints, String params, int page) {
		// TODO Auto-generated method stub
		return null;
	}


}

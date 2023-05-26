package com.app.moviePilot.controller.parser;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
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
import com.app.moviePilot.model.mediaPersonnel.CrewMember;
import com.app.moviePilot.model.season.Season;

/**
 * 
 * @author Alberto Johnson
 *
 */
public class CrewParser extends DataParser{
	//checks if the json its about to check is a tvshow credits json
		@Override
		public boolean isCorrectFormat(JsonElement el) {
			return 	el.getAsJsonObject().get("cast")!=null;
		}
		
		//creates a crew json
		public JsonElement getJson(String url) {
			Client client = ClientBuilder.newClient();
			JsonArray castJson = new JsonArray();
			
			try {
				WebTarget service = client.target(url);
				String jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
				if(!this.isCorrectFormat(JsonParser.parseString(jsonResponse).getAsJsonObject())) return null;
				castJson = JsonParser.parseString(jsonResponse).getAsJsonObject().get("crew").getAsJsonArray();
				}catch(Exception ex) {
				return null;
			}
			return castJson;
		}
		
		//creates a list of crewmembers from a crew json
		public Set<CrewMember> getObjectCast(JsonElement el) {
			Set<CrewMember> castList = new HashSet<>();
			JsonArray crewArray = el.getAsJsonArray();
			for(JsonElement elem: crewArray) {
				JsonObject obAux = elem.getAsJsonObject();
				castList.add(new CrewMember(obAux.get("id").getAsLong(),
						obAux.get("name").getAsString(), 
						obAux.get("job").getAsString()));
			}
			return castList;
		}
		
		public <CrewMember> JsonElement toJson(CrewMember object) {
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

		public Set<CrewMember> getObject(JsonElement el) {
			Set<CrewMember> crewList = new HashSet<>();
			JsonArray crewArray = el.getAsJsonArray();
			try {
				for(JsonElement elem: crewArray) {
					JsonObject obAux = elem.getAsJsonObject();
					CrewMember crewAux = new CrewMember();
					JsonElement id = obAux.get("id");
					JsonElement name = obAux.get("name");
					JsonElement job = obAux.get("job");
					
					if(id!=null) crewAux.setId(id.getAsLong());
					if(name!=null) crewAux.setName(name.getAsString());
					if(job!=null) crewAux.setJob(job.getAsString());
					
					crewList.add(crewAux);
				}
			}catch(Exception e) {
				return crewList;
			}
			return crewList;
		}
}

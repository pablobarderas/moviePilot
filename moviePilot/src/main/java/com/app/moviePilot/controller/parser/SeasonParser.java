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

import com.app.moviePilot.model.season.Episode;
import com.app.moviePilot.model.season.Season;
import com.app.moviePilot.model.visualContent.VisualContent;
/**
 * 
 * @author Alberto Johnson
 *
 */
public class SeasonParser extends DataParser{
	public SeasonParser(){
		super();
	}
	//checks if the json its about to check is a tvshow season json
	@Override
	public boolean isCorrectFormat(JsonElement el) {
		return 	el.getAsJsonObject().get("season_number")!=null;
	}
	//gets json from a tvshows season
	@Override
	public JsonElement getJson(String url) {
		Client client = ClientBuilder.newClient();
		JsonObject seasonJson = new JsonObject();
		
		try {
			WebTarget service = client.target(url);
			
			String jsonResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
			seasonJson = JsonParser.parseString(jsonResponse).getAsJsonObject();
			if(!this.isCorrectFormat(seasonJson)) return null;
		}catch(Exception ex) {
			return null;
		}
		return seasonJson;
	}
	//creates a season object from a season json
	public Season getObject(JsonElement el) {
		Season obj = new Season();
		try {
			JsonElement name = el.getAsJsonObject().get("name");
			JsonElement air_date = el.getAsJsonObject().get("air_date");
			JsonElement overview = el.getAsJsonObject().get("overview");
			JsonElement poster_path = el.getAsJsonObject().get("poster_path");
			JsonElement season_number = el.getAsJsonObject().get("season_number");
			if(name!=null)
				obj.setName(el.getAsJsonObject().get("name").getAsString());
			if(air_date!=null)
				obj.setAirDate(el.getAsJsonObject().get("air_date").getAsString());
			if(overview!=null)
				obj.setOverview(el.getAsJsonObject().get("overview").getAsString());
			if(poster_path!=null)
				obj.setPosterPath(el.getAsJsonObject().get("poster_path").getAsString());
			if(season_number!=null)
				obj.setSeasonNumber(Integer.parseInt(el.getAsJsonObject().get("season_number").getAsString()));
			JsonArray arrEpisodes = el.getAsJsonObject().get("episodes").getAsJsonArray();
			Set<Episode> episodeList = new HashSet<>();
			EpisodeParser episodeParser = new EpisodeParser();
			for(JsonElement o: arrEpisodes) {
				episodeList.add(episodeParser.getObject(o.getAsJsonObject()));
			}
			obj.setEpisodes(episodeList);
		}catch(Exception e) {
			return obj;
		}
		return obj;
	}
	
	public <Season> JsonElement toJson(Season object) {
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

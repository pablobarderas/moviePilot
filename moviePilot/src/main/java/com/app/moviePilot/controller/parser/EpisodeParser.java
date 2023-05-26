package com.app.moviePilot.controller.parser;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

 import com.app.moviePilot.model.season.Episode;
import com.app.moviePilot.model.visualContent.VisualContent;
/**
 * 
 * @author Emilio Izquierdo
 *
 */
 @Component
public class EpisodeParser extends DataParser{
	
	public boolean isCorrectFormat(JsonElement episodeJson) {
		// Check if the episodeJson object is not null and contains the "name" key
		if(episodeJson!=null) {
			return episodeJson.getAsJsonObject().has("episode_number");
		}
		return false;
	}//end method

	public JsonElement getJson(String URL) {
		Client client = ClientBuilder.newClient();
		JsonObject episodeJson = new JsonObject();

		try {
			WebTarget service = client.target(URL);

			String stringResponse = service.request(MediaType.APPLICATION_JSON).get(String.class);
			episodeJson = JsonParser.parseString(stringResponse).getAsJsonObject();

		} catch (Exception e) {
			return null;
		}

		if (isCorrectFormat(episodeJson)) {
			return episodeJson;
		}
		return null;
	}//end method
	
	/**
	 * Get an Episode object with information from the JSON.
	 * @author Alberto Johnson, Emilio Izquierdo
	 * @param o JsonObject of the episode 
	 * @return an episode object with the info from the json
	 */
	public Episode getObject(JsonElement o) {
		try {
			if(isCorrectFormat(o)) {
				JsonObject jsonEpisode = o.getAsJsonObject();
				
				JsonElement name = jsonEpisode.get("name");
				JsonElement overview = jsonEpisode.get("overview");
				JsonElement runtime = jsonEpisode.get("runtime");
				JsonElement seasonNumber = jsonEpisode.get("season_number");
				JsonElement episodeNumber = jsonEpisode.get("episode_number");
				JsonElement stillPath = jsonEpisode.get("still_path");
				JsonElement voteAverageApi = jsonEpisode.get("vote_average");
				JsonElement voteCount = jsonEpisode.get("vote_count");
				Episode episodeAux = new Episode();
				if(name!=null) episodeAux.setName(name.toString());
				if(overview!=null) episodeAux.setOverview(overview.getAsString());
				if(runtime!=null) episodeAux.setRuntime(runtime.getAsInt());
				if(seasonNumber!=null) episodeAux.setSeasonNumber(seasonNumber.getAsInt());
				if(episodeNumber!=null) episodeAux.setEpisodeNumber(episodeNumber.getAsInt());
				if(stillPath!=null) episodeAux.setStillPath(stillPath.toString());
				if(voteAverageApi!=null) episodeAux.setVoteAverageApi(voteAverageApi.getAsDouble());
				if(voteCount!=null) episodeAux.setVoteCount(voteCount.getAsInt());

				return episodeAux;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Episode();
		}
		return null;
	}//end method
	
	/**
	 * Convert an Episode object to JSON format.
	 * 
	 * @param episode Episode object to convert
	 * @return Episode object in JSON format
	 */
	public String toJson(Episode episode) {
		Gson gson = new Gson();
		String json = gson.toJson(episode);
		return json;
	}//end method

	@Override
	public List<VisualContent> getVisualContentFromPage(String endPoints, String params, int page) {
		// TODO Auto-generated method stub
		return null;
	}
}//end class


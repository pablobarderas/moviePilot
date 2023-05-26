package com.app.moviePilot.restControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.app.moviePilot.controller.parser.SeasonParser;
import com.app.moviePilot.model.season.Season;

@RestController
public class SeasonController {
	
	private final String URL = "https://api.themoviedb.org/3/tv/";
	private final String API_KEY = "?api_key=6cacd119a397de0ec8845d760efdb7ab";
	
	@GetMapping(value = "/tv/{tvId}/season/{seasonId}")
	public Season getSeason(@PathVariable Integer tvId, @PathVariable Integer seasonId) {
		SeasonParser sp = new SeasonParser();
		return sp.getObject(sp.getJson(URL+tvId+"/season/"+seasonId+API_KEY));
	}
}

package com.app.moviePilot.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.controller.parser.NetworksParser;
import com.google.gson.JsonElement;

@RestController
public class NetworkController {
	
	private final String URL = "https://api.themoviedb.org/3/person/popular?api_key=6cacd119a397de0ec8845d760efdb7ab";
	@GetMapping("/allnetworks")
	public String getAllNetworks() {
		return new NetworksParser().getJson(URL).toString();
	}
}

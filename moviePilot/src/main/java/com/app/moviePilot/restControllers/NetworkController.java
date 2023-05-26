package com.app.moviePilot.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.controller.parser.NetworksParser;
import com.app.moviePilot.model.network.Network;
import com.google.gson.JsonElement;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class NetworkController {
	
	private final String URL = "https://api.themoviedb.org/3/watch/providers/movie?api_key=6cacd119a397de0ec8845d760efdb7ab";
	@GetMapping("/allnetworks")
	public List<Network> getAllNetworks() {
		NetworksParser np = new NetworksParser();
		return np.toList(np.getJson(URL));
	}
}

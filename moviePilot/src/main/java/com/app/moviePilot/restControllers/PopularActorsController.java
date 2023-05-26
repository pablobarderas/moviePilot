package com.app.moviePilot.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.model.mediaPersonnel.CastMember;
import com.app.moviePilot.controller.parser.PopularActorsParser;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PopularActorsController {
	
	private final String URL = "https://api.themoviedb.org/3/person/popular?api_key=6cacd119a397de0ec8845d760efdb7ab";
	@GetMapping("/popularactors")
	public List<CastMember> getPopularActors() {
		PopularActorsParser pp = new PopularActorsParser();
		return pp.toList(pp.getJson(URL));
	}

}

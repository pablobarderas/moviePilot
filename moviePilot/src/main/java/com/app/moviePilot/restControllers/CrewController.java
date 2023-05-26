package com.app.moviePilot.restControllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.controller.parser.CrewParser;
import com.app.moviePilot.model.mediaPersonnel.CrewMember;
/**
 * 
 * @author Alberto Jhonson
 *
 */
@RestController
public class CrewController {
	
	private final String URL = "https://api.themoviedb.org/3/";
	private final String API_KEY = "?api_key=6cacd119a397de0ec8845d760efdb7ab";
	
	@GetMapping(value ="/{mediaType}/{id}/crew")
	public Set<CrewMember> getCrew(@PathVariable String mediaType, @PathVariable Integer id){
		Set<CrewMember> crewList = new HashSet();
		CrewParser cp = new CrewParser();
		
		return cp.getObject(cp.getJson(URL+mediaType+"/"+id+"/credits"+API_KEY));
	}
}

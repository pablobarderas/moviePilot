package com.app.moviePilot.restControllers;

import java.util.HashSet;
import java.util.Set;

import com.app.moviePilot.controller.parser.CastParser;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.model.mediaPersonnel.CastMember;
/**
 * 
 * @author Alberto Jhonson
 *
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CastController {
	
	private final String URL = "https://api.themoviedb.org/3/";
	private final String API_KEY = "?api_key=6cacd119a397de0ec8845d760efdb7ab";

	@GetMapping(value="/{mediaType}/{id}/cast")
	public Set<CastMember> getCast(@PathVariable String mediaType, @PathVariable Integer id){
		Set<CastMember> castList = new HashSet();
		CastParser cp = new CastParser();
		
		return cp.getObject(cp.getJson(URL+mediaType+"/"+id+"/credits"+API_KEY));
	}
}

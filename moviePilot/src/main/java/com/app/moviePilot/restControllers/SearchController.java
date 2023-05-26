package com.app.moviePilot.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.controller.parser.ShowParser;
import com.app.moviePilot.model.film.Film;
import com.app.moviePilot.model.visualContent.VisualContent;

/**
 * 
 * @author Pablo Barderas
 *
 */

@RestController
@RequestMapping(value = "/search")
public class SearchController {

	// TODO Multi parse
	@GetMapping(value = "/multi/{keyword}/page/{page}")
	public List<VisualContent> getResults(@PathVariable String keyword, @PathVariable int page) {
		
		FilmParser filmParser = new FilmParser();
		ShowParser showParser = new ShowParser();
		//String url = "https://api.themoviedb.org/3/search/movie?api_key=6cacd119a397de0ec8845d760efdb7ab&query="+ keyword;
		//JsonElement filmResults = filmParser.getJson(url);
		//JsonElement showResults = filmParser.getJson(url);
		
		List<VisualContent> filmsList = filmParser.getVisualContentFromPage("search/movie", "&query="+keyword, page);
		return  filmsList;
	}
	
	// GET ALL MOVIES FROM PAGE
	@GetMapping(value = "/movie/{keyword}/page/{page}")
	public List<VisualContent> getMovieResults(@PathVariable String keyword, @PathVariable int page) {
		FilmParser filmParser = new FilmParser();
		List<VisualContent> filmsList = filmParser.getVisualContentFromPage("search/movie", "&query="+keyword, page);
		return  filmsList;
	}
	
}

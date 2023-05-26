package com.app.moviePilot.restControllers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.controller.parser.ShowParser;
import com.app.moviePilot.model.visualContent.VisualContent;

/**
 * 
 * @author Pablo Barderas
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/search")
public class SearchController {

	// TODO Multi parse
	@GetMapping(value = "/multi/{keyword}/page/{page}")
	public List<VisualContent> getResults(@PathVariable String keyword, @PathVariable int page) {
		
		FilmParser filmParser = new FilmParser();
		ShowParser showParser = new ShowParser();
		
		
		List<VisualContent> filmsList = filmParser.getVisualContentFromPage("search/movie", "&query="+keyword, page);
		List<VisualContent> showList = showParser.getVisualContentFromPage("search/tv", "&query="+keyword, page);

		List<VisualContent> results = new LinkedList<VisualContent>();
		results.addAll(filmsList);
		results.addAll(showList);

		Collections.shuffle(results);

		return results;
	}
	
	// GET ALL MOVIES FROM PAGE
	@GetMapping(value = "/movie/{keyword}/page/{page}")
	public List<VisualContent> getFilmsFromPage(@PathVariable String keyword, @PathVariable int page) {
		FilmParser filmParser = new FilmParser();
		List<VisualContent> filmsList = filmParser.getVisualContentFromPage("search/movie", "&query="+keyword, page);
		return  filmsList;
	}

	// GET ALL SHOWS FROM PAGE
	@GetMapping(value = "/tv/{keyword}/page/{page}")
	public List<VisualContent> getShowsFromPage(@PathVariable String keyword, @PathVariable int page) {
		ShowParser showParser = new ShowParser();
		List<VisualContent> showsList = showParser.getVisualContentFromPage("search/tv", "&query="+keyword, page);
		return  showsList;
	}
	
}

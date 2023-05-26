package com.app.moviePilot.restControllers;

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
@RequestMapping(value = "/discover")
public class DiscoverFilterController {

	@GetMapping(value = "/movie/{filterParams}/page/{page}")
	public List<VisualContent> getResults(@PathVariable String filterParams, @PathVariable int page) {

		FilmParser filmParser = new FilmParser();
		ShowParser showParser = new ShowParser();

		List<VisualContent> filmsList = filmParser.getVisualContentFromPage("discover/movie", "&with_watch_providers=" + filterParams, page);
		List<VisualContent> showList = showParser.getVisualContentFromPage("discover/tv", "&query=" + filterParams, page);
		return null;
	}

}

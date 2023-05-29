package com.app.moviePilot.restControllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.controller.filter.FilterProcessor;
import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.controller.parser.ShowParser;
import com.app.moviePilot.model.visualContent.VisualContent;

/**
 * 
 * @author Pablo Barderas
 *
 */

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/discover")
public class DiscoverFilterController {

	// TODO apply filters here
	@GetMapping(value = "/movie/{filterParams}/page/{page}")
	public List<VisualContent> getResults(@PathVariable String filterParams, @PathVariable int page) {

		FilmParser filmParser = new FilmParser();
		ShowParser showParser = new ShowParser();

		// GET URL
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/movie");
		filters.put("language","language=es");
		filters.put("release_date", "release_date.gte=2000-01-01");
		filters.put("vote_average", "vote_average.lte=3");
		FilterProcessor pf = new FilterProcessor();
		String url = pf.processClientFilters(filters).toString();
		
		List<VisualContent> filmsList = filmParser.getVisualContentFromPage("discover/movie", "&with_watch_providers=" + filterParams, page);
		List<VisualContent> showList = showParser.getVisualContentFromPage("discover/tv", "&query=" + filterParams, page);
		return null;
	}

}

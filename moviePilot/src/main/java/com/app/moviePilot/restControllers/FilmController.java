package com.app.moviePilot.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.model.film.Film;

/**
 * 
 * @author Pablo Barderas
 *
 */
@RestController
public class FilmController {

	@GetMapping(value = "/popularMovies/page/{page}")
	public List<Film> getPopularMovies(@PathVariable int page) {
		FilmParser filmParser = new FilmParser();
		String popular = "movie/popular";
		List<Film> popularFilms = filmParser.getFilmsFromPage(popular, page);
		
		return popularFilms;
	}

	// one film 

	

}

package com.app.moviePilot.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.model.film.Film;

@RestController
public class FilmController {

	@GetMapping(value = "/popularMovies/{page}")
	public List<Film> getPopularMovies(@PathVariable int page) {
		FilmParser filmParser = new FilmParser();
		String URL = "http://api.themoviedb.org/3/movie/popular";
		List<Film> popularFilms = filmParser.getFilmsFromPage(URL, page);
		
		return popularFilms;
	}

	

	

}

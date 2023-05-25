package com.app.moviePilot.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.model.film.Film;
import com.google.gson.JsonElement;

/**
 * 
 * @author Pablo Barderas
 *
 */
@RestController
@RequestMapping(value = "/movies")
public class FilmController {

	// GET POPULAR MOVIES
	@GetMapping(value = "/popularMovies/page/{page}")
	public List<Film> getPopularMovies(@PathVariable int page) {
		FilmParser filmParser = new FilmParser();
		String popular = "movie/popular";
		return filmParser.getFilmsFromPage(popular, page);
	}

	// GET ONE MOVIE
	@GetMapping(value = "/movie/{idFilm}")
	public Film getMovie(@PathVariable Long idFilm) {
		FilmParser filmParser = new FilmParser();
		JsonElement jsonFilm = filmParser.getJson("https://api.themoviedb.org/3/movie/"+idFilm+"?api_key=6cacd119a397de0ec8845d760efdb7ab");
		return filmParser.getObject(jsonFilm);
	}

	

}

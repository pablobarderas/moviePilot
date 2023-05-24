package com.app.moviePilot.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.model.film.Film;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * 
 * @author Pablo Barderas
 *
 */
@RestController
public class FilmController {

	// GET POPULAR MOVIES
	@GetMapping(value = "/popularMovies/page/{page}")
	public List<Film> getPopularMovies(@PathVariable int page) {
		FilmParser filmParser = new FilmParser();
		String popular = "movie/popular";
		List<Film> popularFilms = filmParser.getFilmsFromPage(popular, page);
		
		return popularFilms;
	}

	// GET ONE MOVIE
	@GetMapping(value = "/movie/{idFilm}")
	public Film getMovie(@PathVariable Long idFilm) {
		FilmParser filmParser = new FilmParser();
		JsonElement jsonFilm = filmParser.getJson("https://api.themoviedb.org/3/movie/"+idFilm+"?api_key=6cacd119a397de0ec8845d760efdb7ab");
		
		return filmParser.getObject(jsonFilm);
	}

	

}

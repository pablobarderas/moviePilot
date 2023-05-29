package com.app.moviePilot.restControllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.controller.filter.FilterProcessor;
import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.controller.parser.GenresParser;
import com.app.moviePilot.controller.parser.ShowParser;
import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.film.Film;
import com.app.moviePilot.model.show.Show;
import com.app.moviePilot.model.visualContent.VisualContent;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author Pablo Barderas
 *
 */

@RestController
@RequestMapping(value = "/genres")
public class GenresController {

	@GetMapping(value = "/genres")
	public ResponseEntity<Set<Genres>> getGenres() {
		
		// /genre/movie/list
		GenresParser genresParser = new GenresParser();
		String url = "https://api.themoviedb.org/3/genre/movie/list?api_key=6cacd119a397de0ec8845d760efdb7ab";
		
		Set<Genres> genresList = genresParser.getGenresList(genresParser.getJson(url));
		//return genresArray;
		return new ResponseEntity<>(genresList, HttpStatus.OK) ;
	}
	
	
	@GetMapping(value = "/{genreId}/page/{page}")
	public List<VisualContent> getGenres(@PathVariable String genreId, @PathVariable int page) {

		FilmParser filmParser = new FilmParser();
		ShowParser showParser = new ShowParser();
		
		List<VisualContent> filmsList = filmParser.getVisualContentFromPage("discover/movie", "&with_genres="+genreId, page);
		List<VisualContent> showsList = showParser.getVisualContentFromPage("discover/movie", "&with_genres="+genreId, page);
		//List<Show> showList = showParser.getAllShows(url, page);
		

		return null;
	}

}

package com.app.moviePilot.filterTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.filter.FilterProcessor;


class FilterTest {
	FilterProcessor pf;
	@BeforeEach
	void setUp() {
		pf = new FilterProcessor();
	}
	
	@Test
	void genreTestMovie() {
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/movie");
		filters.put("language","language=es");
		filters.put("genre", "with_genres=27");
		String expectedUrl ="https://api.themoviedb.org/3/discover/movie?language=es&with_genres=27&api_key=6cacd119a397de0ec8845d760efdb7ab";
		assertEquals(expectedUrl, pf.processClientFilters(filters));
	}
	@Test 
	void genreTvTest(){
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/tv");
		filters.put("language","language=es");
		filters.put("genre", "with_genres=27");
		String expectedUrl ="https://api.themoviedb.org/3/discover/tv?language=es&with_genres=27&api_key=6cacd119a397de0ec8845d760efdb7ab";
		assertEquals(expectedUrl, pf.processClientFilters(filters));
	}
	@Test
	void titleMovieTest() {
		String expectedUrl = "https://api.themoviedb.org/3/search/movie?language=es&query=Spook&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "search/movie");
		filters.put("language","language=es");
		filters.put("query", "query=Spook");
		assertEquals(expectedUrl, pf.processClientFilters(filters));	
	}
	@Test
	void titleTvTest() {
		String expectedUrl = "https://api.themoviedb.org/3/search/tv?language=es&query=Spook&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "search/tv");
		filters.put("language","language=es");
		filters.put("query", "query=Spook");
		assertEquals(expectedUrl, pf.processClientFilters(filters));	
	}
	@Test
	void adultMovieTest(){
		String expectedUrl ="https://api.themoviedb.org/3/discover/movie?language=en&include_adult=true&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/movie");
		filters.put("language","language=en");
		filters.put("include_adult", "include_adult=true");
		assertEquals(expectedUrl,pf.processClientFilters(filters));
	}
	@Test
	void adultTvTest(){
		String expectedUrl ="https://api.themoviedb.org/3/discover/tv?language=en&include_adult=true&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/tv");
		filters.put("language","language=en");
		filters.put("include_adult", "include_adult=true");
		assertEquals(expectedUrl,pf.processClientFilters(filters));
	}
	@Test
	void releaseMovieTest() {
		String expectedUrl ="https://api.themoviedb.org/3/discover/movie?language=en&release_date.lte=2001-01-01&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/movie");
		filters.put("language","language=en");
		filters.put("release_date", "release_date.lte=2001-01-01");
		assertEquals(expectedUrl,pf.processClientFilters(filters));
	}
	@Test
	void releaseTvTest() {
		String expectedUrl ="https://api.themoviedb.org/3/discover/tv?language=en&release_date.lte=2001-01-01&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/tv");
		filters.put("language","language=en");
		filters.put("release_date", "release_date.lte=2001-01-01");
		assertEquals(expectedUrl,pf.processClientFilters(filters));
	}
	@Test
	void voteAvgMovieTest() {
		String expectedUrl ="https://api.themoviedb.org/3/discover/movie?language=es&vote_average.gte=8.5&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/movie");
		filters.put("language","language=es");
		filters.put("vote_average", "vote_average.gte=8.5");
		assertEquals(expectedUrl,pf.processClientFilters(filters));
	}
	@Test
	void voteAvgTvTest() {
		String expectedUrl ="https://api.themoviedb.org/3/discover/tv?language=es&vote_average.gte=8.5&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/tv");
		filters.put("language","language=es");
		filters.put("vote_average", "vote_average.gte=8.5");
		assertEquals(expectedUrl,pf.processClientFilters(filters));
	}
	@Test
	void multipleFiltersTvTest() {
		String expectedUrl ="https://api.themoviedb.org/3/discover/tv?language=en&first_air_date.lte=2002-01-01&vote_average.gte=8.5&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/tv");
		filters.put("language","language=en");
		filters.put("first_air_date", "first_air_date.lte=2002-01-01");
		filters.put("vote_average", "vote_average.gte=8.5");
		assertEquals(expectedUrl,pf.processClientFilters(filters));
	}
	@Test
	void multipleFiltersMovieTest() {
		String expectedUrl ="https://api.themoviedb.org/3/discover/movie?language=es&release_date.gte=2000-01-01&vote_average.lte=3&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "discover/movie");
		filters.put("language","language=es");
		filters.put("release_date", "release_date.gte=2000-01-01");
		filters.put("vote_average", "vote_average.lte=3");
		assertEquals(expectedUrl,pf.processClientFilters(filters));
	}
	@Test
	void searchByMultipleTypesMovieTest() {
		String expectedUrl ="{\"adult\":false,\"backdrop_path\":\"/qU7tNIMpRqkizIObXfkJY3haTqh.jpg\",\"belongs_to_collection\":null,\"budget\":1000000,\"genres\":[{\"id\":18,\"name\":\"Drama\"},{\"id\":10402,\"name\":\"Music\"},{\"id\":10749,\"name\":\"Romance\"}],\"homepage\":\"\",\"id\":27,\"imdb_id\":\"tt0411705\",\"original_language\":\"en\",\"original_title\":\"9 Songs\",\"overview\":\"Matt, a young glaciologist, soars across the vast, silent, icebound immensities of the South Pole as he recalls his love affair with Lisa. They meet at a mobbed rock concert in a vast music hall - London's Brixton Academy. They are in bed at night's end. Together, over a period of several months, they pursue a mutual sexual passion whose inevitable stages unfold in counterpoint to nine live-concert songs.\",\"popularity\":25.699,\"poster_path\":\"/91O7z0vo7MiNWd5xD2BoivwbQsb.jpg\",\"production_companies\":[{\"id\":163,\"logo_path\":null,\"name\":\"Revolution Films\",\"origin_country\":\"GB\"}],\"production_countries\":[{\"iso_3166_1\":\"GB\",\"name\":\"United Kingdom\"}],\"release_date\":\"2004-07-16\",\"revenue\":1574623,\"runtime\":69,\"spoken_languages\":[{\"english_name\":\"English\",\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"2 lovers, one summer, and the 9 songs that defined them.\",\"title\":\"9 Songs\",\"video\":false,\"vote_average\":5.701,\"vote_count\":441}";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "movie/27");
		filters.put("language","language=en");
		assertEquals(expectedUrl,pf.processClientFilters(filters).toString());
	}
	@Test
	void searchByMultipleTypesTvTest() {
		String expectedUrl ="{\"adult\":false,\"backdrop_path\":\"/61WtwoHaWHRiabd3DkHCDmLKzaT.jpg\",\"created_by\":[],\"episode_run_time\":[20,30],\"first_air_date\":\"\",\"genres\":[],\"homepage\":\"\",\"id\":27,\"in_production\":false,\"languages\":[\"en\"],\"last_air_date\":null,\"last_episode_to_air\":null,\"name\":\"Bratz\",\"next_episode_to_air\":null,\"networks\":[{\"id\":19,\"logo_path\":\"/1DSpHrWyOORkL9N2QHX7Adt31mQ.png\",\"name\":\"FOX\",\"origin_country\":\"US\"}],\"number_of_episodes\":0,\"number_of_seasons\":2,\"origin_country\":[\"US\"],\"original_language\":\"en\",\"original_name\":\"Bratz\",\"overview\":\"Bratz is a computer-animated television series, based on a line of toy dolls of the same name. It is produced by Mike Young Productions and MGA Entertainment, and premiered on 4Kids TV on the Fox Television Network.\",\"popularity\":3.57,\"poster_path\":\"/qbQfewZQRwQMrd4enAPRx5AMjng.jpg\",\"production_companies\":[],\"production_countries\":[],\"seasons\":[{\"air_date\":null,\"episode_count\":0,\"id\":102855,\"name\":\"Bratz Season 1\",\"overview\":\"\",\"poster_path\":null,\"season_number\":1},{\"air_date\":null,\"episode_count\":0,\"id\":102856,\"name\":\"Bratz Season 2\",\"overview\":\"\",\"poster_path\":null,\"season_number\":2}],\"spoken_languages\":[{\"english_name\":\"English\",\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Ended\",\"tagline\":\"\",\"type\":\"Scripted\",\"vote_average\":8.429,\"vote_count\":7}";
		Map<String,String> filters = new LinkedHashMap<>();
		filters.put("type", "tv/27");
		filters.put("language","language=en");
		assertEquals(expectedUrl,pf.processClientFilters(filters).toString());
	}
}

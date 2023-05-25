package com.app.moviePilot.filterTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.filter.FilterProcessor;


class PostFilterTest {
	FilterProcessor pf;
	@BeforeEach
	void setUp() {
		pf = new FilterProcessor();
	}
	@Test
	void createProcessor() {
		assertNotNull(pf);
	}
	@Test
	void invalidActionTest() {
		assertNull(pf.postFilters("not valid", Map.of("language","es")));
	}
	@Test
	void validActionInvalidParameterTest() {
		assertNull(pf.postFilters("movieById", Map.of("language","es")));
	}
	@Test
	void validActionTest() {
		assertNotNull(pf.postFilters("filterMovie", null));
		assertNotNull(pf.postFilters("filterMovie", Map.of()));
		assertNotNull(pf.postFilters("movieById", Map.of("id","2")));
		assertNotNull(pf.postFilters("filterMovie", Map.of("language","es","with_genres","with_genres=27")));
	}
	@Test
	void retunsExpectedUrlTest() {
		String expectedUrl ="https://api.themoviedb.org/3/discover/movie?language=es&with_genres=27&api_key=6cacd119a397de0ec8845d760efdb7ab";
		Map<String, String> filters = new LinkedHashMap<>();
		filters.put("language", "language=es");
		filters.put("with_genres", "with_genres=27");
		assertNotNull(pf.postFilters("filterMovie",filters));
	}
	@Test
	void returnsExpectedUrlWithParamsTest() {
		String expectedUrl ="{\"adult\":false,\"backdrop_path\":\"/l94l89eMmFKh7na2a1u5q67VgNx.jpg\",\"belongs_to_collection\":null,\"budget\":0,\"genres\":[{\"id\":18,\"name\":\"Drama\"},{\"id\":35,\"name\":\"Comedia\"},{\"id\":10749,\"name\":\"Romance\"}],\"homepage\":\"\",\"id\":3,\"imdb_id\":\"tt0092149\",\"original_language\":\"fi\",\"original_title\":\"Varjoja paratiisissa\",\"overview\":\"Nikander es un conductor de un camión de la basura, que una noche verá como su vida se complica al morirse su compañero de trabajo. Además, se enamora de Ilona, una cajera de un supermercado. Primera entrega de \\\"La trilogía del proletariado\\\" que se compone además de \\\"Ariel\\\" y \\\"La chica de la fábrica de cerillas.\\\"\",\"popularity\":6.275,\"poster_path\":\"/o2cWCX48flnEgDwSWu0Gpia0i0N.jpg\",\"production_companies\":[{\"id\":2303,\"logo_path\":null,\"name\":\"Villealfa Filmproductions\",\"origin_country\":\"FI\"}],\"production_countries\":[{\"iso_3166_1\":\"FI\",\"name\":\"Finland\"}],\"release_date\":\"1986-10-17\",\"revenue\":0,\"runtime\":76,\"spoken_languages\":[{\"english_name\":\"English\",\"iso_639_1\":\"en\",\"name\":\"English\"}],\"status\":\"Released\",\"tagline\":\"\",\"title\":\"Sombras en el paraíso\",\"video\":false,\"vote_average\":7.182,\"vote_count\":270}";
		Map<String, String> filters = new LinkedHashMap<>();
		filters.put("language", "language=es");
		filters.put("id", "3");
		assertNotNull(pf.postFilters("movieById",filters).toString());
	}
}

package com.app.moviePilot.filterTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.filter.UrlBuilder;
import com.google.gson.JsonElement;

class UrlBuilderTest {
	public static final String URL ="https://api.themoviedb.org/3/";
	public static final String KEY = "api_key=6cacd119a397de0ec8845d760efdb7ab"; 
	UrlBuilder ub;
	@BeforeEach
	void setUp() {
		ub = UrlBuilder.create(URL, KEY);
	}

	@Test
	void createBuilder() {
		assertNotNull(ub);
	}
	@Test
	void addingParameters() {
		UrlBuilder addingParameter= ub.addQueryParameter("testing", "testing");
		assertNotNull(addingParameter);
	}
	@Test
	void checkingBuildWithoutType() {
		UrlBuilder addingParameter= ub.addQueryParameter("type", "movie/popular");
		JsonElement response = addingParameter.build();
		assertNotNull(response);
	}
	@Test
	void checkingBuildWithType() {
		UrlBuilder addingParameter= ub.addQueryParameter("type", "tv/popular").addQueryParameter("language", "language=es");
		JsonElement response = addingParameter.build();
		assertNotNull(response);
	}
}

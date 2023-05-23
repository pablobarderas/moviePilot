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
		UrlBuilder addingParameter= ub.addQueryParameter("testing", "testing").addQueryParameter("testing2", "testing2");
		JsonElement response = addingParameter.build();
		String expectedResponse = URL+"testing&testing2&"+KEY;
		assertNotNull(response);
		assertEquals(expectedResponse, response);
	}
	@Test
	void checkingBuildWithType() {
		UrlBuilder addingParameter= ub.addQueryParameter("type", "testingType").addQueryParameter("testing2", "testing2");
		JsonElement response = addingParameter.build();
		String expectedResponse = URL+"testingType?testing2&"+KEY;
		assertNotNull(response);
		assertEquals(expectedResponse, response);
	}
}

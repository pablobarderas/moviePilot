package com.app.moviePilot.configTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.moviePilot.config.GsonDeserializerBuilder;
/**
 * 
 * @author Marino Burillo
 *
 */
class GsonDeserializerBuilderTest {

	@Test
	void createGsonTest() {
		assertNotNull(GsonDeserializerBuilder.getDeserializedGson());
	}

}

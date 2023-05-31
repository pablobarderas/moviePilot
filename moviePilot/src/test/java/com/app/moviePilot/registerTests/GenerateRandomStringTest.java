package com.app.moviePilot.registerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.controller.register.AlphabeticStringGenerator;
/**
 * 
 * @author Marino Burillo
 *
 */

class GenerateRandomStringTest {
	@Test
	void generateRandomStringTest() {
		int textLength = 20;
		String randomString = AlphabeticStringGenerator.create(textLength);
		assertNotNull(randomString);
		assertEquals(textLength, randomString.length());
	}
	@RepeatedTest(10)
	void checkOnlyAlphabeticCharactersTest() {
		int textLength = 20;
		String randomString = AlphabeticStringGenerator.create(textLength);
		assertTrue(randomString.matches("[a-z]+"));
	}
}

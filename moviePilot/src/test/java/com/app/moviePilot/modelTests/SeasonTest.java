package com.app.moviePilot.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.season.Season;

/**
 * 
 * @author Alberto Johnson
 *
 */
public class SeasonTest {

	Season newSeason;
	
	@BeforeEach
	public void setup() {
    	newSeason = new Season();
	}
	
	@Test
	public void testCreateSeason() {
		assertInstanceOf(Season.class, newSeason);
		assertNotNull(newSeason);
	}
    
}
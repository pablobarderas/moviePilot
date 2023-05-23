package com.app.moviePilot.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.season.Episode;

public class EpisodeTest {

	Episode newEpisode;
	
	@BeforeEach
	public void setup() {
		newEpisode = new Episode();
	}
	
	@Test
	public void testCreateComment() {
		assertInstanceOf(Episode.class, newEpisode);
		assertNotNull(newEpisode);
	}
    
}
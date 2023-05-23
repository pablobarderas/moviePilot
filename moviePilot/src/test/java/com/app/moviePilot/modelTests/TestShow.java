package com.app.moviePilot.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.show.Show;
import com.app.moviePilot.model.visualContent.VisualContent;

/**
 * 
 * @author Arismendy Castillo
 *
 */
class TestShow {

	VisualContent show;

	@BeforeEach
	void setup() {
		show = new Show();
	}//end method
	
	@Test
	void testObject() {
		assertNotNull(show);
	}//end test
	
	
}//end class
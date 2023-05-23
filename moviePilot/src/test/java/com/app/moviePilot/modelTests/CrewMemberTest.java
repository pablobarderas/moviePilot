package com.app.moviePilot.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.mediaPersonnel.CrewMember;

class CrewMemberTest {
	
	private CrewMember crew;
	
	@BeforeEach
	void setUp(){
		crew = new CrewMember();
	}

	@Test
	void CreationTest() {
		assertNotNull(crew);
		assertInstanceOf(CrewMember.class, crew);
	}
	
	@Test
	void settersAndGettersTest() {
		crew.setId((long) 1406855);
		crew.setName("Duncan Muggoch");
		crew.setJob("Producer");
		assertEquals(1406855, crew.getId());
		assertEquals("Duncan Muggoch", crew.getName());
		assertEquals("Producer", crew.getJob());
	}

}
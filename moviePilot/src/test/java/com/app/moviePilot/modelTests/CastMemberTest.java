package com.app.moviePilot.modelTests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.mediaPersonnel.CastMember;

class CastMemberTest {
	
	private CastMember cast;
	
	@BeforeEach
	void setUp(){
		cast = new CastMember();
	}

	@Test
	void creationTest() {
		assertNotNull(cast);
		assertInstanceOf(CastMember.class, cast);
	}
	
	@Test
	void settersAndGettersTest() {
		cast.setId((long) 22970);
		cast.setName("Peter Dinklage");
		cast.setProfilePath("/lRsRgnksAhBRXwAB68MFjmTtLrk.jpg");
		cast.setCharacterName("Tyrion Lannister");
		assertEquals(22970, cast.getId());
		assertEquals("Peter Dinklage", cast.getName());
		assertEquals("/lRsRgnksAhBRXwAB68MFjmTtLrk.jpg", cast.getProfilePath());
		assertEquals("Tyrion Lannister", cast.getCharacterName());
	}

}
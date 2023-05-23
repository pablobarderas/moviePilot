package com.app.moviePilot.userTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.app.moviePilot.model.visualContent.VisualContent;
import com.app.moviePilot.model.film.Film;
import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.User;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Marino Burillo
 *
 */
class UserTest {
	@Test
	void createUserTest() {
		User genericUser = new ActiveUser("TestUserName","emailTest@gmail.com","passwordTest","C:/fakepath",Set.of(new Genres("Action",1)),null, null,LocalDateTime.now());
		assertNotNull(genericUser);
		Set<ActiveUser> friends = new HashSet<>();
		friends.add((ActiveUser)genericUser);
		User userWithFriends = new ActiveUser("TestUserNameWFriends","emailTestFriends@gmail.com","passwordTest2","C:/fakepath",null,null, friends,LocalDateTime.now());
		assertNotNull(userWithFriends);
		VisualContent f = new Film();
		User userWithList =new ActiveUser("TestUserNameWFriends","emailTestFriends@gmail.com","passwordTest2","C:/fakepath",null,Set.of(f), friends,LocalDateTime.now());
		assertNotNull(userWithList);
	}
	@Test
	void createEmptyUser() {
		User emptyUser = new ActiveUser();
		assertNotNull(emptyUser);
		emptyUser.setId(4L);
		assertEquals(4, emptyUser.getId());
		LocalDateTime createdAt = LocalDateTime.now();		
		emptyUser.setCreatedAt(createdAt);
		assertEquals(createdAt, emptyUser.getCreatedAt());
		assertNull(emptyUser.getEmail());
	}

}

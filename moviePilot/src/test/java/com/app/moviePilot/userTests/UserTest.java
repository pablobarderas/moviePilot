package com.app.moviePilot.userTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.app.moviePilot.model.visualContent.VisualContent;
import com.app.moviePilot.model.film.Film;
import com.app.moviePilot.model.enums.Genres;
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
		User genericUser = new User("TestUserName","emailTest@gmail.com","passwordTest","C:/fakepath",Set.of(new Genres("Action",1)),null, null,LocalDateTime.now());
		assertNotNull(genericUser);
		Set<User> friends = new HashSet<>();
		friends.add(genericUser);
		User userWithFriends = new User("TestUserNameWFriends","emailTestFriends@gmail.com","passwordTest2","C:/fakepath",null,null, friends,LocalDateTime.now());
		assertNotNull(userWithFriends);
		VisualContent f = new Film();
		User userWithList =new User("TestUserNameWFriends","emailTestFriends@gmail.com","passwordTest2","C:/fakepath",null,Map.of(1,Set.of(f)), friends,LocalDateTime.now());
		assertNotNull(userWithList);
	}
	@Test
	void createEmptyUser() {
		User emptyUser = new User();
		assertNotNull(emptyUser);
		emptyUser.setId(4);
		assertEquals(4, emptyUser.getId());
		LocalDateTime createdAt = LocalDateTime.now();		
		emptyUser.setCreatedAt(createdAt);
		assertEquals(createdAt, emptyUser.getCreatedAt());
		assertNull(emptyUser.getEmail());
	}

}

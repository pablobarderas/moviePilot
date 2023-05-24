package com.app.moviePilot.serviceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.services.DeletedUserService;
/**
 * 
 * @author Marino Burillo
 *
 */
@SpringBootTest
class DeletedUserServiceTest {
	@Autowired
	private DeletedUserService deletedUserService;
	private ActiveUser userToDelete;
	@BeforeEach
	void setup() {
		userToDelete = new ActiveUser();
		userToDelete.setEmail("fakeEmail");
		userToDelete.setUserName("username");
		userToDelete.setPassword("password");
		userToDelete.setCreatedAt(LocalDateTime.now());
	}
	@Test
	void creationTest() {
		assertNotNull(deletedUserService);
	}
	@Test
	void addDeletedUserTest() {
		
		assertNotNull(deletedUserService.add(userToDelete));
	}
	@Test
	void getDeletedUserTest() {
		deletedUserService.add(userToDelete);
		assertNotNull(deletedUserService.getDeletedUser(userToDelete.getUserName()));
	}
}

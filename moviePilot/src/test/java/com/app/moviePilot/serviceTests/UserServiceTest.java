package com.app.moviePilot.serviceTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.moviePilot.model.register.UserRegisterDTO;
import com.app.moviePilot.model.register.UserUpdateDTO;
import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.roles.Role;
import com.app.moviePilot.services.UserService;
/**
 * 
 * @author Marino Burillo
 *
 */
@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;
	private String username;
	private String password;
	private String email;
	private UserRegisterDTO registerData;
	@BeforeEach
	void setup() {
		username = "name";
		password = "pass";
		email = "email";
		registerData = new UserRegisterDTO(username, password, email);
	}
	@Test
	void creationTest() {
		assertNotNull(userService);
	}
	@Test
	void registerUserTest() {	
		ActiveUser u = userService.registerDataToUser(registerData);
		assertNotNull(u);
		assertEquals(username, u.getUserName());
		assertEquals(password,u.getPassword());
		assertEquals(email,u.getEmail());
		assertNull(u.getProfilePicture());
		userService.deleteUser(u);
	}
	@Test
	void findUserTest() {
		userService.registerDataToUser(registerData);
		ActiveUser u = userService.getUser(username);
		assertNotNull(u);
		assertEquals(username, u.getUserName());
		assertEquals(password,u.getPassword());
		assertEquals(email,u.getEmail());
		userService.deleteUser(u);
	}
	@Test
	void userNotFoundTest() {
		assertNull(userService.getUser("a"));
	}
	@Test
	void updateUserTest() {
		userService.registerDataToUser(registerData);
		ActiveUser u = userService.updateUser(new UserUpdateDTO(username, password, "anotherMail"));
		assertNotNull(u);
		assertEquals(username, u.getUserName());
		assertNotEquals(email, u.getEmail());
		userService.deleteUser(u);
	}
	@Test
	void makeUserAdminTest() {
		ActiveUser u =userService.registerDataToUser(registerData);
		u=userService.makeUserAdmin(u);
		assertNotNull(u);
		assertEquals(Role.ADMIN, u.getRole());
		userService.deleteUser(u);
	}
}

package com.app.moviePilot.controller.register;

import java.time.LocalDateTime;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;

import com.app.moviePilot.model.register.RegisterData;
import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.User;
import com.app.moviePilot.repository.ActiveUserRepository;
/**
 * 
 * @author Marino Burillo
 *
 */
@Component
public class SaveRegisteredUser {
	@Autowired
	private ActiveUserRepository userService;
	public SaveRegisteredUser() {

	}
	public User registerDataToUser(RegisterData userToSave) {
		ActiveUser user = new ActiveUser();
		user.setEmail(userToSave.getEmail());
		user.setUserName(userToSave.getUsername());
		user.setPassword(userToSave.getPassword());
		user.setCreatedAt(LocalDateTime.now());
		return userService.save(user);
	}
}

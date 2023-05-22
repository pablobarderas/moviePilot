package com.app.moviePilot.controller.register;

import java.time.LocalDateTime;

import javax.persistence.EntityManagerFactory;

import com.google.gson.JsonElement;

import com.app.moviePilot.model.register.RegisterData;
import com.app.moviePilot.model.user.User;
/**
 * 
 * @author Marino Burillo
 *
 */
public class SaveRegisteredUser {
	RegisterData registerData;
	public SaveRegisteredUser(final RegisterData registerData) {
		this.registerData=registerData; 
	}
	public JsonElement registerDataToUser() {
		User userToSave = new User();
		userToSave.setEmail(registerData.getEmail());
		userToSave.setUserName(registerData.getUsername());
		userToSave.setPassword(registerData.getPassword());
		userToSave.setCreatedAt(LocalDateTime.now());
		return null;
	}
}

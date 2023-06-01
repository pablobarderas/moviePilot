package com.app.moviePilot.services;

import java.time.LocalDateTime;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.app.moviePilot.controller.register.AlphabeticStringGenerator;
import com.app.moviePilot.model.register.UserRegisterDTO;
import com.app.moviePilot.model.register.UserUpdateDTO;
import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.User;
import com.app.moviePilot.model.user.roles.Role;
import com.app.moviePilot.repository.ActiveUserRepository;
/**
 * 
 * @author Marino Burillo
 *
 */
@Service
public class UserService {
	@Autowired
	private ActiveUserRepository userRepository;
	@Autowired
	private DeletedUserService deletedUserSer;
	public UserService() {

	}
	public ActiveUser registerDataToUser(UserRegisterDTO userToSave) {
		int verificationCodeLength = 64;
		ActiveUser user = new ActiveUser();
		user.setEmail(userToSave.getEmail());
		user.setUserName(userToSave.getUsername());
		user.setPassword(userToSave.getPassword());
		user.setCreatedAt(LocalDateTime.now());
		user.setVerificationCode(AlphabeticStringGenerator.create(verificationCodeLength));
		user.setEnabled(false);		
		return userRepository.save(user);
	}
	public ActiveUser getUser(String username) {
		return userRepository.findByUsername(username);
	}
	public void deleteUser(ActiveUser userToDelete) {
		userRepository.delete(userToDelete);
		deletedUserSer.add(userToDelete);
	}
	public ActiveUser updateUser(UserUpdateDTO userToUpdate) {
		ActiveUser user = getUser(userToUpdate.getUsername());
		user.setComments(userToUpdate.getComments());
		user.setEmail(userToUpdate.getEmail());
		user.setPassword(userToUpdate.getPassword());
		user.setFavoriteGenres(userToUpdate.getFavoriteGenres());
		user.setUserFriends(userToUpdate.getUserFriends());
		user.setProfilePicture(userToUpdate.getProfilePicture());
		user.setUserVisualContent(user.getUserVisualContent());
		return userRepository.save(user);
	}
	public ActiveUser makeUserAdmin(ActiveUser u) {
		u.setRole(Role.ADMIN);
		return userRepository.save(u);
	}
	public ActiveUser checkLogin(ActiveUser u) {
		return userRepository.findByUsernameAndPassword(u.getUsername(), u.getPassword());
	}
}

package com.app.moviePilot.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.DeletedUser;
import com.app.moviePilot.repository.ActiveUserRepository;
import com.app.moviePilot.repository.DeletedUserRepository;
/**
 * 
 * @author Marino Burillo
 *
 */
@Service
public class DeletedUserService {
	@Autowired
	private DeletedUserRepository deletedUserRepository;
	public DeletedUserService() {

	}
	public DeletedUser getDeletedUser(String username) {
		return deletedUserRepository.findByUsername(username);
	}
	public DeletedUser add(ActiveUser userToDelete) {
		if(getDeletedUser(userToDelete.getUserName())!=null) return null;
		DeletedUser delUser = new DeletedUser();
		delUser.setCreatedAt(userToDelete.getCreatedAt());
		delUser.setUserName(userToDelete.getUserName());
		delUser.setEmail(userToDelete.getEmail());
		delUser.setPassword(userToDelete.getPassword());
		delUser.setComments(userToDelete.getComments());
		delUser.setDeletedAt(LocalDateTime.now());
		delUser.setFavoriteGenres(userToDelete.getFavoriteGenres());
		delUser.setProfilePicture(userToDelete.getProfilePicture());
		delUser.setUserFriends(userToDelete.getUserFriends());
		delUser.setUserVisualContent(userToDelete.getUserVisualContent());
		return deletedUserRepository.save(delUser);	
	}
}

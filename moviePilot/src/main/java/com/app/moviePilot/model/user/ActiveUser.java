package com.app.moviePilot.model.user;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.visualContent.VisualContent;

@Entity
@Table(name="ACTIVE_USERS")
public class ActiveUser extends User {
	public ActiveUser(String userName, String email, String password, String profilePicture,
			Set<Genres> favoriteGenres, Map<Integer, Set<VisualContent>> userVisualContent, Set<User> userFriends,
			LocalDateTime createdAt) {
		super(userName, email, password, profilePicture, favoriteGenres, userVisualContent, userFriends, createdAt);
	}
	public ActiveUser() {
		
	}
}

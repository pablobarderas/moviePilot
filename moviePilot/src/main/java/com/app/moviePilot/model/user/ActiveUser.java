package com.app.moviePilot.model.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.app.moviePilot.model.comment.Comment;
import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.user.roles.Role;
import com.app.moviePilot.model.visualContent.VisualContent;

@Entity
@Table(name="ACTIVE_USERS")
public class ActiveUser extends User {
	
	public ActiveUser(String username, String email, String password, String profilePicture,
			Set<Genres> favoriteGenres, Set<VisualContent> userVisualContent, Set<ActiveUser> userFriends,
			LocalDateTime createdAt, List<Comment> comments, Role role) {
		super(username, email, password, profilePicture, favoriteGenres, userVisualContent, userFriends, createdAt,
				comments, role);
	}

	public ActiveUser() {
		
	}
}

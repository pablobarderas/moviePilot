package com.app.moviePilot.model.user;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.visualContent.VisualContent;
@Entity
@Table(name="DELETED_USERS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DeletedUser extends User {
	@Column(name="deleted_at")
	private LocalDateTime deletedAt;

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public DeletedUser(String userName, String email, String password, String profilePicture,
			Set<Genres> favoriteGenres, Set<VisualContent> userVisualContent, Set<ActiveUser> userFriends,
			LocalDateTime createdAt, LocalDateTime deletedAt) {
		super(userName, email, password, profilePicture, favoriteGenres, userVisualContent, userFriends, createdAt);
		this.deletedAt = deletedAt;
	}
	
}

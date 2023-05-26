package com.app.moviePilot.model.register;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.app.moviePilot.model.comment.Comment;
import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.visualContent.VisualContent;

public class UserUpdateDTO extends UserRegisterDTO {
	public UserUpdateDTO(String username, String password, String email) {
		super(username, password, email);
	}
	private String profilePicture;
	private Set<Genres> favoriteGenres;
	private Set<VisualContent> userVisualContent;
	private Set<ActiveUser> userFriends;
	private LocalDateTime createdAt;
    private List<Comment> comments;
    
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public Set<Genres> getFavoriteGenres() {
		return favoriteGenres;
	}
	public void setFavoriteGenres(Set<Genres> favoriteGenres) {
		this.favoriteGenres = favoriteGenres;
	}
	public Set<VisualContent> getUserVisualContent() {
		return userVisualContent;
	}
	public void setUserVisualContent(Set<VisualContent> userVisualContent) {
		this.userVisualContent = userVisualContent;
	}
	public Set<ActiveUser> getUserFriends() {
		return userFriends;
	}
	public void setUserFriends(Set<ActiveUser> userFriends) {
		this.userFriends = userFriends;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
    
}

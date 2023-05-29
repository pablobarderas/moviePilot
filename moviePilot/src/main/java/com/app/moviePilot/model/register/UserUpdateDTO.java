package com.app.moviePilot.model.register;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.app.moviePilot.model.comment.Comment;
import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.visualContent.VisualContent;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
/**
 * 
 * @author Marino Burillo
 *
 */
public class UserUpdateDTO extends UserRegisterDTO {
	public UserUpdateDTO(String username, String password, String email) {
		super(username, password, email);
	}
	private String profilePicture;
	private Set<Genres> favoriteGenres;
	private Set<VisualContent> userVisualContent;
	private Set<ActiveUser> userFriends;
    private List<Comment> comments;
    
	public UserUpdateDTO(String username, String password, String email, String profilePicture,
			Set<Genres> favoriteGenres, Set<VisualContent> userVisualContent, Set<ActiveUser> userFriends,
			List<Comment> comments) {
		super(username, password, email);
		this.profilePicture = profilePicture;
		this.favoriteGenres = favoriteGenres;
		this.userVisualContent = userVisualContent;
		this.userFriends = userFriends;
		this.comments = comments;
	}
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
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	class LocalDateTimeDeserializer implements JsonDeserializer < LocalDateTime > {
		@Override
		public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
		throws JsonParseException {
		    return LocalDateTime.parse(json.getAsString(),
		        DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss").withLocale(Locale.ENGLISH));
		}
	}
}

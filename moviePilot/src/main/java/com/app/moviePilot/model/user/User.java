package com.app.moviePilot.model.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.JoinColumn;

import com.app.moviePilot.model.comment.Comment;
import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.visualContent.VisualContent;
/**
 * 
 * @author Marino Burillo
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	@Column(nullable = false, name="username", unique=true)
	private String username;
	@Column(nullable = false, unique=true)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(name="profile_picture")
	private String profilePicture;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "user_genres", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genres> favoriteGenres;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "user_lits", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "content_id"))
	private Set<VisualContent> userVisualContent;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	    name = "user_friends",
	    joinColumns = @JoinColumn(name = "user_id"),
	    inverseJoinColumns = @JoinColumn(name = "friend_id")
	)
	private Set<ActiveUser> userFriends;
	@Column(nullable = false, name="created_at")
	private LocalDateTime createdAt;
	@OneToMany(fetch = FetchType.EAGER)
    private List<Comment> comments;
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public User() {
		
	}
	public User(String userName, String email, String password, String profilePicture,
			Set<Genres> favoriteGenres, Set<VisualContent> userVisualContent, Set<ActiveUser> userFriends,
			LocalDateTime createdAt) {
		super();
		this.username = userName;
		this.email = email;
		this.password = password;
		this.profilePicture = profilePicture;
		this.favoriteGenres = favoriteGenres;
		this.userVisualContent = userVisualContent;
		this.userFriends = userFriends;
		this.createdAt = createdAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + username + ", email=" + email + ", password=" + password
				+ ", profilePicture=" + profilePicture + ", favoriteGenres=" + favoriteGenres + ", userVisualContent="
				+ userVisualContent + ", userFriends=" + userFriends + ", createdAt=" + createdAt + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(createdAt, other.createdAt) && Objects.equals(email, other.email)
				&& Objects.equals(favoriteGenres, other.favoriteGenres) && id == other.id
				&& Objects.equals(password, other.password) && Objects.equals(profilePicture, other.profilePicture)
				&& Objects.equals(userFriends, other.userFriends) && Objects.equals(username, other.username)
				&& Objects.equals(userVisualContent, other.userVisualContent);
	}
}
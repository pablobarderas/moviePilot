package com.app.moviePilot.model.user;

import java.time.LocalDateTime;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.JoinColumn;

import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.visualContent.VisualContent;
/**
 * 
 * @author Marino Burillo
 *
 */
@Entity
@Table(name = "ACTIVE_USERS")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long id;
	@Column(nullable = false, name="user_name")
	private String userName;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(name="profile_picture")
	private String profilePicture;
	@ManyToMany
	@JoinTable(
	  name = "user_genres", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genres> favoriteGenres;
	@ElementCollection
	@MapKeyColumn(name="list_id")
	@Column(name="user_lists")
	@CollectionTable(name="USER_LISTS", joinColumns=@JoinColumn(name="user_id"))
	private Map<Integer,Set<VisualContent>> userVisualContent;
	@JoinTable(name = "user_friends",
		    joinColumns = @JoinColumn(name = "user_id"),
		    inverseJoinColumns = @JoinColumn(name = "friend_id"))
	@ManyToMany(mappedBy = "userFriends", fetch = FetchType.LAZY)
	private Set<User> userFriends;
	@Column(nullable = false, name="created_at")
	private LocalDateTime createdAt;
	public User() {
		
	}
	public User(String userName, String email, String password, String profilePicture,
			Set<Genres> favoriteGenres, Map<Integer, Set<VisualContent>> userVisualContent, Set<User> userFriends,
			LocalDateTime createdAt) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.profilePicture = profilePicture;
		this.favoriteGenres = favoriteGenres;
		this.userVisualContent = userVisualContent;
		this.userFriends = userFriends;
		this.createdAt = createdAt;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Map<Integer, Set<VisualContent>> getUserVisualContent() {
		return userVisualContent;
	}
	public void setUserVisualContent(Map<Integer, Set<VisualContent>> userVisualContent) {
		this.userVisualContent = userVisualContent;
	}
	public Set<User> getUserFriends() {
		return userFriends;
	}
	public void setUserFriends(Set<User> userFriends) {
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
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
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
				&& Objects.equals(userFriends, other.userFriends) && Objects.equals(userName, other.userName)
				&& Objects.equals(userVisualContent, other.userVisualContent);
	}
}
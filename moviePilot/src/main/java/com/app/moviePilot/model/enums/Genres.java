package com.app.moviePilot.model.enums;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.app.moviePilot.model.user.User;

/**
 * 
 * @author Pablo Barderas
 *
 */
@Entity
@Table(name="GENRES")
public class Genres {

	private String name;
	@Id
	@Column(name="genre_id")
	private long id;
	@ManyToMany(mappedBy = "user_genres")
	Set<User> favoriteByUser;
	public Genres() {}
	
	public Genres(String name, long id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	public Genres(String name, long id, Set<User> favoriteByUser) {
		super();
		this.name = name;
		this.id = id;
		this.favoriteByUser = favoriteByUser;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Set<User> getFavoriteByUser() {
		return favoriteByUser;
	}

	public void setFavoriteByUser(Set<User> favoriteByUser) {
		this.favoriteByUser = favoriteByUser;
	}
	
}

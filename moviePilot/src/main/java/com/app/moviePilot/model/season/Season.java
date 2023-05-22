package com.app.moviePilot.model.season;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
/**
 * 
 * @author Alberto Johnson
 *
 */
public class Season {

	private Long id;
	
	private String name;
	private int seasonNumber;
	private String overview;
	private String posterPath;
	private String airDate;
	private Set<Episode> episodes;

	public Season(String name, int seasonNumber, String overview, String posterPath, String airDate) {
		this.name = name;
		this.seasonNumber = seasonNumber;
		this.overview = overview;
		this.posterPath = posterPath;
		this.airDate = airDate;
		this.episodes = new HashSet<>();
	}

	public Season() {	}

	public String getName() {
		return name;
	}

	public int getSeasonNumber() {
		return seasonNumber;
	}

	public String getOverview() {
		return overview;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public String getAirDate() {
		return airDate;
	}

	public Set<Episode> getEpisodes() {
		return episodes;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public void setAirDate(String airDate) {
		this.airDate = airDate;
	}

	public void setEpisodes(Set<Episode> episodes) {
		this.episodes = episodes;
	}

	@Override
	public String toString() {
		return "Season [name=" + name + ", seasonNumber=" + seasonNumber + ", overview=" + overview + ", posterPath="
				+ posterPath + ", airDate=" + airDate + ", episodes=" + episodes.toString() + "]";
	}

	
}

package com.app.moviePilot.model.show;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.network.Network;
import com.app.moviePilot.model.visualContent.VisualContent;

/**
 * 
 * @author Arismendy Castillo
 *
 */
@Entity
@Table(name = "SHOWS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Show extends VisualContent{
	
	@Transient
	private boolean inProduction;
	@Expose
	@Transient
	private LocalDate lastAirDate;
	@Expose
	@Transient
	private LocalDate firstAirDate;
	@Transient
	private int numberOfEpisodes;
	@Transient
	private int numberOfSeasons;
	@Transient
	private Set<String> originCountry;
	@Transient
	private String originalLanguage;
	@OneToMany
	private Set<Network> networks;
	
	public Show() {
		super();
	}

	public Show(Long idApi, String title, String overview, boolean adult, Set<Genres> genres,
			String posterPath, Set<String> languages, LocalDate releaseDate, int runtime, int voteAverageApi,
			int voteAverageOwn, int voteCount, String status, boolean inProduction, LocalDate lastAirDate, LocalDate firstAirDate, int numberOfEpisodes,
			int numberOfSeasons, Set<String> originCountry, String originalLanguage, Set<Network> networks) {
		super(idApi, title, overview, adult, genres, posterPath, languages, releaseDate, runtime, voteAverageApi, voteAverageOwn, voteCount, status);
		this.inProduction = inProduction;
		this.lastAirDate = lastAirDate;
		this.firstAirDate = firstAirDate;
		this.numberOfEpisodes = numberOfEpisodes;
		this.numberOfSeasons = numberOfSeasons;
		this.originCountry = originCountry;
		this.originalLanguage = originalLanguage;
		this.networks = networks;
	}

	public boolean isInProduction() {
		return inProduction;
	}

	public void setInProduction(boolean inProduction) {
		this.inProduction = inProduction;
	}

	public LocalDate getLastAirDate() {
		return lastAirDate;
	}

	public void setLastAirDate(LocalDate lastAirDate) {
		this.lastAirDate = lastAirDate;
	}

	public LocalDate getFirstAirDate() {
		return firstAirDate;
	}

	public void setFirstAirDate(LocalDate firstAirDate) {
		this.firstAirDate = firstAirDate;
	}

	public int getNumberOfEpisodes() {
		return numberOfEpisodes;
	}

	public void setNumberOfEpisodes(int numberOfEpisodes) {
		this.numberOfEpisodes = numberOfEpisodes;
	}

	public int getNumberOfSeasons() {
		return numberOfSeasons;
	}

	public void setNumberOfSeasons(int numberOfSeasons) {
		this.numberOfSeasons = numberOfSeasons;
	}

	public Set<String> getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(Set<String> originCountry) {
		this.originCountry = originCountry;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public void setOriginalLanguage(String originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	public Set<Network> getNetworks() {
		return networks;
	}

	public void setNetworks(Set<Network> networks) {
		this.networks = networks;
	}

}//end class
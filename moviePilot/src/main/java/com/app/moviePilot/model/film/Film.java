package com.app.moviePilot.model.film;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.app.moviePilot.model.enums.Genres;
import com.app.moviePilot.model.visualContent.VisualContent;

@Entity
@Table(name = "FILMS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Film extends VisualContent{

	@Transient
	private Set<Languages> spokenLanguages;
	
	public Film() {
		super();	
	}
	
	public Film(Long idApi, String title, String overview, boolean adult, Set<Genres> genres, String posterPath,
			Set<String> languages, LocalDate releaseDate, int runtime, int voteAverageApi, int voteAverageOwn,
			int voteCount, String status, Set<Languages> spokenLanguages) {
		super(idApi, title, overview, adult, genres, posterPath, languages, releaseDate, runtime, voteAverageApi,
				voteAverageOwn, voteCount, status);
		this.spokenLanguages = spokenLanguages;
	}
	
	public Set<Languages> getLanguagesFilm() {
		return spokenLanguages;
	}
	
	public void setLanguagesFilm(Set<Languages> spokenLanguages) {
		this.spokenLanguages = spokenLanguages;
	}
	
	
	
}

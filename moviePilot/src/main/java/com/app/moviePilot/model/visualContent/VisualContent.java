package com.app.moviePilot.model.visualContent;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import com.app.moviePilot.model.comment.Comment;
import com.app.moviePilot.model.enums.Genres;

/**
 * 
 * @author Pablo Barderas, Arismendy Castillo
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class VisualContent {
	
	private Long idApi;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_content")
	private Long idOwn;
	@Transient
	private String title;
	@Transient
	@Lob
	private String overview;
	@Transient
	private boolean adult;
	@Transient
	private Set<Genres> genres;
	@Transient
	private String posterPath;
	@Transient
	private Set<String> languages;
	@Expose
	@Transient
	private LocalDate releaseDate;
	@Transient
	private int runtime;
	private double voteAverageApi;
	private double voteAverageOwn;
	private int voteCount;
	@Transient
	private String status;
	@OneToMany
    private List<Comment> comments;

	
	
	public VisualContent() {
	}//end constructor
	

	public VisualContent(Long idApi, String title, String overview, boolean adult, Set<Genres> genres,
			String posterPath, Set<String> languages, LocalDate releaseDate, int runtime, int voteAverageApi,
			int voteAverageOwn, int voteCount, String status) {
		this.idApi = idApi;
		this.title = title;
		this.overview = overview;
		this.adult = adult;
		this.genres = genres;
		this.posterPath = posterPath;
		this.languages = languages;
		this.releaseDate = releaseDate;
		this.runtime = runtime;
		this.voteAverageApi = voteAverageApi;
		this.voteAverageOwn = voteAverageOwn;
		this.voteCount = voteCount;
		this.status = status;
	}//end constructor
	
	
	public Long getIdApi() {
		return idApi;
	}

	public void setIdApi(Long idApi) {
		this.idApi = idApi;
	}

	public Long getIdOwn() {
		return idOwn;
	}

	public void setIdOwn(Long idOwn) {
		this.idOwn = idOwn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public boolean getAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public Set<Genres> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genres> genres) {
		this.genres = genres;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public Set<String> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<String> languages) {
		this.languages = languages;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public double getVoteAverageApi() {
		return voteAverageApi;
	}

	public void setVoteAverageApi(double d) {
		this.voteAverageApi = d;
	}

	public double getVoteAverageOwn() {
		return voteAverageOwn;
	}

	public void setVoteAverageOwn(int voteAverageOwn) {
		this.voteAverageOwn = voteAverageOwn;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public void setVoteAverageOwn(double voteAverageOwn) {
		this.voteAverageOwn = voteAverageOwn;
	}
	
}//end class

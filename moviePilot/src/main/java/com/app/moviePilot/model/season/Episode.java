package com.app.moviePilot.model.season;

import javax.persistence.*;

/**
 * 
 * @author Emilio Izquierdo
 *
 */
@Entity
@Table(name = "EPISODES")
public class Episode {

	@Id
	@GeneratedValue
    private Long id;
    
	@Column(nullable = false)
	private String name;
    private String overview;
    private int runtime;
    @Column(nullable = false, name="seasion_number")
    private int seasonNumber;
    @Column(nullable = false, name="episode_number")
    private int episodeNumber;
    @Column(name="still_path")
    private String stillPath;
    @Column(name="vote_average_api")
    private double voteAverageApi;
    @Column(name="vote_count")
    private int voteCount;
    
    private Season season;
    
    public Episode(String name, String overview, int runtime, int seasonNumber, int episodeNumber,
                   String stillPath, double voteAverageApi, int voteCount) {
        this.name = name;
        this.overview = overview;
        this.runtime = runtime;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.stillPath = stillPath;
        this.voteAverageApi = voteAverageApi;
        this.voteCount = voteCount;
    }

    public Episode() {
	}

	public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getStillPath() {
        return stillPath;
    }

    public double getVoteAverageApi() {
        return voteAverageApi;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setName(String name) {
		this.name = name;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public void setSeasonNumber(int seasonNumber) {
		this.seasonNumber = seasonNumber;
	}

	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public void setStillPath(String stillPath) {
		this.stillPath = stillPath;
	}

	public void setVoteAverageApi(double voteAverageApi) {
		this.voteAverageApi = voteAverageApi;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
    
    
}

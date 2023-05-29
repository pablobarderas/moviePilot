package com.app.moviePilot.model.season;

import javax.persistence.*;

/**
 * 
 * @author Emilio Izquierdo
 *
 */
public class Episode implements Comparable<Episode>{

    private Long id;
    
	private String name;
    private String overview;
    private int runtime;
    private int seasonNumber;
    private int episodeNumber;
    private String stillPath;
    private double voteAverageApi;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Episode [id=" + id + ", name=" + name + ", overview=" + overview + ", runtime=" + runtime
				+ ", seasonNumber=" + seasonNumber + ", episodeNumber=" + episodeNumber + ", stillPath=" + stillPath
				+ ", voteAverageApi=" + voteAverageApi + ", voteCount=" + voteCount + ", season=" + season + "]";
	}

	@Override
	public int compareTo(Episode o) {
		return Integer.compare(this.episodeNumber, o.getEpisodeNumber());
	}
    
    
}

package com.app.moviePilot.model.comment;
/**
 * @author Marino, alex
 */
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.moviePilot.model.user.User;
import com.app.moviePilot.model.visualContent.VisualContent;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private int rating;
    @Column(nullable = false, name="published_at")
    private LocalDateTime publishedAt;

    public Comment(Long id, String content, int rating, LocalDateTime publishedAt) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.publishedAt = publishedAt;
    }

    public Comment() {
    	
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

}

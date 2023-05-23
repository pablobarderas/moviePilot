package com.app.moviePilot.model.comment;
/**
 * @author Marino, alex
 */
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.moviePilot.model.user.User;
import com.app.moviePilot.model.visualContent.VisualContent;
@Entity
@Table(name="COMMENTS")
public class Comment {
	@Id
	@GeneratedValue
    private long id;
	/*@ManyToOne
    private User commentAuthor;*/
    private String content;
    private int rating;
    @Column(nullable = false, name="published_at")
    private LocalDateTime publishedAt;
    @Column(name="deleted_at")
    private LocalDateTime deletedAt;
    /*@ManyToOne
    @JoinColumn(name = "id_content")
    private VisualContent visualContent;*/
    // Constructor con parámetros
    public Comment(long id, String content, int rating, LocalDateTime publishedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.publishedAt = publishedAt;
        this.deletedAt = deletedAt;
    }

    // Constructor vacío
    public Comment() {
    	
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}


	
}

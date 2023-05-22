package com.app.moviePilot.model.comment;
/**
 * @author Marino, alex
 */
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.moviePilot.model.user.User;
@Entity
@Table(name="COMMENTS")
public class Comment {
	@Id
	@GeneratedValue
    private long id;
	@Column(name="comment_author")
	@ManyToOne
    private User commentAuthor;
    private String content;
    private int rating;
    @Column(nullable = false, name="published_at")
    private LocalDateTime publishedAt;
    @Column(name="deleted_at")
    private LocalDateTime deletedAt;
    @ManyToOne
    private Long idVisualcontent;
    
    // Constructor con parámetros
    public Comment(long id, User commentAuthor, String content, int rating, LocalDateTime publishedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.commentAuthor = commentAuthor;
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

	public User getCommentAuthor() {
		return commentAuthor;
	}

	public void setCommentAuthor(User commentAuthor) {
		this.commentAuthor = commentAuthor;
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

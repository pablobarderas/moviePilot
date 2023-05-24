package com.app.moviePilot.model.comment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author Marino Burillo
 *
 */
@Entity
@Table(name="DELETED_COMMENT")
public class DeletedComment extends Comment {
    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

	public DeletedComment() {
		super();
	}

	public DeletedComment(Long id, String content, int rating, LocalDateTime publishedAt, LocalDateTime deletedAt) {
		super(id, content, rating, publishedAt);
		this.deletedAt = deletedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
    
}

package com.app.moviePilot.model.comment;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author Marino Burillo
 *
 */
@Entity
@Table(name="AVAILABLE_COMMENT")
public class AvailableComment extends Comment {

	public AvailableComment() {
		super();
	}

	public AvailableComment(Long id, String content, int rating, LocalDateTime publishedAt) {
		super(id, content, rating, publishedAt);
	}

}

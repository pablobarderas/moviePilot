package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.moviePilot.model.comment.AvailableComment;

/**
 * 
 * @author Marino Burillo
 *
 */
@Repository
public interface AvailableCommentRepository extends JpaRepository<AvailableComment, Long> {

	
}
package com.app.moviePilot.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.comment.AvailableComment;
import com.app.moviePilot.model.comment.Comment;
import com.app.moviePilot.model.comment.DeletedComment;
/**
 * Alex, Marino Burillo
 */
class CommentTest {


	@Test
	void testCreateAvailableComment() {
		assertNotNull(new AvailableComment());
	}
    @Test
    void createEmptyDeletedCommentTest() {
    	assertNotNull(new DeletedComment());
    }
}
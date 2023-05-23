package com.app.moviePilot.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.comment.Comment;

public class CommentTest {

	Comment newComment;
	
	@BeforeEach
	public void setup() {
		newComment = new Comment();
	}
	
	@Test
	public void testCreateComment() {
		assertInstanceOf(Comment.class, newComment);
		assertNotNull(newComment);
	}
    
}
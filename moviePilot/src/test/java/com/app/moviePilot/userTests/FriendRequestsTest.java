package com.app.moviePilot.userTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.user.friendRequest.FriendRequest;
/**
 * 
 * @author Marino Burillo
 *
 */
class FriendRequestsTest {

	@Test
	void creationTest() {
		assertNotNull(new FriendRequest());
	}

}

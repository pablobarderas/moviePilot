package com.app.moviePilot.modelTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.model.network.Network;

/**
 * 
 * @author Arismendy Castillo
 *
 */
class NetworkTest {

	Network network;
	
	@BeforeEach
	public void setup() {
		network = new Network();
	}
	
	@Test
	public void testCreateComment() {
		assertInstanceOf(Network.class, network);
		assertNotNull(network);
	}

}

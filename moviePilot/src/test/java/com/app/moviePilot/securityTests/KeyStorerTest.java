package com.app.moviePilot.securityTests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.moviePilot.security.KeyStorer;

/**
 * 
 * @author Marino Burillo
 *
 */
class KeyStorerTest {

	@Test
	void creationTest() {
		assertNotNull(new KeyStorer());
	}
	@Test
	void storingKeyTest() {
		assertTrue(KeyStorer.saveEncryptedSecretToProperties("randomSecret", "myUser.PASSWORD"));
		assertFalse(KeyStorer.saveEncryptedSecretToProperties(null, null));
		assertFalse(KeyStorer.saveEncryptedSecretToProperties("", ""));
		assertFalse(KeyStorer.saveEncryptedSecretToProperties("		", "		"));
	}
	
}
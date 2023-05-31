package com.app.moviePilot.registerTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.moviePilot.controller.register.UserMailSender;
import com.app.moviePilot.model.user.ActiveUser;
/**
 * 
 * @author Marino Burillo
 *
 */
@SpringBootTest
class MailSenderTests {
	@Autowired 
	UserMailSender mailSender;
	@Test
	void sendEmailTest() {
		String origin = "www.google.com";
		ActiveUser u = new ActiveUser();
		u.setUsername("userToGetEmail");
		u.setEmail("marino.burillo@metrica.es");
		u.setVerificationCode("validCode");
		u.setEnabled(false);
		assertTrue(mailSender.sendEmail(origin, u));
	}
	@Test
	void sendInexistentEmailTest() {
		String origin = "localhost:4200";
		ActiveUser u = new ActiveUser();
		u.setUsername("userToGetEmail");
		u.setEmail("IDontExistSoIShouldFail321321sddsadsa@gmail.es");
		u.setVerificationCode("validCode");
		u.setEnabled(false);
		assertTrue(mailSender.sendEmail(origin, u));
	}
}

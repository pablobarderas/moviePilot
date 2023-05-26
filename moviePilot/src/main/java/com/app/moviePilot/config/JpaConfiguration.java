package com.app.moviePilot.config;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.moviePilot.repository.ActiveUserRepository;

import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.roles.Role;

/**
 * @author Marino Burillo
 */
@Component
public class JpaConfiguration {
	@Autowired
	ActiveUserRepository activeUserRepository;

	@PostConstruct
	public void initialize() {
		ActiveUser admin = new ActiveUser();
		admin.setUserName("admin123");
		admin.setPassword("admin4691");
		admin.setEmail("admin@gmail.com");
		admin.setCreatedAt(LocalDateTime.now());
		admin.setRole(Role.ADMIN);
		activeUserRepository.save(admin);
	}
}

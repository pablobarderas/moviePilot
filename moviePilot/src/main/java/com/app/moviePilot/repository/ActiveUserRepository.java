package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.User;

public interface ActiveUserRepository extends JpaRepository<ActiveUser, Long> {
	ActiveUser findByUsername(String username);
}

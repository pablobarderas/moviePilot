package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.app.moviePilot.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}

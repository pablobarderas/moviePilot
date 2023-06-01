package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.moviePilot.model.user.ActiveUser;

/**
 * 
 * @author Marino Burillo
 *
 */
@Repository
public interface ActiveUserRepository extends JpaRepository<ActiveUser, Long> {

	ActiveUser findByUsername(String username);
	ActiveUser findByUsernameAndPassword(String username, String password);
}

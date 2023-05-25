package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.DeletedUser;

/**
 * 
 * @author Marino Burillo
 *
 */
@Repository
public interface DeletedUserRepository extends JpaRepository<DeletedUser, Long> {

	DeletedUser findByUsername(String username);
	
}

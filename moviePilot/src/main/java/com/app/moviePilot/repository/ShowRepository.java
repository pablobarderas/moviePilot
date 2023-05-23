package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.moviePilot.model.show.Show;

public interface ShowRepository extends JpaRepository<Show, Long>{
    
}//end class
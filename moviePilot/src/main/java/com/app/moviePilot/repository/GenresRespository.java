package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.moviePilot.model.enums.Genres;

/**
 * 
 * @author Pablo Barderas
 *
 */
public interface GenresRespository extends JpaRepository<Genres, Long>{

}

package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.moviePilot.model.film.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{

}

package com.app.moviePilot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.moviePilot.controller.parser.FilmParser;
import com.app.moviePilot.model.film.Film;
import com.app.moviePilot.repository.FilmRepository;

@Service
public class FilmService {

	private FilmRepository filmRepository;
    private FilmParser filmParser;

    @Autowired
    public FilmService(FilmParser filmParser) {
        this.filmParser = filmParser;
    }//end constructor

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }//end method

	
}

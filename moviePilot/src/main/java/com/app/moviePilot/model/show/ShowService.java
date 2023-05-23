package com.app.moviePilot.model.show;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.moviePilot.controller.parser.ShowParser;
import com.app.moviePilot.repository.ShowRepository;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final ShowParser showParser;

    @Autowired
    public ShowService(ShowRepository showRepository, ShowParser showParser) {
        this.showRepository = showRepository;
        this.showParser = showParser;
    }//end constructor

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }//end method

    public List<Show> getPopularShows() {
        return showRepository.findAll();
    }//end method

}//end class
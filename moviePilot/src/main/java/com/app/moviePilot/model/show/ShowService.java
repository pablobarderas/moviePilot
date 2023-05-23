package com.app.moviePilot.model.show;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.moviePilot.repository.ShowRepository;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    @Autowired
    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    // Other methods in the service class
}//end class
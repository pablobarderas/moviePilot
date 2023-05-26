package com.app.moviePilot.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.controller.parser.ShowParser;
import com.app.moviePilot.model.show.Show;

/**
 * Class that manages the different endpoints
 * to retrieve information from shows.
 * @author Arismendy Castillo
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/shows")
public class ShowController {

    private final ShowParser showParser;

    public ShowController(ShowParser showService) {
        this.showParser = showService;
    }

    @GetMapping("/{showId}")
    public Show getShow(@PathVariable Integer showId) {
        return showParser.fetchShow(showParser.getJsonShow(showId));
    }//end method

    @GetMapping("/popular/{page}")
    public List<Show> getPopularShows(@PathVariable Integer page) {
        return showParser.getShowsFromPage(page);
    }//end method

}//end class
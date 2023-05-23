package com.app.moviePilot.model.show;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.moviePilot.controller.parser.ShowParser;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowParser showParser;

    @Autowired
    public ShowController(ShowParser showService) {
        this.showParser = showService;
    }

    @GetMapping
    public List<Show> getAllShows() {
        return showParser.getAllShows(null);
    }

    @GetMapping("/popular/{page}")
    public List<Show> getPopularShows(@PathVariable Integer page) {
        return showParser.getShowsFromPage(page);
    }

}//end class
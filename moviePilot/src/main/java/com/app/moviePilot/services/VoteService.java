package com.app.moviePilot.services;

import com.app.moviePilot.repository.VoteRepository;

import vote.VoteDTO;

public class VoteService {

    private final VoteRepository voteRepository;

    
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void vote(VoteDTO voteDto) {
        //TODO
    }//end method
    
}//end class
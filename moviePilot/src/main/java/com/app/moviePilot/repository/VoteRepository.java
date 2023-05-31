package com.app.moviePilot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.moviePilot.model.user.User;
import com.app.moviePilot.model.visualContent.VisualContent;

import vote.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(VisualContent visualContent, User currentUser);
}//end class
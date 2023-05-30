package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.moviePilot.model.chat.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
    
}//end class
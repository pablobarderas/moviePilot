package com.app.moviePilot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.moviePilot.model.chat.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long>{
    
}//end class
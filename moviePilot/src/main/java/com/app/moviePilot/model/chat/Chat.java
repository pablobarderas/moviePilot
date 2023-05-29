package com.app.moviePilot.model.chat;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CHATS")
public class Chat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_chat;
	private Long idOtherUser;
    @OneToMany
	private Set<Message> messages;
    
    public Chat() {	}
    
	public Chat(Long id_chat, Long idOtherUser, Set<Message> messages) {
		setId_chat(id_chat);
		this.setIdOtherUser(idOtherUser);
		this.setMessages(messages);
	}
    
	public Long getId_chat() {
		return id_chat;
	}

	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	public Long getIdOtherUser() {
		return idOtherUser;
	}
	public void setIdOtherUser(Long idOtherUser) {
		this.idOtherUser = idOtherUser;
	}
	public void setId_chat(Long id_chat) {
		this.id_chat = id_chat;
	}
	
	
}

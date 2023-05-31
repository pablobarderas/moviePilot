package com.app.moviePilot.model.user.friendRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.moviePilot.model.user.ActiveUser;
import com.app.moviePilot.model.user.User;
/**
 * 
 * @author Marino Burillo
 *
 */
@Entity
@Table(name="FRIEND_REQUESTS")
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private ActiveUser sender;
    
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private ActiveUser receiver;
    
    public FriendRequest() {
    	
    }

	public FriendRequest(Long id, ActiveUser sender, ActiveUser receiver) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ActiveUser getSender() {
		return sender;
	}

	public void setSender(ActiveUser sender) {
		this.sender = sender;
	}

	public ActiveUser getReceiver() {
		return receiver;
	}

	public void setReceiver(ActiveUser receiver) {
		this.receiver = receiver;
	}
    
}
//package com.app.moviePilot.model.message;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//@Entity
//@Table(name="Messages")
//public class Message {
//	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="id_message")
//	private Long id;
//	private String text;
//	private LocalDateTime fecha;
//	private String username;
////	@ManyToOne(cascade = CascadeType.REMOVE)
//	private Chat chat;
//	
//	public Message() {	}
//	
//	public Message(Long id, String text, LocalDateTime fecha, String username, Chat chat) {
//		setText(text);
//		setFecha(fecha);
//		setId(id);
//		setUsername(username);
//		setChat(chat);
//	}
//
//	public Chat getChat() {
//		return chat;
//	}
//
//	public void setChat(Chat chat) {
//		this.chat = chat;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getText() {
//		return text;
//	}
//
//	public void setText(String text) {
//		this.text = text;
//	}
//
//	public LocalDateTime getFecha() {
//		return fecha;
//	}
//
//	public void setFecha(LocalDateTime fecha) {
//		this.fecha = fecha;
//	}
//	
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	@Override
//	public String toString() {
//		return "Message [text=" + text + ", fecha=" + fecha + "]";
//	}
//	
//	
//}

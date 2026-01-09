package com.example.models;

public class ChatMessage {
	private String type;	// "JOIN", "LEAVE", "CHAT"
	private String roomId;
	private String sender;
	private String content;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ChatMessage [type=" + type + ", roomId=" + roomId + ", sender=" + sender + ", content=" + content + "]";
	}
	
	
	
	
}

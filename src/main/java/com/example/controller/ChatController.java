package com.example.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.models.ChatMessage;

@Controller
public class ChatController {
//====================== VERSION 2
	private final SimpMessagingTemplate messagingTemplate;
	public ChatController(SimpMessagingTemplate messagingTemplate) { 
		this.messagingTemplate = messagingTemplate; 
	}
	
	@MessageMapping("/chat.sendMessage") 
	public void sendMessage(@Payload ChatMessage message) 
	{ // broadcast to the room topic 
		System.out.println("Send Message: " + message.toString());
		messagingTemplate.convertAndSend("/broker/" + message.getRoomId(), message); 
	} 
	
	@MessageMapping("/chat.addUser") 
	public void addUser(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor) { 
		System.out.println("Add User: " + message.toString());
		headerAccessor.getSessionAttributes().put("username", message.getSender()); 
		headerAccessor.getSessionAttributes().put("roomId", message.getRoomId()); 
		message.setType("JOIN"); 
		// broadcast join event to the room 
		messagingTemplate.convertAndSend("/broker/" + message.getRoomId(), message); 
	}
//====================== VERSION 1
//	@MessageMapping("/chat.sendMessage")
////	@SendTo("/broker/public")
//	@SendTo("/broker/{roomid}")
//	public ChatMessage sendMessage(@Payload ChatMessage message) {
//		return message;
//	}
//	
////	@MessageMapping("/chat.addUser") 
////	@SendTo("/broker/public")
////	public ChatMessage addUser(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor){
////		headerAccessor.getSessionAttributes().put("username", message.getSender());
////		return message;
////	}
//	
//	
//	
//	@MessageMapping("/chat.addUser") 
//	@SendTo("/broker/{roomid}")
//	public ChatMessage addUser(@Payload ChatMessage message, SimpMessageHeaderAccessor headerAccessor){
//		headerAccessor.getSessionAttributes().put("username", message.getSender());
//		headerAccessor.getSessionAttributes().put("roomId", message.getRoomId());
//		message.setType("JOIN");
//		return message;
//	}

}

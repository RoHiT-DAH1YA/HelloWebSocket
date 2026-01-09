package com.example.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.models.ChatMessage;

@Component
public class WebSocketEventListener {
	private final SimpMessagingTemplate messagingTemplate;
	
	public WebSocketEventListener(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		String username = (String) event.getMessage().getHeaders() 
										.get("simpSessionAttributes", java.util.Map.class) 
										.get("username"); 
		String roomId = (String) event.getMessage().getHeaders() 
									.get("simpSessionAttributes", java.util.Map.class) 
									.get("roomId");
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setType("LEAVE");
		chatMessage.setSender(username);
		chatMessage.setRoomId(roomId);
		messagingTemplate.convertAndSend("/broker/" + roomId, chatMessage);
	}
}

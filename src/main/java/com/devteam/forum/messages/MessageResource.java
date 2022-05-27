package com.devteam.forum.messages;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

@Path("messages")
public class MessageResource {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message createMessage(Message m) {
		return messageRepository.save(m);
	}

}

package com.devteam.forum.messages;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getAllCategories(){
		List<Message> messages = new ArrayList<Message>();
		messageRepository.findAll().forEach(messages::add);
		return messages;
	}

}

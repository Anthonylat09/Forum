package com.devteam.forum.messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	
	
	@GET
	@Path("{idMessage}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProprietairePseudo(@PathParam ("idMessage") Long id) {
		Optional<Message> mOpt = messageRepository.findById(id);
		
		if (mOpt.isPresent()) {
			Message message = mOpt.get();
			String proprio = message.getProprietaire().getPseudo();
			return proprio;
		}
		
		return "";
	}
	
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMessage(@PathParam("id") Long id) {
		if (messageRepository.findById(id).isPresent()) {
			messageRepository.deleteById(id);
		}
		return Response.noContent().build();
	}

}

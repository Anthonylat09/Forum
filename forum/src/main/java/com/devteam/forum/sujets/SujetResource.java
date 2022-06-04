package com.devteam.forum.sujets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.devteam.forum.categories.Categorie;
import com.devteam.forum.messages.Message;
import com.devteam.forum.messages.MessageRepository;


@Path("sujets")
public class SujetResource {
	
	@Autowired
	private SujetRepository sujetRepository;
	
	@Autowired
	private MessageRepository messageRepository;

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Sujet createSujet(Sujet s) {
		return sujetRepository.save(s);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sujet> getAllCategories(){
		List<Sujet> sujets = new ArrayList<Sujet>();
		sujetRepository.findAll().forEach(sujets::add);
		return sujets;
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSujetById(@PathParam("id") Long id) {
		Optional<Sujet> s = sujetRepository.findById(id);
		if (s.isPresent()) {
			return Response.ok(s.get()).build()
;		}
		else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("{id}/messages")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> listerMessages(@PathParam("id") Long id) {
		List<Message> sujetsList =  sujetRepository.findById(id).get().getMessages();
		if (sujetsList.size() == 1 || sujetsList.size() == 0) return sujetsList;

		List<Message> sujets = new ArrayList<Message>();
		sujets.add(sujetsList.get(0));
		

		for (int i = 1; i < sujetsList.size(); i++) {
			if (!sujetsList.get(i).getId().equals(sujetsList.get(i-1).getId())) {
				sujets.add(sujetsList.get(i));
			}
		}
		return sujets;

	}
	
	@POST
	@Path("{idSujet}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(@PathParam("idSujet") Long id, Message message) {
		Optional<Sujet> sOpt = sujetRepository.findById(id);

		if(sOpt.isPresent()) {
			Sujet s = sOpt.get();
			message.setSujet(s);
			s.addMessage(message);
			sujetRepository.save(s);
			return Response.ok(s).build();
		}
		else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}
	

	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteSujet(@PathParam("id") Long id) {
		if (sujetRepository.findById(id).isPresent()) {
			sujetRepository.deleteById(id);
		}
		return Response.noContent().build();
	}
	


}

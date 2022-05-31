package com.devteam.forum.sujets;

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

import com.devteam.forum.messages.Message;


@Path("sujets")
public class SujetResource {
	
	@Autowired
	private SujetRepository sujetRepository;
	
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
		return (List<Message>) sujetRepository.findById(id).get().getMessages();
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

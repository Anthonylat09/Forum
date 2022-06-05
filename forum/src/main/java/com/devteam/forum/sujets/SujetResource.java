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
import com.devteam.forum.personnes.Personne;
import com.devteam.forum.personnes.PersonneRepository;


@Path("sujets")
public class SujetResource {
	
	@Autowired
	private SujetRepository sujetRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private PersonneRepository personneRepository;

	
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
		List<Message> messagesList =  sujetRepository.findById(id).get().getMessages();
		if (messagesList.size() == 1 || messagesList.size() == 0) return messagesList;

		List<Message> messages = new ArrayList<Message>();
		messages.add(messagesList.get(0));
		

		for (int i = 1; i < messagesList.size(); i++) {
			if (!messagesList.get(i).getId().equals(messagesList.get(i-1).getId())) {
				messages.add(messagesList.get(i));

			}
			

		}
		return messages;

	}
	
	@POST
	@Path("{idSujet}/personnes/{idProprietaire}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(@PathParam("idSujet") Long idSujet,@PathParam("idProprietaire") Long idProprietaire, Message message) {
		Optional<Sujet> sOpt = sujetRepository.findById(idSujet);
		
		Optional<Personne> pOpt = personneRepository.findById(idProprietaire);


		if(sOpt.isPresent() && pOpt.isPresent()) {
			Sujet s = sOpt.get();
			Personne p = pOpt.get();
			message.setSujet(s);
			message.setProprietaire(p);
			message.setProprietairePseudo(p.getPseudo());
			s.addMessage(message);
			p.addMessage(message);
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

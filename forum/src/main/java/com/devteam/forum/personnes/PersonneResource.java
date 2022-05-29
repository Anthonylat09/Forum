package com.devteam.forum.personnes;


import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.CrossOrigin;


@Path("personnes")
@CrossOrigin
public class PersonneResource {
	
	@Autowired
	private PersonneRepository personneRepository;
	
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Personne createPersonne(Personne p) {
		return personneRepository.save(p);
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Personne> getAllPersonnes(){
		List<Personne> personnes = new ArrayList<>();
		personneRepository.findAll().forEach(personnes::add);
		return personnes;
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePersonne(@PathParam("id") Long id) {
		if (personneRepository.findById(id).isPresent()) {
			personneRepository.deleteById(id);
		}
		return Response.noContent().build();
	}
	@POST
	@Path("connect")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean connect(@PathParam("pseudo")String pseudo, @PathParam("motDePasse") String motDePasse)
	{
		return !getAllPersonnes().stream().filter(p -> p.getPseudo().equals("issa") ).toList().isEmpty();


	}
	
	
}

package com.devteam.forum.personnes;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getpersonneById(@PathParam("id") Long id) {
		Optional<Personne> p = personneRepository.findById(id);
		if (p.isPresent()) {
			return Response.ok(p.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
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
	public Personne connect(Personne p)
	{
		List<Personne> per = getAllPersonnes().stream().filter(personne -> personne.getPseudo().equals(p.getPseudo()) && personne.getMotDePasse().equals(p.getMotDePasse()) ).collect(Collectors.toUnmodifiableList());
		boolean existe = !per.isEmpty();
		if (existe){
			return per.get(0);
		}
		else
		{
			Personne pers = new Personne();
			pers.setId((long) 0);
			return pers;
		}

	}
	
	@POST
	@Path("verif")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean verifierPseudoEtEmail(Personne p)
	{
		boolean existeDeja = false;
		List<Personne> liste = getAllPersonnes();
		for (Personne personne: liste) {
			if (personne.getPseudo().equals(p.getPseudo()) || personne.getEmail().equals(p.getEmail())) existeDeja= true; 
		}
		return existeDeja;
	}


	
	
}

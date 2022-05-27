package com.devteam.forum.sujets;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

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

}

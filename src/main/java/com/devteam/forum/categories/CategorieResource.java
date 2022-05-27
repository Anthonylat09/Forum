package com.devteam.forum.categories;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;


@Path("categories")
public class CategorieResource {
	
	@Autowired
	private CategorieRepository categorieRepository;

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categorie createCategorie(Categorie c) {
		return categorieRepository.save(c);
	}
}
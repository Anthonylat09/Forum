package com.devteam.forum.categories;

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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categorie> getAllCategories(){
		List<Categorie> categories = new ArrayList<Categorie>();
		categorieRepository.findAll().forEach(categories::add);
		return categories;
	}
	
	@DELETE
	@PathParam("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCategorie(@PathParam("id") Long id) {
		if (categorieRepository.findById(id).isPresent()) {
			categorieRepository.deleteById(id);
		}
		return Response.noContent().build();
	}
}

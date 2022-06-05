package com.devteam.forum.categories;

import java.io.Console;
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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.devteam.forum.messages.Message;
import com.devteam.forum.personnes.Personne;
import com.devteam.forum.personnes.PersonneRepository;
import com.devteam.forum.sujets.Sujet;
import com.devteam.forum.sujets.SujetRepository;


@Path("categories")
public class CategorieResource {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private SujetRepository sujetRepository;
	
	@Autowired
	private PersonneRepository personneRepository;

	
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
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCategorie(@PathParam("id") Long id) {
		if (categorieRepository.findById(id).isPresent()) {
			categorieRepository.deleteById(id);
		}
		return Response.noContent().build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategorieById(@PathParam("id") Long id) {
		Optional<Categorie> c = categorieRepository.findById(id);
		if (c.isPresent()) {
			return Response.ok(c.get()).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	
	@GET
	@Path("{id}/sujets")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sujet> listerSujets(@PathParam("id") Long id) {
		List<Sujet> sujetsList =  categorieRepository.findById(id).get().getSujets();
		if (sujetsList.size() == 1 || sujetsList.size() == 0) return sujetsList;

		List<Sujet> sujets = new ArrayList<Sujet>();
		sujets.add(sujetsList.get(0));
		for (int i = 1; i < sujetsList.size(); i++) {
			if (!sujetsList.get(i).getId().equals(sujetsList.get(i-1).getId())) {
				sujets.add(sujetsList.get(i));
			}
		}
		return sujets;
	}
	
	
	
	@POST
	@Path("{idCategorie}/personnes/{idProprietaire}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSujet(@PathParam("idCategorie") Long idCategorie,@PathParam("idProprietaire") Long idProprietaire, Sujet sujet) {
		Optional<Categorie> cOpt = categorieRepository.findById(idCategorie);
		
		Optional<Personne> pOpt = personneRepository.findById(idProprietaire);
		

		if(cOpt.isPresent() && pOpt.isPresent()) {
			Categorie c = cOpt.get();
			Personne p = pOpt.get();
			sujet.setCategorie(c);
			sujet.setProprietaire(p);
			c.addSujet(sujet);
			p.addSujet(sujet);
			categorieRepository.save(c);
			return Response.ok(c).build();
		}
		else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		
	}
	
	@PUT
	@Path("{idCategorie}/sujets/{idSujet}")
	@Produces(MediaType.APPLICATION_JSON)
	public Categorie assignerSujet (@PathParam("idCategorie")Long idCategorie, @PathParam("idSujet")Long idSujet) {
		Sujet sujet = sujetRepository.findById(idSujet).get();
		Categorie categorie = categorieRepository.findById(idCategorie).get();
		sujet.setCategorie(categorie);
		categorie.addSujet(sujet);
		return categorieRepository.save(categorie);
	}
}

package com.devteam.forum.categories;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.devteam.forum.sujets.Sujet;

@Entity
public class Categorie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nom;
	
	private String description;
	
	@OneToMany(mappedBy = "categorie")
	private Collection<Sujet> sujets;
	
	

	public Categorie() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Sujet> getSujets() {
		return sujets;
	}

	public void setSujets(Collection<Sujet> sujets) {
		this.sujets = sujets;
	}

	

}

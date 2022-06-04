package com.devteam.forum.categories;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.devteam.forum.sujets.Sujet;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Categorie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5830920388677169510L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nom;
	
	private String description;
	
	@OneToMany(mappedBy = "categorie",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Sujet> sujets ;
	
	

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

	public List<Sujet> getSujets() {
		return (List<Sujet>) sujets;
	}

	public void setSujets(List<Sujet> sujets) {
		this.sujets = sujets;
	}
	
	public void addSujet(Sujet sujet) {
		this.sujets.add(sujet);
	}

	

}

package com.devteam.forum.messages;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.devteam.forum.personnes.Personne;
import com.devteam.forum.sujets.Sujet;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7157382781960681026L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String contenu;
	
	@JsonIgnore
	@ManyToOne
	private Sujet sujet;
	
	@JsonIgnore
	@ManyToOne
	private Personne proprietaire;
	
	private String proprietairePseudo;
	
	

	public Message() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Sujet getSujet() {
		return sujet;
	}

	public void setSujet(Sujet sujet) {
		this.sujet = sujet;
	}

	public Personne getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Personne proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getProprietairePseudo() {
		return proprietairePseudo;
	}

	public void setProprietairePseudo(String proprietairePseudo) {
		this.proprietairePseudo = proprietairePseudo;
	}
	
	
	
}

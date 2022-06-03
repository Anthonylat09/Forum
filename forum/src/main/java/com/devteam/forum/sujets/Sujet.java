package com.devteam.forum.sujets;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.devteam.forum.categories.Categorie;
import com.devteam.forum.messages.Message;
import com.devteam.forum.personnes.Personne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Sujet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5319324685932635618L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titre;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "sujet")
	private List<Message> messages;
	
	@ManyToOne
	private Personne proprietaire;
	
	@JsonIgnore
	@ManyToOne
	private Categorie categorie;
	
	

	public Sujet() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}


	public Personne getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Personne proprietaire) {
		this.proprietaire = proprietaire;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	
}

package com.devteam.forum.sujets;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.devteam.forum.messages.Message;
import com.devteam.forum.personnes.Personne;

@Entity
public class Sujet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titre;
	
	@OneToMany(mappedBy = "sujet")
	private Collection<Message> messages;
	
	@ManyToOne
	private Personne proprietaire;

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

	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}
	
	
	
	
}

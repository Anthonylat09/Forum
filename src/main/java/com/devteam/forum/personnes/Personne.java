package com.devteam.forum.personnes;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.devteam.forum.messages.Message;
import com.devteam.forum.sujets.Sujet;

@Entity
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String pseudo;
	
	private String email;
	
	private String motDePasse;
	
	/*
	@OneToMany(mappedBy = "proprietaire")
	private Collection<Sujet> sujets;
	
	@OneToMany(targetEntity = Message.class, mappedBy = "proprietaire")
	private Collection<Message> messages;
	*/
	

	public Personne() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
/*
	public Collection<Sujet> getSujets() {
		return sujets;
	}

	public void setSujets(Collection<Sujet> sujets) {
		this.sujets = sujets;
	}

	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}
*/
	
	
}

package com.devteam.forum.personnes;

import org.springframework.data.repository.CrudRepository;

public interface PersonneRepository extends CrudRepository<Personne, Long> {
    Personne findByPseudoAndMotDePasse(String pseudo, String motDePasse);
    Personne findByPseudo(String pseudo);




}

package com.example.demo.services;

import com.example.demo.entities.Utilisateur;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface IUtilisateurService {
    void ajouterUser(Utilisateur utilisateur);
    public void modifierUser(Utilisateur utilisateur);
    public void supprimerUser(Integer id);
    public Utilisateur rechercherUser(Integer id); //rechercher un seul utilisteur
    public List<Utilisateur> listUsers();

}

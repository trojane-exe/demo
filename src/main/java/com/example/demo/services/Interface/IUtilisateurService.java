package com.example.demo.services.Interface;

import com.example.demo.entities.Utilisateur;

import java.util.List;

public interface IUtilisateurService {
    public void ajouterUser(Utilisateur utilisateur);
    public void modifierUser(int id ,Utilisateur utilisateur);
    public void supprimerUser(Integer id);
    public Utilisateur rechercherUser(Integer id); //rechercher un seul utilisteur
    public List<Utilisateur> listUsers();
    public void deleteAll();

}

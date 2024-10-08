package com.example.demo.services.Interface;

import com.example.demo.dto.UtilisateurDTO;
import com.example.demo.entities.Utilisateur;

import java.util.List;

public interface IUtilisateurService {

    public void addUser(Utilisateur utilisateur);
    public String ajouterUser(UtilisateurDTO utilisateur);
    public String modifierUser(int id ,Utilisateur utilisateur);
    public void supprimerUser(Integer id);
    public UtilisateurDTO rechercherUser(Integer id); //rechercher un seul utilisteur
    public List<UtilisateurDTO> listUsers();
    public void deleteAll();
    public List<Utilisateur> getAllUsers();
    public List<Object> getAllId();
    public Integer getUserIdFromEmail(String email);


}

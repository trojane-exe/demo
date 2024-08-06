package com.example.demo.dto;


import com.example.demo.entities.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDTO  {
    private Integer idUser;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;
    private String role;

}

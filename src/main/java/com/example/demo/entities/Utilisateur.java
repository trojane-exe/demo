package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    private String nom;

    private String prenom;
    private String adresse;

    private String email;


    private String password;
    private RoleEnum role;
    @OneToMany(mappedBy = "utilisateur")
    private List<Emprunt> emprunt;
}

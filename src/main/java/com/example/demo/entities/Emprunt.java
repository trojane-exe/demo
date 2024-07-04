package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmp;
    @ManyToOne
    private Utilisateur utilisateur;
    @ManyToOne
    private Document document;
    private Date date_debut;
    private Date date_retour_prevue;
    private Date date_retour;
    private String status;
}

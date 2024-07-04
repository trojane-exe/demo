package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDoc;
    private String titre;

    private String auteur;
    private Date date_ecriture;

    private boolean disponible;
    @OneToMany(mappedBy = "document")
    private List<Emprunt> emprunt;

}

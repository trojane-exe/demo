package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity

public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoc;
    private String titre;

    private String auteur;
    private LocalDate date_ecriture;
    private int stock;
    private Boolean disponible;
    @OneToMany(mappedBy = "document")
    private List<Emprunt> emprunt;

}

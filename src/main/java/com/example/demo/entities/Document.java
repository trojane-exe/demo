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
    @Column(columnDefinition = "int default 0")
    private Integer stock = 0;
    @OneToMany(mappedBy = "document" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Reservation> reservations;

}

package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    @ManyToOne
    private Document document;
    @ManyToOne
    private Utilisateur utilisateur;
    @Column(columnDefinition = "boolean default true")
    private Boolean isActive = true;
    @OneToMany(mappedBy = "reservation" , cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<Emprunt> emprunts;
    private LocalDate date_reservation = LocalDate.now();
}

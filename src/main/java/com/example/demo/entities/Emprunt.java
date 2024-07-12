package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmp;
    @ManyToOne
    private Reservation reservation;
    private LocalDate date_debut = LocalDate.now();
    private LocalDate date_retour_prevue;
    private LocalDate date_retour;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    @OneToOne
    @Transient
    private Transaction transaction;
}

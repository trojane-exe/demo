package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaction;
    @ManyToOne
    private Utilisateur utilisateur;
    @OneToOne
    private Emprunt emprunt;
    private LocalDate date_transaction;
}

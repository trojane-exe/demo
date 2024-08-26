package com.example.demo.dto;

import com.example.demo.entities.Emprunt;
import com.example.demo.entities.Utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {


    private Integer idTransaction;


    private Integer idUser;

    private Integer idEmp;
    private LocalDate date_transaction;
}

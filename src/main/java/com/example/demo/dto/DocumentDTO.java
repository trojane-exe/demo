package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private Integer idDoc;
    private String titre;
    private String auteur;
    private LocalDate date_ecriture;
    private Integer stock;
}

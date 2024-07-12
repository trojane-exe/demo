package com.example.demo.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpruntDTO {
    private Integer idEmp;
    private Integer idRes;
    private LocalDate date_debut = LocalDate.now();
    private LocalDate date_retour_prevue;
    private LocalDate date_retour;
    private String status;

}

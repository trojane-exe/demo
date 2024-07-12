package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Integer idReservation;
    private Integer idUser;
    private Integer idDoc;
    private Boolean isActive = true;
    private LocalDate date_reservation = LocalDate.now();

    /* l'utilisation du dto facilite le transfert de données entre les couches du projets, par exemple pour une reservation
    on est conçu a inserer un id d'utilisateur et du document cela sera un peux compliqué a atteindre puisque un objet user contient tous
    les autre champs qui ne sont pas necessaire , un dto qui contient uniquement les champs necessaires a inserer
     comme le id user , id document , id reservation et la date */
}

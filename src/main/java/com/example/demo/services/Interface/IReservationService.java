package com.example.demo.services.Interface;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.entities.Reservation;

import java.util.List;

public interface IReservationService {
    public void ajouterReservation(ReservationDTO reservation);
    public void modifierReservation(int it , ReservationDTO reservation);
    public String supprimerReservation(int id);
    public ReservationDTO rechercherReservation(int id);
    public List<ReservationDTO> listerReservation();
    public void deleteall();

}

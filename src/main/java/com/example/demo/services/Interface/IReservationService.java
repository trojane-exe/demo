package com.example.demo.services.Interface;

import com.example.demo.dto.ReservationDTO;

import java.util.List;

public interface IReservationService {
    public String ajouterReservation(ReservationDTO reservation);
    public String modifierReservation(int it , ReservationDTO reservation);
    public String supprimerReservation(int id);
    public String annulerReservation(int id);
    public ReservationDTO rechercherReservation(int id);
    public List<ReservationDTO> listerReservation();
    public void deleteall();

}

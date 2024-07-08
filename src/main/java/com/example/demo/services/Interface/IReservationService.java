package com.example.demo.services.Interface;

import com.example.demo.entities.Reservation;

import java.util.List;

public interface IReservationService {
    public void ajouterReservation(Reservation reservation);
    public void modifierReservation(int it , Reservation reservation);
    public void supprimerReservation(int id);
    public Reservation rechercherReservation(int id);
    public List<Reservation> listerReservation();
    public void deleteall();

}

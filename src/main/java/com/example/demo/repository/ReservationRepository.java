package com.example.demo.repository;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    @Query("select r from Reservation r where r.utilisateur.idUser = :idUser")
    List<Reservation> findByUserId(@Param("idUser") Integer idUser);
}

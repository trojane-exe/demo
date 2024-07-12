package com.example.demo.repository;

import com.example.demo.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    //List<Reservation>findByUserID(Integer id);
}

package com.example.demo.repository;

import com.example.demo.dto.EmpruntDTO;
import com.example.demo.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt,Integer> {
//    @Query("select e.idEmp,e.date_debut,e.date_retour,e.date_retour_prevue,e.reservation,e.status from Emprunt e")
//    List<Emprunt> allEmprunts();
}

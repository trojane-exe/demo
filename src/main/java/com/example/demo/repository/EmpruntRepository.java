package com.example.demo.repository;

import com.example.demo.dto.EmpruntDTO;
import com.example.demo.entities.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt,Integer> {
//    @Query("select e.idEmp,e.date_debut,e.date_retour,e.date_retour_prevue,e.reservation,e.status from Emprunt e")
//    List<Emprunt> allEmprunts();


    @Query("SELECT e FROM Emprunt e INNER JOIN e.reservation r WHERE r.utilisateur.idUser = :userId")
    List<Emprunt> findEmpruntsByUserId(@Param("userId") Integer userId);


}

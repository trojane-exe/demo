package com.example.demo.repository;

import com.example.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    @Query("select u.idUser,u.nom,u.prenom from Utilisateur u")
    List<Object[]> getAllId();
}

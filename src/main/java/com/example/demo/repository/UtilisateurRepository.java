package com.example.demo.repository;

import com.example.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    @Query("select u.idUser,u.nom,u.prenom from Utilisateur u")
    List<Object> getAllId();

    @Query("select u.idUser from Utilisateur u where u.email= :email ")
    Optional<Integer> getIdByEmail(@Param("email") String email);

    Optional<Utilisateur> findByEmail(String email);


}

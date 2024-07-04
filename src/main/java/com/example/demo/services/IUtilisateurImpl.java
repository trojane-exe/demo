package com.example.demo.services;

import com.example.demo.entities.Utilisateur;
import com.example.demo.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class IUtilisateurImpl {
    private final UtilisateurRepository ur;
    @Autowired
    public  IUtilisateurImpl(UtilisateurRepository userRep){
        this.ur = userRep;
    }



    public void ajouterUser(Utilisateur utilisateur){
        ur.save(utilisateur);
    }
}

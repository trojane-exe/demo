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

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class IUtilisateurImpl implements IUtilisateurService{
    private final UtilisateurRepository ur;
    @Autowired
    public  IUtilisateurImpl(UtilisateurRepository userRep){
        this.ur = userRep;
    }


    @Override
    public void ajouterUser(Utilisateur utilisateur){
        ur.save(utilisateur);
    }

    @Override
    public void modifierUser(Utilisateur utilisateur) {

        ur.save(utilisateur);

    }

    @Override
    public void supprimerUser(Integer id) {

        ur.deleteById(id);
    }

    @Override
    public Utilisateur rechercherUser(Integer id) {
        Optional<Utilisateur> userInfo = ur.findById(id);
        if (userInfo.isPresent()) {
            return userInfo.get();
        }
        return null;
    }

    @Override
    public List<Utilisateur> listUsers() {
        return ur.findAll();
    }
}

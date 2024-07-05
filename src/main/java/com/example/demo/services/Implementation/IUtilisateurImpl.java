package com.example.demo.services.Implementation;

import com.example.demo.entities.Utilisateur;
import com.example.demo.repository.UtilisateurRepository;
import com.example.demo.services.Interface.IUtilisateurService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class IUtilisateurImpl implements IUtilisateurService {
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
    public void modifierUser(int id ,Utilisateur utilisateur) {
        Utilisateur existant = ur.findById(id).orElseThrow(()->new EntityNotFoundException("user not found"));
        existant.setNom(utilisateur.getNom());
        existant.setPrenom(utilisateur.getPrenom());
        existant.setAdresse(utilisateur.getAdresse());
        existant.setEmail(utilisateur.getEmail());
        existant.setPassword(utilisateur.getPassword());
        existant.setRole(utilisateur.getRole());

        ur.save(existant);

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

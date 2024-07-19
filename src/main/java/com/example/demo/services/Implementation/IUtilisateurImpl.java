package com.example.demo.services.Implementation;

import com.example.demo.entities.RoleEnum;
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
    public void deleteAll(){
        ur.deleteAll();
    }

    @Override
    public void ajouterUser(Utilisateur utilisateur){
        if(utilisateur.getRole()==null){
            utilisateur.setRole(RoleEnum.User);
        }
        else{
            utilisateur.setRole(utilisateur.getRole());
        }
        ur.save(utilisateur);
    }

    @Override
    public void modifierUser(int id ,Utilisateur utilisateur) {
        Utilisateur existant = ur.findById(id).orElseThrow(()->new EntityNotFoundException("user not found"));
        if(existant != null) {
            if (utilisateur.getNom() != null) {
                existant.setNom(utilisateur.getNom());
            }
            if (utilisateur.getPrenom() != null) {
                existant.setPrenom(utilisateur.getPrenom());
            }
            if (utilisateur.getAdresse() != null) {
                existant.setAdresse(utilisateur.getAdresse());
            }
            if (utilisateur.getEmail() != null) {
                existant.setEmail(utilisateur.getEmail());
            }
            if (utilisateur.getPassword() != null) {
                existant.setPassword(utilisateur.getPassword());
            }
            if (utilisateur.getRole() != null) {
                existant.setRole(utilisateur.getRole());
            }
            ur.save(existant);
        }

    }

    @Override
    public void supprimerUser(Integer id) {

        ur.deleteById(id);
    }

    @Override
    public Utilisateur rechercherUser(Integer id) {
        Optional<Utilisateur> userInfo = ur.findById(id);
        return userInfo.orElse(null);
    }

    @Override
    public List<Utilisateur> listUsers() {
        return ur.findAll();
    }
}

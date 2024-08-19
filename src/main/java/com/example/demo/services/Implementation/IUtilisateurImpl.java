package com.example.demo.services.Implementation;

import com.example.demo.JwtConfig.JwtService;
import com.example.demo.dto.UtilisateurDTO;
import com.example.demo.entities.RoleEnum;
import com.example.demo.entities.Utilisateur;
import com.example.demo.repository.UtilisateurRepository;
import com.example.demo.services.Interface.IUtilisateurService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class IUtilisateurImpl implements IUtilisateurService {
    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository ur;
    private final JwtService jwtService;
    @Autowired
    public  IUtilisateurImpl(PasswordEncoder passwordEncoder, UtilisateurRepository userRep, JwtService jwtService){
        this.passwordEncoder = passwordEncoder;
        this.ur = userRep;
        this.jwtService = jwtService;
    }







    public Utilisateur toEntity(UtilisateurDTO dto){
        Utilisateur user  =  new Utilisateur();
        user.setIdUser(dto.getIdUser());
        user.setPrenom(dto.getPrenom());
        user.setNom(dto.getNom());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(RoleEnum.valueOf(dto.getRole()));
        user.setAdresse(dto.getAdresse());
        return user;
    }








    public UtilisateurDTO toDto (Utilisateur utilisateur){
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setIdUser(utilisateur.getIdUser()!= null ? utilisateur.getIdUser() : null);
        dto.setNom(utilisateur.getNom()!= null ? utilisateur.getNom() : "UNDEFINED");
        dto.setPrenom(utilisateur.getPrenom()!= null ? utilisateur.getPrenom() : "UNDEFINED");
        dto.setAdresse(utilisateur.getAdresse()!= null ? utilisateur.getAdresse() : "UNDEFINED");
        dto.setEmail(utilisateur.getEmail()!= null ? utilisateur.getEmail() : "UNDEFINED");
        dto.setPassword(utilisateur.getPassword() != null ? utilisateur.getPassword() : "UNDEFINED");
        dto.setRole(utilisateur.getRole() != null ? utilisateur.getRole().toString(): "UNKNOWN");
        return dto;
    }

    @Override
    public void deleteAll(){
        ur.deleteAll();
    }


    @Override
    public void addUser(Utilisateur utilisateur){
        if(utilisateur.getRole()==null){
            utilisateur.setRole(RoleEnum.User);
            ur.save(utilisateur);
        }
        else{
            ur.save(utilisateur);
        }
    }

    @Override
    public String ajouterUser(UtilisateurDTO userdto){
        if(userdto.getRole()==null||userdto.getRole().describeConstable().isEmpty()){
            userdto.setRole(RoleEnum.User.toString());
        }
        else{
            userdto.setRole(userdto.getRole());
        }
        Utilisateur utilisateur = toEntity(userdto);
        ur.save(utilisateur);
        return jwtService.generateToken(null, new org.springframework.security.core.userdetails.User(
                utilisateur.getEmail(),
                utilisateur.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name()))
        ));
    }

    @Override
    public String modifierUser(int id ,Utilisateur utilisateur) {
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
                existant.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            }
            if (utilisateur.getRole() != null) {
                existant.setRole(utilisateur.getRole());
            }
            ur.save(existant);
            return jwtService.generateToken(null, new org.springframework.security.core.userdetails.User(
                    utilisateur.getEmail(),
                    utilisateur.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name()))
            ));
        }
        return null;

    }

    @Override
    public void supprimerUser(Integer id) {

        ur.deleteById(id);
    }

    @Override
    public UtilisateurDTO rechercherUser(Integer id) {
        Optional<Utilisateur> userInfo = ur.findById(id);
        Utilisateur utilisateur = userInfo.get();

        return toDto(utilisateur);
    }

    @Override
    public List<UtilisateurDTO> listUsers() {
        List<Utilisateur> users = ur.findAll();
        List<UtilisateurDTO> dto = new ArrayList<>();
        for (Utilisateur user : users){
            UtilisateurDTO dtos = toDto(user);
            dto.add(dtos);
        }
        return dto;
    }

    @Override
    public List<Utilisateur> getAllUsers(){
        return ur.findAll();
    }

    public List<Object> getAllId(){
        return ur.getAllId();
    }
}


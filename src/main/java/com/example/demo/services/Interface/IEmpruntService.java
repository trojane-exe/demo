package com.example.demo.services.Interface;

import com.example.demo.dto.EmpruntDTO;

import java.util.List;

public interface IEmpruntService {
    public String ajouterEmprunt(EmpruntDTO empruntDTO);
    public String modifierEmpruntUser(int id , EmpruntDTO empruntDTO);
    public String modifierEmpruntAdmin(int id );
    public String supprimerEmprunt(int id);
    public EmpruntDTO rechercherEmprunt(int id);
    public List<EmpruntDTO> listerEmprunt();
    //public List<Emprunt> allEmprunts();
    public void retard();
    public String annulerEmprunt(EmpruntDTO dto);

    public List<EmpruntDTO> findEmpruntsByUserId(Integer id );
}

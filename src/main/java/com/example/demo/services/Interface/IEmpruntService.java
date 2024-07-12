package com.example.demo.services.Interface;

import com.example.demo.dto.EmpruntDTO;

import java.util.List;

public interface IEmpruntService {
    public String ajouterEmprunt(EmpruntDTO empruntDTO);
    public String modifierEmprunt(int id , EmpruntDTO empruntDTO);
    public String supprimerEmprunt(int id);
    public EmpruntDTO rechercherEmprunt(int id);
    public List<EmpruntDTO> listerEmprunt();
}

package com.example.demo.services.Implementation;

import com.example.demo.dto.EmpruntDTO;
import com.example.demo.entities.*;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.EmpruntRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.services.Interface.IEmpruntService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@Data

public class IEmpruntImpl implements IEmpruntService {
    @Autowired
    private final ReservationRepository rr;
    @Autowired
    private final EmpruntRepository er;
    @Autowired
    private final TransactionRepository tr;
    @Autowired
    private final DocumentRepository dr;

    //convertir le dto en entity
    public Emprunt toEntity(EmpruntDTO dto){
        Emprunt emprunt = new Emprunt();
        Reservation reservation = rr.findById(dto.getIdRes()).orElse(null);
        emprunt.setReservation(reservation);
        emprunt.setDate_debut(dto.getDate_debut());
        emprunt.setDate_retour_prevue(dto.getDate_retour_prevue());
        emprunt.setStatus(StatusEnum.valueOf(dto.getStatus()));
        emprunt.setDate_retour(dto.getDate_retour());
        return emprunt;
    }

    @Override
    public String ajouterEmprunt(EmpruntDTO empruntDTO) {
        Reservation reservation = rr.findById(empruntDTO.getIdRes()).orElse(null);
        if(reservation != null && reservation.getIsActive()){
            Emprunt emprunt =toEntity(empruntDTO);
            if(emprunt.getStatus()==StatusEnum.annulé){
                Document document = dr.findById(reservation.getDocument().getIdDoc()).orElse(null);
                document.setStock(document.getStock()+1);
                reservation.setIsActive(false);
                emprunt.setDate_retour(null);
                emprunt.setDate_retour_prevue(null);
                er.save(emprunt);
                Transaction transaction = new Transaction();
                transaction.setEmprunt(emprunt);
                transaction.setUtilisateur(reservation.getUtilisateur());
                transaction.setDate_transaction(emprunt.getDate_debut());
                tr.save(transaction);
                return null;
            }
            else {
                er.save(emprunt);
                reservation.setIsActive(false);
                Transaction transaction = new Transaction();
                transaction.setEmprunt(emprunt);
                transaction.setUtilisateur(reservation.getUtilisateur());
                transaction.setDate_transaction(emprunt.getDate_debut());
                tr.save(transaction);
                return null;
            }
        }
        else{
            return "error occurred , this reservation is not active it might be already used or cancelled";
        }

    }

    @Override
    public String modifierEmprunt(int id, EmpruntDTO empruntDTO) {
        return "";
    }

    @Override
    public String supprimerEmprunt(int id) {
        return "";
    }

    @Override
    public EmpruntDTO rechercherEmprunt(int id) {
        return null;
    }

    @Override
    public List<EmpruntDTO> listerEmprunt() {
        return List.of();
    }
}

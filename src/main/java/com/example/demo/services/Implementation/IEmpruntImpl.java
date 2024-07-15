package com.example.demo.services.Implementation;

import com.example.demo.dto.EmpruntDTO;
import com.example.demo.entities.*;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.EmpruntRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.services.Interface.IEmpruntService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        if(reservation!=null) {
            emprunt.setReservation(reservation);
        }
        if(dto.getDate_debut()!=null) {
            emprunt.setDate_debut(dto.getDate_debut());
        }
        if(dto.getDate_retour_prevue()!=null) {
            emprunt.setDate_retour_prevue(dto.getDate_retour_prevue());
        }
        if(dto.getStatus()!=null) {
            emprunt.setStatus(StatusEnum.valueOf(dto.getStatus()));
        }
        if(dto.getDate_retour()!=null) {
            emprunt.setDate_retour(dto.getDate_retour());
        }
        return emprunt;
    }


    @Override
    public String ajouterEmprunt(EmpruntDTO empruntDTO){
        Reservation reservation = rr.findById(empruntDTO.getIdRes()).orElse(null);

        if (reservation!= null && reservation.getIsActive()) {
            Emprunt emprunt = toEntity(empruntDTO);

            emprunt.setStatus(StatusEnum.approuvé);
            if (emprunt.getDate_debut() != null) {
                if(empruntDTO.getDate_debut().isBefore(LocalDate.now())){
                    return "invalid start date";
                }
                else {
                    emprunt.setDate_debut(empruntDTO.getDate_debut());
                }
            }
            else {
                emprunt.setDate_debut(LocalDate.now());
            }
            if (empruntDTO.getDate_retour_prevue() == null) {
                return "the return date is required";
            }
            else if (empruntDTO.getDate_debut().isAfter(empruntDTO.getDate_retour_prevue())) {
                return "the date de debut cant be after the return date";
            }

            else {
                emprunt.setDate_retour_prevue(empruntDTO.getDate_retour_prevue());
                emprunt.setDate_retour(null);
                emprunt.setReservation(reservation);
                er.save(emprunt);
                reservation.setIsActive(false);
                Transaction transaction = new Transaction();
                transaction.setEmprunt(emprunt);
                transaction.setUtilisateur(reservation.getUtilisateur());
                transaction.setDate_transaction(emprunt.getDate_debut());
                tr.save(transaction);
                return "emprunt added successfully";
            }
        }
        else{
            return "this reservation is already used";
        }

    }

//    @Override
//    public String ajouterEmprunt(EmpruntDTO empruntDTO) {
//        Reservation reservation = rr.findById(empruntDTO.getIdRes()).orElse(null);
//        if(reservation != null && reservation.getIsActive()){
//            Emprunt emprunt =toEntity(empruntDTO);
//            if(emprunt.getStatus()==StatusEnum.annulé){
//                Document document = dr.findById(reservation.getDocument().getIdDoc()).orElse(null);
//                document.setStock(document.getStock()+1);
//                reservation.setIsActive(false);
//                if(empruntDTO.getDate_debut()!=null){
//                    emprunt.setDate_debut(empruntDTO.getDate_debut());
//                }
//                emprunt.setStatus(StatusEnum.approuvé);
//                emprunt.setDate_retour(null);
//                emprunt.setDate_retour_prevue(empruntDTO.getDate_retour_prevue());
//                er.save(emprunt);
//                Transaction transaction = new Transaction();
//                transaction.setEmprunt(emprunt);
//                transaction.setUtilisateur(reservation.getUtilisateur());
//                transaction.setDate_transaction(emprunt.getDate_debut());
//                tr.save(transaction);
//                return null;
//            }
//            else {
//                er.save(emprunt);
//                reservation.setIsActive(false);
//                Transaction transaction = new Transaction();
//                transaction.setEmprunt(emprunt);
//                transaction.setUtilisateur(reservation.getUtilisateur());
//                transaction.setDate_transaction(emprunt.getDate_debut());
//                tr.save(transaction);
//                return null;
//            }
//        }
//        else{
//            return "error occurred , this reservation is not active it might be already used or cancelled";
//        }
//
//    }

    @Override
    public String modifierEmprunt(int id, EmpruntDTO empruntDTO) {
        //pour l'utilisateur , il peut modifer uniquement la date de debut et date de retour prevue de l'emprunt
        Emprunt oldEmprunt = er.findById(id).orElse(null);
        if(oldEmprunt != null ) {
            if (oldEmprunt.getStatus() == StatusEnum.annulé || oldEmprunt.getStatus() == StatusEnum.retourné) {
                return "this emprunt is canceled or returned";
            } else {
                if (empruntDTO.getDate_debut().isAfter(empruntDTO.getDate_retour_prevue())) {
                    return "date error: date debut is after date de retour prevue";
                } else {
                    if (empruntDTO.getDate_retour_prevue() != null) {
                        oldEmprunt.setDate_retour_prevue(empruntDTO.getDate_retour_prevue());
                    }
                    if (empruntDTO.getDate_debut() != null) {
                        oldEmprunt.setDate_debut(empruntDTO.getDate_debut());
                    }
                }
            }

        }
        return null;
    }

    @Override
    public String supprimerEmprunt(int id) {
        Emprunt emprunt = er.findById(id).orElse(null);
        if(emprunt==null){
            return "error : the emrpunt can't be found";
        }
        else {
            er.deleteById(id);
        }
        return null;
    }

    @Override
    public EmpruntDTO rechercherEmprunt(int id) {
        return null;
    }

    @Override
    public List<EmpruntDTO> listerEmprunt() {
        return List.of();
    }

    @Override
    public void retard() {
        List<Emprunt> listofEmprunt = er.findAll();
        for(Emprunt em : listofEmprunt){
            if(em.getDate_retour_prevue()!=null && (LocalDate.now().isAfter(em.getDate_retour_prevue()))){
                em.setStatus(StatusEnum.retardé);
                er.save(em);
            }
        }
    }
    @PostConstruct
    public void onAppLoad(){
        retard();
    }
}

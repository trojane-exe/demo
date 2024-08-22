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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        else{
            emprunt.setDate_debut(LocalDate.now());
        }
        if(dto.getDate_retour_prevue()!=null) {
            emprunt.setDate_retour_prevue(dto.getDate_retour_prevue());
        }
        else{
            emprunt.setDate_retour_prevue(LocalDate.now().plusDays(7));
        }
//        if(dto.getStatus()!=null) {
//            emprunt.setStatus(StatusEnum.valueOf(dto.getStatus()));
//        }
        if(dto.getDate_retour()!=null) {
            emprunt.setDate_retour(dto.getDate_retour());
        }
        else{
            emprunt.setDate_retour(null);
        }
        return emprunt;
    }

    //convertir une Entity en dto :
    public EmpruntDTO toDto(Emprunt emprunt){
        EmpruntDTO dto = new EmpruntDTO();
        if(emprunt.getIdEmp()!=null){
            dto.setIdEmp(emprunt.getIdEmp());
        }
        if(emprunt.getReservation()!=null){
            dto.setIdRes(emprunt.getReservation().getIdReservation());
        }
        if(emprunt.getDate_debut()!=null){
            dto.setDate_debut(emprunt.getDate_debut());
        }
        if(emprunt.getDate_retour_prevue()!=null){
            dto.setDate_retour_prevue(emprunt.getDate_retour_prevue());
        }
        if(emprunt.getDate_retour()!=null){
            dto.setDate_retour(emprunt.getDate_retour());
        }
        if(emprunt.getStatus()!=null){
            dto.setStatus(emprunt.getStatus().toString());
        }
        return dto;
    }


    public Emprunt toEntityUpdate(EmpruntDTO dto){
        Emprunt emprunt = new Emprunt();
        if(dto.getDate_debut()!=null) {
            emprunt.setDate_debut(dto.getDate_debut());
        }
        else{
            emprunt.setDate_debut(LocalDate.now());
        }
        if(dto.getDate_retour_prevue()!=null) {
            emprunt.setDate_retour_prevue(dto.getDate_retour_prevue());
        }
        else{
            emprunt.setDate_retour_prevue(LocalDate.now().plusDays(7));
        }

        if(dto.getDate_retour()!=null) {
            emprunt.setDate_retour(dto.getDate_retour());
        }
        else{
            emprunt.setDate_retour(null);
        }
        return emprunt;
    }


    @Override
    public String ajouterEmprunt(EmpruntDTO empruntDTO) {

        Reservation reservation = rr.findById(empruntDTO.getIdRes()).orElse(null);

        // Check if reservation exists and is active
        if (reservation == null || !reservation.getIsActive()) {
            return "This reservation does not exist or is not active.";
        }

        Emprunt emprunt = toEntity(empruntDTO);
        emprunt.setStatus(StatusEnum.approuvé);

        // Check date_debut
        if (emprunt.getDate_debut() != null) {
            if (emprunt.getDate_debut().isBefore(LocalDate.now())) {
                return "Invalid start date.";
            }
            else {
                emprunt.setDate_debut(emprunt.getDate_debut());
                if (emprunt.getDate_retour_prevue() == null) {
                    emprunt.setDate_retour_prevue(emprunt.getDate_debut().plusDays(7));
                } else if (emprunt.getDate_retour_prevue().isBefore(emprunt.getDate_debut())) {
                    return "Return date cannot be before start date.";
                }
                else{
                    emprunt.setDate_retour_prevue(emprunt.getDate_retour_prevue());
                }
            }
        }
        else {
            emprunt.setDate_debut(LocalDate.now());
            if (emprunt.getDate_retour_prevue() == null) {
                emprunt.setDate_retour_prevue(emprunt.getDate_debut().plusDays(7));
            } else if (emprunt.getDate_retour_prevue().isBefore(emprunt.getDate_debut())) {
                return "Return date cannot be before start date.";
            }
            else{
                emprunt.setDate_retour_prevue(emprunt.getDate_retour_prevue());
            }
        }




        // Save emprunt
        emprunt.setDate_retour(null);
        emprunt.setReservation(reservation);
        er.save(emprunt);

        // Update reservation status
        reservation.setIsActive(false);
        rr.save(reservation);

        // Insert transaction
        Transaction transaction = new Transaction();
        transaction.setEmprunt(emprunt);
        transaction.setUtilisateur(reservation.getUtilisateur());
        transaction.setDate_transaction(emprunt.getDate_debut());
        tr.save(transaction);

        return "Emprunt added successfully.";
    }


    @Override
    public String annulerEmprunt(int id){
        Emprunt emprunt = er.findById(id).orElse(null);
        Reservation reservation = rr.findById(emprunt.getReservation().getIdReservation()).orElse(null);
            emprunt.setDate_debut(LocalDate.now());
            emprunt.setDate_retour_prevue(null);
            emprunt.setDate_retour(LocalDate.now());
            emprunt.setStatus(StatusEnum.annulé);
            er.save(emprunt);
            Document doc = dr.findById(reservation.getDocument().getIdDoc()).orElse(null);
            doc.setStock(doc.getStock()+1);
            dr.save(doc);
            Transaction transaction = new Transaction();
            transaction.setEmprunt(emprunt);
            transaction.setUtilisateur(reservation.getUtilisateur());
            transaction.setDate_transaction(emprunt.getDate_debut());
            tr.save(transaction);
            return "this reservation has been cancelled the transaction is saved ";
        }


    @Override
    public List<EmpruntDTO> findEmpruntsByUserId(Integer id) {

        List<Emprunt> emprunts = er.findEmpruntsByUserId(id);
        List<EmpruntDTO> dto = new ArrayList<>();
        for(Emprunt emprunt:emprunts){
            EmpruntDTO dtos = toDto(emprunt);
            dto.add(dtos);
        }
        return dto;

    }


    @Override
    public String modifierEmpruntUser(int id, EmpruntDTO empruntDTO) {
        //pour l'utilisateur , il peut modifer uniquement la date de debut et date de retour prevue de l'emprunt
        Emprunt oldEmprunt = er.findById(id).orElse(null);
        Emprunt emprunt = toEntityUpdate(empruntDTO);
        if(oldEmprunt != null ) {
            if (emprunt.getDate_debut().isAfter(emprunt.getDate_retour_prevue())) {
                    return "date error: date debut is after date de retour prevue";
                }
//            else if (emprunt.getDate_debut().isBefore(LocalDate.now())) {
//                return "invalid start date";
//            }
            else{
                //oldEmprunt.setDate_debut(emprunt.getDate_debut());
                oldEmprunt.setDate_retour_prevue(emprunt.getDate_retour_prevue());
                er.save(oldEmprunt);
                return "updated successfully";
            }

        }
        return null;
    }

    @Override
    public String confirmerRetour(int id) {
        Emprunt emprunt = er.findById(id).orElse(null);
        emprunt.setDate_retour(LocalDate.now());
        emprunt.setStatus(StatusEnum.retourné);
        Reservation reservation = rr.findById(emprunt.getReservation().getIdReservation()).orElse(null);
        Document doc = dr.findById(reservation.getDocument().getIdDoc()).orElse(null);
        doc.setStock(doc.getStock()+1);
        dr.save(doc);
        return null;
    }

//    @Override
//    public String modifierEmpruntAdmin(int id){
//        //pour l'admin , il peut modifer la date de retour et le status de l'emprunt
//        Emprunt oldEmprunt = er.findById(id).orElse(null);
//        if(oldEmprunt != null ) {
//            if (oldEmprunt.getStatus() == StatusEnum.annulé || oldEmprunt.getStatus() == StatusEnum.retourné) {
//                return "this emprunt is canceled or returned";
//            }
//            else{
//                oldEmprunt.setDate_retour(LocalDate.now());
//                oldEmprunt.setStatus(StatusEnum.retourné);
//                er.save(oldEmprunt);
//                return "updated successfully : retourné";
//            }
//
//        }
//        return null;
//
//    }

    @Override
    public String supprimerEmprunt(int id) {
        Emprunt emprunt = er.findById(id).orElse(null);
        if(emprunt==null){
            return "error : the emprunt can't be found";
        }
        else {
            er.deleteById(id);
        }
        return null;
    }

    @Override
    public EmpruntDTO rechercherEmprunt(int id) {
        Optional<Emprunt> empruntInfo = er.findById(id);
        if(empruntInfo.isPresent()){
            Emprunt emprunt = empruntInfo.get();
            return toDto(emprunt);
        }
        return null;
    }

    @Override
    public List<EmpruntDTO> listerEmprunt() {
        //i need to use a dto and link it to the actuall enitity so I can retrieve only the fields requeired like in the reservation all i need is the id
        List<Emprunt> emprunts = er.findAll();
        List<EmpruntDTO> dto = new ArrayList<>();
        for(Emprunt emprunt:emprunts){
            EmpruntDTO dtos = toDto(emprunt);
            dto.add(dtos);
        }
        return dto;
    }

//    @Override
//    public List<Emprunt>allEmprunts(){
//        return er.allEmprunts();
//    }


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


    /* this annotation help to automatically execute the methode retard every time we relaunche the app*/
    @PostConstruct
    public void onLaunch(){
        retard();
    }


    @Scheduled(cron = "0 0 0/12 * * ?")
    public void scheduledTask() {
        retard();
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
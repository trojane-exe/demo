package com.example.demo.services.Implementation;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.entities.Document;
import com.example.demo.entities.Reservation;
import com.example.demo.entities.Utilisateur;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UtilisateurRepository;
import com.example.demo.services.Interface.IReservationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional

public class IReservationImpl implements IReservationService {
    //public static Integer id;
    /* after adding the security stuff to the project I won't be needing a user id to make the reservation i will be retrieving that if at the start of the
    app and make it public static to be accessible everywhere and then use it to add new reservation*/

    @Autowired
    private final ReservationRepository rr;
    @Autowired
    private final DocumentRepository dr;
    @Autowired
    private final UtilisateurRepository ur;

    //convertir le dto a une entité
    public Reservation entity(ReservationDTO dto) {
        Reservation reservation = new Reservation();
        Utilisateur user = ur.findById(dto.getIdUser()).orElseThrow(() -> new RuntimeException("user not found"));
        Document doc = dr.findById(dto.getIdDoc()).orElseThrow(() -> new RuntimeException("document not found"));
        reservation.setUtilisateur(user);
        reservation.setDocument(doc);
        reservation.setDate_reservation(dto.getDate_reservation());
        return reservation;
    }

    //convertir l'entité en dto
    public ReservationDTO dto(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setIdUser(reservation.getUtilisateur().getIdUser());
        dto.setIdDoc(reservation.getDocument().getIdDoc());
        dto.setDate_reservation(reservation.getDate_reservation());
        return dto;
    }


    @Override
    public String ajouterReservation(ReservationDTO dto) {
        Document doc = dr.findById(dto.getIdDoc()).orElse(null);//.orElseThrow(() -> new RuntimeException("not found"));
        Utilisateur user = ur.findById(dto.getIdUser()).orElse(null);
        if (doc == null || user == null) {
            return "User or document are invalid";
        }
        else {
            if (doc.getStock() == 0) {
                return "Document is out of stock";
            } else {
                Reservation res = entity(dto);
                rr.save(res);
                doc.setStock(doc.getStock() - 1);
                dr.save(doc);
            }
            return null;

        }


    }


    @Override
    public String modifierReservation(int id, ReservationDTO dto) {
        Reservation oldRes = rr.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
        if (oldRes != null && oldRes.getIsActive()) {

            if(dto.getIdUser()!=null){
                Utilisateur user = ur.findById(dto.getIdUser()).orElseThrow(()->new RuntimeException("not found"));
                oldRes.setUtilisateur(user);
            }
            if(dto.getIdDoc()!=null){
                Document doc = dr.findById(dto.getIdDoc()).orElseThrow(()-> new RuntimeException("not found"));
                if(doc.getStock()==0){
                    return "out of stock";
                }
                else {
                    oldRes.setDocument(doc);
                }
            }
            if(dto.getDate_reservation()!=null){
                oldRes.setDate_reservation(dto.getDate_reservation());
            }
            rr.save(oldRes);
        }
        else{
            return "this reservation is already canceled ";
        }
        return null;
    }

    @Override
    public String supprimerReservation(int id) {
        Reservation res = rr.findById(id).orElse(null);

        if(res!=null){
            rr.deleteById(id);
        }
        else{
            return "error occured while deleting";
        }
        return null;
    }

    @Override
    public ReservationDTO rechercherReservation(int id) {
        Optional<Reservation> reservationInfo = rr.findById(id);
        if (reservationInfo.isPresent()) {
            Reservation reservation = reservationInfo.get();

            // on cree un dto et on le lie avec les attributs primaires

            ReservationDTO dto = new ReservationDTO();
            dto.setIdReservation(reservation.getIdReservation());
            if (reservation.getUtilisateur() != null) {
                dto.setIdUser(reservation.getUtilisateur().getIdUser());
            }
            else{
                dto.setIdUser(null);
            }
            if (reservation.getDocument() != null) {
                dto.setIdDoc(reservation.getDocument().getIdDoc());
            }
            else{
                dto.setIdDoc(null);
            }
            if(reservation.getIsActive()!=null){
                dto.setIsActive(reservation.getIsActive());
            }

            return dto;
        }
        return null;

    }

    @Override
    public List<ReservationDTO> listerReservation() {
        List<Reservation> res = rr.findAll();
        List<ReservationDTO> dto = new ArrayList<>();
        for(Reservation reservation : res ){
            ReservationDTO dtos = new ReservationDTO();
            if (reservation.getIdReservation() != null) {
                dtos.setIdReservation(reservation.getIdReservation());
            } else {
                dtos.setIdReservation(null); // or any default value you prefer
            }
            if (reservation.getUtilisateur() != null) {
                dtos.setIdUser(reservation.getUtilisateur().getIdUser());
            } else {
                dtos.setIdUser(null); // or any default value you prefer
            }
            if (reservation.getDocument() != null) {
                dtos.setIdDoc(reservation.getDocument().getIdDoc());
            } else {
                dtos.setIdDoc(null); // or any default value you prefer
            }
            if (reservation.getDate_reservation() != null) {
                dtos.setDate_reservation(reservation.getDate_reservation());
            } else {
                dtos.setDate_reservation(null); // or any default value you prefer
            }
            if(reservation.getIsActive()!=null){
                dtos.setIsActive(reservation.getIsActive());
            }
            dto.add(dtos);
        }

        return dto;
    }

    @Override
    public void deleteall() {
        rr.deleteAll();

    }
}

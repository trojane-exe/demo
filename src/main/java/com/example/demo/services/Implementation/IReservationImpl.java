package com.example.demo.services.Implementation;

import com.example.demo.entities.Document;
import com.example.demo.entities.Reservation;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UtilisateurRepository;
import com.example.demo.services.Interface.IReservationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional

public class IReservationImpl implements IReservationService {
    private final ReservationRepository rr;
    private final DocumentRepository dr;
    private final UtilisateurRepository ur;
    @Autowired
    public IReservationImpl(ReservationRepository resrep, DocumentRepository dr, UtilisateurRepository ur){
        this.rr = resrep;
        this.dr = dr;
        this.ur = ur;
    }


    @Override
    public void ajouterReservation(Reservation reservation) {
        //Document doc = dr.findById()
        /* khasni ghaliban nkhdem b dto bch ntransferi les données li khassini ma bin document , utilisateur w reservation */

        rr.save(reservation);
    }

    @Override
    public void modifierReservation(int id, Reservation reservation) {
        Reservation oldRes = rr.findById(id).orElseThrow(()-> new EntityNotFoundException("not found"));
        if(oldRes != null){
            if(reservation.getDocument()!=null){
                oldRes.setDocument(reservation.getDocument());
            }
            if(reservation.getUtilisateur()!=null)
            {
                oldRes.setUtilisateur(reservation.getUtilisateur());
            }
            if(reservation.getDate_reservation()!=null){
                oldRes.setDate_reservation(reservation.getDate_reservation());
            }
        }


    }

    @Override
    public void supprimerReservation(int id) {
        rr.deleteById(id);

    }

    @Override
    public Reservation rechercherReservation(int id) {
        Optional<Reservation> reservationInfo = rr.findById(id);
        return reservationInfo.orElse(null);

    }

    @Override
    public List<Reservation> listerReservation() {

        return rr.findAll();
    }

    @Override
    public void deleteall() {
        rr.deleteAll();

    }
}

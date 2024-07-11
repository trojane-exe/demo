package com.example.demo.web;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.entities.Reservation;
import com.example.demo.services.Interface.IReservationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@Validated
@RequestMapping("/api/reservation")
public class ReservationController {
    private final IReservationService rs;
    @Autowired
    public ReservationController(IReservationService rs){
        this.rs = rs;
    }


    @GetMapping("/res_home")
    public ResponseEntity<List<ReservationDTO>> allReservation(){
        List<ReservationDTO>res = rs.listerReservation();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> singleReservation(@PathVariable("id") Integer id){
        ReservationDTO res = rs.rechercherReservation(id);
        if(res!=null){
            return ResponseEntity.ok(res);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/add_reservation")
    public ResponseEntity<?> addReservation(@Validated @RequestBody ReservationDTO dto){
        try{
            rs.ajouterReservation(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reservation saved");
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/update_res/{id}")
    public ResponseEntity<?>updateReservation(@PathVariable("id") Integer id,@Validated @RequestBody ReservationDTO dto){
        try{
            rs.modifierReservation(id,dto);
            return ResponseEntity.ok("Updated successfully");

        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/delete_res/{id}")
    public String deleteReservation(@PathVariable("id") Integer id){
        try {
            rs.supprimerReservation(id);
            return "deleted successfully";
        }
        catch (Exception e){
            return "error occured" + e.getMessage();
        }
    }
    
}
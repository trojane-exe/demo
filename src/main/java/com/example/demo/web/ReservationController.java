package com.example.demo.web;

import com.example.demo.dto.ReservationDTO;
import com.example.demo.services.Interface.IReservationService;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String resultat = rs.ajouterReservation(dto);
        if (resultat==null){
            return ResponseEntity.ok("Reservation added successfully");
        }
        else{
            return ResponseEntity.badRequest().body(resultat) ;
        }
    }
    @PutMapping("/update_res/{id}")
    public ResponseEntity<?>updateReservation(@PathVariable("id") Integer id,@Validated @RequestBody ReservationDTO dto){

        String resultat = rs.modifierReservation(id,dto);
    if(resultat==null){
            return ResponseEntity.ok("Updated successfully");

        }
        else{
            return ResponseEntity.badRequest().body(resultat);
        }
    }
    @PutMapping("cancel/{id}")
    public ResponseEntity<?>cancelReservation(@PathVariable("id") Integer id){
        String resultat = rs.annulerReservation(id);
        if(resultat==null){
            return ResponseEntity.ok(resultat);
        }
        else{
            return ResponseEntity.badRequest().body("error");
        }
    }
    @DeleteMapping("/delete_res/{id}")
    public ResponseEntity<Map<String,String>> deleteReservation(@PathVariable("id") Integer id){
        rs.supprimerReservation(id);
        Map<String,String> reponse = new HashMap<>();
        reponse.put("message","deleted");
        return ResponseEntity.ok(reponse);
    }
    
}

package com.example.demo.web;

import com.example.demo.dto.EmpruntDTO;
import com.example.demo.entities.Emprunt;
import com.example.demo.services.Interface.IEmpruntService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/emprunt")
@Validated
public class EmpruntController {
    @Autowired
    private final IEmpruntService es;


    @GetMapping("/home")
    public ResponseEntity<List<EmpruntDTO>>allEmprunts(){
        List<EmpruntDTO> emprunts = es.listerEmprunt();
        return ResponseEntity.ok(emprunts);
    }


    @PostMapping("/cancel")
    public ResponseEntity<?> cancel(@Validated @RequestBody EmpruntDTO emprunt){
        String result = es.annulerReservation(emprunt);
        if(result==null){
            return ResponseEntity.ok(result);
        }
        else{
            return ResponseEntity.badRequest().body(result);
        }
    }
    @PostMapping("/add_emprunt")
    public ResponseEntity<?> addEmprunt(@Validated @RequestBody EmpruntDTO dto){
        String resultat = es.ajouterEmprunt(dto);
        if(resultat ==null){
            return ResponseEntity.ok(resultat);
        }
        else{
            return ResponseEntity.badRequest().body(resultat);
        }
    }
    @PostMapping("/update_emprunt/user/{id}")
    public ResponseEntity<?> modifierEmprunt(@PathVariable("id") Integer id , @Validated @RequestBody EmpruntDTO dto){
        String resultat = es.modifierEmpruntUser(id,dto);
        if(resultat == null){
            return ResponseEntity.ok(resultat);
        }
        else {
            return ResponseEntity.badRequest().body(resultat);
        }
    }

    @PostMapping("update_emprunt/admin/{id}")
    public ResponseEntity<?> modifierEmprunt(@PathVariable("id") Integer id){
        String resultat = es.modifierEmpruntAdmin(id);
        if (resultat==null){
            return  ResponseEntity.ok(resultat);
        }
        else{
            return ResponseEntity.badRequest().body(resultat);
        }
    }
    @PostMapping("delete_emprunt/{id}")
    public ResponseEntity<?>supprimerEmprunt(@PathVariable("id")Integer id){
        String resultat = es.supprimerEmprunt(id);
        if(resultat==null){
            return ResponseEntity.ok("deleted successfully");
        }
        else{
            return ResponseEntity.badRequest().body(resultat);
        }
    }
}

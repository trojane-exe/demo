package com.example.demo.web;

import com.example.demo.dto.EmpruntDTO;
import com.example.demo.services.Interface.IEmpruntService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api/emprunt")
@Validated
public class EmpruntController {
    @Autowired
    private final IEmpruntService es;




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
    @PostMapping("/update_emprunt/{id}")
    public ResponseEntity<?> modifierEmprunt(@PathVariable("id") Integer id , @Validated @RequestBody EmpruntDTO dto){
        String resultat = es.modifierEmprunt(id,dto);
        if(resultat == null){
            return ResponseEntity.ok("updated successfully");
        }
        else {
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

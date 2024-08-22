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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RestController
@RequestMapping("/api/emprunt")
@Validated
public class EmpruntController {
    @Autowired
    private final IEmpruntService es;


    @GetMapping("/home")
    public List<EmpruntDTO> myEmprunts( ){
        return es.listerEmprunt();
    }




//    @PostMapping("/cancel/{id}")
//    public ResponseEntity<?> cancel(@PathVariable("id") Integer id,@Validated @RequestBody EmpruntDTO emprunt){
//        String result = es.annulerEmprunt(id,emprunt);
//        if(result==null){
//            return ResponseEntity.ok(result);
//        }
//        else{
//            return ResponseEntity.badRequest().body(result);
//        }
//    }

    @GetMapping("/emprunt-user/{id}")
    public ResponseEntity<List<EmpruntDTO>> getEmpruntsByUserId(@PathVariable Integer id) {
        List<EmpruntDTO> emprunts = es.findEmpruntsByUserId(id);
        if (emprunts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(emprunts);
    }
    @PostMapping("/add_emprunt")
    public ResponseEntity<String> addEmprunt(@Validated @RequestBody EmpruntDTO dto){
        String resultat = es.ajouterEmprunt(dto);
        if(resultat ==null){
            return ResponseEntity.ok(resultat);
        }
        else{
            return ResponseEntity.badRequest().body(resultat);
        }
    }
    @PutMapping("/update_emprunt/user/{id}")
    public ResponseEntity<?> modifierEmprunt(@PathVariable("id") Integer id , @Validated @RequestBody EmpruntDTO dto){
        String resultat = es.modifierEmpruntUser(id,dto);
        if(resultat == null){
            return ResponseEntity.ok(resultat);
        }
        else {
            return ResponseEntity.badRequest().body(resultat);
        }
    }

    @DeleteMapping("delete_emprunt/{id}")
    public ResponseEntity<Map<String,String>>supprimerEmprunt(@PathVariable("id")Integer id){
        es.supprimerEmprunt(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","Deleted successfully");
        return ResponseEntity.ok(response);
    }
}

package com.example.demo.web;

import com.example.demo.dto.EmpruntDTO;
import com.example.demo.services.Interface.IEmpruntService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.ok("done successfully");
        }
        else{
            return ResponseEntity.badRequest().body(resultat);
        }
    }
}

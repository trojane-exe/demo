package com.example.demo.web;


import com.example.demo.dto.EmpruntDTO;
import com.example.demo.services.Interface.IEmpruntService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Data
@Validated
@RestController
@RequestMapping("api/emprunt/admin")
public class AdminEmpruntController {
    @Autowired
    private final IEmpruntService es;



    @GetMapping("/home")
    public ResponseEntity<List<EmpruntDTO>> allEmprunts(){
        List<EmpruntDTO> emprunts = es.listerEmprunt();
        return ResponseEntity.ok(emprunts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpruntDTO> rechercherEmprunt(@PathVariable("id") Integer id){
        EmpruntDTO resultat = es.rechercherEmprunt(id);
        return ResponseEntity.ok(resultat);

    }

    @PostMapping("update_emprunt/{id}")
    public ResponseEntity<?> modifierEmprunt(@PathVariable("id") Integer id){
        String resultat = es.modifierEmpruntAdmin(id);
        if (resultat==null){
            return  ResponseEntity.ok(resultat);
        }
        else{
            return ResponseEntity.badRequest().body(resultat);
        }
    }



}


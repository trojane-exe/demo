package com.example.demo.web;


import com.example.demo.dto.EmpruntDTO;
import com.example.demo.services.Interface.IEmpruntService;
import lombok.Data;
import org.hibernate.persister.collection.mutation.RemoveCoordinatorTablePerSubclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.NestingKind;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @PostMapping("/add_emprunt")
    public ResponseEntity<?> addEmprunt( @RequestBody EmpruntDTO dto){

        try {
            String resultat = es.ajouterEmprunt(dto);
            Map<String, String> response = new HashMap<>();
            response.put("message", "emprunt saved successfully");
            response.put("result",resultat);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to emprunt: " + e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpruntDTO> rechercherEmprunt(@PathVariable("id") Integer id){
        EmpruntDTO resultat = es.rechercherEmprunt(id);
        return ResponseEntity.ok(resultat);

    }

    @PutMapping("update_emprunt/{id}")
    public ResponseEntity<Map<String, String>> modifierEmprunt(@PathVariable("id") Integer id, @Validated @RequestBody EmpruntDTO dto){
        String resultat = es.modifierEmpruntUser(id,dto);
        Map<String,String> reponse = new HashMap<>();
        if (resultat==null){
            reponse.put("message",resultat) ;
        }
        else{
            reponse.put("message",resultat);
        }
        return ResponseEntity.ok(reponse);

    }

    @PutMapping("/retourner/{id}")
    public ResponseEntity<Map<String,String>> retourner(@PathVariable("id") Integer id){
        String result = es.confirmerRetour(id);
        Map<String,String> reponse = new HashMap();
        if(result==null){
            reponse.put("Message",result);
        }
        else{
            reponse.put("Message",result);
        }
        return ResponseEntity.ok(reponse);
    }


    @PutMapping("/cancel/{id}")
    public ResponseEntity<Map<String,String>> cancel(@PathVariable("id") Integer id){
        String result = es.annulerEmprunt(id);
        Map<String,String> reponse = new HashMap();
        if(result==null){
             reponse.put("Message",result);
        }
        else{
            reponse.put("Message",result);
        }
        return ResponseEntity.ok(reponse);
    }

    @DeleteMapping("delete_emprunt/{id}")
    public ResponseEntity<Map<String,String>>supprimerEmprunt(@PathVariable("id")Integer id){
        es.supprimerEmprunt(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","Deleted successfully");
        return ResponseEntity.ok(response);
    }



}


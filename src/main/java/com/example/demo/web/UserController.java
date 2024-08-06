package com.example.demo.web;


import com.example.demo.dto.UtilisateurDTO;
import com.example.demo.entities.Utilisateur;
import com.example.demo.services.Interface.IUtilisateurService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    private final IUtilisateurService us;
    @Autowired
    public UserController(IUtilisateurService utilisateurService){
        this.us = utilisateurService;
    }

    @GetMapping("/users_home")
    public ResponseEntity<List<UtilisateurDTO>> allUsers(){
        List<UtilisateurDTO> users = us.listUsers();
        return ResponseEntity.ok(users);
    }

//    @GetMapping("/home2")
//    public ResponseEntity<List<Utilisateur>> getAllUsers(){
//        List<Utilisateur> allusers = us.getAllUsers();
//        return ResponseEntity.ok(allusers);
//    }

    @GetMapping(/*/users_home/*/"/{id}")
    public ResponseEntity<UtilisateurDTO> singleUser(@PathVariable("id") Integer id) {
        UtilisateurDTO user = us.rechercherUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        else {
            return ResponseEntity.notFound().build();
        }
}


    @GetMapping("/add_user")
    public String addUserForm(Model model){
        model.addAttribute("users",new Utilisateur());
        return "add_user";
    }



    @PostMapping("/add_user")
    public ResponseEntity<?> addUser(@Validated @RequestBody UtilisateurDTO user) {
        try {
            us.ajouterUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add user: " + e.getMessage());
        }
    }

    @GetMapping("/bookingId")
    public ResponseEntity<List<Object>> getBookingInfo(){
        List<Object> users = us.getAllId();
        return ResponseEntity.ok(users);
    }
    @PutMapping("/update_user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id,@Validated @RequestBody Utilisateur user){
        try{
            us.modifierUser(id,user);
            return ResponseEntity.ok("Updated successfully");
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable("id") Integer id){
        us.supprimerUser(id);
        Map<String,String> reponse = new HashMap<>();
        reponse.put("message","delete success");
        return ResponseEntity.ok(reponse);
    }
}

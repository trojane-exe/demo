package com.example.demo.web;


import com.example.demo.entities.Utilisateur;
import com.example.demo.services.IUtilisateurService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Utilisateur>> allUsers(){
        List<Utilisateur> users = us.listUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> singleUser(@PathVariable("id") Integer id) {
        Utilisateur user = us.rechercherUser(id);
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
    public ResponseEntity<?> addUser(@Validated @RequestBody Utilisateur user) {
        try {
            us.ajouterUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add user: " + e.getMessage());
        }
    }

    @PostMapping("/update_user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id,@Validated @RequestBody Utilisateur user){
        Utilisateur existant = us.rechercherUser(id);
        if(existant != null) {
            existant.setNom(user.getNom());
            existant.setPrenom(user.getPrenom());
            existant.setAdresse(user.getAdresse());
            existant.setEmail(user.getEmail());
            existant.setPassword(user.getPassword());
            existant.setRole(user.getRole());
            us.modifierUser(existant);
            return ResponseEntity.ok("Updated successfully");
        }
        else
            return ResponseEntity.notFound().build();
    }
    @PostMapping("/delete_user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
        us.supprimerUser(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}

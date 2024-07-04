package com.example.demo.web;


import com.example.demo.entities.Utilisateur;
import com.example.demo.services.IUtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUtilisateurService us;
    @Autowired
    public UserController(IUtilisateurService utilisateurService) {
        this.us = utilisateurService;
    }


    @PostMapping("/add_user")
    public ResponseEntity<String> addUser(@RequestBody Utilisateur user) {
        us.ajouterUser(user);
        return ResponseEntity.ok("User added successfully");
    }
}

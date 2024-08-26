package com.example.demo.web;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.entities.Transaction;
import com.example.demo.services.Implementation.Historique;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RestController
@Validated
@RequestMapping("/api/historique")
public class HistoriqueController {

    @Autowired
    private Historique historique;


    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionDTO>> getEmpruntsByUserId(@PathVariable("id") Integer userId) {
        List<TransactionDTO> emprunts = historique.findTransactionByUserId(userId);
        return ResponseEntity.ok(emprunts);
    }
}

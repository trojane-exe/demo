package com.example.demo.services.Implementation;

import com.example.demo.dto.EmpruntDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.entities.Emprunt;
import com.example.demo.entities.Transaction;
import com.example.demo.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
@Transactional
public class Historique implements com.example.demo.services.Interface.Historique {
    private final TransactionRepository tr;


    public TransactionDTO toDto(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();

        if (transaction.getIdTransaction() != null) {
            dto.setIdTransaction(transaction.getIdTransaction());
        }

        if (transaction.getUtilisateur() != null) {
            dto.setIdUser(transaction.getUtilisateur().getIdUser());
        }

        if (transaction.getEmprunt() != null) {
            dto.setIdEmp(transaction.getEmprunt().getIdEmp());
        }

        if (transaction.getDate_transaction() != null) {
            dto.setDate_transaction(transaction.getDate_transaction());
        }

        return dto;
    }










    @Override
    public List<TransactionDTO> findTransactionByUserId(Integer id) {
        List<Transaction> transactions = tr.findTransactionByUserId(id);
        List<TransactionDTO> dto = new ArrayList<>();
        for(Transaction transaction : transactions){
            TransactionDTO dtos = toDto(transaction);
            dto.add(dtos);
        }
        return dto;
    }
}

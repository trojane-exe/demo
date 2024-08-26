package com.example.demo.services.Interface;

import com.example.demo.dto.EmpruntDTO;
import com.example.demo.dto.TransactionDTO;

import java.util.List;

public interface Historique {
    public List<TransactionDTO> findTransactionByUserId(Integer id );
}

package com.example.demo.repository;

import com.example.demo.entities.Emprunt;
import com.example.demo.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    @Query("SELECT t FROM Transaction t  WHERE t.utilisateur.idUser = :userId")
    List<Transaction> findTransactionByUserId(@Param("userId") Integer userId);

}

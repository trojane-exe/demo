package com.example.demo.repository;

import com.example.demo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Integer> {
    @Query("select d.titre from Document d where d.stock>0")
    List<Object[]>getActifDocs();

}

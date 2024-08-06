package com.example.demo.repository;

import com.example.demo.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Integer> {
    @Query("select d.idDoc,d.titre from Document d where d.stock>0")
    List<Object>getActifDocs();
    @Query("select d.idDoc,d.titre,d.auteur,d.date_ecriture,d.stock from Document d")
    List<Document>getAllDocs();

}

package com.example.demo.services.Implementation;

import com.example.demo.entities.Document;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.services.Interface.IDocumentService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@Transactional
public class IDocumentImpl implements IDocumentService {
    private final DocumentRepository dr;
    @Autowired
    public IDocumentImpl (DocumentRepository docRep){
        this.dr = docRep;
    }

    @Override
    public void ajouterDocument(Document document) {
        dr.save(document);
    }
    

    @Override
    public void modifierDocument(int id , Document document) {

    }

    @Override
    public void supprimerDocument(Integer id) {

    }

    @Override
    public Document rechercherDocument(Integer id) {
        return null;
    }

    @Override
    public List<Document> listDocument() {
        return List.of();
    }
}

package com.example.demo.services.Interface;

import com.example.demo.entities.Document;

import java.util.List;

public interface IDocumentService {
    public void ajouterDocument(Document document);
    public void modifierDocument(int id, Document document);
    public void supprimerDocument(Integer id);
    public Document rechercherDocument(Integer id);
    public List<Document> listDocument();
    public void deleteAll();
}

package com.example.demo.services.Implementation;

import com.example.demo.entities.Document;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.services.Interface.IDocumentService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public String ajouterDocument(Document document) {
        if(document.getStock()<0){
            return "stock cant be under 0";
        }
        dr.save(document);
        return null;
    }

    @Override
    public void deleteAll(){
        dr.deleteAll();
    }


    @Override
    public void modifierDocument(int id , Document document) {
        Document doc = dr.findById(id).orElse(null);
        if(doc!=null){
            if (document.getTitre() != null) {
                doc.setTitre(document.getTitre());
            }
            if (document.getAuteur() != null) {
                doc.setAuteur(document.getAuteur());
            }
            if (document.getDate_ecriture() != null) {
                doc.setDate_ecriture(document.getDate_ecriture());
            }
            if(document.getStock()!=null && document.getStock()<0){
                doc.setStock(document.getStock());
            }
            dr.save(doc);
        }

    }

    @Override
    public String supprimerDocument(Integer id){
            dr.deleteById(id);
            return null;
    }

    @Override
    public Document rechercherDocument(Integer id) {
        Optional<Document> docInfo = dr.findById(id);
        return docInfo.orElse(null);
    }

    @Override
    public List<Document> listDocument() {

        return dr.findAll();
    }
}

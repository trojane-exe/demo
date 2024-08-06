package com.example.demo.services.Implementation;

import com.example.demo.dto.DocumentDTO;
import com.example.demo.entities.Document;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.services.Interface.IDocumentService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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



    //i will be using again a methode to convert the dto to entity and the entity to dto
    //otherwise i will have a problem when retreving the info of docs , when using findAll() the reservation fields appears as a looped list

    public Document toEntity(DocumentDTO dto){
        Document document = new Document();
        document.setIdDoc(dto.getIdDoc());
        document.setTitre(dto.getTitre());
        document.setAuteur(dto.getAuteur());
        document.setDate_ecriture(dto.getDate_ecriture());
        document.setStock(dto.getStock());
        return document;
    }

    public DocumentDTO toDto(Document document){
        DocumentDTO dto = new DocumentDTO();
        dto.setIdDoc(document.getIdDoc());
        dto.setTitre(document.getTitre());
        dto.setAuteur(document.getAuteur());
        dto.setDate_ecriture(document.getDate_ecriture());
        dto.setStock(document.getStock());
        return dto;
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
            if(document.getStock()!=null && document.getStock()>=0){
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
    public DocumentDTO rechercherDocument(Integer id) {
        Optional<Document> docInfo = dr.findById(id);
        Document document = docInfo.get();
        return toDto(document);
    }

    @Override
    public List<DocumentDTO> listDocument() {
        List<Document> docs = dr.findAll();
        List<DocumentDTO>dto = new ArrayList<>();
        for(Document doc : docs){
            DocumentDTO dtos = toDto(doc);
            dto.add(dtos);
        }


        return dto;
    }
    @Override
    public List<Object>getActifDocs(){
        return dr.getActifDocs();
    }
}

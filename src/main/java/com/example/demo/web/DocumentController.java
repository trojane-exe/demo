package com.example.demo.web;


import com.example.demo.dto.DocumentDTO;
import com.example.demo.entities.Document;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.services.Interface.IDocumentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@RestController
@Data
@RequestMapping("/api/documents")
@Validated
public class DocumentController {
    private final IDocumentService ds;
    @Autowired
    public DocumentController(IDocumentService documentService){
        this.ds = documentService;
    }
    @GetMapping("/doc_home")
    public ResponseEntity<List<DocumentDTO>> AllDoc(){
        List<DocumentDTO> docs = ds.listDocument();
        return ResponseEntity.ok(docs);
    }

    @GetMapping("/actifDoc")
    public ResponseEntity<List<Object>> getActifDocs(){
        List<Object> docs = ds.getActifDocs();
        return ResponseEntity.ok(docs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> singledoc(@PathVariable("id") Integer id){
        DocumentDTO doc = ds.rechercherDocument(id);
        if(doc != null){
            return ResponseEntity.ok(doc);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add_doc")
    public ResponseEntity<?> addDoc(@Validated @RequestBody Document document){
        try {
            ds.ajouterDocument(document);
            return ResponseEntity.status(HttpStatus.CREATED).body("Document added successfully");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add this document");
        }
    }
    @PutMapping("/update_doc/{id}")
    public ResponseEntity<?> updateDoc(@PathVariable("id") Integer iddoc ,@Validated @RequestBody Document newdoc){
        try{
            ds.modifierDocument(iddoc,newdoc);
            return ResponseEntity.ok("Updated successfully");
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete_doc/{id}")
    public ResponseEntity<Map<String,String>> deleteDoc(@PathVariable("id") Integer id){
            ds.supprimerDocument(id);
        Map<String,String> reponse = new HashMap<>();
        reponse.put("message","delete success");
        return ResponseEntity.ok(reponse);}

}

import { Component, OnInit } from '@angular/core';
import { Document } from '../../../models/Document.model';
import { Router } from '@angular/router';
import { DocumentService } from '../../../services/AdminServices/Documents/document.service';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrl: './documents.component.css'
})
export class DocumentsComponent implements OnInit {

  docs : Document[]=[];
  constructor(private router : Router,private documentService : DocumentService){}


  loadDocs():void{
    this.documentService.gettAllDocs().subscribe({
      next:(data)=>{
        this.docs = data;
      }
    })
  }
  ngOnInit(): void {
    this.loadDocs();
  }
}

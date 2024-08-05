import { Component, OnInit } from '@angular/core';
import { Document } from '../../../models/Document.model';
import { Router } from '@angular/router';
import { DocumentService } from '../../../services/AdminServices/Documents/document.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-documents',
  templateUrl: './documents.component.html',
  styleUrl: './documents.component.css'
})
export class DocumentsComponent implements OnInit {

  docs : Document[]=[];
  constructor(private router : Router,private documentService : DocumentService,private toast : ToastrService){}


  navigateToBooking( id : number) : void{
    this.router.navigate(['/reserve-doc' ,id]);
  }
  navigateToUpdate(id : number) : void{
    this.router.navigate(['/update-doc',id]);
  }
  deleteDoc(id : number) : void{
    const dialog = confirm("are you sure you want to delete this document!!");
    if(id == null){
      this.toast.warning("the ID given is null try again please","ERROR");
    }
    else{
      if(dialog){
        this.documentService.deleteDoc(id).subscribe({
          next:()=>{
            this.docs = this.docs.filter(document =>document.idDoc !==id);
            this.toast.warning("Document deleted",'DELETE');
          }
        })
      }
    }
    
  }

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

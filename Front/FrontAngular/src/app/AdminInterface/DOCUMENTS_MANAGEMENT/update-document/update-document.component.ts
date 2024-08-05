import { Component,OnInit} from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Document } from '../../../models/Document.model';
import { DocumentService } from '../../../services/AdminServices/Documents/document.service';
import { ToastrService } from 'ngx-toastr';
import { NgForm } from '@angular/forms'; 
@Component({
  selector: 'app-update-document',
  templateUrl: './update-document.component.html',
  styleUrl: './update-document.component.css'
})
export class UpdateDocumentComponent implements OnInit{
  id! : number;
  document : Document = new Document();
  

  constructor(private router : Router,private toast : ToastrService,private route : ActivatedRoute,private docService : DocumentService ){}

  loadDocInfo(id:number):void{
    this.docService.getDocById(id).subscribe({
      next : (data)=>{
        this.document = data;
      },
      error : (err)=>{
        this.toast.warning(err);
      }
    })
  };

  updateDoc(form : NgForm) : void{
    const stock = (document.getElementById('stock') as HTMLInputElement).value
    if(form.invalid){
      this.toast.warning("please recheck and fill all the required fields","WARNING",{
        toastClass:'alert alert-success',
        positionClass:'toast-top-right'
      });
    }
    else if(Number(stock)<0){
      this.toast.warning("the stock cant be negative please retry",'ERROR')
    }
    else{
      this.docService.updateDoc(this.id,this.document).subscribe({
        next : ()=>{
          this.toast.success("Document updated successfully",'UPDATE');
          this.router.navigate(['/documents']);
      },
    error : (err)=>{
      this.toast.error("error"+err)
    }
  }
)}
}
  
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loadDocInfo(this.id);
    
  }
}

import { Component, OnInit } from '@angular/core';
import { Document } from '../../../models/Document.model';
import { ToastrService } from 'ngx-toastr';
import { NgForm } from '@angular/forms';
import { DocumentService } from '../../../services/AdminServices/Documents/document.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../services/authenticationService/authentication.service';

@Component({
  selector: 'app-add-document',
  templateUrl: './add-document.component.html',
  styleUrl: './add-document.component.css'
})
export class AddDocumentComponent implements OnInit{

  constructor (private router : Router,private docService : DocumentService,private toast : ToastrService
    ,private auth :AuthenticationService
  ){}

  document : Document = new Document();

  addDoc(form : NgForm):void{
    const stock = (document.getElementById('stock') as HTMLInputElement).value

    if(form.invalid ){
      this.toast.warning("please fill the required fields and respect the format",'ERROR');
    }
    else {
      if(Number(stock) < 0 ){
        this.toast.warning("the stock cant be negative please retry",'ERROR')
      }
      else{
      this.docService.addDoc(this.document).subscribe({
        next:()=>{
          this.toast.success("Document added successfully",'INSERT');
          this.router.navigate(['/documents']);
        },
        error:(err)=>
          this.toast.error("error"+err)
      })
    }
  }


}  

ngOnInit(): void {
  const role = this.auth.getRole();
  if(role=="User"){
    this.toast.warning("simple users are not allowed to use the admin dashboard");
    this.router.navigate(['/profile']);
  }
  }
}

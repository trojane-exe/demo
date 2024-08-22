import { Component, OnInit } from '@angular/core';
import { Emprunt } from '../../../models/Emprunt.model';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { EmpruntService } from '../../../services/AdminServices/Emprunts/emprunt.service';
import { AuthenticationService } from '../../../services/authenticationService/authentication.service';

@Component({
  selector: 'app-validate-emprunt',
  templateUrl: './validate-emprunt.component.html',
  styleUrl: './validate-emprunt.component.css'
})
export class ValidateEmpruntComponent implements OnInit{

  emprunt : Emprunt[]=[];
  idRes ! : number
  saveEmprunt : Emprunt = new Emprunt();

  constructor(private router: Router,private route:ActivatedRoute,private empruntService : EmpruntService , 
    private toast : ToastrService,private auth :AuthenticationService){}
  



  addEmprunt(form:NgForm):void{
    if(form.valid){
      this.saveEmprunt.idRes = this.idRes;
      this.empruntService.addEmprunt(this.saveEmprunt).subscribe({
        next:()=>{
          this.toast.success("Emprunt saved");
          const role = this.auth.getRole();
          if(role=="User"){
            this.router.navigate(['/emprunt-user']);
          }
          else if(role=="Admin"){
            this.router.navigate(['/emprunts'])
          }
           
        },
        error :(err)=>{
          this.toast.error(err,"error");
        }
      })
      
     

      
      
    }

  }
  ngOnInit(): void {

    this.idRes = this.route.snapshot.params['id'];
    this.saveEmprunt.idRes = this.idRes
    }
  

}

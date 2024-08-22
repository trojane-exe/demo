import { Component, OnInit } from '@angular/core';
import { Emprunt } from '../../../../models/Emprunt.model';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { EmpruntsUserService } from '../../service/emprunts-user.service';
import { StatusEnum } from '../../../../models/StatusEnum.enum';
import { AuthenticationService } from '../../../../services/authenticationService/authentication.service';
import { EmpruntService } from '../../../../services/AdminServices/Emprunts/emprunt.service';

@Component({
  selector: 'app-emprunts-user',
  templateUrl: './emprunts-user.component.html',
  styleUrl: './emprunts-user.component.css'
})
export class EmpruntsUserComponent implements OnInit {

  emprunts :Emprunt[]=[];
  status = StatusEnum;
  currentId !:number;

  constructor(private router  : Router, private toast : ToastrService, 
    private empruntService : EmpruntsUserService,private auth :AuthenticationService,private empServ : EmpruntService){}

    getAllEmprunt(id:number):void{
      this.empruntService.getEmpruntsByUserId(id).subscribe({
        next : (data)=>{
          this.emprunts =data;
        }
      })
    }

    cancelEmprunt(id:number):void{
      const dialog = confirm("are you sure you want to delete this emprunt!");
      if(dialog){
        this.empServ.cancelEmprunt(id).subscribe({
          next:()=>{this.getAllEmprunt(this.currentId);}
        })
    }
  }

  ngOnInit(): void {
    this.currentId = Number(localStorage.getItem('userId'));
    this.getAllEmprunt(this.currentId);
    
  }

}

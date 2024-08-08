import { Component, OnInit } from '@angular/core';
import { Emprunt } from '../../../models/Emprunt.model';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { EmpruntService } from '../../../services/AdminServices/Emprunts/emprunt.service';
import { StatusEnum } from '../../../models/StatusEnum.enum';
@Component({
  selector: 'app-emprunts',
  templateUrl: './emprunts.component.html',
  styleUrl: './emprunts.component.css'
})
export class EmpruntsComponent implements OnInit{

  emprunts :Emprunt[]=[];
  status = StatusEnum;

  constructor(private router  : Router, private toast : ToastrService, private empruntService : EmpruntService){}

  getAllEmprunt():void{
    this.empruntService.getAllEmprunt().subscribe({
      next : (data)=>{
        this.emprunts =data;
      }
    })
  }

  ngOnInit(): void {
    this.getAllEmprunt();
    
  }

}

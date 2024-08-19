import { Component, OnInit } from '@angular/core';
import { Emprunt } from '../../../models/Emprunt.model';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { EmpruntService } from '../../../services/AdminServices/Emprunts/emprunt.service';
import { StatusEnum } from '../../../models/StatusEnum.enum';
import { NotExpr } from '@angular/compiler';
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


  navigateToUpdate(id:number):void{
    this.router.navigate(['/update-emp',id]);
  }

  doEmprunt():void{
    this.router.navigate(['/reservations']);
    this.toast.info("confirm the reservation to pass an emprunt","INFO")
  }

  deleteEmprunt(id:number):void{
    const dialog = confirm("are you sure you want to delete this emprunt!");
    if(dialog){
      this.empruntService.deleteEmprunt(id).subscribe({
        next:()=>{
          this.toast.warning("Deleted successfully","DELETE");
          this.emprunts = this.emprunts.filter(emp =>emp.idEmp !==id );
        }
      })
    }
  }
  cancelEmprunt(id:number):void{
    const dialog = confirm("are you sure you want to delete this emprunt!");
    if(dialog){
      this.empruntService.cancelEmprunt(id).subscribe({
        next:()=>{this.getAllEmprunt();}
      })
  }
}

confirmReturn(id:number):void{
  const dialog = confirm("are you sure this emprunt is being returned!");
  if(dialog){
    this.empruntService.returnEmprunt(id).subscribe({
      next:()=>{this.getAllEmprunt();}
    })
}
}

  ngOnInit(): void {
    this.getAllEmprunt();
    
  }

}

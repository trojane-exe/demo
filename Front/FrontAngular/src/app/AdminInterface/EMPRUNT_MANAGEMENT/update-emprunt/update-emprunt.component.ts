import { Component,OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Emprunt } from '../../../models/Emprunt.model';
import { ToastrService } from 'ngx-toastr';
import { NgForm } from '@angular/forms';
import { EmpruntService } from '../../../services/AdminServices/Emprunts/emprunt.service';


@Component({
  selector: 'app-update-emprunt',
  templateUrl: './update-emprunt.component.html',
  styleUrl: './update-emprunt.component.css'
})
export class UpdateEmpruntComponent implements OnInit{

  id! : number;
  emprunt : Emprunt = new Emprunt();
  date_debut! :Date ;
  date_retour_prevue!:Date
  constructor(private router : Router,private toast : ToastrService,private route : ActivatedRoute,private empruntService : EmpruntService){}

  getEmpruntInfos(id:number):void{
    this.empruntService.getEmpruntById(id).subscribe({
      next : (data)=>{this.emprunt =data
        this.date_debut = new Date(data.date_debut);
        this.date_retour_prevue = new Date(data.date_retour_prevue); 
      },
      error :(err)=>{this.toast.error("Error loading data",err)}
    })
  }

  updateemprunt(form:NgForm) : void{
    if (form.valid) {

      if (this.date_debut > this.date_retour_prevue) {
          this.toast.error("Erreur: La date de début est après la date de retour prévue");
      }
    else{
    this.empruntService.updateEmprunt(this.id,this.emprunt).subscribe({
      
      next:()=>{
        this.router.navigate(['/emprunts'])
        this.toast.warning("Updated")},
        error :(err)=>{this.toast.error(err)}
      
    });
  
  }
    
  }
}





  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.getEmpruntInfos(this.id);
    
  }
}

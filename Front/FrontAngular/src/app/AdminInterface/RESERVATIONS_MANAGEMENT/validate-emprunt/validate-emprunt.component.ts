import { Component } from '@angular/core';
import { Emprunt } from '../../../models/Emprunt.model';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { EmpruntService } from '../../../services/AdminServices/Emprunts/emprunt.service';

@Component({
  selector: 'app-validate-emprunt',
  templateUrl: './validate-emprunt.component.html',
  styleUrl: './validate-emprunt.component.css'
})
export class ValidateEmpruntComponent {

  emprunt : Emprunt[]=[];
  idRes ! : number
  saveEmprunt : Emprunt = new Emprunt();

  constructor(private router: Router,private route:ActivatedRoute,private empruntService : EmpruntService , private toast : ToastrService){}
  



  addEmprunt(form:NgForm):void{
    if(form.valid){
      this.saveEmprunt.idRes = this.idRes;
      this.empruntService.addEmprunt(this.saveEmprunt).subscribe({
        next:()=>{
          this.toast.success("Emprunt saved");
        }
      })
    }

  }
  ngOnInit(): void {
    this.idRes = this.route.snapshot.params['id'];
    this.saveEmprunt.idRes = this.idRes
    
  }

}

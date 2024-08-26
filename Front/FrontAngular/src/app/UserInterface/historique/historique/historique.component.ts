import { Component, OnInit} from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { ReservationService } from '../../../services/AdminServices/Reservations/reservation.service';
import { AuthenticationService } from '../../../services/authenticationService/authentication.service';
import { HistoriqueService } from '../../../services/UserServices/Historique/historique.service';
import { Transaction } from '../../../models/Transaction.model';
@Component({
  selector: 'app-historique',
  templateUrl: './historique.component.html',
  styleUrl: './historique.component.css'
})
export class HistoriqueComponent implements OnInit{


  currentId !: number
  transaction : Transaction[]=[]
constructor(private router :Router,private toast:ToastrService, private historique : HistoriqueService
  ,private auth :AuthenticationService
){}

getHistorique(id:number):void{
  this.historique.getTransactionByUserId(id).subscribe({
    next :(data)=>{
      this.transaction = data
    }
  })
}

ngOnInit(): void {

  this.currentId = Number(localStorage.getItem("userId"));
  this.getHistorique(this.currentId);
  
}

}

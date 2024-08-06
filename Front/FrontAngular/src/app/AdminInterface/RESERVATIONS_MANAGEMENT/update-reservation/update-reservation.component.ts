import { Component, OnInit, ValueProvider } from '@angular/core';
import { Document } from '../../../models/Document.model';
import { ToastrService } from 'ngx-toastr';
import { User } from '../../../models/User.model';
import { NgForm } from '@angular/forms';
import { DocumentService } from '../../../services/AdminServices/Documents/document.service';
import { Router,ActivatedRoute} from '@angular/router';
import { GestionUtilisateurService } from '../../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { ReservationService } from '../../../services/AdminServices/Reservations/reservation.service';
import { Reservation } from '../../../models/Reservation.model';
import { dateTimestampProvider } from 'rxjs/internal/scheduler/dateTimestampProvider';

@Component({
  selector: 'app-update-reservation',
  templateUrl: './update-reservation.component.html',
  styleUrl: './update-reservation.component.css'
})
export class UpdateReservationComponent implements OnInit {
  selectedUserId!: number;
  selectedDocId! : number;
  varToFillReservation : any;
  user : any[] = [];
  id! : number
  doc : any[] = [];
  reservation: Reservation = new Reservation();
  constructor(private router : Router,private route:ActivatedRoute,private docService : DocumentService,private toast : ToastrService,
    private userService : GestionUtilisateurService,private reservationService:ReservationService){}

  getActifDocs():void{
    this.docService.getActifDocs().subscribe({
      next:(data)=>{
        this.doc=data
      },
      error:(err)=>{
        this.toast.error(err);
      }
    })
  }

  getReservationById(id : number):void{
    this.reservationService.getReservationById(id).subscribe({
      next:(data)=>{
        this.varToFillReservation=data;
        this.selectedUserId=this.varToFillReservation.idUser;
        this.selectedDocId=this.varToFillReservation.idDoc;
      }
    })
  }

  getUsersInfo():void{
    this.userService.getInfoForBooking().subscribe({
      next:(data)=>{
        this.user=data;
      },      
      error : (err)=>{
        this.toast.error(err);
      }
    })
  }

  confirmUpdate():void{
    const userId = (document.getElementById('identifiantuser')as HTMLInputElement).value;
    const docId = (document.getElementById('identifiantdoc') as HTMLInputElement).value;
    const dialog = confirm("you are about to update the reservation info : \"user : " + userId+" \"document : "+docId);
    if(dialog){
      this.reservation.idDoc = Number(docId);
      this.reservation.idUser = Number(userId);
      this.reservationService.updateReservation(this.id,this.reservation).subscribe({
        next:()=>{
          this.toast.success("Reservation info updated successfully",'UPDATE');
        },
        error : (err)=>{this.toast.error(err)}
      })

    }
  }
  
    ngOnInit(): void {
      this.id=this.route.snapshot.params['id'];
      this.getReservationById(this.id);
      this.getActifDocs();
      this.getUsersInfo();
    }
}

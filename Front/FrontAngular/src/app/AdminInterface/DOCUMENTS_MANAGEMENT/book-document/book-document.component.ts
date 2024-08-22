import { Component, OnInit } from '@angular/core';
import { Document } from '../../../models/Document.model';
import { ToastrService } from 'ngx-toastr';
import { User } from '../../../models/User.model';
import { NgForm } from '@angular/forms';
import { DocumentService } from '../../../services/AdminServices/Documents/document.service';
import { Router,ActivatedRoute} from '@angular/router';
import { GestionUtilisateurService } from '../../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { ReservationService } from '../../../services/AdminServices/Reservations/reservation.service';
import { Reservation } from '../../../models/Reservation.model';
import { AuthenticationService } from '../../../services/authenticationService/authentication.service';

@Component({
  selector: 'app-book-document',
  templateUrl: './book-document.component.html',
  styleUrl: './book-document.component.css'
})
export class BookDocumentComponent implements OnInit{
  user : any[] = [];
  id! : number;
  reservation: Reservation = new Reservation();
  constructor(private router : Router,private route:ActivatedRoute,private docService : DocumentService,private toast : ToastrService,
    private userService : GestionUtilisateurService,private reservationService:ReservationService
  ,private auth :AuthenticationService){}

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
  
  confirmBooking():void{
    const userId = (document.getElementById('identifiant') as HTMLInputElement).value
    const dialog =confirm("you are about to book this document for the user : "+userId);
    if(dialog){
      this.reservation.idDoc=this.id;
      this.reservation.idUser=Number(userId);
      this.reservationService.addReservation(this.reservation).subscribe({
        next:()=>{
          this.toast.success("Reservation booked successfully",'INSERT');
          this.router.navigate(['/reservations']);
        },
        error:(err)=>
          this.toast.error("error"+err)
      })

    }
  }
  ngOnInit(): void {

    const role = this.auth.getRole();
    if(role=="User"){
      this.toast.warning("simple users are not allowed to use the admin dashboard");
      this.router.navigate(['/profile']);
    }
    else{
    this.id = this.route.snapshot.params['id'];
    this.getUsersInfo();
  }
  }
}

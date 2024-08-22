import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Document } from '../../../models/Document.model';
import { DocumentService } from '../../../services/AdminServices/Documents/document.service';
import { Router,ActivatedRoute} from '@angular/router';
import { GestionUtilisateurService } from '../../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { ReservationService } from '../../../services/AdminServices/Reservations/reservation.service';
import { Reservation } from '../../../models/Reservation.model';
@Component({
  selector: 'app-documents-user',
  templateUrl: './documents-user.component.html',
  styleUrl: './documents-user.component.css'
})
export class DocumentsUserComponent {
  docs : Document[]=[];
  user : any[] = [];
  userId !:number
  reservation: Reservation = new Reservation();
  constructor(private router : Router,private route:ActivatedRoute,private docService : DocumentService,private toast : ToastrService,
    private userService : GestionUtilisateurService,private reservationService:ReservationService){}



  bookDoc(id:number):void{
    
    const dialog =confirm("you are about to book this document !!");
    if(dialog){
      this.reservation.idDoc=id;
      this.reservation.idUser=Number(localStorage.getItem('userId'));
      this.reservationService.addReservation(this.reservation).subscribe({
        next:()=>{
          this.loadDocs();
          this.toast.success("Reservation booked successfully",'INSERT');
          this.router.navigate(['/reservation-user'])
        },
        error:(err)=>
          this.toast.error("error"+err)
      })

    }

  }
  

  loadDocs():void{
    this.docService.gettAllDocs().subscribe({
      next:(data)=>{
        this.docs = data;
        
      }
    })
  }
  ngOnInit(): void {
    this.loadDocs();
  }

}

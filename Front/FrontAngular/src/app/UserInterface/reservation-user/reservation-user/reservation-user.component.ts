import { Component, OnInit} from '@angular/core';
import { Reservation } from '../../../models/Reservation.model';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { ReservationService } from '../../../services/AdminServices/Reservations/reservation.service';
import { AuthenticationService } from '../../../services/authenticationService/authentication.service';
@Component({
  selector: 'app-reservation-user',
  templateUrl: './reservation-user.component.html',
  styleUrl: './reservation-user.component.css'
})
export class ReservationUserComponent implements OnInit{

  currentId !: number
  reservations : Reservation[]=[];
  constructor(private router :Router,private toast:ToastrService, private reservationService : ReservationService
    ,private auth :AuthenticationService
  ){}


  getAllReservation(id:number):void{
    var button = document.getElementById('toggleStatus');
    this.reservationService.getAllReservationbyUserId(id).subscribe({
      next : (data)=>{
        this.reservations = data;

      }
    })

  }
  navigateToUpdate(id:number):void{
  this.router.navigate(['/update-reservation',id]);
  }



  navigateToEmprunt(id:number):void{

    this.router.navigate(['add-emprunt',id]);
  }



  cancelReservation(id: number): void {
    const dialog = confirm("Are you sure you want to cancel this reservation ? \n it wont be available anymore!!");
    if(dialog){
    this.reservationService.cancelReservation(id).subscribe({
      next: () => {
        this.toast.success("Canceled");
        this.getAllReservation(this.currentId);
      }
    });
  }
  }



  ngOnInit(): void {

    this.currentId = Number(localStorage.getItem("userId"));
    this.getAllReservation(this.currentId);
  }

}

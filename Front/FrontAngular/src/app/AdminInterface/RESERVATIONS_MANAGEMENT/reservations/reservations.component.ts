import { Component, OnInit} from '@angular/core';
import { Reservation } from '../../../models/Reservation.model';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { ReservationService } from '../../../services/AdminServices/Reservations/reservation.service';
import { __importDefault } from 'tslib';
import { Observable } from 'rxjs';
import { NodeCompatibleEventEmitter } from 'rxjs/internal/observable/fromEvent';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrl: './reservations.component.css'
})
export class ReservationsComponent implements OnInit {

  reservations : Reservation[]=[];
  constructor(private router :Router,private toast:ToastrService, private reservationService : ReservationService){}

  displayToast():void{
    this.toast.info("choose a valid document from the list and click on the booking icon")
  }
  getAllReservation():void{
    var button = document.getElementById('toggleStatus');
    this.reservationService.getAllReservation().subscribe({
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
        this.getAllReservation();
      }
    });
  }
  }

  deleteReservation(id:number):void{
    const dialog=confirm("are you sure you want to delete this reservation!!");
    if(dialog){
    this.reservationService.deleteReservation(id).subscribe({
      next:()=>{
        //this.reservations = this.reservations.filter(this.reservations => this.reservations.idReservation !==id);
      this.reservations = this.reservations.filter(res =>res.idReservation !==id);
      this.toast.warning("Deleted","DELETE");
    },
    error:(err)=>{
      this.toast.error(err);
    }
    })
  }
}

  ngOnInit(): void {
    this.getAllReservation();
    
  }
}

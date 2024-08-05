import { Component, OnInit} from '@angular/core';
import { Reservation } from '../../../models/Reservation.model';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { ReservationService } from '../../../services/AdminServices/Reservations/reservation.service';
import { __importDefault } from 'tslib';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrl: './reservations.component.css'
})
export class ReservationsComponent implements OnInit {

  reservations : Reservation[]=[];
  constructor(private router :Router,private toast:ToastrService, private reservationService : ReservationService){}

  getAllReservation():void{
    var button = document.getElementById('toggleStatus');
    this.reservationService.getAllReservation().subscribe({
      next : (data)=>{
        this.reservations = data;
          
      }
    })

  }

  ngOnInit(): void {
    this.getAllReservation();
    
  }
}

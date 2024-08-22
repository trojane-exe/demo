import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authenticationService/authentication.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrl: './add-reservation.component.css'
})
export class AddReservationComponent implements OnInit {
  constructor(private auth :AuthenticationService,private toast : ToastrService,private router : Router){}
  ngOnInit(): void {
    const role = this.auth.getRole();
    if(role=="User"){
      this.toast.warning("simple users are not allowed to use the admin dashboard");
      this.router.navigate(['/profile']);
    }
    
  }

}

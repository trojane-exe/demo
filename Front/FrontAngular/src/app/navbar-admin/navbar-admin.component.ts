import { Component } from '@angular/core';
import { AuthenticationService } from '../services/authenticationService/authentication.service';
import {  ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar-admin',
  templateUrl: './navbar-admin.component.html',
  styleUrl: './navbar-admin.component.css'
})
export class NavbarAdminComponent {

  constructor(private authService :AuthenticationService , private toast : ToastrService, private router : Router){}


  isConnected():void{
    if(this.authService.getToken() === null){
      this.toast.info("Please log in or register if you are new ","Authentication",
        {positionClass: "toast-top-center"})
        this.router.navigate(['/login']);
      
    }
  }
  logout():void{
    this.authService.logout();
  }

}

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authenticationService/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  constructor(private router : Router,private auth : AuthenticationService){}

  logout():void{
    localStorage.removeItem('userId');
    this.auth.logout();
    this.router.navigate(['']);

  }

}

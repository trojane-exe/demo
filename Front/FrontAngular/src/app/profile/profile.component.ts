import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  profile={
    nom:'elbouazzaoui',
    prenom: 'soufiane',
    adresse: '123 Main St',
    email: 'test@gmail.com',
    password:'12345'
  }

}

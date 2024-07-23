import { Component } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {

  deleteUser(user:any){
    let id = this.listusers.indexOf(user);
    this.listusers.splice(id,1);

  }
  constructor (private router : Router){};

  listusers = [
    {
      id: 1,
      nom: 'Dupont',
      prenom: 'Jean',
      email: 'jean.dupont@example.com',
      adresse: '123 Rue de Paris, 75001 Paris, France',
      motDePasse: 'password123',
      role: 'Utilisateur',
      showPassword: false
    },
    {
      id: 2,
      nom: 'Martin',
      prenom: 'Sophie',
      email: 'sophie.martin@example.com',
      adresse: '456 Avenue des Champs-Élysées, 75008 Paris, France',
      motDePasse: 'securepassword',
      role: 'Admin',
      showPassword: false
    },
    {
      id: 3,
      nom: 'Leroy',
      prenom: 'Antoine',
      email: 'antoine.leroy@example.com',
      adresse: '789 Boulevard Saint-Germain, 75006 Paris, France',
      motDePasse: 'mypassword',
      role: 'Utilisateur',
      showPassword: false
    },
    {
      id: 4,
      nom: 'Dubois',
      prenom: 'Claire',
      email: 'claire.dubois@example.com',
      adresse: '321 Rue de Lyon, 69002 Lyon, France',
      motDePasse: 'clairepassword',
      role: 'Utilisateur'
    },
    {
      id: 5,
      nom: 'Moreau',
      prenom: 'Pierre',
      email: 'pierre.moreau@example.com',
      adresse: '654 Rue de Bordeaux, 33000 Bordeaux, France',
      motDePasse: 'pierr3pwd',
      role: 'Utilisateur',
      showPassword: false
    },
    {
      id: 6,
      nom: 'Lefevre',
      prenom: 'Marie',
      email: 'marie.lefevre@example.com',
      adresse: '987 Rue de Lille, 59000 Lille, France',
      motDePasse: 'marie123',
      role: 'Admin',
      showPassword: false
    }
  ]
  togglePasswordVisibility(user: any) {
    user.showPassword = !user.showPassword;
  }





}

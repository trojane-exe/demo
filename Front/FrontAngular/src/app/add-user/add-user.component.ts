import { Component } from '@angular/core';
import { User } from '../models/User.model';
import { TestingAPIService } from '../services/newUsers/testing-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {
  user: User = {
    idUser: null,
    nom: '',
    prenom: '',
    adresse: '',
    email: '',
    password: '',
    role: '',
  };
  roles : string[]=['Admin','User'];
  constructor(private router:Router,private userService : TestingAPIService){}
  addUser() {
    const n= (document.getElementById('nomtxt') as HTMLInputElement).value;
    const p= (document.getElementById('prenomtxt') as HTMLInputElement).value;
    const e= (document.getElementById('emailtxt') as HTMLInputElement).value;
    const a= (document.getElementById('adress') as HTMLInputElement).value;
    const r = (document.getElementById('r') as HTMLInputElement).value;
    if(!r ||! n || !p || !a||!e){
      this.user.nom=null;
      this.user.prenom=null;
      this.user.adresse=null;
      this.user.role=null;
      this.user.email=null;
      
    }
    else{this.user.role= r;
      
    }
    this.userService.addUser(this.user).subscribe({
      next: () => {
        console.log('Utilisateur ajouté avec succès !');
        
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout de l\'utilisateur', err);
      }
    });
  }
  passwordConfirm(){
    const pass = (document.getElementById('passwordId') as HTMLInputElement).value;
    const passConfirm = (document.getElementById('passwordIdConfirm') as HTMLInputElement).value;
    if (pass!=passConfirm){
      alert("the password arent matching");
    }
    else{
      this.router.navigate(['/users']);
    }
  }


}

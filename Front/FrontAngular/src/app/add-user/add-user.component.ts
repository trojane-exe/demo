import { Component, OnInit } from '@angular/core';
import { User } from '../models/User.model';
import { TestingAPIService } from '../services/newUsers/testing-api.service';
import { Router } from '@angular/router';
import { GestionUtilisateurService } from '../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { compileFactoryFunction } from '@angular/compiler';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent implements OnInit{


  user: User = new User();
  roles : string[]=['Admin','User'];
  constructor(private router:Router,private userService : GestionUtilisateurService){}
  ngOnInit(): void {
    
  }
  addUser() {
    const n= (document.getElementById('nomtxt') as HTMLInputElement).value;
    const p= (document.getElementById('prenomtxt') as HTMLInputElement).value;
    const e= (document.getElementById('emailtxt') as HTMLInputElement).value;
    const a= (document.getElementById('adress') as HTMLInputElement).value;
    const r = (document.getElementById('r') as HTMLInputElement).value;
    const pass = (document.getElementById('passwordId')as HTMLInputElement).value;
    const confirm = (document.getElementById('passwordIdConfirm') as HTMLInputElement).value;
    if (!n) {
      this.user.nom = null;
      alert('Nom is required');
      this.toastr.error('');
    } else {
      this.user.nom = n;
    }
  
    if (!p) {
      this.user.prenom = null;
      alert('Prenom is required');
    } else {
      this.user.prenom = p;
    }
  
    if (!e) {
      this.user.email = null;
      alert('Email is required');
    } else {
      this.user.email = e;
    }
  
    if (!a) {
      this.user.adresse = null;
      alert('Adresse is required');
    } else {
      this.user.adresse = a;
    }
  
    if (!r) {
      this.user.role = null;
      console.log('Role is required');
    } else {
      this.user.role = r;
    }
    if (!pass || !confirm) {
      alert('please fill the password and confirm it ');
    } else if (pass!=confirm){
      alert('the passwords arent matching!!')
    }
    else {
      this.user.password = p;
    }
    if (!this.user.nom || !this.user.prenom || !this.user.email || !this.user.adresse || !this.user.role || !this.user.password && pass!=confirm) {
      alert('Please fill in all required fields.\n and check the password');
      return; // Stop execution if any field is missing
    }
    else{
      if(pass===confirm){
    this.userService.addUser(this.user).subscribe({
      next: () => {
        console.log('Utilisateur ajouté avec succès !');
        
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout de l\'utilisateur', err);
      }
    });
  }
}
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

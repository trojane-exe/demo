import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User.model';
import { TestingAPIService } from '../../services/newUsers/testing-api.service';
import { Router } from '@angular/router';
import { GestionUtilisateurService } from '../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { compileFactoryFunction } from '@angular/compiler';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent implements OnInit{


  errormessage! : string;
  passconfirm! : string;
  passwordsMatch = true;
  user: User = new User();
  roles : string[]=['Admin','User'];
  constructor(private router:Router,private userService : GestionUtilisateurService){}
  ngOnInit(): void {
    
  }
  addUser(form :NgForm) {

    const r = (document.getElementById('r') as HTMLInputElement).value;
    const pass = (document.getElementById('passwordId')as HTMLInputElement).value;
    const confirm = (document.getElementById('passwordIdConfirm') as HTMLInputElement).value;
    if (!r) {
      this.user.role = 'User';
    } else {
      this.user.role = r;
    }

    if(form.invalid){
      this.errormessage = 'please fill all the required fields';
      return;
    }
    else{
      if (pass!=confirm){
        alert('the passwords arent matching!!')
      }
      else{
      this.userService.addUser(this.user).subscribe({
      next: () => {
        console.log('Utilisateur ajouté avec succès !');
        
      },
      error: (err) => {
        this.errormessage='Erreur lors de l\'ajout de l\'utilisateur',+err;
      }
    });
  }
    }
}
  


}

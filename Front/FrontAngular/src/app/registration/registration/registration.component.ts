import { Component, OnInit  } from '@angular/core';
import { User } from '../../models/User.model'; 
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../services/authenticationService/authentication.service';
import { GestionUtilisateurService } from '../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  errormessage! : string;
  passconfirm! : string;
  passwordsMatch = true;
  user: User = new User();
  roles : string[]=['Admin','User'];
  constructor(private router:Router,private userService : GestionUtilisateurService,
    private toast : ToastrService,private auth :AuthenticationService){}

    addUser(form :NgForm) {

      
      const pass = (document.getElementById('passwordId')as HTMLInputElement).value;
      const confirm = (document.getElementById('passwordIdConfirm') as HTMLInputElement).value;
  
      if(form.invalid){
        this.errormessage = 'please fill all the required fields';
        return;
      }
      else{
        if (pass!=confirm){
          alert('the passwords arent matching!!')
        }
        else{
        this.auth.register(
              
          this.user.nom,this.user.prenom,this.user.adresse,this.user.email,this.user.password,"User").subscribe({
        next: () => {
          this.toast.success("User added successfully",'INSERT');
          this.router.navigate(['/users']);
          
        },
        error: (err) => {
          this.errormessage='Erreur lors de l\'ajout de l\'utilisateur',+err;
        }
      });
    }
      }
  }

}

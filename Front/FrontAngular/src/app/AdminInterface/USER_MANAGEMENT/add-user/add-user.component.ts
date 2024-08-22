import { Component, OnInit  } from '@angular/core';
import { User } from '../../../models/User.model';
import { Router } from '@angular/router';
import { GestionUtilisateurService } from '../../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../../services/authenticationService/authentication.service';

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
  constructor(private router:Router,private userService : GestionUtilisateurService,
    private toast : ToastrService,private auth :AuthenticationService){}

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

    ngOnInit(): void {
      const role = this.auth.getRole();
      if(role=="User"){
        this.toast.warning("simple users are not allowed to use the admin dashboard");
        this.router.navigate(['/profile']);
      }
    
  }


}

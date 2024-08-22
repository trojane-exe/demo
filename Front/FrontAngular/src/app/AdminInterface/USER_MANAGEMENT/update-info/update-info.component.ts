import { Component, AfterViewInit,OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../../models/User.model';
import { GestionUtilisateurService } from '../../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { NgForm, NgModel } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../../services/authenticationService/authentication.service';
@Component({
  selector: 'app-update-info',
  templateUrl: './update-info.component.html',
  styleUrl: './update-info.component.css'
})
export class UpdateInfoComponent implements OnInit{


  errormessage! : string;
  roles : string[]=['Admin','User'];
  confirmfield! :any;
  user : User =  new User();
  id! : number
  constructor(private router : Router,
    private toastService : ToastrService,private userService :GestionUtilisateurService, 
    private route : ActivatedRoute, private auth :AuthenticationService){}





  updateUser(form:NgForm):void{

    const pass = (document.getElementById('passwordId')as HTMLInputElement).value;
    const confirm = (document.getElementById('passwordIdConfirm') as HTMLInputElement).value;
    if(form.invalid){
      this.errormessage = 'please fill all the required fields or respect the required form';
      return;
    }
    else{
      if (pass!=confirm){
        alert('the passwords arent matching!!')
      }
      else{
      this.userService.updateUser(this.id,this.user).subscribe({
      next: () => {
        this.router.navigate(['/users']);
        this.toastService.success('User added successfully!', 'UPDATE', {
          timeOut: 3000,
          toastClass: 'alert alert-success',
          positionClass:'toast-top-right'
        });
        
      },
      error: (err) => {
        this.errormessage='Erreur lors de la modification de l\'utilisateur',+err;
      }
    });
  }
    }


  }

  loadUserInfo(id : number) : void{
    this.userService.getUserById(id).subscribe({
      next : (data : User)=>{
        this.user = data
        this.confirmfield = this.user.password;

        
      },
      error :(err)=>{
        console.error('error',err);
      }
      
    })
  }





   ngOnInit(): void {
    const role = this.auth.getRole();
    if(role=="User"){
      this.toastService.warning("simple users are not allowed to use the admin dashboard");
      this.router.navigate(['/profile']);
    }
    else{
    this.id = this.route.snapshot.params['id'];
    this.loadUserInfo(this.id);
   }
  }
   


}

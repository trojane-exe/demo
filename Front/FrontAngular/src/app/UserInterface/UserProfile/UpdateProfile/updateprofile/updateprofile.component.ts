import { Component, AfterViewInit,OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../../../models/User.model';
  
import { GestionUtilisateurService } from '../../../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { NgForm, NgModel } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-updateprofile',
  templateUrl: './updateprofile.component.html',
  styleUrl: './updateprofile.component.css'
})
export class UpdateprofileComponent {




  confirmfield! :any;
  user : User =  new User();
  id! : number

  constructor(private router :Router,private userService : GestionUtilisateurService,
    private toast : ToastrService){}


  updateUser(form:NgForm):void{

    const pass = (document.getElementById('passwordId')as HTMLInputElement).value;
    const confirm = (document.getElementById('passwordIdConfirm') as HTMLInputElement).value;
    if(form.invalid){
      this.toast.warning('please fill all the required fields or respect the required form');
      return;
    }
    else{
      if (pass!=confirm){
        alert('the passwords arent matching!!')
      }
      else{
      this.userService.updateUser(this.id,this.user).subscribe({
      next: () => {
        this.router.navigate(['/profile']);
        this.toast.success('User added successfully!', 'UPDATE', {
          timeOut: 3000,
          toastClass: 'alert alert-success',
          positionClass:'toast-top-right'
        });
        
      },
      error: (err) => {
        this.toast.error('Erreur lors de la modification de l\'utilisateur'),+err;
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
    this.id = Number(localStorage.getItem('userId'));
    this.loadUserInfo(this.id);
   }
   


}

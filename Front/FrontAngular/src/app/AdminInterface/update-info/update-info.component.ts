import { Component, AfterViewInit,OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../models/User.model';
import { GestionUtilisateurService } from '../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { NgForm, NgModel } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
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
    private toastService : ToastrService,private userService :GestionUtilisateurService, private route : ActivatedRoute){}



  // toggleClick() {
  //   const button = document.getElementById('toggleBtn') as HTMLButtonElement;
  //   const inputpass = document.getElementById('passwordIdConfirm') as HTMLInputElement;
    
    

  //   if (inputpass.type === 'password') {
  //     inputpass.type = 'text';
  //     button.textContent = 'Hide';
  //   } else {
  //     inputpass.type = 'password';
  //     button.textContent = 'Show';
  //   }
  // }

  updateUser(form:NgForm):void{

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
      this.userService.updateUser(this.id,this.user).subscribe({
      next: () => {
        this.router.navigate(['/users']);
        this.toastService.success('User added successfully!', 'Success!', {
          timeOut: 3000,
          toastClass: 'alert alert-success',
          positionClass:'top-right'
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
        if(this.user.adresse==null){
          //this.user.adresse='undefined';
        }
        
      },
      error :(err)=>{
        console.error('error',err);
      }
      
    })
  }





   ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.loadUserInfo(this.id);
   }
   


}

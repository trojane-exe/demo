import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../../models/User.model';
import { ProfileService } from '../../../services/UserServices/profile.service';
import { ToastrService } from 'ngx-toastr';
import { AuthenticationService } from '../../../services/authenticationService/authentication.service';
import { GestionUtilisateurService } from '../../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { LoginComponent } from '../../../login/login.component';
import { SharedIDService } from '../../../services/sharedService/shared-id.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  userId!:number
  user :User=new User();
  newUserId!:number 

  constructor(private router : Router,private userService : GestionUtilisateurService,private profileService : ProfileService,private toast : ToastrService
    ,private authentication : AuthenticationService,private sharedService : SharedIDService){}



  getUser(id : number):void{
    this.userService.getUserById(id).subscribe({
      next : (data : User)=>{
        this.user = data        
      },
      error :(err)=>{
        console.error('error',err);
      }
      
    })

     



  }

  deleteAccount(){
    
    const dialog = confirm("are you sure you want to delete the account");
    if(dialog){
      
      this.userService.deleteUser(Number(localStorage.getItem('userId'))).subscribe();
      localStorage.removeItem('userId');
      this.authentication.logout();
      this.router.navigate(['']);
      this.toast.warning("this account has been deleted , all the reservations and emprunts are deleted too \n please register ")
      localStorage.removeItem('userId');
    }
    else{
      alert("nothing");
    }
    
  }



  toggleClick() {
    const button = document.getElementById('toggleBtn') as HTMLButtonElement;
    const inputpass = document.getElementById('passwordId') as HTMLInputElement;
    

    if (inputpass.type === 'password') {
      inputpass.type = 'text';
      button.textContent = 'Hide';
    } else {
      inputpass.type = 'password';
      button.textContent = 'Show';
    }
  }

  ngOnInit():void{
    const userIdFromStorage = localStorage.getItem('userId');
    if (userIdFromStorage) {
      this.userId = parseInt(userIdFromStorage, 10);
      console.log('User ID retrieved in ProfileComponent:', this.userId);
    } else {
      console.error('User ID is not available in localStorage.');
      // Handle the case where userId is not available, e.g., redirect to login
    }
    this.getUser(this.userId);
    }


    

  
    
  }

  



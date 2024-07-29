import { Component, AfterViewInit,OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../models/User.model';
import { GestionUtilisateurService } from '../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';
import { NgForm, NgModel } from '@angular/forms';
@Component({
  selector: 'app-update-info',
  templateUrl: './update-info.component.html',
  styleUrl: './update-info.component.css'
})
export class UpdateInfoComponent implements OnInit{



  roles : string[]=['Admin','User'];
  confirmfield! :any;
  user : User =  new User();
  id! : number
  constructor(private router : Router,private userService :GestionUtilisateurService, private route : ActivatedRoute){}



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

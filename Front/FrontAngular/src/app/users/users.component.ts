import { Component } from '@angular/core';
import { LoginComponent } from '../login/login.component';
import { Router } from '@angular/router';
import { TestingAPIService } from '../services/newUsers/testing-api.service';
import { User } from '../models/User.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
  listusers! :Array<any>;

  users:User[]=[];

  errorMessage! : string ;

  deleteUser(user:any){
    let id = this.listusers.indexOf(user);
    this.listusers.splice(id,1);

  }
  constructor (private router : Router,private userService : TestingAPIService){};

  
    
  togglePasswordVisibility(user: any) {
    user.showPassword = !user.showPassword;
  }

  loadUsers():void{
    this.userService.getAllUsers().subscribe({
      next : (data)=>{
        
        this.users = data;
      },
      error : (err)=>{
        console.error('failed to import data',err);
      }

    })
  }

  ngOnInit():void{
    this.loadUsers();
    // this.userService.getAllUsers().subscribe({
    //   next : (data)=>{
    //     this.listusers = data;
    //   },
    //   error : (error)=>{
    //     this.errorMessage = error;

    //   }
    // });

  }





}

import { Component } from '@angular/core';
import { LoginComponent } from '../../login/login.component';
import { Router } from '@angular/router';
//import { TestingAPIService } from '../services/newUsers/testing-api.service';
import { User } from '../../models/User.model';
import { GestionUtilisateurService } from '../../services/AdminServices/Utilisateurs/gestion-utilisateur.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent {
  listusers! :Array<any>;

  users:User[]=[];
  showPasswordMap = new Map<number,boolean>(); // i used this so i can toggle all the passwords in the table to show or hide them


  constructor (private router : Router,private userService : GestionUtilisateurService){};

  
    
  togglePasswordVisibility(userId: number) {
    const currentState = this.showPasswordMap.get(userId) || false;
    this.showPasswordMap.set(userId, !currentState);
  }



    //navigate to the update form and passing the id for the update : 
    //hadi bach nhez id li kayn fla ligne w nkhdem bih fl update
    navigateToUpdate( id : number) : void{
      this.router.navigate(['/update-profile' ,id]);
    }
  


  


  // khasni ndir les chams kamlin required bach manl9ach prb mea  input b string null





  deleteUser(id : number) : void{
    if (id === null) {
      console.error('User ID is null');
      return;
    }
    const dialog  = confirm("are you sure you want to delete this user!!");
    if(dialog){
      this.userService.deleteUser(id).subscribe({
        next: () => {
          this.users = this.users.filter(user => user.idUser !==id);
          alert("deleted successfully");
        },
        error: (err) => {
          console.error('error deleting',err);
        }



      })
    }
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

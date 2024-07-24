import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  profile = {
    id: 0,
    nom: '',
    prenom: '',
    adresse: '',
    email: '',
    password: ''
  };

  constructor(private router : Router){}



  deleteAccount(){
    const dialog = confirm("are you sure you want to delete the account");
    if(dialog){
      alert("i will handle the delete later");
      
      this.router.navigate(['']);

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
    this.profile={
      id:1,
      nom:'elbouazzaoui',
      prenom: 'soufiane',
      adresse: '123 Main St',
      email: 'test@gmail.com',
      password:'12345'
    }


    

  
    
  }

  


}

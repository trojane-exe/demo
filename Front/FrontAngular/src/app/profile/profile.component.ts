import { Component } from '@angular/core';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  profile={
    id:1,
    nom:'elbouazzaoui',
    prenom: 'soufiane',
    adresse: '123 Main St',
    email: 'test@gmail.com',
    password:'12345'
  }

  password: string = this.profile.password;

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
    
  }

  


}

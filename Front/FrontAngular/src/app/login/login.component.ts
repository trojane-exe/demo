import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor (private router : Router){}
  email :string='';
  password : string ='';
  userrole : string='';
  users =[
    {email:'user1@gmail.com',password:'123',role:'user'},
    {email:'admin1@gmail.com',password:'123',role:'admin'}
  ]
  onlogin(){
    var email = document.getElementById('emailtxt');
    var pass = document.getElementById('passtxt');
    var rolee
    const foundUser = this.users.find(u => u.email===this.email && u.password===this.password);
    if(foundUser!=null){
      this.userrole = foundUser.role;
      alert("you are an : "+this.userrole)
      if(this.userrole==='admin'){
        this.router.navigate(['/users']);
      }
      else{
        this.router.navigate(['/profile']);
      
      }
    }

  }

  
}

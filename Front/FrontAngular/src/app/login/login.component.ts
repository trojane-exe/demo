import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ProfileService } from '../services/UserServices/profile.service';
import { SharedIDService } from '../services/sharedService/shared-id.service';

import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../services/authenticationService/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  errorMessage: string | null = null;

  public static userId : number

  constructor(private fb: FormBuilder, private authService: AuthenticationService, private router: Router
    ,private toast : ToastrService,private profile : ProfileService
  , private sharedService : SharedIDService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }




  onLogin(): void {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.authService.login(email, password).subscribe({
        next: (response) => {
          this.authService.storeToken(response.token);
          this.profile.getUserIdByEmail(email).subscribe(
            (id: number) => {
              localStorage.setItem('userId', id.toString());
              console.log('User ID set in LoginComponent:', id);
              const role = this.authService.getRole();
              if (role === 'Admin') {
                this.router.navigate(['/users']);
              } else if (role === 'User') {
                this.router.navigate(['/profile']);
              }
            },
            (error) => {
              console.error('Error fetching user ID:', error);
            }
          );
        },
        error: (err) => {
          this.errorMessage = 'Invalid email or password';
          this.toast.error(this.errorMessage);
        }
      });
    }
  }

  ngOnInit(): void {
  }
}


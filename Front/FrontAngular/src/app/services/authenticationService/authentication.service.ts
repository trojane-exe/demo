import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

interface AuthResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private apiUrl = 'http://localhost:8080/api/auth'; 
  private tokenKey = 'authToken';
  private jwtHelper = new JwtHelperService();

  private authSubject = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/authenticate`, { email, password });
  }

  register(nom: string, prenom: string, adresse: string, email: string, password: string, role: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, { nom, prenom, adresse, email, password, role });
  }

  storeToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
    this.authSubject.next(true);
  }

  getToken(): string | null {
    
    return localStorage.getItem(this.tokenKey);
  }

  hasToken(): boolean {
    return !!this.getToken();
  }
  
  getRole(): string | null {
    const token = this.getToken();  // Retrieve the token from localStorage
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);  // Decode the token
      console.log('Decoded Token:', decodedToken);  // Debugging: Inspect the token structure
  
      // Assume the role is stored under a key like 'role' or 'authorities'
      if (decodedToken) {
        // Try to access the role based on the common claim names
        if (decodedToken.role) {
          return decodedToken.role;  // Return the role if it's directly available
        } else if (decodedToken.authorities && Array.isArray(decodedToken.authorities)) {
          return decodedToken.authorities[0];  // Return the first authority if it's stored as an array
        }
      }
    }
    return null;  // Return null if no role is found or the token is invalid
  }
  
  

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    this.authSubject.next(false);
    this.router.navigate(['/login']);
  }

  isAuthenticated(): Observable<boolean> {
    return this.authSubject.asObservable();
  }

  getAuthHeader(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${this.getToken()}`
    });
  }
}

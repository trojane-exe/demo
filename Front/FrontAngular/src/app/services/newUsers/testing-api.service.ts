import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../models/User.model';

@Injectable({
   providedIn: 'root'
})
export class TestingAPIService {
  private apiUrl = 'http://localhost:8080/api/users';

   constructor(private http : HttpClient) { }

  addUser(user: any): Observable<HttpResponse<string>> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.apiUrl}/add_user`, user, { headers, responseType: 'text', observe: 'response' });
  }
    getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/users_home`);
  }

  deleteUser(id:number): Observable<{message:string}>{
    return this.http.delete<{message : string}>(`${this.apiUrl}/delete_user/${id}`);
  }
 }

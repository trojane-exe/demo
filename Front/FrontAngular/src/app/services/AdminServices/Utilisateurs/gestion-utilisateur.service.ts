import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../../models/User.model';

@Injectable({
  providedIn: 'root'
})
export class GestionUtilisateurService {
  private apiUrl= 'http://localhost:8080/api/users';



  constructor(private http : HttpClient) { }


  addUser(user: User): Observable<HttpResponse<string>> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(`${this.apiUrl}/add_user`, user, { headers, responseType: 'text', observe: 'response' });
  }
   getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/users_home`);
  }
  getUserById(id:number) : Observable<User>{
    return this.http.get<User>(`${this.apiUrl}/${id}`);

  }
  updateUser(id:number,user:User) : Observable<HttpResponse<string>>{
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(`${this.apiUrl}/update_user/${id}`,user,{headers,responseType:'text',observe:'response'});
  }

  deleteUser(id:number): Observable<HttpResponse<any>>{
    return this.http.delete<any>(`${this.apiUrl}/delete_user/${id}`);
  }
  getInfoForBooking():Observable<any>{
    return this.http.get<any>(`${this.apiUrl}/bookingId`);
  }
}

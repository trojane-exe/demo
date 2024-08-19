import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpHeaders, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../models/User.model';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private apiUrl= 'http://localhost:8080/api/users';

  constructor(private http : HttpClient) { }

  getUserById(id:number) : Observable<User>{
    return this.http.get<User>(`${this.apiUrl}/${id}`);

  }

  getUserIdByEmail(email: string): Observable<number> {
    const params = new HttpParams().set('email', email);
    return this.http.get<number>(`${this.apiUrl}/getId`,{params});
  }

  updateUser(id:number,user:User) : Observable<HttpResponse<string>>{
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(`${this.apiUrl}/update_user/${id}`,user,{headers,responseType:'text',observe:'response'});
  }

  deleteUser(id:number): Observable<HttpResponse<any>>{
    return this.http.delete<any>(`${this.apiUrl}/delete_user/${id}`);
  }
}

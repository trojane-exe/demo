import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Emprunt } from '../../../models/Emprunt.model';
import { Reservation } from '../../../models/Reservation.model';

@Injectable({
  providedIn: 'root'
})
export class EmpruntService {
  private apiUrl ='http://localhost:8080/api/emprunt';

  constructor(private http:HttpClient) { }

  getAllEmprunt():Observable<Emprunt[]>{
    return this.http.get<Emprunt[]>(`${this.apiUrl}/home`);
  }
  // getEmpruntById(id:number):Observable<Emprunt[]>{
  //   return this.http.get<Emprunt[]>(`${this.apiUrl}/`)
  // }

  addEmprunt(emprunt:Emprunt):Observable<HttpResponse<string>>{
    const headers = new HttpHeaders({'Content-Type':'application/json'});
    return this.http.post(`${this.apiUrl}/add_emprunt`,emprunt,{headers,responseType:'text',observe:'response'});
  }

  deleteEmprunt(id:number):Observable<HttpResponse<any>>{
    return this.http.delete<any>  (`${this.apiUrl}/delete_emprunt/${id}`)
  }

  
}

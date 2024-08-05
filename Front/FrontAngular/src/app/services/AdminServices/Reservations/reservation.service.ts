import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation } from '../../../models/Reservation.model';



@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl= 'http://localhost:8080/api/reservation';

  constructor(private http : HttpClient) { }


  getAllReservation():Observable<Reservation[]>{
    return this.http.get<Reservation[]>(`${this.apiUrl}/res_home`);
  }

  addReservation(reservation : Reservation):Observable<HttpResponse<string>>{
    const headers = new HttpHeaders({'Content-Type':'application/json'});
    return this.http.post(`${this.apiUrl}/add_reservation`,reservation,{headers,responseType:'text',observe:'response'});
  }
  
  updateReservation(id:number,reservation:Reservation):Observable<HttpResponse<string>>{
    const headers = new HttpHeaders({'ContentType':'application/json'});
    return this.http.put(`${this.apiUrl}/update_res/${id}`,reservation,{headers,responseType:'text',observe:'response'});
  }

  deleteReservation(id:number):Observable<HttpResponse<any>>{
    return this.http.delete<any>(`${this.apiUrl}/delete_res/${id}`);
  }
  
}

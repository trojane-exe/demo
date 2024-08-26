import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpHeaders, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from '../../../models/Transaction.model';

@Injectable({
  providedIn: 'root'
})
export class HistoriqueService {
  private apiUrl= 'http://localhost:8080/api/historique';



  constructor( private http : HttpClient) { }
  getTransactionByUserId(id:number) : Observable<Transaction[]>{
    return this.http.get<Transaction[]>(`${this.apiUrl}/${id}`);

  }
}

import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Emprunt } from '../../../models/Emprunt.model';

@Injectable({
  providedIn: 'root'
})
export class EmpruntsUserService {

  private apiUrl= 'http://localhost:8080/api/emprunt';


  constructor(private http : HttpClient) { }


  getEmpruntsByUserId(id:number) : Observable<Emprunt[]>{
    return this.http.get<Emprunt[]>(`${this.apiUrl}/emprunt-user/${id}`);

  }
}

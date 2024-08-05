import { Injectable } from '@angular/core';
import { HttpClient,HttpResponse,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Document } from '../../../models/Document.model';


@Injectable({
  providedIn: 'root'
})
export class DocumentService {
  private apiUrl = 'http://localhost:8080/api/documents'

  constructor(private http : HttpClient) { }
  
  gettAllDocs() : Observable<Document[]>{
    return this.http.get<Document[]>(`${this.apiUrl}/doc_home`);
  }
  addDoc(document : Document):Observable<HttpResponse<string>>{
    const headers = new HttpHeaders({'Content-Type':'application/json'});
    return this.http.post(`${this.apiUrl}/add_doc` , document , {headers , responseType : 'text' ,observe : 'response'});
  }
  updateDoc(id : number , document : Document) : Observable<HttpResponse<string>>{
    const headers = new HttpHeaders({'Content-Type':'application/json'});
    return this.http.put(`${this.apiUrl}/update_doc/${id}`,document,{headers,responseType:'text',observe:'response'});
  }
  getDocById(id:number) : Observable<Document>{
    return this.http.get<Document>(`${this.apiUrl}/${id}`);
  }
  deleteDoc(id:number) : Observable<HttpResponse<any>>{
    return this.http.delete<any>(`${this.apiUrl}/delete_doc/${id}`);
  }
}

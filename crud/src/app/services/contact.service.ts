import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { CONTACT } from '../clases/Contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  private BASE_URL:string = "http://localhost:8080/api/contacto"
  private httpHeaders:HttpHeaders = new HttpHeaders({'Content-Type' : 'application/json'})

  constructor(private http:HttpClient) { }

  addContact(contact:CONTACT):Observable<CONTACT>{ 
    return this.http.post<CONTACT>(this.BASE_URL, contact, {headers: this.httpHeaders})
  }
}

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Progettista } from 'src/app/models/progettista';
import { Ente } from 'src/app/models/ente';
import { Esperto } from 'src/app/models/esperto';
import { Proponente } from 'src/app/models/proponente';

@Injectable({
  providedIn: 'root'
})
export class UserManagerService {

  constructor(private httpClient: HttpClient) {}

  authHeader = {
    Authorization : sessionStorage.getItem("Bearer")
  }

  getUserUrl="http://localhost:8080/progettista/getProgettistaById"

  // Ritorna l'Ente tramite id
  getEnte(id:number): Observable<Ente>{
    const params = new HttpParams().set('id', id.toString());
    return this.httpClient.get<Ente>(this.getUserUrl,{params, headers:this.authHeader});
  }

  // Ritorna l'esperto tramite id
  getExpert(id:number): Observable<Esperto>{
    const params = new HttpParams().set('id', id.toString());
    return this.httpClient.get<Esperto>(this.getUserUrl,{params, headers:this.authHeader});
  }

  // Ritorna il proponente tramite id
  getProponente(id:number): Observable<Proponente>{
    const params = new HttpParams().set('id', id.toString());
    return this.httpClient.get<Proponente>(this.getUserUrl,{params, headers:this.authHeader});
  }

  // Ritorna il progettista tramite id
  getProgettista(id:number): Observable<Progettista>{
    const params = new HttpParams().set('id', id.toString());
    return this.httpClient.get<Progettista>(this.getUserUrl,{params, headers:this.authHeader});
  }
}

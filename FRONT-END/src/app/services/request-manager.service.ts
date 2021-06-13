import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ente } from 'src/app/models/ente';
import { Progetto } from 'src/app/models/progetto';

@Injectable({
  providedIn: 'root'
})
export class RequestManagerService {

  constructor(private httpClient: HttpClient) {}

  authHeader = {
    Authorization: sessionStorage.getItem("Bearer")
  }

  // Richiesta per essere assunto da un ente
  richiestaEnte(id: number): Observable<Ente> {
    return this.httpClient.post<Ente>("http://localhost:8080/progettista/richiestaEnte", id, { headers: this.authHeader })
  }

  // Richiesta per candidarsi ad un progetto
  candidaturaProgettista(id: number): Observable<Progetto> {
    return this.httpClient.post<Progetto>("http://localhost:8080/progettista/candidaturaProgetto", id, { headers: this.authHeader })
  }

  // Richiesta per candidarsi ad un progetto
  candidaturaEsperto(id: number): Observable<Progetto> {
    return this.httpClient.post<Progetto>("http://localhost:8080/esperto/proponiEsperto", id, { headers: this.authHeader })
  }

  // Richiesta per assumere un candidato (tramite id)
  accettaProgettista(id: number) {
    return this.httpClient.post<any>("http://localhost:8080/ente/accettaProgettista", id, { headers: this.authHeader })
  }

}

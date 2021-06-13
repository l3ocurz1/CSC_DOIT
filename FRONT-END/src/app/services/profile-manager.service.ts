import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Progettista} from '../models/progettista';

@Injectable({
  providedIn: 'root'
})
export class ProfileManagerService {

  constructor(private httpClient: HttpClient) {
  }

  profiloURL = 'http://localhost:8080/progettista/profilo';
  modificaCredenzialiURL = 'http://localhost:8080/progettista/aggiornaCredenziali';
  nuoveNotificheURL = 'http://localhost:8080/progettista/nuoveNotifiche';
  cancellaNotificaURL = 'http://localhost:8080/progettista/cancellaNotifica'
  cancellaProgettoURL = 'http://localhost:8080/proponente/removeProgetto';
  abbandonaEnteURL = 'http://localhost:8080/progettista/abbandonaEnte';
  accettaProgettistaURL = 'http://localhost:8080/ente/accettaProgettista';
  rifiutaProgettistaURL = 'http://localhost:8080/ente/rifiutaProgettista';
  espelliProgettistaURL = 'http://localhost:8080/ente/espelliProgettista';
  cancellaRichiestaEnteURL = 'http://localhost:8080/progettista/annullaRichiestaEnte'
  authHeader = {Authorization: sessionStorage.getItem('Bearer')};


  // Ritorna il profilo dell'utente loggato
  getProfile(): Observable<Progettista>  {
    return this.httpClient.get<Progettista>(this.profiloURL, {headers: this.authHeader});
}

  // Modifica le credenziali dell'utente correntemente loggato
  modificaProfilo(username: string, email: string, password: string) {
    let params = new HttpParams();
    params = params
      .append('username', username.toString())
      .append('email', email.toString())
      .append('password', password.toString());
    return this.httpClient.post<void>(this.modificaCredenzialiURL, null, {params: params, headers: this.authHeader});
  }

  // Ritorna il numero di notifiche non lette
  nuoveNotifiche(): Observable<number> {
    return this.httpClient.get<number>(this.nuoveNotificheURL, {headers: this.authHeader});
  }

  // Cancella una notifica
  cancellaNotifica(id: number) {
    let params = new HttpParams();
    params = params.append('idNotifica', id.toString());
    return this.httpClient.post(this.cancellaNotificaURL, null, {params: params, headers: this.authHeader});
  }

  // Cancella la richiesta presentata per un ente
  cancellaRichiestaEnte(id: number){
    let params = new HttpParams();
    params = params.append('idEnte', id.toString());
    return this.httpClient.post(this.cancellaRichiestaEnteURL, null, {params: params, headers: this.authHeader});
  }

  // Abbandona un ente
  abbandonaEnte(id: number){
    let params = new HttpParams();
    params = params.append('idEnte', id.toString());
    return this.httpClient.post(this.abbandonaEnteURL, null, {params:params, headers:this.authHeader});
  }

  // Accetta un progettista come progettista dell'ente
  accettaProgettista(id: number){
    let params = new HttpParams();
    params = params.append('idProgettista', id.toString());
    return this.httpClient.post(this.accettaProgettistaURL, null, {params:params, headers:this.authHeader});
  }

  // Rifiuta un progettista come progettista dell'ente
  rifiutaProgettista(id: number){
    let params = new HttpParams();
    params = params.append('idProgettista', id.toString());
    return this.httpClient.post(this.rifiutaProgettistaURL, null, {params: params, headers: this.authHeader});
  }

  // Espelli un progettista
  espelliProgettista(id: number){
    let params = new HttpParams();
    params = params.append('idProgettista', id.toString());
    return this.httpClient.post(this.espelliProgettistaURL, null, {params:params, headers:this.authHeader});
  }

  // Cancella un progetto di un proponente loggato
  cancellaProgetto(id: number){
    let params = new HttpParams();
    params = params.append('idProgetto', id.toString());
    return this.httpClient.post(this.cancellaProgettoURL, null, {params:params, headers:this.authHeader})
  }
}

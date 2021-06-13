import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';
import {AuthRequest} from '../models/authRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationManagerService implements CanActivate {

  constructor(private route: Router, private httpClient: HttpClient) { }

  authenticateURL = 'http://localhost:8080/guest/authenticate';
  signupProgettistaURL = 'http://localhost:8080/guest/signupProgettista';
  signupProponenteURL = 'http://localhost:8080/guest/signupProponente'
  signupEnteURL = 'http://localhost:8080/guest/signupEnte';
  signupEspertoURL = 'http://localhost:8080/guest/signupEsperto';

  // Login - Ritorna il JWT
  autentica(authRequest: AuthRequest): Observable<string> {
    return this.httpClient.post(this.authenticateURL, authRequest, {"responseType": 'text'})
  }

  // Registrazione utente
  registra = (utente: User, type: Number): Observable<Object> => {
    if (type == 1) {
      return this.httpClient.post(this.signupProponenteURL, utente);
    } else if (type == 2) {
      return this.httpClient.post(this.signupProgettistaURL, utente);
    } else if (type == 3) {
      return this.httpClient.post(this.signupEnteURL, utente);
    } else if (type == 4) {
      return this.httpClient.post(this.signupEspertoURL, utente);
    }
  }

  // Ritorna true se l'utente è loggato, false altrimenti
  isLogged = () => (sessionStorage.getItem("Bearer") != null) ? true : false;

  // Pulisce il session storage e il local storage
  clearAll() {sessionStorage.clear(); localStorage.clear();}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this.isLogged()) {
      this.route.navigate(['login']);
      return false;
    } else { return true; }
  }
}

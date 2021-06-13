import { HttpClient, HttpHeaders, HttpParams, } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Competenza } from '../models/competenza';

@Injectable({
  providedIn: 'root'
})
export class CompetenceManagerService {

  constructor(private httpClient: HttpClient) { }

  //Sicurezza
  authHeader = {
    Authorization: sessionStorage.getItem("Bearer")
  }

  // Ritorna la lista di tutte le competenze(requisiti)
  listCompetenze = (): Observable<Competenza[]> => {
    return this.httpClient.get<Competenza[]>("http://localhost:8080/progettista/listaCompetenze");
  }

  // Ritorna solo le competenze non inserite nel profilo
  getAltreCompetenze = (): Observable<Competenza[]> => {
    return this.httpClient.get<Competenza[]>("http://localhost:8080/progettista/competenzeNonPossedute", { headers: this.authHeader });
  }

  // Ritorna solo i requisiti inseriti nel progetto
  getRequisitiProgetto = (id_progetto: Number): Observable<Competenza[]> => {
    const params = new HttpParams().set('id', id_progetto.toString());
    return this.httpClient.get<Competenza[]>("http://localhost:8080/proponente/getRequisitiProgetto", { params, headers: this.authHeader });
  }

  // Ritorna solo i requisiti non inseriti nel progetto
  getAltriRequisiti = (id_progetto: Number): Observable<Competenza[]> => {
    const params = new HttpParams().set('id', id_progetto.toString());
    return this.httpClient.get<Competenza[]>("http://localhost:8080/proponente/getAltriRequisiti", { params, headers: this.authHeader });
  }

  // Aggiunge una competenza al profilo
  addCompetenza = (nomeCompetenza: string) => {
    return this.httpClient.post<any>("http://localhost:8080/progettista/aggiungiCompetenza", nomeCompetenza, { headers: this.authHeader });
  }

  // Rimuove una competenza dal profilo
  removeCompetenza = (nomeCompetenza: string) => {
    return this.httpClient.post<any>("http://localhost:8080/progettista/removeCompetenza", nomeCompetenza, { headers: this.authHeader });
  }

  // Aggiunge un requisito al progetto
  addRequisito = (nomeRequisito:string, id_progetto:Number) => {
    let body = new HttpParams();
    body=body.append('nomeCompetenza',nomeRequisito);
    body=body.append('idProgetto',id_progetto.toString());
    return this.httpClient.post<any>("http://localhost:8080/proponente/aggiungiRequisito", null, {params:body, headers: this.authHeader })
  }

  // Rimuove un requisito dal progetto
  removeRequisito = (nomeRequisito:string, id_progetto:Number) => {
    let body = new HttpParams();
    body=body.append('nomeCompetenza',nomeRequisito);
    body=body.append('idProgetto',id_progetto.toString());
    return this.httpClient.post<any>("http://localhost:8080/proponente/removeRequisito", null, {params:body, headers: this.authHeader })
  }
}

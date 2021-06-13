import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Progetto } from 'src/app/models/progetto';
import {Competenza} from '../models/competenza';

@Injectable({
  providedIn: 'root'
})
export class ProjectManagerService {

  constructor(private httpClient: HttpClient) {}

  getProgettoURL = 'http://localhost:8080/progettista/getProgetto';
  getProgettiPubblicatiURL = 'http://localhost:8080/proponente/getProgettiPubblicati';
  creaProgettoURL = 'http://localhost:8080/proponente/creaProgetto';
  approvaProgettistaURL = 'http://localhost:8080/proponente/approvaProgettista';
  abbandonaProgettoURL = 'http://localhost:8080/progettista/abbandonaProgetto';
  getAltriRequisitiURL = 'http://localhost:8080/proponente/getAltriRequisiti';
  candidaturaEspertoURL = 'http://localhost:8080/esperto/proponiEsperto';
  approvaEspertoURL = 'http://localhost:8080/proponente/approvaEsperto';
  rifiutaEspertoURL = 'http://localhost:8080/proponente/rifiutaEsperto';
  completaValutazioneURL = 'http://localhost:8080/esperto/completaValutazione';
  rimozioneCandidaturaURL = 'http://localhost:8080/progettista/rimozioneCandidatura';
  setStatoChiusoURL = 'http://localhost:8080/proponente/setProgettoChiuso';
  setStatoCompletatoURL = 'http://localhost:8080/proponente/setProgettoCompletato';
  rifiutaProgettistaURL = 'http://localhost:8080/proponente/rifiutaProgettista';
  espelliProgettistaURL = 'http://localhost:8080/proponente/espelliProgettista';
  progettistaAppartenenteURL = 'http://localhost:8080/progettista/progettistaAppartenente';
  progettistaCoinvoltoURL = 'http://localhost:8080/progettista/progettistaCoinvolto';
  enteAppartenenteURL = 'http://localhost:8080/progettista/enteAppartenente';

  authHeader = {
    Authorization : sessionStorage.getItem("Bearer")
  }

  // Progetti pubblicati dal proponente loggato
  listProjects():  Observable<Progetto[]> {
    return this.httpClient.get<Progetto[]>(this.getProgettiPubblicatiURL,{headers: this.authHeader});
  }

  // Ritorna il progetto selezionato (tramite id)
  getProject(id:number): Observable<Progetto>{
    const params = new HttpParams().set('id', id.toString());
    return this.httpClient.get<Progetto>(this.getProgettoURL,{params, headers:this.authHeader});
  }

  // Ritorna la lista delle competenze non richieste dal progetto
  getAltriRequisiti(id: number): Observable<Competenza[]>{
    let params = new HttpParams();
    params = params.append('id', id.toString());
    return this.httpClient.get<Competenza[]>(this.getAltriRequisitiURL, {params: params, headers: this.authHeader});
  }

  // Richiesta per salvare un progetto appena creato
  addProject(project: Progetto){
    return this.httpClient.post<any>(this.creaProgettoURL, project,  {headers:this.authHeader})
  }

  // Approva un candidato al progetto spostandolo nei partecipanti
  approve(id_progetto: number, id_candidato: number){
    let params = new HttpParams();
    params = params.append('idProgettista', id_candidato.toString());
    params = params.append('idProgetto', id_progetto.toString());
    return this.httpClient.post(this.approvaProgettistaURL, null ,{params, headers:this.authHeader})
  }

  // Rifiuta un candidato al progetto eliminandolo dalla lista candidati
  rifiutaCandidato(idProgettista: number, idProgetto: number){
    let params = new HttpParams();
    params = params.append('idProgettista', idProgettista.toString());
    params = params.append('idProgetto', idProgetto.toString());
    return this.httpClient.post(this.rifiutaProgettistaURL, null, {params: params, headers: this.authHeader});
  }

  // Espelle un progettista dal progetto
  espelliProgettista(idProgettista: number, idProgetto: number){
    let params = new HttpParams();
    params = params.append('idProgettista', idProgettista.toString());
    params = params.append('idProgetto', idProgetto.toString());
    return this.httpClient.post(this.espelliProgettistaURL, null, {params: params, headers: this.authHeader});
  }

  // Abbandona un progetto
  abbandono(id:number){
    let params = new HttpParams();
    params = params.append('idProgetto', id.toString());
    return this.httpClient.post(this.abbandonaProgettoURL,null,{params: params,headers:this.authHeader});
  }

  // Cancella la candidature
  cancella(idProgetto: number){
    let params = new HttpParams();
    params = params.append('idProgetto', idProgetto.toString());
    return this.httpClient.post(this.rimozioneCandidaturaURL, null, {params:params, headers:this.authHeader});
  }

  // Candidatura esperto
  candidaturaEsperto(idProgetto: number){
    let params = new HttpParams();
    params = params.append('idProgetto', idProgetto.toString());
    return this.httpClient.post(this.candidaturaEspertoURL, null, {params: params, headers: this.authHeader});
  }

  // Approva esperto
  approvaEsperto(idEsperto: number, idProgetto: number){
    let params = new HttpParams();
    params = params.append('idEsperto', idEsperto.toString());
    params = params.append('idProgetto', idProgetto.toString());
    return this.httpClient.post(this.approvaEspertoURL, null, {params: params, headers: this.authHeader});
  }

  // Rifiuta un esperto candidato
  rifiutaEsperto(idEsperto: number, idProgetto: number){
    let params = new HttpParams();
    params = params.append('idEsperto', idEsperto.toString());
    params = params.append('idProgetto', idProgetto.toString());
    return this.httpClient.post(this.rifiutaEspertoURL, null, {params: params, headers: this.authHeader});
  }

  // Completa valutazione progetto - passa allo stato aperto
  completaValutazione(id: number){
    let params = new HttpParams();
    params = params.append('idProgetto', id.toString());
    return this.httpClient.post(this.completaValutazioneURL, null, {params:params, headers:this.authHeader});
  }

  // Passa allo stato CHIUSO
  setProgettoChiuso(id: number){
    let params = new HttpParams();
    params = params.append('idProgetto', id.toString());
    return this.httpClient.post(this.setStatoChiusoURL, null, {params: params, headers: this.authHeader});
  }

  // Passa allo stato COMPLETATO

  setProgettoCompletato(id: number){
    let params = new HttpParams();
    params = params.append('idProgetto', id.toString());
    return this.httpClient.post(this.setStatoCompletatoURL, null, {params: params, headers: this.authHeader});
  }

  // Ritorna true se il progettista loggato appartiene all'ente
  getProgettistaAppartenete(idEnte: number){
    let params = new HttpParams();
    params = params.append('idEnte', idEnte.toString());
    return this.httpClient.get<boolean>(this.progettistaAppartenenteURL,{params: params, headers: this.authHeader});
  }

  // Ritorna true se il progettista loggato è candidato o in carico al progetto, false altrimenti
  getProgettistaCoinvolto(idProgetto: number){
    let params = new HttpParams();
    params = params.append('idProgetto', idProgetto.toString());
    return this.httpClient.get<boolean>(this.progettistaCoinvoltoURL, {params: params, headers: this.authHeader});
  }

  // Ritorna true se uno degli enti del progettista loggato ha il progetto inc arico, false altrimenti
  getEnteAppartenente(idProgetto: number){
    let params = new HttpParams();
    params = params.append('idProgetto', idProgetto.toString());
    return this.httpClient.get<boolean>(this.enteAppartenenteURL, {params: params, headers: this.authHeader});
  }
}

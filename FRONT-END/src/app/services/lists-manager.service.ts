import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Progettista } from 'src/app/models/progettista';
import { Ente } from 'src/app/models/ente';
import { Esperto } from 'src/app/models/esperto';
import { Progetto } from 'src/app/models/progetto';
import { Proponente } from 'src/app/models/proponente';

@Injectable({
  providedIn: 'root'
})
export class ListsManagerService {

  constructor(private httpClient: HttpClient) { }

  listDesignersURL = 'http://localhost:8080/guest/listaProgettisti';
  listProponentsURL = 'http://localhost:8080/guest/listaProponenti';
  listEntitiesURL = 'http://localhost:8080/guest/listaEnti';
  listExpertsURL = 'http://localhost:8080/guest/listaEsperti';
  listProjectsURL = 'http://localhost:8080/guest/listaProgetti';

  authHeader = {
    Authorization: sessionStorage.getItem("Bearer")
  }

  // Ritorna la lista di tutti i progettisti
  listDesigners(): Observable<Progettista[]>{
    return this.httpClient.get<Progettista[]>(this.listDesignersURL);
  }

  // Ritorna la lista di tutti i proponenti
  listProponents(): Observable<Proponente[]>{
    return this.httpClient.get<Proponente[]>(this.listProponentsURL);
  }

  // Ritorna la lista di tutti gli enti
  listEntities(): Observable<Ente[]>{
    return this.httpClient.get<Ente[]>(this.listEntitiesURL);
  }

  // Ritorna la lista di tutti gli esperti
  listExperts(): Observable<Esperto[]>{
    return this.httpClient.get<Esperto[]>(this.listExpertsURL);
  }

  // Ritorna la lista di tutti i progetti
  listProjects(): Observable<Progetto[]>{
    return this.httpClient.get<Progetto[]>(this.listProjectsURL);
  }
}

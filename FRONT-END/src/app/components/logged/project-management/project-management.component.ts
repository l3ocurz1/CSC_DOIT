import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Competenza } from 'src/app/models/competenza';
import { Progettista } from 'src/app/models/progettista';
import { CompetenceManagerService } from 'src/app/services/competence-manager.service';
import { ProjectManagerService } from 'src/app/services/project-manager.service';
import {Progetto} from '../../../models/progetto';
import {Statoprogetto} from '../../../models/statoprogetto';
import {ProfileManagerService} from '../../../services/profile-manager.service';

@Component({
  selector: 'app-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.css']
})
export class ProjectManagementComponent implements OnInit {

  progetto: Progetto;
  id_progetto: number;
  altri_requisiti: Competenza[];
  partecipanti: Progettista[];
  candidati: Progettista[];
  loggato: Progettista;


  constructor(private router: Router,
              private route:ActivatedRoute,
              private projectManager: ProjectManagerService,
              private competenceManager: CompetenceManagerService,
              private profileManager: ProfileManagerService) { }

  ngOnInit(): void {
    this.id_progetto = this.route.snapshot.params['id'];
    this.getProject(this.id_progetto);
    this.getAltriRequisiti(this.id_progetto);
    this.getLoggato();
  }

  // Ritorna il progetto selezionato
  getProject(id: number){
    this.projectManager.getProject(id).subscribe(data => {
      this.progetto = data;
    })
  }

  // Prende l'utente loggato
  getLoggato(){
    this.profileManager.getProfile().subscribe(data => {
      this.loggato = data;
    })
  }

  getAltriRequisiti(id: number){
    this.projectManager.getAltriRequisiti(id).subscribe(data => {
      this.altri_requisiti = data;
    })
  }

  accettaCandidato(id:number){
    this.projectManager.approve(this.id_progetto, id).subscribe(() =>
      this.ngOnInit());
  }

  rifiutaCandidato(idProgettista: number, idProgetto: number) {
    this.projectManager.rifiutaCandidato(idProgettista, idProgetto).subscribe(() => window.location.reload());
  }

  espelliProgettista(idProgettista: number, idProgetto: number) {
    this.projectManager.espelliProgettista(idProgettista, idProgetto).subscribe(() => window.location.reload());
  }

  aggiungiRequisito(nome_requisito:string){
    this.competenceManager.addRequisito(nome_requisito, this.id_progetto).subscribe(() => {
      this.ngOnInit();
    })
  }

  rimuoviRequisito(nome_requisito:string){
    this.competenceManager.removeRequisito(nome_requisito, this.id_progetto).subscribe(() => {
      this.ngOnInit();
    })
  }

  // Torna alla pagina profilo
  salva(){
    this.router.navigate(['profile']);
  }

  completaValutazione(id: number) {
    this.projectManager.completaValutazione(id).subscribe(() => window.location.reload());
  }

  setProgettoChiuso(id: number) {
    this.projectManager.setProgettoChiuso(id).subscribe(() => window.location.reload());
  }

  setProgettoCompletato(id: number) {
    this.projectManager.setProgettoCompletato(id).subscribe(() => window.location.reload());
  }
}

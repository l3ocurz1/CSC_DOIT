import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Progetto} from 'src/app/models/progetto';
import {User} from 'src/app/models/user';
import {ProfileManagerService} from 'src/app/services/profile-manager.service';
import {ProjectManagerService} from 'src/app/services/project-manager.service';
import {RequestManagerService} from 'src/app/services/request-manager.service';
import {Ente} from '../../../models/ente';
import {Progettista} from '../../../models/progettista';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  id: number;
  project: Progetto;
  utente: User;
  progettistaCoinvolto: boolean;
  enteAppartenente: boolean;

  constructor(private profileManager: ProfileManagerService,
              private projectManager: ProjectManagerService,
              private route: ActivatedRoute,
              private reqManager: RequestManagerService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.profileManager.getProfile().subscribe(data => {
      this.utente = data;
    });
    this.projectManager.getProject(this.id).subscribe(data => {
      this.project = data;
    });
    this.getProgettistaCoinvolto(this.route.snapshot.params['id']);
    this.getEnteAppartenente(this.route.snapshot.params['id']);
  }

  candidaturaProgettista(id: number) {
    this.reqManager.candidaturaProgettista(id).subscribe(data => {
      this.project = data;
      this.router.navigate(['candidatura-ok']);
    });
  }

  candidaturaEsperto(id: number) {
    this.projectManager.candidaturaEsperto(id).subscribe(() => {
      this.router.navigate(['candidatura-ok']);
    });
  }

  gestioneProgetto(id: number) {
    this.router.navigate(['project-management', id]);
  }

  entraProgettista(id: number){
    this.router.navigate(['/progettista',id]);
  }

  entraEsperto(id: number) {
    this.router.navigate(['/expert', id]);
  }

  approvaEsperto(idEsperto: number, idProgetto: number) {
    this.projectManager.approvaEsperto(idEsperto, idProgetto).subscribe(() => {
      window.location.reload();
    });
  }

  rifiutaEsperto(idEsperto: number, idProgetto: number) {
    this.projectManager.rifiutaEsperto(idEsperto, idProgetto).subscribe(() => {
      window.location.reload();
    });
  }

  getProgettistaCoinvolto(idProgetto: number){
    this.projectManager.getProgettistaCoinvolto(idProgetto).subscribe(data => {
      this.progettistaCoinvolto = data;
      console.log(data);
    })
  }

  getEnteAppartenente(idProgetto: number){
    this.projectManager.getEnteAppartenente(idProgetto).subscribe(data => {
      this.enteAppartenente = data;
      console.log(data);
    })
  }

}

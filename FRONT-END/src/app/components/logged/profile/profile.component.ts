import {Component, OnChanges, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Progetto} from 'src/app/models/progetto';
import {User} from '../../../models/user';
import {ProfileManagerService} from '../../../services/profile-manager.service';
import {ProjectManagerService} from 'src/app/services/project-manager.service';
import {Ente} from 'src/app/models/ente';
import {Progettista} from 'src/app/models/progettista';
import {RequestManagerService} from 'src/app/services/request-manager.service';
import {Competenza} from 'src/app/models/competenza';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  utente: Progettista;

  constructor(private profileManager: ProfileManagerService,
              private projectManager: ProjectManagerService,
              private request: RequestManagerService,
              private router: Router
  ) {
  }

  ngOnInit(): void {
    this.profileManager.getProfile().subscribe(
      data => {
        this.update(data);
        console.log(this.utente);
      });
  }

  update(data) {
    this.utente = data;
  }

  Entra(id: number) {
    this.router.navigate(['/project', id]);
  }

  entraProgettista(id: number) {
    this.router.navigate(['/progettista', id]);
  }

  entraEnte(id: number) {
    this.router.navigate(['/entity', id]);
  }

  publish(): void {
    this.router.navigate(['/create-project']);
  }

  modifica() {
    this.router.navigate(['/competenze-info']);
  }

  abbandona(id: number) {
    this.projectManager.abbandono(id).subscribe(() => {
      this.profileManager.getProfile().subscribe(
        data => {
          this.update(data);
          console.log(this.utente);
        });
      this.router.navigate(['profile']);
    });
  }

  cancella(idProgetto: number) {
    this.projectManager.cancella(idProgetto).subscribe(() => window.location.reload());
  }

  // Cancella la richiesta per un ente
  cancellaRichiestaEnte(idEnte: number) {
    this.profileManager.cancellaRichiestaEnte(idEnte).subscribe(() => window.location.reload());
  }

  abbandonaEnte(id: number) {
    this.profileManager.abbandonaEnte(id).subscribe(() => {
      this.profileManager.getProfile().subscribe(
        data => {
          this.update(data);
        });
      this.router.navigate(['profile']);
    });
  }

  modificaProfilo() {
    this.router.navigate(['/modifica-profilo']);
  }

  // Accetta il progettista candidato per l'ente
  accettaProgettista(id: number) {
    this.profileManager.accettaProgettista(id).subscribe(() => {
      this.profileManager.getProfile().subscribe(
        data => {
          this.update(data);
        });
      this.router.navigate(['profile']);
    });
  }

  // Rifiuta il progettista candidato per l'ente
  rifiutaProgettista(id: number){
    this.profileManager.rifiutaProgettista(id).subscribe(()=> window.location.reload());
  }

  // Espelle il progettista dall'ente
  espelliProgettista(id: number) {
    this.profileManager.espelliProgettista(id).subscribe(() => {
      this.profileManager.getProfile().subscribe(
        data => {
          this.update(data);
        });
      this.router.navigate(['profile']);
    });
  }

  cancellaProgetto(id: number) {
    this.profileManager.cancellaProgetto(id).subscribe(() => {
      this.profileManager.getProfile().subscribe(
        data => {
          this.update(data);
        });
      this.router.navigate(['profile']);
    });
  }
}

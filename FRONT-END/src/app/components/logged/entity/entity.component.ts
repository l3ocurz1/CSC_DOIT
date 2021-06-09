import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Progettista } from 'src/app/models/progettista';
import { Ente } from 'src/app/models/ente';
import { Progetto } from 'src/app/models/progetto';
import { User } from 'src/app/models/user';
import { ProfileManagerService } from 'src/app/services/profile-manager.service';
import { ProjectManagerService } from 'src/app/services/project-manager.service';
import { RequestManagerService } from 'src/app/services/request-manager.service';
import { UserManagerService } from 'src/app/services/user-manager.service';

@Component({
  selector: 'app-entity',
  templateUrl: './entity.component.html',
  styleUrls: ['./entity.component.css']
})
export class EntityComponent implements OnInit {

  id:number;
  entity:Ente;
  loggato: Progettista;
  progettistaAppartenente: boolean;

  constructor(private userManager: UserManagerService,
              private profileManagerService: ProfileManagerService,
              private route:ActivatedRoute,
              private reqManager: RequestManagerService,
              private projectManager: ProjectManagerService,
              private router: Router) { }

  ngOnInit(): void {
    this.getEntity();
    this.getLoggato();
    this.getProgettistaAppartenente(this.route.snapshot.params['id']);

  }

  getEntity(){
    this.id = this.route.snapshot.params['id'];
    this.userManager.getEnte(this.id).subscribe( data =>{
      this.entity=data;
    })
  }

  getLoggato(){
    this.profileManagerService.getProfile().subscribe( data => {
      this.loggato = data;
    })
  }

  getProgettistaAppartenente(idEnte: number){
    this.projectManager.getProgettistaAppartenete(idEnte).subscribe(data => {
      this.progettistaAppartenente = data;
      console.log(this.progettistaAppartenente);
    })
  }

  richiesta(id:number){
    this.reqManager.richiestaEnte(id).subscribe(data=>{
      this.entity=data;
      this.router.navigate(['richiesta-ok']);
    })
  }

  Entra(id:number){
    if(sessionStorage.getItem("Bearer")==null){
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/project',id]);
    }
  }

  entraProgettista(id:number){
    this.router.navigate(['/progettista', id]);
  }

}

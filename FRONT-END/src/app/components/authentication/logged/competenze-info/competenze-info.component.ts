import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Competenza } from 'src/app/models/competenza';
import { User } from 'src/app/models/user';
import { CompetenceManagerService } from 'src/app/services/competence-manager.service';
import {Progettista} from '../../../models/progettista';
import {AuthenticationManagerService} from '../../../services/authentication-manager.service';
import {ProfileManagerService} from '../../../services/profile-manager.service';

@Component({
  selector: 'app-competenze-info',
  templateUrl: './competenze-info.component.html',
  styleUrls: ['./competenze-info.component.css']
})
export class CompetenzeInfoComponent implements OnInit {

  constructor(private compManager: CompetenceManagerService, private router: Router, private profileManager: ProfileManagerService) { }

  possedute: Competenza[];
  nonPossedute: Competenza[];
  utente: Progettista;

  ngOnInit(): void {
    this.getCompetenzePersonali();
    this.getAltreCompetenze();
  }

  getCompetenzePersonali() {
    this.profileManager.getProfile().subscribe(data =>{
      this.possedute = data.competenze;
    })
  }

  getAltreCompetenze(){
    this.compManager.getAltreCompetenze().subscribe(data=>{
      this.nonPossedute=data
    })
  }

  aggiungi(nome:string){
    this.compManager.addCompetenza(nome).subscribe(()=>{
      this.getCompetenzePersonali();
      this.getAltreCompetenze();
      this.router.navigate(['competenze-info']);
    })
  }

  remove(nome:string){
    this.compManager.removeCompetenza(nome).subscribe(()=>{
      this.getCompetenzePersonali();
      this.getAltreCompetenze();
      this.router.navigate(['competenze-info']);
    })
  }

  save(){
    this.router.navigate(['profile']);
  }

}

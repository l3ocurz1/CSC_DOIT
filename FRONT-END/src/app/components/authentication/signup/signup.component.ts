import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IDropdownSettings } from 'ng-multiselect-dropdown';
import { Competenza } from 'src/app/models/competenza';
import { AuthenticationManagerService } from 'src/app/services/authentication-manager.service';
import { CompetenceManagerService } from 'src/app/services/competence-manager.service';
import {Progettista} from '../../../models/progettista';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  email;
  username;
  password;
  authorities;
  checkpassword;
  type: Number;
  utente: Progettista;
  autenticato=true;

  title:string;
  description: string;
  dropdownList = [];
  selectedItems: Competenza[];
  dropdownSettings:IDropdownSettings;
  competences: Competenza[];

  constructor(private router: Router, private authManager: AuthenticationManagerService, private compManager: CompetenceManagerService) { }

  registrazione(){
    this.competences = this.selectedItems;
    this.utente =  new Progettista(null, this.username, this.password, this.email, null, this.competences, null, null, null, null, null);
    console.log(this.utente);
    this.authManager.registra(this.utente, this.type).subscribe(() => {
        this.router.navigate(["/signup-redirect"]);
    },(error) => {
      console.error('error');
      this.alert();
    });
  }

  ngOnInit(): void {
    this.getCompetences();
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'nome',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      allowSearchFilter: true
     };
  }

  getCompetences() {
    this.compManager.listCompetenze().subscribe(data =>
      {this.dropdownList = data})
  }

  alert(){
    this.autenticato=false;
  }

}

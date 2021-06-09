import {Component, OnInit} from '@angular/core';
import {Competenza} from '../../../models/competenza';
import {IDropdownSettings} from 'ng-multiselect-dropdown';
import {ProjectManagerService} from 'src/app/services/project-manager.service';
import {Progetto} from 'src/app/models/progetto';
import {Router} from '@angular/router';
import {CompetenceManagerService} from 'src/app/services/competence-manager.service';
import {Statoprogetto} from '../../../models/statoprogetto';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  title: string;
  description: string;
  competences: Competenza[];
  dropdownList = [];
  selectedItems: Competenza[] = [];
  dropdownSettings: IDropdownSettings;
  option;


  constructor(private compManager: CompetenceManagerService, private projectManager: ProjectManagerService, private router: Router) {
  }

  getCompetences() {
    this.compManager.listCompetenze().subscribe(data => {
      this.dropdownList = data;
    });
  }

  addProject() {
    this.competences = this.selectedItems;
    console.log(this.competences);
    if(this.option == 'true'){
    this.projectManager.addProject(new Progetto(null, this.title, this.description, this.competences, Statoprogetto.APERTO, null, null, null, null, null)).subscribe(() =>
      this.router.navigate(['/profile']));
    }
    if(this.option == 'false') {
      this.projectManager.addProject(new Progetto(null, this.title, this.description, this.competences, Statoprogetto.VALUTAZIONE, null, null, null, null, null)).subscribe(() =>
      this.router.navigate(['/profile']));
    }
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
}

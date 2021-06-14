import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListsManagerService } from 'src/app/services/lists-manager.service';
import { Progetto } from '../../../models/progetto';

@Component({
  selector: 'app-list-projects',
  templateUrl: './list-projects.component.html',
  styleUrls: ['./list-projects.component.css']
})
export class ListProjectsComponent implements OnInit {

  projects: Progetto[];
  progetti: Progetto;

  constructor(private listsManager: ListsManagerService, private router: Router) {
  }

  ngOnInit(): void {
    this.listsManager.listProjects().subscribe(data => {
      this.projects = data
    })
  }

  Entra(id:number){
    if(sessionStorage.getItem("Bearer")==null){
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/project',id]);
    }
  }

}

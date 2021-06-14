import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListsManagerService } from 'src/app/services/lists-manager.service';
import { Ente } from '../../../models/ente';

@Component({
  selector: 'app-list-entities',
  templateUrl: './list-entities.component.html',
  styleUrls: ['./list-entities.component.css']
})
export class ListEntitiesComponent implements OnInit {

  entities: Ente[];

  constructor(private listsManager : ListsManagerService, private router:Router) { }

  ngOnInit(): void {
    this.listsManager.listEntities().subscribe(data => {
      this.entities = data
    })
  }

  entra(id:number){
    if(sessionStorage.getItem("Bearer")==null){
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/entity',id]);
    }
  }

}

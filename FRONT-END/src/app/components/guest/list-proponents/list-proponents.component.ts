import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListsManagerService } from 'src/app/services/lists-manager.service';
import { Proponente } from '../../../models/proponente';

@Component({
  selector: 'app-list-proponents',
  templateUrl: './list-proponents.component.html',
  styleUrls: ['./list-proponents.component.css']
})
export class ListProponentsComponent implements OnInit {

  proponents: Proponente[];

  constructor(private listsManager : ListsManagerService, private router: Router) { }

  ngOnInit(): void {
    this.listsManager.listProponents().subscribe(data => {
      this.proponents = data
    })
  }

  entra(id:number){
    if(sessionStorage.getItem("Bearer")==null){
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/proponent',id]);
    }
  }

}

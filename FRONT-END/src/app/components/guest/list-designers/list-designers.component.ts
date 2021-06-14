import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListsManagerService } from 'src/app/services/lists-manager.service';
import { Progettista } from '../../../models/progettista';

@Component({
  selector: 'app-list-designers',
  templateUrl: './list-designers.component.html',
  styleUrls: ['./list-designers.component.css']
})
export class ListDesignersComponent implements OnInit {

  designers: Progettista[];

  constructor(private listsManager : ListsManagerService, private router: Router) { }

  fillList(){
    this.listsManager.listDesigners().subscribe(data => {
      this.designers = data
    })
  }

  ngOnInit(): void {
    this.fillList();
  }

  entra(id:number){
    if(sessionStorage.getItem("Bearer")==null){
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/progettista',id]);
    }
  }

}

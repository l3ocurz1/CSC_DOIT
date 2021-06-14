import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListsManagerService } from 'src/app/services/lists-manager.service';
import { Esperto } from '../../../models/esperto';

@Component({
  selector: 'app-list-experts',
  templateUrl: './list-experts.component.html',
  styleUrls: ['./list-experts.component.css']
})
export class ListExpertsComponent implements OnInit {

  experts: Esperto[];

  constructor(private listsManager : ListsManagerService, private router:Router) { }

  ngOnInit(): void {
    this.listsManager.listExperts().subscribe(data => {
      this.experts = data
    })
  }

  entra(id:number){
    if(sessionStorage.getItem("Bearer")==null){
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/expert',id]);
    }
  }

}

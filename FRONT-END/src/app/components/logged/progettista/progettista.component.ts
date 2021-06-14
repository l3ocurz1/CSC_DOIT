import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Progettista } from 'src/app/models/progettista';
import { Progetto } from 'src/app/models/progetto';
import { UserManagerService } from 'src/app/services/user-manager.service';

@Component({
  selector: 'app-progettista',
  templateUrl: './progettista.component.html',
  styleUrls: ['./progettista.component.css']
})
export class ProgettistaComponent implements OnInit {

  id:number;
  progettista:Progettista;

  constructor(private userManager: UserManagerService,
              private route: ActivatedRoute,
              private usermanager:UserManagerService,
              private router:Router) { }

  ngOnInit(): void {
    this.getProgettista();
  }

  getProgettista(){
    this.id = this.route.snapshot.params['id'];
    this.userManager.getProgettista(this.id).subscribe( data =>{
      this.progettista=data;
    })
  }

  Entra(id:number){
    if(sessionStorage.getItem("Bearer")==null){
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/project',id]);
    }
  }

  entraEnte(id: number){
    this.router.navigate(['/entity', id])
  }

}

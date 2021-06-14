import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Progetto } from 'src/app/models/progetto';
import { Proponente } from 'src/app/models/proponente';
import { UserManagerService } from 'src/app/services/user-manager.service';

@Component({
  selector: 'app-proponent',
  templateUrl: './proponent.component.html',
  styleUrls: ['./proponent.component.css']
})
export class ProponentComponent implements OnInit {

  constructor(private userManager: UserManagerService, private router: Router, private route: ActivatedRoute) { }

  id:number;
  proponente:Proponente;


  ngOnInit(): void {
    this.getPropontente();
  }

  // Ritorna il proponente corrispondente all'id
  getPropontente(){
    this.id = this.route.snapshot.params['id'];
    this.userManager.getProponente(this.id).subscribe( data =>{
      this.proponente=data;
    })
  }

  // Apre il progetto selezionato
  Entra(id:number){
    if(sessionStorage.getItem("Bearer")==null){
      this.router.navigate(['/login']);
    } else {
      this.router.navigate(['/project',id]);
    }
  }

  // Apre l'ente selezionato
  entraEnte(id: number){
    this.router.navigate(['/entity', id])
  }

}

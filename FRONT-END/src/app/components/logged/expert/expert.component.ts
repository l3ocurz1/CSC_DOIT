import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Esperto } from 'src/app/models/esperto';
import { Progetto } from 'src/app/models/progetto';
import { ProfileManagerService } from 'src/app/services/profile-manager.service';
import { UserManagerService } from 'src/app/services/user-manager.service';

@Component({
  selector: 'app-expert',
  templateUrl: './expert.component.html',
  styleUrls: ['./expert.component.css']
})
export class ExpertComponent implements OnInit {

  id:number
  expert:Esperto;

  constructor(private userManager: UserManagerService,
              private route: ActivatedRoute,
              private usermanager:UserManagerService,
              private router:Router) { }

  ngOnInit(): void {
    this.getExpert();
  }

  getExpert(){
    this.id = this.route.snapshot.params['id'];
    this.userManager.getExpert(this.id).subscribe( data =>{
      this.expert=data;
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
    this.router.navigate(['entity', id]);
  }
}

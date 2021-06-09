import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthenticationManagerService } from 'src/app/services/authentication-manager.service';
import { ProfileManagerService } from 'src/app/services/profile-manager.service';

@Component({
  selector: 'app-modifica-profilo',
  templateUrl: './modifica-profilo.component.html',
  styleUrls: ['./modifica-profilo.component.css']
})
export class ModificaProfiloComponent implements OnInit {

  email:string;
  username:string;
  password:string;
  checkpassword;
  utente: User;
  registrato = true;

  constructor(private profileManager: ProfileManagerService,
              public authManager: AuthenticationManagerService,
              private router:Router) { }

  ngOnInit(): void {
  }

  modificaProfilo(){
    this.profileManager.modificaProfilo(this.username,this.email,this.password).subscribe(() => {
      this.authManager.clearAll();
      this.router.navigate(['/modifica-redirect']);
    },(error) => {
      console.error('error');
      this.alert();
    });
  }

  alert(){
    this.registrato=false;
  }

}

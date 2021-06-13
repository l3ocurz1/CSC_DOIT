import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationManagerService } from 'src/app/services/authentication-manager.service';
import {AuthRequest} from '../../../models/authRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = "";
  password = "";
  autenticato = true;

  constructor(private router: Router,
              private authManager: AuthenticationManagerService) { }

  accesso() {
    let authRequest = new AuthRequest(this.username, this.password);
    this.authManager.autentica(authRequest).subscribe(data =>{
      console.log(data);
      sessionStorage.setItem('Bearer', data);
      this.router.navigate(["/profile"]).then(() => window.location.reload());
    }, (error) => {
      console.error('error');
      this.alert();
    });
  }

  ngOnInit(): void {
    this.authManager.clearAll();
  }

  alert(){
    this.autenticato=false;
  }
}

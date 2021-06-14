import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthenticationManagerService } from 'src/app/services/authentication-manager.service';
import { ProfileManagerService } from 'src/app/services/profile-manager.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  authorities: string = "";
  notifiche:Number = 0;

  constructor(public authManager: AuthenticationManagerService,
              private profManager: ProfileManagerService,
              private router: Router) { }

  getNuoveNotifiche(): void {
    this.profManager.nuoveNotifiche().subscribe(
      data => {
        this.notifiche = data;
      })
  }

  ngOnInit(): void {
    this.getNuoveNotifiche();
  }

}

import { Component, OnInit } from '@angular/core';
import { AuthenticationManagerService } from 'src/app/services/authentication-manager.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private authManager: AuthenticationManagerService,
              private router: Router) { }

  ngOnInit(): void {
    this.authManager.clearAll();
    this.router.dispose();
  }

}

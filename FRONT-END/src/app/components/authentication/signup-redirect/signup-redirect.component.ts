import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup-redirect',
  templateUrl: './signup-redirect.component.html',
  styleUrls: ['./signup-redirect.component.css']
})
export class SignupRedirectComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
    setTimeout(callback => {
      this.router.navigate(["/home"]);
    }, 3000);
  }

}

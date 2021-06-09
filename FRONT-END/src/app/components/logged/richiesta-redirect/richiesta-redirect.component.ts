import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-richiesta-redirect',
  templateUrl: './richiesta-redirect.component.html',
  styleUrls: ['./richiesta-redirect.component.css']
})
export class RichiestaRedirectComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
    setTimeout(callback =>{
      this.router.navigate(["/home"]);
    }, 3000);
  }

}

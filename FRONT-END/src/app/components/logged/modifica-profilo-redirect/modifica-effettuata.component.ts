import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modifica-effettuata',
  templateUrl: './modifica-effettuata.component.html',
  styleUrls: ['./modifica-effettuata.component.css']
})
export class ModificaEffettuataComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
    setTimeout(callback =>{
      this.router.navigate(["/home"]);
    }, 3000);
  }

}

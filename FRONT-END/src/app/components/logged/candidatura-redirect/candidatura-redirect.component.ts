import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-candidatura-redirect',
  templateUrl: './candidatura-redirect.component.html',
  styleUrls: ['./candidatura-redirect.component.css']
})
export class CandidaturaRedirectComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
    setTimeout(callback =>{
      this.router.navigate(["/home"]);
    }, 3000);
  }


}

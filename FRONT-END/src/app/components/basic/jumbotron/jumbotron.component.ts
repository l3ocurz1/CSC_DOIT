import { Component, Input, OnInit } from '@angular/core';
import { AuthenticationManagerService } from 'src/app/services/authentication-manager.service';

@Component({
  selector: 'app-jumbotron',
  templateUrl: './jumbotron.component.html',
  styleUrls: ['./jumbotron.component.css']
})
export class JumbotronComponent implements OnInit {

  @Input() Titolo:string | undefined;
  @Input() SottoTitolo: string | undefined;
  @Input() Show: boolean = true;

  constructor(public authManager: AuthenticationManagerService) { }

  ngOnInit(): void {
  }

}

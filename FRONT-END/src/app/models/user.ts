import {Competenza} from './competenza';

export class User {
  id: number;
  userName: string;
  password: string;
  email: string;
  ruolo: string;


  constructor(id: number,
              userName: string,
              password: string,
              email: string,
              ruolo: string) {
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.email = email;
    this.ruolo = ruolo;
  }

}

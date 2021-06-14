import {Competenza} from './competenza';
import {Ente} from './ente';
import {Progetto} from './progetto';
import {User} from './user';
import {Notification} from './notification';

export class Progettista extends User {

  competenze: Competenza[];
  progetti_candidature: Progetto[];
  progetti_in_carico: Progetto[];
  richieste_ente: Ente[];
  ente: Ente[];
  notifiche: Notification[];

  constructor(id: number,
              userName: string,
              password: string,
              email: string,
              authorities: string,
              competenze: Competenza[],
              progetti_candidature: Progetto[],
              progetti_in_carico: Progetto[],
              richieste_ente: Ente[],
              ente: Ente[],
              notifiche: Notification[]) {
    super(id, userName, password, email, authorities);
    this.competenze = competenze;
    this.progetti_candidature = progetti_candidature;
    this.progetti_in_carico = progetti_in_carico;
    this.richieste_ente = richieste_ente;
    this.ente = ente;
    this.notifiche = notifiche;
  }

}

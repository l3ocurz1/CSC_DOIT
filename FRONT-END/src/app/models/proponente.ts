import {Competenza} from './competenza';
import {Progettista} from './progettista';
import {Ente} from './ente';
import {Progetto} from './progetto';
import {Notification} from './notification';

export class Proponente extends Progettista {

  progetti_pubblicati: Progetto[];

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
              notifiche: Notification[],
              progetti_pubblicati: Progetto[]) {
    super(id, userName, password, email, authorities, competenze, progetti_candidature, progetti_in_carico, richieste_ente, ente, notifiche);
    this.progetti_pubblicati = progetti_pubblicati;
  }


}

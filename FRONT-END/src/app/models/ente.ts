import {Competenza} from './competenza';
import {Progettista} from './progettista';
import {Progetto} from './progetto';
import {Proponente} from './proponente';
import {Notification} from './notification';

export class Ente extends Proponente {

  richieste: Progettista[];
  progettisti: Progettista[];

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
              progetti_pubblicati: Progetto[],
              richieste: Progettista[],
              progettisti: Progettista[]) {
    super(id, userName, password, email, authorities, competenze, progetti_candidature, progetti_in_carico, richieste_ente, ente, notifiche, progetti_pubblicati);
    this.richieste = richieste;
    this.progettisti = progettisti;
  }
}

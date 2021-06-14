import {Competenza} from './competenza';
import {Progettista} from './progettista';
import {Ente} from './ente';
import {Progetto} from './progetto';
import {Notification} from './notification';

export class Esperto extends Progettista {

  proposte_progetti: Progetto[];
  progetti_assegnati: Progetto[];

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
              proposte_progetti: Progetto[],
              progetti_assegnati: Progetto[]) {
    super(id, userName, password, email, authorities, competenze, progetti_candidature, progetti_in_carico, richieste_ente, ente, notifiche);
    this.proposte_progetti = proposte_progetti;
    this.progetti_assegnati = progetti_assegnati;
  }
}

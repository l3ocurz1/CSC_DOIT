import {Competenza} from './competenza';
import {Progettista} from './progettista';
import {Esperto} from './esperto';
import {Statoprogetto} from './statoprogetto';
import {Proponente} from './proponente';

export class Progetto {

  id: number;
  titolo: string;
  descrizione: string;
  requisiti: Competenza[];
  statoProgetto: Statoprogetto;
  autore: Proponente;
  candidati: Progettista[];
  progettisti: Progettista[];
  esperti_proposti: Esperto[];
  esperto: Esperto;

  constructor(id: number,
              titolo: string,
              descrizione: string,
              requisiti: Competenza[],
              statoProgetto: Statoprogetto,
              autore: Proponente,
              candidati: Progettista[],
              progettisti: Progettista[],
              esperti_proposti: Esperto[],
              esperto: Esperto) {
    this.id = id;
    this.titolo = titolo;
    this.descrizione = descrizione;
    this.requisiti = requisiti;
    this.statoProgetto = statoProgetto;
    this.autore = autore;
    this.candidati = candidati;
    this.progettisti = progettisti;
    this.esperti_proposti = esperti_proposti;
    this.esperto = esperto;
  }

}

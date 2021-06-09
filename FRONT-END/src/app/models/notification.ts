import {Progettista} from './progettista';

export class Notification {

  id: number;
  message: string;
  seen: boolean;
  reciver: Progettista;

  constructor(id: number, message: string, seen: boolean, reciver: Progettista) {
    this.id = id;
    this.message = message;
    this.seen = seen;
    this.reciver = reciver;
  }
}

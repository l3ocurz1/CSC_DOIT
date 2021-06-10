package com.example.doit2.service;

import com.example.doit2.model.*;
import com.example.doit2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    ProponenteRepository proponenteRepository;
    @Autowired
    ProgettistaRepository progettistaRepository;
    @Autowired
    EnteRepository enteRepository;
    @Autowired
    EspertoRepository espertoRepository;

    // Crea una notifica di candidatura
    public void creaNotificaCandidatura(Proponente proponente, Progettista candidato, Progetto progetto) {
        Notification notifica = new Notification();
        notifica.setReceiver(proponente);
        notifica.setMessage(candidato.getUserName() + " si è candidato al tuo progetto: " + progetto.getTitolo());
        proponente.addNotifica(notifica);
        notificationRepository.save(notifica);
        proponenteRepository.save(proponente);
    }

    //  Crea una notifica di approvazione
    public void creaNotificaApprovazione(Proponente proponente, Progettista progettista, Progetto progetto) {
        Notification notifica = new Notification();
        notifica.setReceiver(progettista);
        notifica.setMessage(proponente.getUserName() + " ti ha approvato come progettista per il progetto: " + progetto.getTitolo());
        progettista.addNotifica(notifica);
        notificationRepository.save(notifica);
        progettistaRepository.save(progettista);
    }

    // Crea notifica richiesta Ente
    public void CreaNotificaRichiesta(Progettista progettista, Ente ente) {
        Notification notifica = new Notification();
        notifica.setReceiver(ente);
        notifica.setMessage("Il progettista " + progettista.getUserName() + " ha richiesto di unirsi alla tua lista progettisti!");
        ente.addNotifica(notifica);
        notificationRepository.save(notifica);
        enteRepository.save(ente);
    }

    // Crea notifica approvazione Ente
    public void CreaNotificaAccettazione(Ente ente, Progettista progettista) {
        Notification notifica = new Notification();
        notifica.setReceiver(progettista);
        notifica.setMessage("L'ente " + ente.getUserName() + " ha accettato la tua richiesta di unirsi alla sua lista progettisti!");
        progettista.addNotifica(notifica);
        notificationRepository.save(notifica);
        progettistaRepository.save(progettista);
    }

    // Crea notifica candidatura esperto
    public void creaNotificaCandidaturaEsperto(Esperto esperto, Progetto progetto){
        Proponente autore = progetto.getAutore();
        Notification notifica = new Notification();
        notifica.setReceiver(autore);
        notifica.setMessage("L'esperto " + esperto.getUserName() + " si è candidato come esperto al tuo progetto: " + progetto.getTitolo());
        autore.addNotifica(notifica);
        notificationRepository.save(notifica);
        proponenteRepository.save(autore);
    }

    // Crea nootifica approvazione esperto
    public void creaNotificaApprovazioneEsperto(Esperto esperto, Progetto progetto){
        Notification notifica = new Notification();
        notifica.setReceiver(esperto);
        notifica.setMessage("Sei stato approvato come esperto per il progetto: " + progetto.getTitolo());
        esperto.addNotifica(notifica);
        notificationRepository.save(notifica);
        espertoRepository.save(esperto);
    }
}

package com.example.doit2.service;

import com.example.doit2.model.*;
import com.example.doit2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgettistaService {

    @Autowired
    private ProgettistaRepository progettistaRepository;
    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    private EnteRepository enteRepository;
    @Autowired
    private CompetenzaRepository competenzaRepository;
    @Autowired
    private NotificationService notificationService;

    // |--------------------------|
    // | OPERAZIONI CRUD STANDARD |
    // |--------------------------|

    // Ritorna la lista di tutti i progettisti
    public List<Progettista> getAll() {
        List<Progettista> result = new ArrayList<Progettista>();
        progettistaRepository.findAll().forEach(result::add);
        return result;
    }

    // Ritorna un progettista specifico dall'id
    public Progettista getProgettistaById(int id) {
        return progettistaRepository.findById(id).get();
    }

    // Crea un nuovo progettista
    public void addProgettista(Progettista progettista) throws Exception {
        if(!progettistaRepository.findByUserName(progettista.getUserName()).isPresent()){
        progettista.setAuthorities("PROGETTISTA");
        progettista.getCompetenze().forEach(competenza -> competenzaRepository.findByNome(competenza.getNome()).get().getPossedenti().add(progettista));
        progettistaRepository.save(progettista);}
        else {throw new Exception();}
    }

    // Aggiorna un progettista esistente
    public void updateProgettista(Progettista progettista) {
        progettistaRepository.save(progettista);
    }

    // Cancella un progettista esistente dall'id
    public void deleteProgettistaById(int id) {
        Progettista progettista = progettistaRepository.findById(id).get();
        progettistaRepository.delete(progettista);
    }

    // |--------------------------|
    // |     ALTRE OPERAZIONI     |
    // |--------------------------|

    // Ritorna un progettista tramite username
    public Progettista getProgettistaByUsername(String username) {
        return progettistaRepository.findByUserName(username).get();
    }

    // Cancella un progettista tramite username
    public void deleteProgettistaByUsername(String username) {
        Optional<Progettista> progettista = progettistaRepository.findByUserName(username);
        progettistaRepository.delete(progettista.get());
    }

    // Ritorna il progettista correntemente loggato
    public Progettista getLoggedProgettista() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return progettistaRepository.findByUserName(currentPrincipalName).get();
    }

    // Aggiunge una competenza al progettista correntemente loggato tramite il nomeCompetenza
    public void addCompetenza(String nomeCompetenza) {
        Progettista progettista = getLoggedProgettista();
        Competenza competenza = competenzaRepository.findByNome(nomeCompetenza).get();
        progettista.addCompetenza(competenza);
        updateProgettista(progettista);
        competenzaRepository.save(competenza);
    }

    // Rimuove una competenza dal progettista correntemente loggato tramite il nomeCompetenza
    public void removeCompetenza(String nomeCompetenza) {
        Progettista progettista = getLoggedProgettista();
        Competenza competenza = competenzaRepository.findByNome(nomeCompetenza).get();
        progettista.getCompetenze().remove(competenza);
        progettistaRepository.save(progettista);
    }

    // Ritorna le competenze non possedute dall'utente loggato
    public List<Competenza> getCompetenzeNonPossedute() {
        Progettista progettista = getLoggedProgettista();
        List<Competenza> competenzeProgettista = progettista.getCompetenze();
        List<Competenza> competenzeTotali = new ArrayList<>();
        competenzaRepository.findAll().forEach(competenzeTotali::add);
        competenzeTotali.removeIf(competenza -> competenzeProgettista.contains(competenza));
        return competenzeTotali;
    }

    // Aggiunge il progettista correntemente loggato come candidato a un progetto (tramite id)
    public void addCandidatura(int idProgetto) {
        Progettista progettista = getLoggedProgettista();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if (!progetto.getCandidati().contains(progettista) && !progetto.getProgettisti().contains(progettista) && progetto.getStatoProgetto().equals(StatoProgetto.APERTO)) {
            progettista.addProgetti_candidature(progetto);
            progetto.addCandidati(progettista);
            progettistaRepository.save(progettista);
            progettoRepository.save(progetto);
            notificationService.creaNotificaCandidatura(progetto.getAutore(), progettista, progetto);
        }
    }

    // Rimuove il progettista correntemente loggato dalla lista candidati del progetto (tramite id)
    public void removeCandidatura(int idProgetto) {
        Progettista progettista = getLoggedProgettista();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if (progetto.getCandidati().contains(progettista)) {
            progetto.removeCandidati(progettista);
            progettista.removeProgetti_candidature(progetto);
            progettistaRepository.save(progettista);
            progettoRepository.save(progetto);
        }
    }

    // Rimuove il progettista correntemente loggato dalla lista progettisti del progetto (tramite id)
    public void abbandonaProgetto(int idProgetto) {
        Progettista progettista = getLoggedProgettista();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if (progetto.getProgettisti().contains(progettista)) {
            progetto.removeProgettisti(progettista);
            progettista.removeProgetti_in_carico(progetto);
            progettistaRepository.save(progettista);
            progettoRepository.save(progetto);
        }
    }

    // Ritorna il numero di notifiche non ancora visualizzate
    public int nuoveNotifiche() {
        Progettista progettista = getLoggedProgettista();
        List<Notification> notifiche = progettista.getNotifiche();
        int cont = 0;
        for (Notification notifica : notifiche) {
            if (!notifica.isSeen()) cont++;
        }
        return cont;
    }

    // Cancella una notifica dell'utente loggato
    public void cancellaNotifica(int idNotifica) {
        Notification notifica = notificationRepository.findById(idNotifica).get();
        Progettista progettista = getLoggedProgettista();
        if (progettista.getNotifiche().contains(notifica))
            notificationRepository.delete(notifica);
    }

    // Crea una richiesta per inserire il progettista correntemente loggato tra i progettisti dell'Ente
    public void richiestaEnte(int idEnte) {
        Progettista progettista = getLoggedProgettista();
        Ente ente = enteRepository.findById(idEnte).get();
        if (!ente.getRichieste().contains(progettista) && !progettista.getRuolo().equals("Ente") && !ente.getProgettisti().contains(progettista)) {
            progettista.getRichieste_ente().add(ente);
            ente.getRichieste().add(progettista);
            progettistaRepository.save(progettista);
            enteRepository.save(ente);
            notificationService.CreaNotificaRichiesta(progettista, ente);
        }
    }

    // Annulla la richiesta per un ente
    public void annullaRichiestaEnte(int idEnte){
        Progettista progettista = getLoggedProgettista();
        Ente ente = enteRepository.findById(idEnte).get();
        if(ente.getRichieste().contains(progettista)){
            progettista.getRichieste_ente().remove(ente);
            ente.getRichieste().remove(progettista);
            progettistaRepository.save(progettista);
            enteRepository.save(ente);
        }
    }

    // Abbandona un ente
    public void abbandonaEnte(int idEnte){
        Progettista progettista = getLoggedProgettista();
        Ente ente = enteRepository.findById(idEnte).get();
        if(ente.getProgettisti().contains(progettista)){
            ente.getProgettisti().remove(progettista);
            progettista.getEnte().remove(ente);
            enteRepository.save(ente);
            progettistaRepository.save(progettista);
        }
    }

    // Aggiorna le credenziali dell'utente loggato
    public void aggiornaCredenziali(String username, String email, String password) {
        Progettista progettista = getLoggedProgettista();
        progettista.setUserName(username);
        progettista.setEmail(email);
        progettista.setPassword(password);
        progettistaRepository.save(progettista);
    }

}

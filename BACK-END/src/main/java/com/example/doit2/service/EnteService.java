package com.example.doit2.service;


import com.example.doit2.model.Ente;
import com.example.doit2.model.Progettista;
import com.example.doit2.repository.CompetenzaRepository;
import com.example.doit2.repository.EnteRepository;
import com.example.doit2.repository.ProgettistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnteService {

    @Autowired
    private EnteRepository enteRepository;
    @Autowired
    private ProgettistaRepository progettistaRepository;
    @Autowired
    private CompetenzaRepository competenzaRepository;
    @Autowired
    private NotificationService notificationService;

    // |--------------------------|
    // | OPERAZIONI CRUD STANDARD |
    // |--------------------------|

    // Ritorna la lista di tutti gli enti
    public List<Ente> getAll() {
        List<Ente> result = new ArrayList<>();
        enteRepository.findAll().forEach(result::add);
        return result;
    }

    // Ritorna un ente dall'id
    public Ente getEnteById(int id) {
        return enteRepository.findById(id).get();
    }

    // Crea un nuovo ente
    public void addEnte(Ente ente) throws Exception{
        if (!enteRepository.findByUserName(ente.getUserName()).isPresent()) {
            ente.setAuthorities("ENTE");
            ente.getCompetenze().forEach(competenza -> competenzaRepository.findByNome(competenza.getNome()).get().getPossedenti().add(ente));
            enteRepository.save(ente);}
        else {throw new Exception();}
    }

    // Aggiorna un ente esistente
    public void updateEnte(Ente ente) {
        enteRepository.save(ente);
    }

    // Cancella un ente esistente dall'id
    public void deleteEnteById(int id) {
        enteRepository.deleteById(id);
    }

    // |--------------------------|
    // |     ALTRE OPERAZIONI     |
    // |--------------------------|

    // Restituisce l'ente loggato
    public Ente getLoggedEnte() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return enteRepository.findByUserName(currentPrincipalName).get();
    }

    // Aggiunge un progettista alla lista progettisti dell'ente correntemente loggato
    public void addProgettistaById(int idProgettista) {
        Ente ente = getLoggedEnte();
        Progettista progettista = progettistaRepository.findById(idProgettista).get();
        if (ente.richieste.contains(progettista)) {
            ente.getProgettisti().add(progettista);
            progettista.getEnte().add(ente);
            enteRepository.save(ente);
            progettistaRepository.save(progettista);
        }
    }

    // Rimuove un progettista dalla lista progettisti dell'ente correntemente loggato
    public void removeProgettistaById(int idProgettista) {
        Ente ente = getLoggedEnte();
        Progettista progettista = progettistaRepository.findById(idProgettista).get();
        if (ente.getProgettisti().contains(progettista)) {
            ente.getProgettisti().remove(progettista);
            progettista.getEnte().remove(ente);
        }
        enteRepository.save(ente);
        progettistaRepository.save(progettista);
    }

    // Accetta la richiesta di un progettista di unirsi all'ente e lo aggiunge alla lista progettisti
    public void accettaProgettista(int idProgettista) {
        Ente ente = getLoggedEnte();
        Progettista progettista = progettistaRepository.findById(idProgettista).get();
        if (ente.getRichieste().contains(progettista) && !ente.getProgettisti().contains(progettista)) {
            ente.getProgettisti().add(progettista);
            ente.getRichieste().remove(progettista);
            progettista.getEnte().add(ente);
            progettista.getRichieste_ente().remove(ente);
            progettistaRepository.save(progettista);
            notificationService.CreaNotificaAccettazione(ente, progettista);
        }
    }

    // Rifiuta la richiesta di un progettista di unirsi all'ente
    public void rifiutaProgettista(int idProgettista){
        Ente ente = getLoggedEnte();
        Progettista progettista = progettistaRepository.findById(idProgettista).get();
        if(ente.getRichieste().contains(progettista)){
            ente.getRichieste().remove(progettista);
            progettista.getRichieste_ente().remove(progettista);
            enteRepository.save(ente);
            progettistaRepository.save(progettista);
        }
    }

    // Espelle un progettista dalla lista progettisti dell'ente
    public void espelliProgettista(int idProgettista) {
        Ente ente = getLoggedEnte();
        Progettista progettista = progettistaRepository.findById(idProgettista).get();
        if (ente.getProgettisti().contains(progettista)){
            ente.getProgettisti().remove(progettista);
            progettista.getEnte().remove(ente);
            enteRepository.save(ente);
            progettistaRepository.save(progettista);
        }
    }
}


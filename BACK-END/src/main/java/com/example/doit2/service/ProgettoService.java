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
public class ProgettoService {

    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    ProponenteRepository proponenteRepository;
    @Autowired
    ProgettistaRepository progettistaRepository;
    @Autowired
    EspertoService espertoService;
    @Autowired
    ProgettistaService progettistaService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    ProponenteService proponenteService;
    @Autowired
    CompetenzaRepository competenzaRepository;
    @Autowired
    EnteRepository enteRepository;
    @Autowired
    CompetenzaService competenzaService;

    // |--------------------------|
    // | OPERAZIONI CRUD STANDARD |
    // |--------------------------|

    // Ritorna la lista di tutti i progetti
    public List<Progetto> getAll() {
        List<Progetto> result = new ArrayList<>();
        progettoRepository.findAll().forEach(result::add);
        return result;
    }

    // Ritorna un progetto dall'id
    public Progetto getProgettoById(int id) {
        return progettoRepository.findById(id).get();
    }

    // Aggiunge un progetto che ha come autore il Proponente/Ente correntemente loggato
    public void addProgetto(Progetto progetto) {
        Proponente proponente = proponenteService.getLoggedProponente();
        progetto.setAutore(proponente);
        progetto.getRequisiti().forEach(competenza -> competenzaRepository.findByNome(competenza.getNome()).get().getRichieste().add(progetto));
        progettoRepository.save(progetto);
    }

    // Aggiorna un progetto esistente
    public void updateProgetto(Progetto progetto) {
        progettoRepository.save(progetto);
    }

    // Cancella un progetto esistente dall'id
    public void deleteProgettoById(int id) {
        Proponente proponente = proponenteService.getLoggedProponente();
        Progetto progetto = progettoRepository.findById(id).get();
        if (progetto.getAutore().equals(proponente)) {
            progetto.getProgettisti().forEach(progettista -> progettista.getProgetti_in_carico().remove(progetto));
            progetto.getCandidati().forEach(progettista -> progettista.getProgetti_candidature().remove(progetto));
            progetto.getRequisiti().forEach(competenza -> competenza.getRichieste().remove(progetto));
            progetto.getAutore().getProgetti_pubblicati().remove(progetto);
            if (progetto.getEsperti_proposti() != null)
                progetto.getEsperti_proposti().forEach(esperto -> esperto.getProposte_progetti().remove(progetto));
            if (progetto.getEsperto() != null)
                progetto.getEsperto().getProgetti_assegnati().remove(progetto);
            progettoRepository.deleteById(id);
        }

    }

    // |--------------------------|
    // |     ALTRE OPERAZIONI     |
    // |--------------------------|

    //Aggiunge una competenza alle competenze richieste dal progetto
    public void aggiungiRequisito(String nomeCompetenza, int idProgetto) {
        Competenza competenza = competenzaRepository.findByNome(nomeCompetenza).get();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if (!progetto.getRequisiti().contains(competenza)) {
            progetto.getRequisiti().add(competenza);
            competenza.getRichieste().add(progetto);
            progettoRepository.save(progetto);
            competenzaRepository.save(competenza);
        }
    }

    //Rimuove una competenza dalle competenza richieste dal progetto
    public void rimuoviCompetenza(String nomeCompetenza, int idProgetto) {
        Competenza competenza = competenzaRepository.findByNome(nomeCompetenza).get();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if (progetto.getRequisiti().contains(competenza)) {
            progetto.getRequisiti().remove(competenza);
            competenza.getRichieste().remove(progetto);
            progettoRepository.save(progetto);
            competenzaRepository.save(competenza);
        }
    }


    // Approva la candidatura di un Progettista candidato
    // Sposta il candidato dalla lista candidati alla lista progettisti
    public void approveCandidato(int idCandidato, int idProgetto) {
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        Progettista progettista = progettistaRepository.findById(idCandidato).get();
        if (progetto.getCandidati().contains(progettista) && !progetto.getProgettisti().contains(progettista)) {
            progetto.getCandidati().remove(progettista);
            progettista.getProgetti_candidature().remove(progetto);
            progetto.getProgettisti().add(progettista);
            progettista.getProgetti_in_carico().add(progetto);
            progettoRepository.save(progetto);
            progettistaRepository.save(progettista);
            notificationService.creaNotificaApprovazione(progetto.getAutore(), progettista, progetto);
        }
    }

    // Rifiuta la candidatura di un progettista, lo rimuove dalla lista candidati
    public void rifiutaCandidato(int idCandidato, int idProgetto){
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        Progettista progettista = progettistaRepository.findById(idCandidato).get();
        if(progetto.getCandidati().contains(progettista)){
            progetto.getCandidati().remove(progettista);
            progettista.getProgetti_candidature().remove(progetto);
            progettoRepository.save(progetto);
            progettistaRepository.save(progettista);
        }
    }

    // Espelle il progettista dal progetto
    public void espelliProgettista(int idProgettista, int idProgetto) {
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        Progettista progettista = progettistaRepository.findById(idProgettista).get();
        if (progetto.getProgettisti().contains(progettista)) {
            progetto.getCandidati().remove(progettista);
            progettista.getProgetti_in_carico().remove(progetto);
            progettoRepository.save(progetto);
            progettistaRepository.save(progettista);
        }
    }

    // Ritorna i requisiti non richiesti nel progetto (tramite id)
    public List<Competenza> getRequisitiNonRichiesti(int progettoId) {
        Progetto progetto = progettoRepository.findById(progettoId).get();
        List<Competenza> requisitiProgetto = progetto.getRequisiti();
        List<Competenza> requisitiTotali = competenzaService.getAll();
        requisitiTotali.removeIf(competenza -> requisitiProgetto.contains(competenza));
        return requisitiTotali;
    }

    // Ritorna true se il progettista appartiene all'ente, false altrimenti
    public boolean progettistaAppartenente(int idEnte){
        Progettista progettista = progettistaService.getLoggedProgettista();
        Ente ente = enteRepository.findById(idEnte).get();
        return progettista.getEnte().contains(ente);
    }

    // Ritorna true se il progettista è in carico o candidato ad un progetto, false altrimenti
    public boolean progettistaCoinvolto(int idProgetto) {
        Progettista progettista = progettistaService.getLoggedProgettista();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        return (progettista.getProgetti_candidature().contains(progetto) || progettista.getProgetti_in_carico().contains(progetto));
    }

    // Ritorna true se uno degli enti del progettista loggato ha in carico il progetto specificato, false altrimenti
    public boolean enteAppartenente(int idProgetto) {
        Progettista progettista = progettistaService.getLoggedProgettista();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        for(Ente ente: progettista.getEnte()){
            if(ente.getProgetti_in_carico().contains(progetto)){
                return true;
            }
        }
        return false;
    }
}

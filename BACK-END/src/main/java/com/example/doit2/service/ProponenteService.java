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
public class ProponenteService {

    @Autowired
    ProponenteRepository proponenteRepository;
    @Autowired
    CompetenzaRepository competenzaRepository;
    @Autowired
    EspertoRepository espertoRepository;
    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    ProgettistaRepository progettistaRepository;
    @Autowired
    NotificationService notificationService;

    // |--------------------------|
    // | OPERAZIONI CRUD STANDARD |
    // |--------------------------|

    // Ritorna la lista di tutti i proponenti
    public List<Proponente> getAll() {
        List<Proponente> result = new ArrayList<>();
        proponenteRepository.findAll().forEach(result::add);
        return result;
    }

    // Ritorna la lista dei SOLI proponenti
    public List<Proponente> getOnly() {
        List<Proponente> temp = new ArrayList<Proponente>();
        List<Proponente> result = new ArrayList<Proponente>();
        proponenteRepository.findAll().forEach(temp::add);
        for (Proponente proponente : temp) {
            if (proponente.getClass().getSimpleName().equals("Proponente")) {
                result.add(proponente);
            }
        }
        return result;
    }

    // Ritorna un proponente dall'id
    public Proponente getProponenteById(int id) {
        return proponenteRepository.findById(id).get();
    }

    // Crea un nuovo proponente
    public void addProponente(Proponente proponente) throws Exception{
        if(!proponenteRepository.findByUserName(proponente.getUserName()).isPresent()) {
            proponente.setAuthorities("PROPONENTE");
            proponente.getCompetenze().forEach(competenza -> competenzaRepository.findByNome(competenza.getNome()).get().getPossedenti().add(proponente));
            proponenteRepository.save(proponente);}
        else {throw new Exception();}
    }

    // Aggiorna un proponente esistente
    public void updateProponente(Proponente proponente) {
        proponenteRepository.save(proponente);
    }

    // Cancella un proponente esistente dall'id
    public void deleteProponenteById(int id) {
        proponenteRepository.deleteById(id);
    }

    // |--------------------------|
    // |     ALTRE OPERAZIONI     |
    // |--------------------------|


    // Recupera un proponente tramite username
    public Proponente getProponenteByUsername(String username) {
        return proponenteRepository.findByUserName(username).get();
    }

    // Recupera il proponente correntemente loggato
    public Proponente getLoggedProponente() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Proponente proponente = proponenteRepository.findByUserName(currentPrincipalName).get();
        return proponente;
    }

    // Accetta esperto proposto
    public void accettaEspertoProposto(int idEsperto, int idProgetto) {
        Esperto esperto = espertoRepository.findById(idEsperto).get();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if (progetto.esperti_proposti.contains(esperto)) {
            progetto.setEsperto(esperto);
            progetto.esperti_proposti.forEach(experto -> experto.proposte_progetti.remove(progetto));
            progetto.esperti_proposti.clear();
            esperto.getProgetti_assegnati().add(progetto);
            progettoRepository.save(progetto);
            espertoRepository.save(esperto);
            notificationService.creaNotificaApprovazioneEsperto(esperto, progetto);
        }
    }

    // Rifiuta esperto proposto
    public void rifiutaEspertoProposto(int idEsperto, int idProgetto){
        Esperto esperto = espertoRepository.findById(idEsperto).get();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if(progetto.esperti_proposti.contains(esperto)){
            progetto.esperti_proposti.remove(esperto);
            esperto.proposte_progetti.remove(progetto);
            progettoRepository.save(progetto);
            espertoRepository.save(esperto);
        }
    }

    // Imposta lo stato di un progetto a CHIUSO
    public void setProgettoChiuso(int idProgetto){
        Proponente proponente = getLoggedProponente();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if(progetto.getAutore().equals(proponente)){
            progetto.setStatoProgetto(StatoProgetto.CHIUSO);
        }
        progettoRepository.save(progetto);
    }

    // Imposta lo stato di un progetto a COMPLETATO
    public void setProgettoCompletato(int idProgetto){
        Proponente proponente = getLoggedProponente();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if(progetto.getAutore().equals(proponente)){
            progetto.setStatoProgetto(StatoProgetto.COMPLETATO);
        }
        progettoRepository.save(progetto);
    }

}

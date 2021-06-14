package com.example.doit2.service;

import com.example.doit2.model.Esperto;
import com.example.doit2.model.Progetto;
import com.example.doit2.model.StatoProgetto;
import com.example.doit2.repository.CompetenzaRepository;
import com.example.doit2.repository.EspertoRepository;
import com.example.doit2.repository.ProgettistaRepository;
import com.example.doit2.repository.ProgettoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EspertoService {

    @Autowired
    private EspertoRepository espertoRepository;
    @Autowired
    private CompetenzaRepository competenzaRepository;
    @Autowired
    ProgettoRepository progettoRepository;
    @Autowired
    ProgettistaRepository progettistaRepository;
    @Autowired
    NotificationService notificationService;

    // |--------------------------|
    // | OPERAZIONI CRUD STANDARD |
    // |--------------------------|

    // Ritorna la lista di tutti gli esperti
    public List<Esperto> getAll() {
        List<Esperto> result = new ArrayList<>();
        espertoRepository.findAll().forEach(result::add);
        return result;
    }

    // Ritorna un esperto dall'id
    public Esperto getEspertoById(int id) {
        return espertoRepository.findById(id).get();
    }

    // Crea un nuovo esperto
    public void addEsperto(Esperto esperto) throws Exception{
        if(!espertoRepository.findByUserName(esperto.getUserName()).isPresent()){
        esperto.setAuthorities("ESPERTO");
        esperto.getCompetenze().forEach(competenza -> competenzaRepository.findByNome(competenza.getNome()).get().getPossedenti().add(esperto));
        espertoRepository.save(esperto);}
        else {throw new Exception();}
    }

    // Aggiorna un esperto esistente
    public void updateEsperto(Esperto esperto) {
        espertoRepository.save(esperto);
    }

    // Cancella un esperto esistente dall'id
    public void deleteEspertoById(int id) {
        espertoRepository.deleteById(id);
    }

    // |--------------------------|
    // |     ALTRE OPERAZIONI     |
    // |--------------------------|

    // Ritorna un esperto dall'username
    public Esperto getEspertoByUsername(String username) {
        return espertoRepository.findByUserName(username).get();
    }

    // Ritorna l'esperto correntemente loggato
    public Esperto getLoggedEsperto() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return espertoRepository.findByUserName(currentPrincipalName).get();
    }

    // Aggiunge l'esperto loggato agli esperti che si propongono per un progetto
    public void proponiEsperto(int idProgetto) {
        Esperto esperto = getLoggedEsperto();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if(!progetto.esperti_proposti.contains(esperto)) {
            esperto.proposte_progetti.add(progetto);
            progetto.esperti_proposti.add(esperto);
            espertoRepository.save(esperto);
            progettoRepository.save(progetto);
            notificationService.creaNotificaCandidaturaEsperto(esperto, progetto);
        }
    }

    // Completa lo stato di valutazione di un progetto
    public void completaValutazione(int idProgetto){
        Esperto esperto = getLoggedEsperto();
        Progetto progetto = progettoRepository.findById(idProgetto).get();
        if(progetto.getEsperto().equals(esperto)){
            progetto.setStatoProgetto(StatoProgetto.APERTO);
        }
        progettoRepository.save(progetto);
    }
}

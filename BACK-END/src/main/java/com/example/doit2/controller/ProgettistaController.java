package com.example.doit2.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.doit2.model.Competenza;
import com.example.doit2.model.Ente;
import com.example.doit2.model.Notification;
import com.example.doit2.model.Progettista;
import com.example.doit2.model.Progetto;
import com.example.doit2.service.CompetenzaService;
import com.example.doit2.service.ProgettistaService;
import com.example.doit2.service.ProgettoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progettista")
@CrossOrigin
public class ProgettistaController {

    @Autowired
    private ProgettistaService progettistaService;
    @Autowired
    private CompetenzaService competenzaService;
    @Autowired
    private ProgettoService progettoService;

    // Profilo del progettista loggato
    @RequestMapping(path = "/profilo")
    @ResponseBody
    public Progettista getPaginaProfilo() {
        Progettista progettista = progettistaService.getLoggedProgettista();
        progettista.setAuthorities(progettista.getRuolo());
        return progettista;
    }

    // Aggiunge una competenza tramite il nomeCompetenza
    @RequestMapping(method = RequestMethod.POST, path = "/aggiungiCompetenza")
    public void aggiungiCompetenza(@RequestBody String nomeCompetenza) {
        progettistaService.addCompetenza(nomeCompetenza);
    }

    // Rimuove una competenza tramite il nomeCompetenza
    @RequestMapping(method = RequestMethod.POST, path = "/removeCompetenza")
    public void removeCompetenza(@RequestBody String nomeCompetenza){
        progettistaService.removeCompetenza(nomeCompetenza);
    }

    // Ritorna la lista delle competenze non possedute
    @RequestMapping(method = RequestMethod.GET, path = "/competenzeNonPossedute")
    @ResponseBody
    public List<Competenza> getCompetenzeNonPossedute(){
        return progettistaService.getCompetenzeNonPossedute();
    }

    // Candidatura a un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/candidaturaProgetto")
    public void candidaturaProgetto(@RequestBody int idProgetto) {
        progettistaService.addCandidatura(idProgetto);
    }

    // Rimozione candiatura a un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/rimozioneCandidatura")
    public void rimozioneCandidatura(@RequestParam int idProgetto) {
        progettistaService.removeCandidatura(idProgetto);
    }

    // Abbandono di un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/abbandonaProgetto")
    public void abbandonaProgetto(@RequestParam int idProgetto) {
        progettistaService.abbandonaProgetto(idProgetto);
    }

    // Cancella una notifica
    @RequestMapping(method = RequestMethod.POST, path = "/cancellaNotifica")
    public void cancellaNotifica(@RequestParam int idNotifica) {
        progettistaService.cancellaNotifica(idNotifica);
    }

    // Notifiche non visualizzate
    @RequestMapping(method = RequestMethod.GET, path = "/nuoveNotifiche")
    @ResponseBody
    public int getNuoveNotifiche(){ return progettistaService.nuoveNotifiche(); }

    // Crea una richiesta da parte del progettista loggato per unirsi all'Ente
    @RequestMapping(method = RequestMethod.POST, path = "/richiestaEnte")
    public void richiestaEnte(@RequestBody int idEnte) {
        progettistaService.richiestaEnte(idEnte);
    }

    // Annulla la richiesta verso un ente
    @RequestMapping(method = RequestMethod.POST, path = "/annullaRichiestaEnte")
    public void annullaRichiestaEnte(@RequestParam int idEnte){
        progettistaService.annullaRichiestaEnte(idEnte);
    }

    // Abbandona ente
    @RequestMapping(method = RequestMethod.POST, path = "/abbandonaEnte")
    public void abbandonaEnte(@RequestParam int idEnte){
        progettistaService.abbandonaEnte(idEnte);
    }

    // Mostra il profilo di un progettista tramite username
    @RequestMapping(path = "/getProgettistaByUsername")
    @ResponseBody
    public Progettista getProfiloProgettistaByUsername(@RequestParam String username) {
        return progettistaService.getProgettistaByUsername(username);
    }

    // Mostra il profilo di un progettista tramite id
    @RequestMapping(path = "/getProgettistaById")
    @ResponseBody
    public Progettista getProfiloProgettistaById(@RequestParam int id){
        return progettistaService.getProgettistaById(id);
    }

    // Mostra un progetto dall'id
    @RequestMapping(path = "/getProgetto")
    @ResponseBody
    public Progetto getProgettoById(@RequestParam int id) {
        return progettoService.getProgettoById(id);
    }

    // Mostra la lista delle competenze registrate da qualsiasi utente
    @GetMapping(path = "/listaCompetenze")
    public List<Competenza> getListaCompetenze() {
        List<Competenza> lista = competenzaService.getAll();
        return lista;
    }

    // Aggiorna le credenziali del progettista loggato
    @RequestMapping(method = RequestMethod.POST, path = "/aggiornaCredenziali")
    public void aggiornaCredenziali(@RequestParam String username, @RequestParam String email, @RequestParam String password){
        progettistaService.aggiornaCredenziali(username, email, password);
    }

    // Ritorna true se il progettista appartiene all'ente, false altrimenti
    @RequestMapping(method = RequestMethod.GET, path = "/progettistaAppartenente")
    @ResponseBody
    public boolean progettistaAppartenente(@RequestParam int idEnte){
        return this.progettoService.progettistaAppartenente(idEnte);
    }

    // Ritorna true se il progettista è candidato o in carico ad un progetto, false altrimenti
    @RequestMapping(method = RequestMethod.GET, path = "/progettistaCoinvolto")
    @ResponseBody
    public boolean progettistaCoinvolto(@RequestParam int idProgetto){
        return this.progettoService.progettistaCoinvolto(idProgetto);
    }

    // Ritorna true se uno degli enti del progettista loggato ha in carico il progetto specificato, false altrimenti
    @RequestMapping(method = RequestMethod.GET, path = "/enteAppartenente")
    @ResponseBody
    public boolean enteAppartenente(@RequestParam int idProgetto){
        return this.progettoService.enteAppartenente(idProgetto);
    }
}

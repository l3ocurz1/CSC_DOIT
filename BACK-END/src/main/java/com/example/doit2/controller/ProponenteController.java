package com.example.doit2.controller;

import java.util.List;

import com.example.doit2.model.Competenza;
import com.example.doit2.model.Progetto;
import com.example.doit2.model.StatoProgetto;
import com.example.doit2.security.JwtUtil;
import com.example.doit2.service.ProgettoService;
import com.example.doit2.service.ProponenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proponente")
@CrossOrigin
public class ProponenteController {

    @Autowired
    ProponenteService proponenteService;
    @Autowired
    ProgettoService progettoService;
    @Autowired
    JwtUtil jwtUtil;

    // RICHIESTE PER IL PROPONENTE CORRENTEMENTE LOGGATO

    // Pubblica un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/creaProgetto")
    public void creaProgetto(@RequestBody Progetto progetto, @RequestHeader String Authorization) {
        progettoService.addProgetto(progetto);
    }

    // Cancella un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/removeProgetto")
    public void cancellaProgetto(@RequestParam int idProgetto){
        progettoService.deleteProgettoById(idProgetto);
    }

    // Approva un progettista presente nella lista candidati del progetto
    @RequestMapping(method = RequestMethod.POST, path = "/approvaProgettista")
    public void approvaProgettista(@RequestParam int idProgettista, @RequestParam int idProgetto) {
        progettoService.approveCandidato(idProgettista, idProgetto);
    }

    // Rifiuta un progettista candidato per un progetto, lo rimuove dalla lista candidati
    @RequestMapping(method = RequestMethod.POST, path = "/rifiutaProgettista")
    public void rifiutaProgettista(@RequestParam int idProgettista, @RequestParam int idProgetto){
        progettoService.rifiutaCandidato(idProgettista, idProgetto);
    }

    // Espelli progettista
    @RequestMapping(method = RequestMethod.POST, path = "/espelliProgettista")
    public void espelliProgettista(@RequestParam int idProgettista, @RequestParam int idProgetto) {
        progettoService.espelliProgettista(idProgettista, idProgetto);
    }

    //Ritorna i requisiti non richiesti nel progetto
    @RequestMapping(path = "/getAltriRequisiti")
    @ResponseBody
    public List<Competenza> altriRequisiti(@RequestParam int id){
       return progettoService.getRequisitiNonRichiesti(id);
    }

    // Aggiunge una competenza alle competenze richieste di un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/aggiungiRequisito")
    public void aggiungiRequisito(@RequestParam String nomeCompetenza, @RequestParam int idProgetto){
        progettoService.aggiungiRequisito(nomeCompetenza, idProgetto);
    }

    // Rimuove una competenza alle competenze richieste di un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/removeRequisito")
    public void rimuoviRequisito(@RequestParam String nomeCompetenza, @RequestParam int idProgetto){
        progettoService.rimuoviCompetenza(nomeCompetenza, idProgetto);
    }

    // Accetta la richiesta di un esperto per un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/approvaEsperto")
    public void approvaEsperto(@RequestParam int idEsperto, @RequestParam int idProgetto){
        proponenteService.accettaEspertoProposto(idEsperto, idProgetto);
    }

    // Rifiuta un esperto proposto per un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/rifiutaEsperto")
    public void rifiutaEsperto(@RequestParam int idEsperto, @RequestParam int idProgetto){
        proponenteService.rifiutaEspertoProposto(idEsperto, idProgetto);
    }

    // Imposta lo stato di un progetto su CHIUSO
    @RequestMapping(method = RequestMethod.POST, path = "/setProgettoChiuso")
    public void setProgettoChiuso(@RequestParam int idProgetto){
        proponenteService.setProgettoChiuso(idProgetto);
    }

    // Imposta lo stato di un progetto su COMPLETATO
    @RequestMapping(method = RequestMethod.POST, path = "/setProgettoCompletato")
    public void setProgettoCompletato(int idProgetto){
        proponenteService.setProgettoCompletato(idProgetto);
    }
}

package com.example.doit2.controller;

import java.util.List;

import com.example.doit2.model.Progettista;
import com.example.doit2.service.EnteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ente")
@CrossOrigin
public class EnteController {

    @Autowired
    private EnteService enteService;

    // RICHIESTE PER L'ENTE CORRENTEMENTE LOGGATO

    // Accetta una richiesta di unirsi all'ente
    @RequestMapping(method = RequestMethod.POST, path = "/accettaProgettista")
    public void accettaProgettista(@RequestParam int idProgettista) {
        enteService.accettaProgettista(idProgettista);
    }

    // Rifiuta la richiesta di un progettista di unirsi all'ente
    @RequestMapping(method = RequestMethod.POST, path = "rifiutaProgettista")
    public void rifiutaProgettista(@RequestParam int idProgettista){
        enteService.rifiutaProgettista(idProgettista);
    }

    // Espelli un progettista
    @RequestMapping(method = RequestMethod.POST, path = "/espelliProgettista")
    public void espelliProgettista(@RequestParam int idProgettista){
        enteService.espelliProgettista(idProgettista);
    }
}

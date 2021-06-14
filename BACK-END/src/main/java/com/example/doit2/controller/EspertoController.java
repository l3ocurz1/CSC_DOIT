package com.example.doit2.controller;

import com.example.doit2.model.Competenza;
import com.example.doit2.service.EspertoService;
import com.example.doit2.model.Esperto;
import com.example.doit2.service.ProgettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/esperto")
@CrossOrigin
public class EspertoController {

    @Autowired
    private EspertoService espertoService;

    @Autowired
    private ProgettoService progettoService;

    //Propone l'esperto loggato come esperto di un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/proponiEsperto")
    public void proponiEsperto(@RequestParam int idProgetto){
        espertoService.proponiEsperto(idProgetto);
    }

    //Ritorna i requisiti non richiesti nel progetto
    @RequestMapping(path = "/getAltriRequisiti")
    @ResponseBody
    public List<Competenza> altriRequisiti(@RequestParam int id){
        return progettoService.getRequisitiNonRichiesti(id);
    }

    // Aggiunge una competenza alle competenze richieste di un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/aggiungiRequisito")
    public void aggiungiRequisito(String nomeCompetenza, int idProgetto){
        progettoService.aggiungiRequisito(nomeCompetenza, idProgetto);
    }

    // Completa lo stato di valutazione di un progetto
    @RequestMapping(method = RequestMethod.POST, path = "/completaValutazione")
    public void completaValutazione(@RequestParam int idProgetto){
        espertoService.completaValutazione(idProgetto);
    }
}

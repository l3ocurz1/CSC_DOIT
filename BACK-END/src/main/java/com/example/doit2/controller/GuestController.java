package com.example.doit2.controller;

import com.example.doit2.model.*;
import com.example.doit2.security.AuthRequest;
import com.example.doit2.security.JwtUtil;
import com.example.doit2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
@CrossOrigin
public class GuestController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ProgettistaService progettistaService;
    @Autowired
    private ProponenteService proponenteService;
    @Autowired
    private EnteService enteService;
    @Autowired
    private EspertoService espertoService;
    @Autowired
    private ProgettoService progettoService;
    @Autowired
    private AuthenticationManager authenticationManager;

    // AUTENTICAZIONE

    // Ritorna JWT
    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        } catch (UsernameNotFoundException ex) { throw new Exception("inavalid username/password");}
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    // REGISTRAZIONE

    // Progettista
    @PostMapping("/signupProgettista")
    public void signUpProgettista(@RequestBody Progettista progettista) throws Exception {
            progettistaService.addProgettista(progettista);
    }

    // Proponente
    @PostMapping("/signupProponente")
    public void signUpProponente(@RequestBody Proponente proponente) throws Exception{
        proponenteService.addProponente(proponente);
    }

    // Ente
    @PostMapping("/signupEnte")
    public void signUpEnte(@RequestBody Ente ente) throws Exception {
        enteService.addEnte(ente);
    }

    // Esperto
    @PostMapping("/signupEsperto")
    public void signUpEsperto(@RequestBody Esperto esperto) throws Exception{
        espertoService.addEsperto(esperto);
    }

    // LISTE UTENTI/PROGETTI

    // Lista progettisti
    @GetMapping(path = "/listaProgettisti")
    public List<Progettista> getListaProgettisti(){
        List<Progettista> lista = progettistaService.getAll();
        return lista;
    }

    // Lista proponenti
    @RequestMapping(path = "/listaProponenti")
    public List<Proponente> getListaProponenti(){
        List<Proponente> lista = proponenteService.getOnly();
        return lista;
    }

    // Lista enti
    @RequestMapping(path = "/listaEnti")
    public List<Ente> getListaEnti(){
        List<Ente> lista = enteService.getAll();
        return lista;
    }

    // Lista esperti
    @RequestMapping(path = "/listaEsperti")
    public List<Esperto> getListaEsperti(){
        List<Esperto> lista = espertoService.getAll();
        return lista;
    }

    // Lista progetti
    @RequestMapping(path = "/listaProgetti")
    @ResponseBody
    public List<Progetto> listaProgetti(){
        return progettoService.getAll();
    }

}

package com.example.doit2.service;


import com.example.doit2.model.Competenza;
import com.example.doit2.model.Progettista;
import com.example.doit2.model.Progetto;
import com.example.doit2.repository.CompetenzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompetenzaService {

    @Autowired
    private CompetenzaRepository competenzaRepository;

    // |--------------------------|
    // | OPERAZIONI CRUD STANDARD |
    // |--------------------------|

    // Ritorna la lista di tutte le competenze
    public List<Competenza> getAll() {
        List<Competenza> result = new ArrayList<>();
        competenzaRepository.findAll().forEach(result::add);
        return result;
    }

    // Ritorna una competenza dall'id
    public Competenza getCompetenzaById(int id) {
        return competenzaRepository.findById(id).get();
    }

    // Crea una nuova competenza
    public void addCompetenza(Competenza competenza) {
        competenzaRepository.save(competenza);
    }

    // Aggiorna una competenza esistente dall'id
    public void updateCompetenza(Competenza competenza) {
        competenzaRepository.save(competenza);
    }

    // Cancella una competenza esistente dall'id
    public void deleteCompetenzaById(int id) {
        competenzaRepository.deleteById(id);
    }

    // |--------------------------|
    // |     ALTRE OPERAZIONI     |
    // |--------------------------|

    // Ritorna una competenza dal nome
    public Competenza getCompetenzaByNome(String nome) {
        return competenzaRepository.findByNome(nome).get();
    }

    // Ritorna la lista dei progettisti che posseggono una specifica competenza dal nome
    public List<Progettista> getPossedenti(String nomeCompetenza) {
        Competenza competenza = competenzaRepository.findByNome(nomeCompetenza).get();
        return competenza.getPossedenti();
    }

    // Ritorna la lista dei progetti che richiedono una specifica competenza dal nome
    public List<Progetto> getRichiedenti(String nomeCompetenza) {
        Competenza competenza = competenzaRepository.findByNome(nomeCompetenza).get();
        return competenza.getRichieste();
    }

}

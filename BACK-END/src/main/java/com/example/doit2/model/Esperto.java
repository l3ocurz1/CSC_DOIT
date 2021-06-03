package com.example.doit2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Esperto extends Progettista {

    // Lista dei progetti per i quali l'esperto si è proposto
    @ManyToMany
    @JoinTable(
            name = "proposte_progetti",
            joinColumns = @JoinColumn(name = "esperto_id"),
            inverseJoinColumns = @JoinColumn(name = "progetto_id"))
    @JsonIgnoreProperties({"esperti_proposti", "esperto"})
    public List<Progetto> proposte_progetti;

    // Lista dei progetti attualmente affidati all'esperto
    @OneToMany(mappedBy = "esperto")
    @JsonIgnoreProperties({"esperto", "esperti_proposti"})
    private List<Progetto> progetti_assegnati;

    public Esperto() {

    }

    public Esperto(int id, String userName, String password, String email, List<Competenza> competenze, List<Progetto> progetti_candidature, List<Progetto> progetti_in_carico, List<Ente> richieste_ente, List<Ente> ente, List<Notification> notifiche, List<Progetto> progetti_assegnati) {
        super(id, userName, password, email, competenze, progetti_candidature, progetti_in_carico, richieste_ente, ente, notifiche);
        this.progetti_assegnati = progetti_assegnati;
    }

    public List<Progetto> getProgetti_assegnati() {
        return progetti_assegnati;
    }

    public void setProgetti_assegnati(List<Progetto> progetti_assegnati) {
        this.progetti_assegnati = progetti_assegnati;
    }

    public List<Progetto> getProposte_progetti() {
        return proposte_progetti;
    }

    public void setProposte_progetti(List<Progetto> proposte_progetti) {
        this.proposte_progetti = proposte_progetti;
    }
}

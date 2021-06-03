package com.example.doit2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Proponente extends Progettista {

    // Lista dei progetti pubblicati dal proponente
    @OneToMany(mappedBy = "autore")
    @JsonIgnoreProperties({"progettisti", "candidati", "esperto"})
    public List<Progetto> progetti_pubblicati;

    public Proponente() {
    }

    public Proponente(int id, String userName, String password, String email, List<Competenza> competenze, List<Progetto> progetti_candidature, List<Progetto> progetti_in_carico, List<Ente> richieste_ente, List<Ente> ente, List<Notification> notifiche, List<Progetto> progetti_pubblicati) {
        super(id, userName, password, email, competenze, progetti_candidature, progetti_in_carico, richieste_ente, ente, notifiche);
        this.progetti_pubblicati = progetti_pubblicati;
    }

    public List<Progetto> getProgetti_pubblicati() {
        return progetti_pubblicati;
    }

    public void addProgetti_pubblicati(Progetto progetto){
        this. progetti_pubblicati.add(progetto);
    }

    public void removeProgetti_pubblicati(Progetto progetto){
        this.progetti_pubblicati.remove(progetto);
    }
}

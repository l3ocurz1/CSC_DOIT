package com.example.doit2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Progettista extends User {

    // Lista delle competenze possedute dal progettista
    @ManyToMany
    @JoinTable(
            name = "possedereCompetenze",
            joinColumns = @JoinColumn(name = "progettista_id"),
            inverseJoinColumns = @JoinColumn(name = "competenza_id")
    )
    private List<Competenza> competenze;

    // Lista dei progetti per cui il progettista è candidato
    @ManyToMany
    @JoinTable(
            name = "candidature",
            joinColumns = @JoinColumn(name = "progettista_id"),
            inverseJoinColumns = @JoinColumn(name = "progetto_id")
    )
    @JsonIgnoreProperties({"candidati", "progettisti", "autore", "esperti_proposti", "esperto"})
    private List<Progetto> progetti_candidature;

    // Lista dei progetti attualmente in carico del progettista
    @ManyToMany
    @JoinTable(
            name = "carico",
            joinColumns = @JoinColumn(name = "progettista_id"),
            inverseJoinColumns = @JoinColumn(name = "progetto_id"))
    private List<Progetto> progetti_in_carico;

    // Lista degli enti per i quali il progettista ha presentato richiesta di assunzione
    @ManyToMany(mappedBy = "richieste")
    @JsonIgnoreProperties({"richieste", "richieste_ente", "progettisti", "progetti_pubblicati", "progetti_in_carico", "progetti_candidature"})
    private List<Ente> richieste_ente;

    // Lista degli enti a cui il progettista appartiene
    @ManyToMany(mappedBy = "progettisti")
    @JsonIgnoreProperties({"progetti_pubblicati", "progettisti", "ente"})
    private List<Ente> ente;

    // Lista delle notifiche del progettista
    @OneToMany(mappedBy = "receiver")
    private List<Notification> notifiche;

    public Progettista() {
    }

    public Progettista(int id, String userName, String password, String email, List<Competenza> competenze, List<Progetto> progetti_candidature, List<Progetto> progetti_in_carico, List<Ente> richieste_ente, List<Ente> ente, List<Notification> notifiche) {
        super(id, userName, password, email);
        this.competenze = competenze;
        this.progetti_candidature = progetti_candidature;
        this.progetti_in_carico = progetti_in_carico;
        this.richieste_ente = richieste_ente;
        this.ente = ente;
        this.notifiche = notifiche;
    }

    public List<Competenza> getCompetenze() {
        return competenze;
    }

    public void setCompetenze(List<Competenza> competenze) {
        this.competenze = competenze;
    }

    public void addCompetenza(Competenza competenza) {
        this.competenze.add(competenza);
    }

    public List<Progetto> getProgetti_in_carico() {
        return this.progetti_in_carico;
    }

    public void addProgetti_in_carico(Progetto p) {
        this.progetti_in_carico.add(p);
    }

    public void removeProgetti_in_carico(Progetto p) {
        this.progetti_in_carico.remove(p);
    }

    public List<Progetto> getProgetti_candidature() {
        return this.progetti_candidature;
    }

    public void addProgetti_candidature(Progetto p) {
        this.progetti_candidature.add(p);
    }

    public void removeProgetti_candidature(Progetto p) {
        this.progetti_candidature.remove(p);
    }

    public void setProgetti_candidature(List<Progetto> progetti_candidature) {
        this.progetti_candidature = progetti_candidature;
    }

    public void setProgetti_in_carico(List<Progetto> progetti_in_carico) {
        this.progetti_in_carico = progetti_in_carico;
    }

    public List<Ente> getRichieste_ente() {
        return richieste_ente;
    }

    public void setRichieste_ente(List<Ente> richieste_ente) {
        this.richieste_ente = richieste_ente;
    }

    public List<Ente> getEnte() {
        return ente;
    }

    public void setEnte(List<Ente> ente) {
        this.ente = ente;
    }

    public List<Notification> getNotifiche() {
        return notifiche;
    }

    public void setNotifiche(List<Notification> notifiche) {
        this.notifiche = notifiche;
    }

    public void addNotifica(Notification notifica) {
        this.notifiche.add(notifica);
    }

    public void removeNotifica(Notification notifica) {
        this.notifiche.remove(notifica);
    }
}

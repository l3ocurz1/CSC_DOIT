package com.example.doit2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Ente extends Proponente {

    // Lista dei progettisti che hanno fatto richiesta per unirsi all'ente
    @ManyToMany
    @JoinTable(
            name = "richieste_ente",
            joinColumns = @JoinColumn(name = "ente_id"),
            inverseJoinColumns = @JoinColumn(name = "progettista_id"))
    @JsonIgnoreProperties({"richieste_ente", "progetti_in_carico"})
    public List<Progettista> richieste;

    // Lista dei progettisti appartenenti all'ente
    @ManyToMany
    @JoinTable(
            name = "appartenenza_ente",
            joinColumns = @JoinColumn(name = "ente_id"),
            inverseJoinColumns = @JoinColumn(name = "progettista_id"))
    @JsonIgnoreProperties({"ente", "candidature", "carico", "richieste"})
    private List<Progettista> progettisti;


    public Ente() {

    }

    public Ente(int id, String userName, String password, String email, List<Competenza> competenze, List<Progetto> progetti_candidature, List<Progetto> progetti_in_carico, List<Ente> richieste_ente, List<Ente> ente, List<Notification> notifiche, List<Progetto> progetti_pubblicati, List<Progettista> richieste, List<Progettista> progettisti) {
        super(id, userName, password, email, competenze, progetti_candidature, progetti_in_carico, richieste_ente, ente, notifiche, progetti_pubblicati);
        this.richieste = richieste;
        this.progettisti = progettisti;
    }

    public List<Progettista> getRichieste() {
        return richieste;
    }

    public void setRichieste(List<Progettista> richieste) {
        this.richieste = richieste;
    }

    public List<Progettista> getProgettisti() {
        return progettisti;
    }

    public void setProgettisti(List<Progettista> progettisti) {
        this.progettisti = progettisti;
    }
}

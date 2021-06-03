package com.example.doit2.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Progetto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String titolo;

    private String descrizione;

    // Lista delle competenze richieste dal progetto
    @ManyToMany(mappedBy = "richieste")
    private List<Competenza> requisiti;

    // Stato attuale del progetto
    @Enumerated(EnumType.STRING)
    private StatoProgetto statoProgetto;

    // Proponente che ha pubblicato il progetto
    @ManyToOne
    @JoinColumn(name = "autore_id")
    @JsonIgnoreProperties({"progetti_pubblicati", "password", "competenze", "ente", "notifiche", "progettisti"})
    private Proponente autore;

    // Lista dei progettisti candidati per il progetto
    @ManyToMany(mappedBy = "progetti_candidature")
    @JsonIgnoreProperties({"notifiche", "progetti_in_carico", "progetti_candidature", "ente", "progetti_pubblicati", "progetti_assegnati", "proposte_progetti"})
    private List<Progettista> candidati;

    // Lista dei progettisti in carico per il progetto
    @ManyToMany(mappedBy = "progetti_in_carico")
    @JsonIgnoreProperties({"notifiche", "progetti_in_carico", "progetti_candidature", "ente", "progetti_pubblicati", "progetti_assegnati", "proposte_progetti", "progettisti", "richieste"})
    private List<Progettista> progettisti;

    // Lista degli esperti che si sono proposti per il progetto
    @ManyToMany(mappedBy = "proposte_progetti")
    @JsonIgnoreProperties({"proposte_progetti", "progetti_assegnati"})
    public List<Esperto> esperti_proposti;

    // Eventuale esperto in carico per il progetto
    @ManyToOne
    @JoinColumn(name = "esperto_id")
    @JsonIgnoreProperties({"progetti_assegnati", "proposte_progetti"})
    private Esperto esperto;

    public Progetto() {
    }

    public Progetto(int id, String titolo, String descrizione, List<Competenza> requisiti, StatoProgetto statoProgetto, Proponente autore, List<Progettista> candidati, List<Progettista> progettisti, Esperto esperto) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.requisiti = requisiti;
        this.statoProgetto = statoProgetto;
        this.autore = autore;
        this.candidati = candidati;
        this.progettisti = progettisti;
        this.esperto = esperto;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Competenza> getRequisiti() {
        return requisiti;
    }

    public void setRequisiti(List<Competenza> requisiti) {
        this.requisiti = requisiti;
    }

    public StatoProgetto getStatoProgetto() {
        return statoProgetto;
    }

    public void setStatoProgetto(StatoProgetto statoProgetto) {
        this.statoProgetto = statoProgetto;
    }

    public Proponente getAutore() {
        return autore;
    }

    public void setAutore(Proponente autore) {
        this.autore = autore;
    }

    public List<Progettista> getCandidati() {
        return candidati;
    }

    public void addCandidati(Progettista progettista) {
        this.candidati.add(progettista);
    }

    public void removeCandidati(Progettista progettista) {
        this.candidati.remove(progettista);
    }

    public List<Progettista> getProgettisti() {
        return progettisti;
    }

    public void addProgettista(Progettista progettista) {
        this.progettisti.add(progettista);
    }

    public void removeProgettisti(Progettista progettista) {
        this.progettisti.remove(progettista);
    }

    public void setCandidati(List<Progettista> candidati) {
        this.candidati = candidati;
    }

    public void setProgettisti(List<Progettista> progettisti) {
        this.progettisti = progettisti;
    }

    public List<Esperto> getEsperti_proposti() {
        return esperti_proposti;
    }

    public void setEsperti_proposti(List<Esperto> esperti_proposti) {
        this.esperti_proposti = esperti_proposti;
    }

    public Esperto getEsperto() {
        return esperto;
    }

    public void setEsperto(Esperto esperto) {
        this.esperto = esperto;
    }
}

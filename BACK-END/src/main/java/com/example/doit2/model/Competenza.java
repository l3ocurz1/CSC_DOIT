package com.example.doit2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
public class Competenza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;

    private String descrizione;

    // Lista dei progettisti che possiedono la competenza
    @ManyToMany(mappedBy = "competenze")
    @JsonIgnore
    private List<Progettista> possedenti;

    // Lista dei progetti che includono nei requisiti la competenza
    @ManyToMany
    @JoinTable(
            name = "richiedereCompetenze",
            joinColumns = @JoinColumn(name = "competenza_id"),
            inverseJoinColumns = @JoinColumn(name = "progetto_id"))
    @JsonIgnore
    private List<Progetto> richieste;

    public Competenza() {
    }

    public Competenza(int id, String nome, String descrizione, List<Progettista> possedenti, List<Progetto> richieste) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.possedenti = possedenti;
        this.richieste = richieste;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Progettista> getPossedenti() {
        return possedenti;
    }

    public void setPossedenti(List<Progettista> possedenti) {
        this.possedenti = possedenti;
    }

    public List<Progetto> getRichieste() {
        return richieste;
    }

    public void setRichieste(List<Progetto> richieste) {
        this.richieste = richieste;
    }

}

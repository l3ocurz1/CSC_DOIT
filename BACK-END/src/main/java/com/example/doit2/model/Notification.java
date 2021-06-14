package com.example.doit2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String message;

    private boolean seen;

    // Destinatario della notifica
    @ManyToOne
    @JoinColumn(name = "progettista_id")
    @JsonIgnore
    private Progettista receiver;

    public Notification() {
    }

    public Notification(int id, String message, boolean seen, Progettista receiver) {
        this.id = id;
        this.message = message;
        this.seen = false;
        this.receiver = receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Progettista getReceiver() {
        return receiver;
    }

    public void setReceiver(Progettista receiver) {
        this.receiver = receiver;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}

package com.example.doit2.model;

public enum StatoProgetto {

    // Progetto aperto, i progettisti possono candidarsi
    APERTO,

    // Progetto in stato di valutazione, i progettisti non possono candidatsi finchè un esperto non viene assunto
    // e imposta le competenze necessarie per il progetto
    VALUTAZIONE,

    // Il progetto è in stato di esecuzione, non è più possibile candidarsi
    CHIUSO,

    // Il progetto è stato completato
    COMPLETATO
}

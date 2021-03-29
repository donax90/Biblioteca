/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe che modella l'entità Prestito
 * @author donatotanieli
 */
public class Prestito implements Serializable{
    
    public static int id_counterPrestito;
    private int id;
    private Utente utente;
    private Libro libro;
    private LocalDateTime inizioPrestito;
    private LocalDateTime finePrestito;

    //COSTRUTTORE
    public Prestito() { 
        id_counterPrestito++;
        this.id = id_counterPrestito;
        finePrestito = null;
    }

    //GETTER AND SETTER
    public int getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void setInizioPrestito(LocalDateTime inizioPrestito) {
        this.inizioPrestito = inizioPrestito;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDateTime getInizioPrestito() {
        return inizioPrestito;
    }

    public LocalDateTime getFinePrestito() {
        return finePrestito;
    }

    public void setFinePrestito(LocalDateTime finePrestito) {
        this.finePrestito = finePrestito;
    }

    /**
     * Override del metodo equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        Prestito p = (Prestito)obj;
        return(p.getId() == this.id);
    }
    
    
    
}

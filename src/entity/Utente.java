/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe che modella l'entità Utente
 * @author donatotanieli
 */
public class Utente implements Serializable{
    
    public static int id_counter;
    private int id;
    private String nome;
    private String cognome;
    private LocalDate data;
    private String telefono;

    //COSTRUTTORI
    public Utente(String nome, String cognome, LocalDate data, String telefono) {
        id_counter++;
        this.id = id_counter;
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.telefono = telefono;
    }

    public Utente() {
        id_counter++;
        this.id = id_counter;
    }

    //GETTER AND SETTER
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Override del metodo equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        Utente u = (Utente) obj;
        if(u.getId() == this.id)
            return true;
        else return false;
    }
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 * Classe che modella l'entità Libro
 * @author donatotanieli
 */
public class Libro implements Serializable{
    
    private String titolo;
    private String autore;
    private int anno;
    private String ibsn;
    private int num_copie;

    //COSTRUTTORI
    
    public Libro() {
    }

    public Libro(String titolo, String autore, int anno, String ibsn, int num_copie) {
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.ibsn = ibsn;
        this.num_copie = num_copie;
    }

    //GETTER AND SETTER
    
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getIbsn() {
        return ibsn;
    }

    public void setIbsn(String ibsn) {
        this.ibsn = ibsn;
    }

    public int getNum_copie() {
        return num_copie;
    }

    public void setNum_copie(int num_copie) {
        this.num_copie = num_copie;
    }
    
    /**
     * Metodo che diminuisce di una unità il numero di copie
     */
    public void diminuisciCopie(){
        this.num_copie--;
    }
    
    /**
     * Metodo che aumenta di una unità il numero di copie
     */
    public void aumentaCopie(){
        this.num_copie++;
    }

    /**
     * Override del metodo equals
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        Libro l = (Libro)obj;
        return l.getIbsn().equals(this.ibsn);
    }
    
    
    
}

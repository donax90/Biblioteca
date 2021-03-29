/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Classe che modella l'entità biblioteca
 * @author donatotanieli
 */
public class Biblioteca implements Serializable{
    
    private List<Prestito> listaPrestiti;
    private List<Libro> listaLibri;
    private List<Utente> listaUtenti;

    //Costruttori
    public Biblioteca() {
        listaUtenti = new ArrayList<>();
        listaLibri = new ArrayList<>();
        listaPrestiti = new ArrayList<>();
    }

    public Biblioteca(List<Prestito> listaPrestiti, List<Libro> listaLibri, List<Utente> listaUtenti) {
        this.listaPrestiti = listaPrestiti;
        this.listaLibri = listaLibri;
        this.listaUtenti = listaUtenti;
    }

    //GETTER AND SETTER
    public List<Prestito> getListaPrestiti() {
        return listaPrestiti;
    }

    public void setListaPrestiti(List<Prestito> listaPrestiti) {
        this.listaPrestiti = listaPrestiti;
    }

    public List<Libro> getListaLibri() {
        return listaLibri;
    }

    public void setListaLibri(List<Libro> listaLibri) {
        this.listaLibri = listaLibri;
    }

    public List<Utente> getListaUtenti() {
        return listaUtenti;
    }

    public void setListaUtenti(List<Utente> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }
    
    //Altri metodi
    
    /**
     * Metodo per cercare degli utenti in base al predicato di tipo Utente
     * @param predicate
     * @return lista di utenti filtrati
     */
    public List<Utente> cercaUtenti(Predicate<Utente> predicate){
        List<Utente> lista = new ArrayList<>();
        for(Utente u : listaUtenti){
            if(predicate.test(u)){
                lista.add(u);
            }
        }
        return lista;
    }
    
    /**
     * Metodo per cercare dei libri in base al predicato di tipo Libro
     * @param predicate
     * @return lista di libri filtrati
     */
    public List<Libro> cercaLibri(Predicate<Libro> predicate){
        List<Libro> lista = new ArrayList<>();
        for(Libro l : listaLibri){
            if(predicate.test(l)){
                lista.add(l);
            }
        }
        return lista;
    }
    
    /**
     * Metodo per cercare dei prestiti in base al predicato di tipo Prestito
     * @param predicate
     * @return lista di prestiti filtrati
     */
    public List<Prestito> cercaPrestiti(Predicate<Prestito> predicate){
        List<Prestito> lista = new ArrayList<>();
        for(Prestito p : listaPrestiti){
            if(predicate.test(p)){
                lista.add(p);
            }
        }
        return lista;
    }
    
    /**
     * Metodo per cercare un prestito in base al predicato di tipo Utente
     * @param predicate
     * @return lista di prestiti filtrati
     */
    public List<Prestito> cercaPrestitoDaUtente(Predicate<Utente> predicate){
        List<Prestito> listaP = new ArrayList<>();
        for(Prestito p : listaPrestiti){
            if(predicate.test(p.getUtente())){
                listaP.add(p);
            }
        }
        return listaP;
    }
    
    /**
     * Metodo per cercare un prestito in base al predicato di tipo Libro
     * @param predicate
     * @return lista di prestiti filtrati
     */
    public List<Prestito> cercaPrestitoDaLibro(Predicate<Libro> predicate){
        List<Prestito> listaP = new ArrayList<>();
        for(Prestito p : listaPrestiti){
            if(predicate.test(p.getLibro())){
                listaP.add(p);
            }
        }
        return listaP;
    }
    
    /**
     * Metodo che consente di cercare un Prestito identificato da id
     * @param id
     * @return prestito se trovato, null altrimenti
     */
    public Prestito cercaPrestitoDaId(int id){
        for(Prestito p : listaPrestiti){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    /**
     * Metodo che restituisce un libro identificato da ibsn
     * @param ibsn
     * @return libro se trovato, null altrimenti
     */
    public Libro getLibroDaIbsn(String ibsn){
        for(Libro l : listaLibri){
            if(l.getIbsn().equals(ibsn)){
                return l;
            }
        }
        return null;
    }
    
    /**
     * Metodo che restituisce un utente identificato da id
     * @param id
     * @return utente se trovato, null altrimenti
     */
    public Utente getUtenteDaId(int id){
        for(Utente u : listaUtenti){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }
    
    /**
     * Metodo che permette di rendere il libro riaggiungendolo alla biblioteca
     * @param p
     * @return true se l'operazione è andata a buon fine, false altrimenti
     */
    public boolean rendiLibro(Prestito p){
        boolean reso = false;
        cicloPrestiti: for(Prestito prestito : listaPrestiti){
            if(prestito.equals(p)){
                //Se trovo il prestito scorro la lista dei libri della biblioteca per poi incrementare di 1 il numero di copie
                cicloLibri : for(Libro l : listaLibri){
                    if(l.equals(p.getLibro())){
                        //Aggiungo il libro nella libreria
                        l.aumentaCopie();
                        reso = true;
                        break cicloPrestiti;
                    }
                }               
            }
        }
        return reso;
    }

}

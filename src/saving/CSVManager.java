/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import entity.Biblioteca;
import entity.Libro;
import entity.Prestito;
import entity.Utente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che gestisce l'import ed export del file CSV all'interno della cartella csv-file
 * @author donatotanieli
 */
public class CSVManager {
    
    FileWriter fw = null;
    BufferedWriter bw = null;
    BufferedReader br = null;
    PrintWriter pw = null;
    
    final String UTENTI_PATH = ".//csv-file//Utenti.csv"; //Percorso sul quale salvare il file Utenti.csv
    final String LIBRI_PATH = ".//csv-file//Libri.csv"; //Percorso sul quale salvare il file Libri.csv
    final String PRESTITI_PATH = ".//csv-file//Prestiti.csv"; //Percorso sul quale salvare il file Prestiti.csv
    
    /**
     * Metodo che gestisce l'esportazione del file CSV
     * @param biblioteca 
     */
    public void esportaCSV(Biblioteca biblioteca){
        esportaUtentiCSV(biblioteca.getListaUtenti());
        esportaLibriCSV(biblioteca.getListaLibri());
        esportaPrestitiCSV(biblioteca.getListaPrestiti());
    }
    
    /**
     * Metodo che si occupa di salvare la lista degli Utenti sul file Utenti.csv
     * @param utenti 
     */
    private void esportaUtentiCSV(List<Utente> utenti){
        
        File f = new File(UTENTI_PATH);
        
        try {
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            if(f.length() == 0){
                pw.append("ID" + ";" + "Nome" + ";" + "Cognome" + ";" + "Data di nascita" + ";" + "Telefono" + "\n");
            }
            for(Utente utente : utenti){
                pw.append(utente.getId() + ";" + utente.getNome() + ";" + utente.getCognome() + ";" + 
                        String.valueOf(utente.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) + ";" + utente.getTelefono() + "\n");
            }
            pw.flush();
            bw.close();
            fw.close();
            
        } catch (Exception e) {
        }
    }
    
    /**
     * Metodo che si occupa di salvare la lista dei libri sul file Libri.csv
     * @param libri 
     */
    private void esportaLibriCSV(List<Libro> libri){
        
        File f = new File(LIBRI_PATH);
        
        try {
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            if(f.length() == 0){
                pw.append("Titolo" + ";" + "Autore" + ";" + "Anno" + ";" + "Ibsn" + ";" + "Numero di copie" + "\n");
            }
            for(Libro libro : libri){
                pw.append(libro.getTitolo() + ";" + libro.getAutore() + ";" + String.valueOf(libro.getAnno()) + ";" + 
                        libro.getIbsn() + ";" + libro.getNum_copie() + "\n");
            }
            pw.flush();
            bw.close();
            fw.close();
            
        } catch (Exception e) {
        }
    }
    
    /**
     * Metodo che si occupa di salvare la lista dei prestiti sul file Prestiti.csv
     * @param prestiti 
     */
    private void esportaPrestitiCSV(List<Prestito> prestiti){
        
        File f = new File(PRESTITI_PATH);
        
        try {
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            if(f.length() == 0){
                pw.append("ID Prestito" + ";" + "ID Utente" + ";" + "Nome" + ";" + "Cognome" + ";" + "Titolo" + ";" + "Autore" + ";" + "Ibsn" + ";" + "Inizio prestito" + ";" + "Fine prestito" + "\n");
            }
            for(Prestito prestito : prestiti){
                pw.append(prestito.getId() + ";" + prestito.getUtente().getId() + ";" + prestito.getUtente().getNome() + ";" + prestito.getUtente().getCognome() + ";" + 
                        prestito.getLibro().getTitolo() + ";" + prestito.getLibro().getAutore() + ";" + prestito.getLibro().getIbsn() + ";" + prestito.getInizioPrestito() +
                        ";" + prestito.getFinePrestito() + "\n");
            }
            pw.flush();
            bw.close();
            fw.close();
            
        } catch (Exception e) {
        }
    }
    
    /**
     * Metodo che gestisce l'importazione del file CSV
     * @return biblioteca
     */
    public Biblioteca importaCSV(){
        
        Biblioteca  biblioteca = new Biblioteca();
        List<Utente> listaUtenti = importaUtentiCSV();
        List<Libro> listaLibri = importaLibriCSV();
        biblioteca.setListaUtenti(listaUtenti);
        biblioteca.setListaLibri(listaLibri);
        List<Prestito> listaPrestiti = importaPrestitiCSV(biblioteca);
        biblioteca.setListaPrestiti(listaPrestiti);
        
        return biblioteca;
    }
    
    /**
     * Metodo che si occupa della lettura del file Utenti.csv e salva tutto nella lista di Utenti
     * @return listaUtenti
     */
    private List<Utente> importaUtentiCSV(){
        
        List<Utente> listaUtenti = new ArrayList<>();
        String line = "";
        
        try {
            br = new BufferedReader(new FileReader(UTENTI_PATH));
            br.readLine();
            
            while((line = br.readLine()) != null){
                String[] fields = line.split(";");
                
                if(fields.length > 0){
                    Utente u = new Utente();
                    u.setNome(fields[1]);
                    u.setCognome(fields[2]);
                    u.setData(LocalDate.parse(fields[3], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    u.setTelefono(fields[4]);
                    listaUtenti.add(u);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return listaUtenti;
    }
    
    /**
     * Metodo che si occupa della lettura del file Libri.csv e salva tutto nella lista di Libri
     * @return listaLibri
     */
    private List<Libro> importaLibriCSV(){
        
        List<Libro> listaLibri = new ArrayList<>();
        String line = "";
        
        try {
            br = new BufferedReader(new FileReader(LIBRI_PATH));
            br.readLine();
            
            while((line = br.readLine()) != null){
                String[] fields = line.split(";");
                
                if(fields.length > 0){
                    Libro l = new Libro();
                    l.setTitolo(fields[0]);
                    l.setAutore(fields[1]);
                    l.setAnno(Integer.parseInt(fields[2]));
                    l.setIbsn(fields[3]);
                    l.setNum_copie(Integer.parseInt(fields[4]));
                    listaLibri.add(l);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaLibri;
    }
    
    /**
     * Metodo che si occupa della lettura del file Prestiti.csv e salva tutto nella lista dei Prestiti
     * @param biblioteca
     * @return listaPrestiti
     */
    private List<Prestito> importaPrestitiCSV(Biblioteca biblioteca){
        List<Prestito> listaPrestiti = new ArrayList<>();
        String line = "";
        
        try {
            br = new BufferedReader(new FileReader(PRESTITI_PATH));
            br.readLine();
            
            while((line = br.readLine()) != null){
                String[] fields = line.split(";");
                
                if(fields.length > 0){
                    Prestito p = new Prestito();
                    Utente u = biblioteca.getUtenteDaId(Integer.parseInt(fields[1]));
                    Libro l = biblioteca.getLibroDaIbsn(fields[6]);
                    p.setUtente(u);
                    p.setLibro(l);
                    p.setInizioPrestito(LocalDateTime.parse(fields[7]));
                    if(fields[8].equals("null")){
                        p.setFinePrestito(null);
                    }else{
                        p.setFinePrestito(LocalDateTime.parse(fields[8]));
                    }
                    listaPrestiti.add(p);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPrestiti;
    }
    
}

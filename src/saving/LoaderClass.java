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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Classe che gestisce il caricamento del file attraverso la deserializzazione
 * @author donatotanieli
 */
public class LoaderClass {
    
    /**
     * Metodo che consente di caricare l'oggetto Biblioteca dal file indicato dal percorso in path
     * @param biblioteca
     * @param path
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void loadFile(Biblioteca biblioteca, String path) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fin = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fin);
        
        biblioteca.setListaUtenti((List<Utente>) in.readObject()); 
        biblioteca.setListaLibri((List<Libro>)in.readObject()); 
        biblioteca.setListaPrestiti((List<Prestito>)in.readObject());
        
        Utente.id_counter = biblioteca.getListaUtenti().size();
        Prestito.id_counterPrestito = biblioteca.getListaPrestiti().size();
        
        fin.close();
        in.close();
 
    }
}

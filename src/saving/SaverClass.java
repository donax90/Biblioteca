/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saving;

import entity.Biblioteca;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Classe che gestisce il salvataggio del file attraverso la serializzazione
 * @author donatotanieli
 */
public class SaverClass {
    
    /**
     * Metodo che consente di salvare l'oggetto Biblioteca in un file indicato dal percorso in path
     * @param biblioteca
     * @param path
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveFile(Biblioteca biblioteca, String path) throws FileNotFoundException, IOException{
        
        FileOutputStream fout = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        
        oos.writeObject(biblioteca.getListaUtenti());
        oos.writeObject(biblioteca.getListaLibri());
        oos.writeObject(biblioteca.getListaPrestiti());
        
        fout.close();
        oos.close();
        
    }
    
}

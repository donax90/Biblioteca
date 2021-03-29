/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Biblioteca;
import entity.Libro;
import entity.Prestito;
import entity.Utente;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import saving.CSVManager;
import saving.LoaderClass;
import saving.SaverClass;


/**
 * Classe che gestisce tutti i componenti dell'interfaccia della Biblioteca
 * @author donatotanieli
 */
public class BibliotecaGUI extends javax.swing.JFrame {

    private Biblioteca biblioteca;


    /**
     * Creates new form BibliotecaGUI
     */
    public BibliotecaGUI() {
        initComponents();
        biblioteca = new Biblioteca();

        //Raggruppo le checkbox utenti e libri
        ButtonGroup bgFiltra = new ButtonGroup();
        bgFiltra.add(utentiCheckBox);
        bgFiltra.add(libriCheckBox);

        //Raggruppo le checkbox dei prestiti in corso e completati
        ButtonGroup bgPrestiti = new ButtonGroup();
        bgPrestiti.add(inCorsoCheckBox);
        bgPrestiti.add(completatiCheckBox);
    }

    //GETTER
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }


    //////////////////// BARRA DEI MENU ///////////////////

    ///////////MENU FILE

    ////MENUITEM IMPORTA CSV

    /**
     * ActionPerformed del MenuItem Importa CSV
     * @param evt
     */
    private void importaCSVMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importaCSVMenuActionPerformed
        CSVManager csv = new CSVManager();
        biblioteca = csv.importaCSV();
        aggiornaTabella(0);
        aggiornaTabella(1);
        aggiornaTabella(2);
        JOptionPane.showMessageDialog(null, "File importato correttamente!", "IMPORTA", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_importaCSVMenuActionPerformed

    ////MENUITEM ESPORTA CSV

    /**
     * ActionPerformed del MenuItem Esporta CSV
     * @param evt
     */
    private void esportaCSVMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esportaCSVMenuActionPerformed
        CSVManager csv = new CSVManager();
        csv.esportaCSV(biblioteca);
        JOptionPane.showMessageDialog(null, "File esportato correttamente!", "ESPORTA", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_esportaCSVMenuActionPerformed

    ////MENUITEM SALVA

    /**
     * ActionPerformed del MenuItem Salva
     * @param evt
     */
    private void salvaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvaMenuActionPerformed
        salva();
    }//GEN-LAST:event_salvaMenuActionPerformed

    ////MENUITEM CARICA

    /**
     * ActionPerformed del MenuItem Carica
     * @param evt
     */
    private void caricaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caricaMenuActionPerformed
        carica();
    }//GEN-LAST:event_caricaMenuActionPerformed

    ///////////MENU UTENTE

    ////MENUITEM REGISTRA UTENTE

    /**
     * ActionPerformed del MenuItem Registra Utente
     * @param evt
     */
    private void registraUtenteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registraUtenteMenuActionPerformed
        RegistraUtenteGUI rU = new RegistraUtenteGUI(BibliotecaGUI.this);
        rU.setVisible(true);
    }//GEN-LAST:event_registraUtenteMenuActionPerformed

    ////MENUITEM MODIFICA UTENTE

    /**
     * ActionPerformed del MenuItem Modifica Utente
     * @param evt
     */
    private void modificaUtenteMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificaUtenteMenuActionPerformed
        int rigaSelezionata = tabellaUtenti.getSelectedRow();//recupero la riga selezionata
        if(rigaSelezionata != -1){
            int idUtente = Integer.parseInt(tabellaUtenti.getValueAt(rigaSelezionata, 0).toString());//recupero l'id dell'utente dalla riga
            ModificaUtenteGUI mU = new ModificaUtenteGUI(BibliotecaGUI.this, idUtente);
            mU.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Perfavore, selezionare almeno una riga!", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_modificaUtenteMenuActionPerformed

    ///////////MENU LIBRO

    ////MENUITEM REGISTRA LIBRO

    /**
     * ActionPerformed del MenuItem Registra Libro
     * @param evt
     */
    private void registraLibroMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registraLibroMenuActionPerformed
        RegistraLibroGUI rL = new RegistraLibroGUI(BibliotecaGUI.this);
        rL.setVisible(true);
    }//GEN-LAST:event_registraLibroMenuActionPerformed

    ////MENUITEM MODIFICA LIBRO

    /**
     * ActionPerformed del MenuItem Modifica Libro
     * @param evt
     */
    private void modificaLibroMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificaLibroMenuActionPerformed
        int rigaSelezionata = tabellaLibri.getSelectedRow();//recupero la riga selezionata
        if(rigaSelezionata != -1){
            String ibsnLibro = tabellaLibri.getValueAt(rigaSelezionata, 3).toString();//recupero l'ibsn del libro dalla riga
            ModificaLibroGUI mU = new ModificaLibroGUI(BibliotecaGUI.this, ibsnLibro);
            mU.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Perfavore, selezionare almeno una riga!", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_modificaLibroMenuActionPerformed

    ///////////MENU PRESTITO

    ////MENUITEM REGISTRA PRESTITO

    /**
     * ActionPerformed del MenuItem Registra Prestito
     * @param evt
     */
    private void registraPrestitoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registraPrestitoMenuActionPerformed
        RegistraPrestitoGUI rP = new RegistraPrestitoGUI(BibliotecaGUI.this);
        rP.setVisible(true);
    }//GEN-LAST:event_registraPrestitoMenuActionPerformed

    ////MENUITEM RESO LIBRO

    /**
     * ActionPerformed del MenuItem Reso Libro
     * @param evt
     */
    private void resoLibroMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resoLibroMenuActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) tablePrestito.getModel();
        if(tablePrestito.getSelectedRow() != -1){
            Prestito prestito = biblioteca.cercaPrestitoDaId(Integer.parseInt(dtm.getValueAt(tablePrestito.getSelectedRow(), 0).toString()));
            ResoLibroGUI rl = new ResoLibroGUI(BibliotecaGUI.this, prestito);
            rl.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Perfavore, selezionare almeno una riga!", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_resoLibroMenuActionPerformed

    ///////////ACTIONPERFORMED DEI BUTTON

    /**
     * ActionPerformed del Button Registra Utente
     * @param evt
     */
    private void btnRegistraUtenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistraUtenteActionPerformed
        RegistraUtenteGUI rU = new RegistraUtenteGUI(BibliotecaGUI.this);
        rU.setVisible(true);

    }//GEN-LAST:event_btnRegistraUtenteActionPerformed

    /**
     * ActionPerformed del Button Modifica Utente
     * @param evt
     */
    private void btnModificaUtenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificaUtenteActionPerformed
        //recupero l'indice della riga selezionata
        int rigaSelezionata = tabellaUtenti.getSelectedRow();
        //controllo se effettivamente risulta una riga selezionata, altrimenti restituisce -1
        if(rigaSelezionata != -1){
            int idUtente = Integer.parseInt(tabellaUtenti.getValueAt(rigaSelezionata, 0).toString()); //recupero l'id dell'utente della riga selezionata
            ModificaUtenteGUI mU = new ModificaUtenteGUI(BibliotecaGUI.this, idUtente);
            mU.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Perfavore, selezionare almeno una riga!", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificaUtenteActionPerformed

    /**
     * ActionPerformed del Button Registra Libro
     * @param evt
     */
    private void btnRegistraLibro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistraLibro1ActionPerformed
        RegistraLibroGUI rL = new RegistraLibroGUI(BibliotecaGUI.this);
        rL.setVisible(true);
    }//GEN-LAST:event_btnRegistraLibro1ActionPerformed

    /**
     * ActionPerformed del Button Modifica Libro
     * @param evt
     */
    private void btnModificaLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificaLibroActionPerformed

        int rigaSelezionata = tabellaLibri.getSelectedRow();//recupero l'indice della riga selezionata (se nonè selezionata mi restituisce -1)
        if(rigaSelezionata != -1){
            String ibsnLibro = tabellaLibri.getValueAt(rigaSelezionata, 3).toString();//recupero l'ibsn del libro dalla riga
            ModificaLibroGUI mU = new ModificaLibroGUI(BibliotecaGUI.this, ibsnLibro);
            mU.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Perfavore, selezionare almeno una riga!", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificaLibroActionPerformed

    /**
     * ActionPerformed del Button Registra Prestito
     * @param evt
     */
    private void btnRegistraPrestitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistraPrestitoActionPerformed
        RegistraPrestitoGUI rP = new RegistraPrestitoGUI(BibliotecaGUI.this);
        rP.setVisible(true);
    }//GEN-LAST:event_btnRegistraPrestitoActionPerformed

    /**
     * ActionPerformed del Button Reso Libro
     * @param evt
     */
    private void btnRestituisciLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestituisciLibroActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) tablePrestito.getModel();
        if(tablePrestito.getSelectedRow() != -1){
            Prestito prestito = biblioteca.cercaPrestitoDaId(Integer.parseInt(dtm.getValueAt(tablePrestito.getSelectedRow(), 0).toString()));
            ResoLibroGUI rl = new ResoLibroGUI(BibliotecaGUI.this, prestito);
            rl.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Perfavore, selezionare almeno una riga!", "ATTENZIONE", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnRestituisciLibroActionPerformed



    ///////////ACTIONPERFORMED DELLE CHECKBOX


    /**
     * ActionPerformed della checkbox relativa agli utenti della sezione prestiti
     * @param evt
     */
    private void utentiCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utentiCheckBoxActionPerformed
        checkBoxFiltroListener();
    }//GEN-LAST:event_utentiCheckBoxActionPerformed

    /**
     * ActionPerformed della checkbox relativa ai libri della sezione prestiti
     * @param evt
     */
    private void libriCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libriCheckBoxActionPerformed
        checkBoxFiltroListener();
    }//GEN-LAST:event_libriCheckBoxActionPerformed

    /**
     * ActionPerformed della checkbox relativa ai prestiti (In corso o completati)
     * @param evt
     */
    private void inCorsoCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inCorsoCheckBoxActionPerformed
        checkBoxPrestitiListener();
    }//GEN-LAST:event_inCorsoCheckBoxActionPerformed

    /**
     * ActionPerformed della checkbox relativa ai prestiti (In corso o completati)
     * @param evt
     */
    private void completatiCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completatiCheckBoxActionPerformed
        checkBoxPrestitiListener();
    }//GEN-LAST:event_completatiCheckBoxActionPerformed



    ///////////KEYLISTENER


    /**
     * KeyListener della textfield relativa al filtraggio degli utenti.
     * Non appena si comincia a digitare il testo nella textfield viene invocato il listener che filtra i risultati
     * @param evt
     */
    private void filtraUtenti(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtraUtenti

        String filterBy = (String) userComboBox.getSelectedItem(); //recupero il tipo di ricerca che deve essere fatta (ad es. per Nome, Cognome, ecc)
        DefaultTableModel dtm = (DefaultTableModel) tabellaUtenti.getModel();
        dtm.setRowCount(0); //azzero le righe della tabella
        List<Utente> utentiFiltrati = null;

        switch(filterBy){
            case "Nome":
                utentiFiltrati = biblioteca.cercaUtenti(u->containsIgnoreCase(u.getNome(),filtroText.getText()));
                break;

            case "Cognome":
                utentiFiltrati = biblioteca.cercaUtenti(u->containsIgnoreCase(u.getCognome(),filtroText.getText()));
                break;

            case "Data":
                utentiFiltrati = biblioteca.cercaUtenti(u->containsIgnoreCase(u.getData().toString(),(filtroText.getText())));
                break;

            case "Telefono":
                utentiFiltrati = biblioteca.cercaUtenti(u->containsIgnoreCase(u.getTelefono(),filtroText.getText()));
                break;
        }
        if(utentiFiltrati != null)
            utentiFiltrati.forEach(u->dtm.addRow(new Object[]{u.getId(), u.getNome(), u.getCognome(), u.getData(), u.getTelefono()}));
    }//GEN-LAST:event_filtraUtenti

    /**
     * KeyListener della textfield relativa al filtraggio dei libri.
     * Non appena si comincia a digitare il testo nella textfield viene invocato il listener che filtra i risultati
     * @param evt
     */
    private void filtraLibri(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtraLibri

        String filterBy = (String) libroComboBox.getSelectedItem();//recupero il tipo di ricerca che deve essere fatta (ad es. per Titolo, Autore, ecc)
        DefaultTableModel dtm = (DefaultTableModel) tabellaLibri.getModel();
        dtm.setRowCount(0);//azzero le righe della tabella
        List<Libro> libriFiltrati = null;
        switch(filterBy){

            case "Titolo":
                libriFiltrati = biblioteca.cercaLibri(l->containsIgnoreCase(l.getTitolo(), bookFilterText.getText()));
                break;

            case "Autore":
                libriFiltrati = biblioteca.cercaLibri(l->containsIgnoreCase(l.getAutore(), bookFilterText.getText()));
                break;

            case "Anno":
                libriFiltrati = biblioteca.cercaLibri(l->containsIgnoreCase(String.valueOf(l.getAnno()), bookFilterText.getText()));
                break;

            case "Ibsn":
                libriFiltrati = biblioteca.cercaLibri(l->containsIgnoreCase(l.getIbsn(), bookFilterText.getText()));
                break;
        }
        if(libriFiltrati != null)
            libriFiltrati.forEach(l->dtm.addRow(new Object[]{l.getTitolo(), l.getAutore(), l.getAnno(), l.getIbsn(), l.getNum_copie()}));
    }//GEN-LAST:event_filtraLibri

    /**
     * KeyReleased della TextField della sezione prestiti
     * @param evt
     */
    private void filtraPrestito(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtraPrestito
        String filtraPer = prestitoComboBox.getSelectedItem().toString();//recupero il criterio di filtraggio dalla combobox
        DefaultTableModel dtm = (DefaultTableModel) tablePrestito.getModel();
        dtm.setRowCount(0);
        List<Prestito> listaPrestitiFiltrati = null;
        switch(filtraPer){
            case "Nome":
                //Ottengo una lista di prestiti filtrati per Nome dell'utente
                listaPrestitiFiltrati = biblioteca.cercaPrestitoDaUtente(u->containsIgnoreCase(u.getNome(),filtroPrestitoText.getText()));
                break;

            case "Cognome":
                //Ottengo una lista di prestiti filtrati per Cognome dell'utente
                listaPrestitiFiltrati = biblioteca.cercaPrestitoDaUtente(u->containsIgnoreCase(u.getCognome(),filtroPrestitoText.getText()));
                break;

            case "Titolo":
                //Ottengo una lista di prestiti filtrati per Titolo del libro
                listaPrestitiFiltrati = biblioteca.cercaPrestitoDaLibro(l->containsIgnoreCase(l.getTitolo(), filtroPrestitoText.getText()));
                break;

            case "Autore":
                //Ottengo una lista di prestiti filtrati per Autore del libro
                listaPrestitiFiltrati = biblioteca.cercaPrestitoDaLibro(l->containsIgnoreCase(l.getAutore(), filtroPrestitoText.getText()));
                break;

            case "Anno":
                //Ottengo una lista di prestiti filtrati per Anno del libro
                listaPrestitiFiltrati = biblioteca.cercaPrestitoDaLibro(l->containsIgnoreCase(String.valueOf(l.getAnno()), filtroPrestitoText.getText()));
                break;
        }

        if(listaPrestitiFiltrati != null)
            //Mostro a video tramite la tabella la lista dei prestiti filtrati
            listaPrestitiFiltrati.forEach(p->dtm.addRow(new Object[]{p.getId(),p.getUtente().getId(), p.getUtente().getNome(), p.getUtente().getCognome(), p.getLibro().getTitolo(), p.getLibro().getAutore(), p.getLibro().getIbsn(), timeFormat(p.getInizioPrestito()), p.getFinePrestito()!=null ? timeFormat(p.getFinePrestito()) : "In Corso"}));

    }//GEN-LAST:event_filtraPrestito


    ////////ALTRI METODI


    /**
     * Aggiorna il contenuto di una tabella identificata da un id:
     * 0 per la tabella degli utenti,
     * 1 per quella dei libri,
     * 2 per quella dei prestiti
     * @param id
     */
    public void aggiornaTabella(int id){
        DefaultTableModel dtm;
        switch(id){
            case 0:
                dtm = (DefaultTableModel) tabellaUtenti.getModel();
                dtm.setNumRows(0);
                //per ciascun utente della lista aggiungo una riga alla tabella degli utenti
                biblioteca.getListaUtenti().forEach(u->dtm.addRow(new Object[]{u.getId(), u.getNome(), u.getCognome(), u.getData(), u.getTelefono()}));
                break;

            case 1:
                dtm = (DefaultTableModel) tabellaLibri.getModel();
                dtm.setNumRows(0);
                //per ciascun libro della lista aggiungo una riga alla tabella dei libri
                biblioteca.getListaLibri().forEach(l->dtm.addRow(new Object[]{l.getTitolo(), l.getAutore(), l.getAnno(), l.getIbsn(), l.getNum_copie()}));
                break;

            case 2:
                //la tabella dei prestiti va aggiornata in base alla checkbox selezionata: in corso o completati
                checkBoxPrestitiListener();
                break;

        }
    }

    /**
     * Metodo che permette di formattare la data e l'orario secondo il pattern "dd-MM-yyyy HH:mm"
     * @param data
     * @return stringa contenente la data formattata
     */
    private String timeFormat(LocalDateTime data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return data.format(formatter);
    }

    /**
     * Metodo che consente di cambiare la lista dei campi della combobox dei prestiti a seconda se risulta selezionata la checkbox utente o libro
     */
    private void checkBoxFiltroListener(){
        if(utentiCheckBox.isSelected()){
            prestitoComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Nome", "Cognome"}));
        }
        else{
            prestitoComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Titolo", "Autore", "Anno"}));
        }
    }

    /**
     * Metodo che consente di visualizzare sulla tabella i prestiti ancora in corso o completati a seconda della combobox selezionata (In Corso, Completati)
     */
    private void checkBoxPrestitiListener(){
        if(inCorsoCheckBox.isSelected()){
            btnRestituisciLibro.setEnabled(true);//abilito il tasto per il reso
            labelListaPrestiti.setText("Lista prestiti in corso:");
            List<Prestito> listaPrestitiInCorso = biblioteca.cercaPrestiti(p->p.getFinePrestito()==null);//ottengo una lista di prestiti in corso
            ((DefaultTableModel)tablePrestito.getModel()).setRowCount(0);
            //Visualizzo sulla tabella i prestiti filtrati
            listaPrestitiInCorso.forEach(p->((DefaultTableModel)tablePrestito.getModel()).addRow(new Object[]{p.getId(), p.getUtente().getId(), p.getUtente().getNome(), p.getUtente().getCognome(), p.getLibro().getTitolo(), p.getLibro().getAutore(), p.getLibro().getIbsn(), timeFormat(p.getInizioPrestito()), p.getFinePrestito()!=null ? timeFormat(p.getFinePrestito()) : "In Corso"}));
        }else{
            btnRestituisciLibro.setEnabled(false);//disabilito il tasto del reso in quanto non posso restituire un libro da un prestito completato
            labelListaPrestiti.setText("Lista prestiti completati:");
            ((DefaultTableModel)tablePrestito.getModel()).setRowCount(0);
            List<Prestito> listaPrestitiCompletati = biblioteca.cercaPrestiti(p->p.getFinePrestito()!=null);//ottengo una lista di prestiti completati
            //Visualizzo sulla tabella i prestiti filtrati
            listaPrestitiCompletati.forEach(p->((DefaultTableModel)tablePrestito.getModel()).addRow(new Object[]{p.getId(), p.getUtente().getId(), p.getUtente().getNome(), p.getUtente().getCognome(), p.getLibro().getTitolo(), p.getLibro().getAutore(), p.getLibro().getIbsn(), timeFormat(p.getInizioPrestito()), p.getFinePrestito()!=null ? timeFormat(p.getFinePrestito()) : "In Corso"}));
        }
    }

    /**
     * Metodo che consente il salvataggio dei dati
     */
    public void salva(){
        JFileChooser fc = new JFileChooser();
        SaverClass sc = new SaverClass();
        fc.setCurrentDirectory(new File(".//SaveData")); // viene visualizzata la cartella inserita nel percorso
            try {
                    if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                        String path = fc.getSelectedFile().getPath(); //Stringa che contiene il persorso del file
                        //Invoco il metodo saveFile per salvare i dati passando le liste e il percorso sul quale salvare i dati
                        sc.saveFile(biblioteca, path);
                    }
                } catch (HeadlessException | IOException e) {
                    JOptionPane.showMessageDialog(null, "Errore: " + e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
                }
    }

    /**
     * Metodo che consente il caricamento dei dati
     */
    private void carica(){
        LoaderClass lc = new LoaderClass();
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(".//SaveData"));// viene visualizzata la cartella inserita nel percorso
        try {

            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getPath();
                lc.loadFile(biblioteca, path);
                aggiornaTabella(0);
                aggiornaTabella(1);
                aggiornaTabella(2);
                JOptionPane.showMessageDialog(null,"Dati caricati correttamente","CARICA", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException | IOException | ClassNotFoundException  e) {
            JOptionPane.showMessageDialog(null, "Errore: " + e.getMessage(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo che consente di controllare se una stringa contiene gli stessi caratteri di un'altra stringa ignorando eventuali caratteri maiuscoli o minuscoli
     * @param s1
     * @param s2
     * @return true se la stringa contiene gli stessi caratteri, false altrimenti
     */
    private boolean containsIgnoreCase(String s1, String s2){
        return s1.toLowerCase().contains(s2.toLowerCase());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabellaUtenti = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        btnRegistraUtente = new javax.swing.JButton();
        btnModificaUtente = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        filtroText = new javax.swing.JTextField();
        userComboBox = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabellaLibri = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnModificaLibro = new javax.swing.JButton();
        btnRegistraLibro1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        libroComboBox = new javax.swing.JComboBox<>();
        bookFilterText = new javax.swing.JTextField();
        prestitiPanel = new javax.swing.JPanel();
        btnRegistraPrestito = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePrestito = new javax.swing.JTable();
        labelListaPrestiti = new javax.swing.JLabel();
        btnRestituisciLibro = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        utentiCheckBox = new javax.swing.JCheckBox();
        libriCheckBox = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        prestitoComboBox = new javax.swing.JComboBox<>();
        filtroPrestitoText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        inCorsoCheckBox = new javax.swing.JCheckBox();
        completatiCheckBox = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        importaCSVMenu = new javax.swing.JMenuItem();
        esportaCSVMenu = new javax.swing.JMenuItem();
        salvaMenu = new javax.swing.JMenuItem();
        caricaMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        registraUtenteMenu = new javax.swing.JMenuItem();
        modificaUtenteMenu = new javax.swing.JMenuItem();
        LibroMenu = new javax.swing.JMenu();
        registraLibroMenu = new javax.swing.JMenuItem();
        modificaLibroMenu = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        registraPrestitoMenu = new javax.swing.JMenuItem();
        resoLibroMenu = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("BIBLIOTECA TANIELI");

        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setForeground(new java.awt.Color(0, 51, 255));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Papyrus", 1, 13)); // NOI18N

        tabellaUtenti.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        tabellaUtenti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Cognome", "Data di nascita", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabellaUtenti);

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Lista utenti registrati:");

        btnRegistraUtente.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnRegistraUtente.setForeground(new java.awt.Color(0, 51, 255));
        btnRegistraUtente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addUser.png"))); // NOI18N
        btnRegistraUtente.setText("Registra Utente");
        btnRegistraUtente.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addUser.png"))); // NOI18N
        btnRegistraUtente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistraUtenteActionPerformed(evt);
            }
        });

        btnModificaUtente.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnModificaUtente.setForeground(new java.awt.Color(0, 51, 255));
        btnModificaUtente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editUser.png"))); // NOI18N
        btnModificaUtente.setText("Modifica Utente");
        btnModificaUtente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificaUtenteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setText("Cerca utente per:");

        filtroText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtraUtenti(evt);
            }
        });

        userComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Cognome", "Data", "Telefono" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(userComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filtroText)
                    .addComponent(btnModificaUtente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRegistraUtente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnRegistraUtente)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificaUtente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(userComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(filtroText, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        btnRegistraUtente.getAccessibleContext().setAccessibleName("btnRegistraUtente");

        jTabbedPane1.addTab("Utenti", new javax.swing.ImageIcon(getClass().getResource("/img/users.png")), jPanel1); // NOI18N

        tabellaLibri.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        tabellaLibri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titolo", "Autore", "Anno", "Ibsn", "Num Copie"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabellaLibri);

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Lista libri nella biblioteca:");

        btnModificaLibro.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnModificaLibro.setForeground(new java.awt.Color(0, 51, 255));
        btnModificaLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editBook.png"))); // NOI18N
        btnModificaLibro.setText("Modifica Libro");
        btnModificaLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificaLibroActionPerformed(evt);
            }
        });

        btnRegistraLibro1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnRegistraLibro1.setForeground(new java.awt.Color(0, 51, 255));
        btnRegistraLibro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/book-icon.png"))); // NOI18N
        btnRegistraLibro1.setText("Registra Libro");
        btnRegistraLibro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistraLibro1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("Cerca libro per:");

        libroComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Titolo", "Autore", "Anno", "Ibsn" }));

        bookFilterText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtraLibri(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnModificaLibro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                                .addComponent(btnRegistraLibro1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bookFilterText)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(libroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnRegistraLibro1)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificaLibro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(libroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(bookFilterText, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
        );

        jTabbedPane1.addTab("Libri", new javax.swing.ImageIcon(getClass().getResource("/img/books-icon.png")), jPanel2); // NOI18N

        btnRegistraPrestito.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnRegistraPrestito.setForeground(new java.awt.Color(0, 51, 255));
        btnRegistraPrestito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user-book.png"))); // NOI18N
        btnRegistraPrestito.setText("Registra Prestito");
        btnRegistraPrestito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistraPrestitoActionPerformed(evt);
            }
        });

        tablePrestito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Prestito", "ID Utente", "Nome", "Cognome", "Titolo", "Autore", "Ibsn", "Inizio Prestito", "Fine Prestito"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablePrestito);

        labelListaPrestiti.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        labelListaPrestiti.setForeground(new java.awt.Color(0, 51, 255));
        labelListaPrestiti.setText("Lista prestiti in corso:");

        btnRestituisciLibro.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btnRestituisciLibro.setForeground(new java.awt.Color(0, 51, 255));
        btnRestituisciLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/deleteBook.png"))); // NOI18N
        btnRestituisciLibro.setText("Reso Libro");
        btnRestituisciLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestituisciLibroActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ricerca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 18))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 255));
        jLabel8.setText("Cerca prestito tra:");

        utentiCheckBox.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        utentiCheckBox.setSelected(true);
        utentiCheckBox.setText("Utenti");
        utentiCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utentiCheckBoxActionPerformed(evt);
            }
        });

        libriCheckBox.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        libriCheckBox.setText("Libri");
        libriCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libriCheckBoxActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 255));
        jLabel9.setText("Filtra per:");

        prestitoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Cognome" }));

        filtroPrestitoText.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        filtroPrestitoText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtraPrestito(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filtroPrestitoText)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(prestitoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prestitoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtroPrestitoText, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 255));
        jLabel10.setText("Prestito:");

        inCorsoCheckBox.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        inCorsoCheckBox.setSelected(true);
        inCorsoCheckBox.setText("In Corso");
        inCorsoCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inCorsoCheckBoxActionPerformed(evt);
            }
        });

        completatiCheckBox.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        completatiCheckBox.setText("Completati");
        completatiCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completatiCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(utentiCheckBox)
                        .addGap(30, 30, 30)
                        .addComponent(libriCheckBox))
                    .addComponent(jLabel10)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(inCorsoCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(completatiCheckBox)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inCorsoCheckBox)
                    .addComponent(completatiCheckBox))
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utentiCheckBox)
                    .addComponent(libriCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout prestitiPanelLayout = new javax.swing.GroupLayout(prestitiPanel);
        prestitiPanel.setLayout(prestitiPanelLayout);
        prestitiPanelLayout.setHorizontalGroup(
            prestitiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prestitiPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(prestitiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(prestitiPanelLayout.createSequentialGroup()
                        .addComponent(labelListaPrestiti)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1019, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(prestitiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(prestitiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnRestituisciLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRegistraPrestito, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        prestitiPanelLayout.setVerticalGroup(
            prestitiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prestitiPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelListaPrestiti)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(prestitiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(prestitiPanelLayout.createSequentialGroup()
                        .addComponent(btnRegistraPrestito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRestituisciLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Prestiti", new javax.swing.ImageIcon(getClass().getResource("/img/user-book.png")), prestitiPanel); // NOI18N

        jMenu1.setText("File");

        importaCSVMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/importIcon.png"))); // NOI18N
        importaCSVMenu.setText("Importa CSV");
        importaCSVMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importaCSVMenuActionPerformed(evt);
            }
        });
        jMenu1.add(importaCSVMenu);

        esportaCSVMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exportMenu.png"))); // NOI18N
        esportaCSVMenu.setText("Esporta CSV");
        esportaCSVMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esportaCSVMenuActionPerformed(evt);
            }
        });
        jMenu1.add(esportaCSVMenu);

        salvaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save-icon.png"))); // NOI18N
        salvaMenu.setText("Salva");
        salvaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvaMenuActionPerformed(evt);
            }
        });
        jMenu1.add(salvaMenu);

        caricaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Load-icon.png"))); // NOI18N
        caricaMenu.setText("Carica");
        caricaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caricaMenuActionPerformed(evt);
            }
        });
        jMenu1.add(caricaMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Utente");

        registraUtenteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addUserMenu.png"))); // NOI18N
        registraUtenteMenu.setText("Registra");
        registraUtenteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registraUtenteMenuActionPerformed(evt);
            }
        });
        jMenu2.add(registraUtenteMenu);

        modificaUtenteMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editUserMenu.png"))); // NOI18N
        modificaUtenteMenu.setText("Modifica");
        modificaUtenteMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaUtenteMenuActionPerformed(evt);
            }
        });
        jMenu2.add(modificaUtenteMenu);

        jMenuBar1.add(jMenu2);
        jMenu2.getAccessibleContext().setAccessibleName("JMenuUtente");

        LibroMenu.setText("Libro");

        registraLibroMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/addBookMenu.png"))); // NOI18N
        registraLibroMenu.setText("Registra");
        registraLibroMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registraLibroMenuActionPerformed(evt);
            }
        });
        LibroMenu.add(registraLibroMenu);

        modificaLibroMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editBookMenu.png"))); // NOI18N
        modificaLibroMenu.setText("Modifica");
        modificaLibroMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificaLibroMenuActionPerformed(evt);
            }
        });
        LibroMenu.add(modificaLibroMenu);

        jMenuBar1.add(LibroMenu);

        jMenu4.setText("Prestito");

        registraPrestitoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Apple-Books-icon.png"))); // NOI18N
        registraPrestitoMenu.setText("Registra");
        registraPrestitoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registraPrestitoMenuActionPerformed(evt);
            }
        });
        jMenu4.add(registraPrestitoMenu);

        resoLibroMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/deleteBook.png"))); // NOI18N
        resoLibroMenu.setText("Reso Libro");
        resoLibroMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resoLibroMenuActionPerformed(evt);
            }
        });
        jMenu4.add(resoLibroMenu);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(475, 475, 475)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu LibroMenu;
    private javax.swing.JTextField bookFilterText;
    private javax.swing.JButton btnModificaLibro;
    private javax.swing.JButton btnModificaUtente;
    private javax.swing.JButton btnRegistraLibro1;
    private javax.swing.JButton btnRegistraPrestito;
    private javax.swing.JButton btnRegistraUtente;
    private javax.swing.JButton btnRestituisciLibro;
    private javax.swing.JMenuItem caricaMenu;
    private javax.swing.JCheckBox completatiCheckBox;
    private javax.swing.JMenuItem esportaCSVMenu;
    private javax.swing.JTextField filtroPrestitoText;
    private javax.swing.JTextField filtroText;
    private javax.swing.JMenuItem importaCSVMenu;
    private javax.swing.JCheckBox inCorsoCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelListaPrestiti;
    private javax.swing.JCheckBox libriCheckBox;
    private javax.swing.JComboBox<String> libroComboBox;
    private javax.swing.JMenuItem modificaLibroMenu;
    private javax.swing.JMenuItem modificaUtenteMenu;
    private javax.swing.JPanel prestitiPanel;
    private javax.swing.JComboBox<String> prestitoComboBox;
    private javax.swing.JMenuItem registraLibroMenu;
    private javax.swing.JMenuItem registraPrestitoMenu;
    private javax.swing.JMenuItem registraUtenteMenu;
    private javax.swing.JMenuItem resoLibroMenu;
    private javax.swing.JMenuItem salvaMenu;
    private javax.swing.JTable tabellaLibri;
    private javax.swing.JTable tabellaUtenti;
    private javax.swing.JTable tablePrestito;
    private javax.swing.JComboBox<String> userComboBox;
    private javax.swing.JCheckBox utentiCheckBox;
    // End of variables declaration//GEN-END:variables


}

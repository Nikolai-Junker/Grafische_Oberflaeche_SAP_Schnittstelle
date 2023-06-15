package bis222;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Klasse welche die grafische Oberflaeche der Anwendung bildet
 * @author Nikolai Junker
 */
public class MainGUI {
    /**
     * Der Button zur Ausfuehrung der BAPI GetList
     */
    private JButton getListButton;
    /**
     * Das JPanel auf dem Elemente eingefuegt werden
     */
    private JPanel panel;
    /**
     * Die Ergebnistabelle, welche die Rueckgabewerte des Aufrufs anzeigt
     */
    private JTable table;
    /**
     * Die JTextArea ausgabe gibt eine Ausgabe der Fehlermeldungen und einen Willkomennstext aus
     */
    private JTextArea ausgabe;
    /**
     * Wird benoetigt zum Einfuegen der Tabelle und ermoeglicht das scrollen innerhalb der Tabelle
     */
    private JScrollPane pane;
    /**
     * Der Button zur Ausfuehrung der BAPI GetDetail
     */
    private JButton getDetailButton;
    /**
     * Auswahl des Parameterwertes fuer den Parameter OBJVERS,
     * nur drei Moeglichkeiten fuer den Wert sind moeglich
     */
    private JComboBox objversComboBox;
    /**
     * Eingabe des Parameterwertes fuer den Parameter IOBJTP
     */
    private JTextField iobjtpText;
    /**
     * Eingabe des Parameterwertes fuer den Parameter INFOOBJCAT
     */
    private JTextField infoobjectcatText;
    /**
     * Standard Modell fuer Tabellen, wird verwendet zum Erzeugen der Tabelle mit den Rueckgabewerten
     */
    private DefaultTableModel model;
    /**
     * Das String Array welches der Ausgangspunkt ist fuer die Tabelle,
     * enthaelt Verweise von allen Rueckgabewerten des BAPI-Aufrufs in Form von String-Werten
     */
    static String[][]tabelleArray;
    /**
     * Dient der Zuweisung von Fehlermeldungen, falls die Methode anmelden nicht erfolgreich ausgefuehrt wurde
     * @see Methoden
     */
    static String fehlermeldung;

    /**
     * Main-Methode der Anwendung
     * @param args Argumente der Main-Methode
     * Enthaelt Standardanweisungen fuer die GUI zur Laufzeit wie z.B.
     * Name, Closeoperation und zusaetzlich
     * frame.set.LocationRelativeTo(null), welches die GUI mittig generiert beim Start
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("InfoObjectCatalog BAPIs");
        frame.setContentPane(new MainGUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Der Konstruktur der GUI,
     * Initialisierung aller Parameter mit Ausgangswerten
     * und setzen des Willkommenstexts beim Start der GUI
     */
    public MainGUI(){
        objversComboBox.addItem("A");
        objversComboBox.addItem("D");
        objversComboBox.addItem("M");
        iobjtpText.setText("%");
        infoobjectcatText.setText("/CPD/AVR_CHA01");
        ausgabe.setText(Daten.willkommensText);

        /*
         Registrierung des ActionListeners bei getListButton
         */
        getListButton.addActionListener(new ActionListener() {
            /*
             Implementierung der Methode actionPerformed von ActionListener
             Anmeldung bei SAP und Aufruf von GetList mit aktuellen Parametern
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    /*
                    Leeren von ausgabe, damit alte Fehlermeldungen nicht mehr angezeigt werden
                     */
                    ausgabe.setText("");
                    /*
                    Der Variable wird der aktuelle Wert vom Parameter OBJVERS zugewiesen
                     */
                    String objversWert = objversComboBox.getSelectedItem().toString();
                    /*
                    Der Variable wird der aktuelle Wert vom Parameter IOBJTP zugewiesen
                     */
                    String iobjtpWert = iobjtpText.getText();
                    /*
                    Zuweisung der aufzurufenden BAPI an BAPI_NAME
                     */
                    Daten.BAPI_NAME = "BAPI_IOBC_GETLIST";
                    /*
                     Zuweisung von "Fehler" an tabelleArray, dient der späteren Überrpüfung, ob die Methode anmelden erfolgreich ausgeführt wurde
                     */
                    tabelleArray = new String[1][1];
                    tabelleArray[0][0] = "Fehler";
                    /*
                    Aufruf von anmelden für die gegebenen Parameter, Methode zum Anmelden bei SAP
                     */
                    Methoden.anmelden(Daten.parameter[0],Daten.parameter[1],objversWert,iobjtpWert,Daten.selectTable[0],6,Daten.spaltenGetList);
                    /*
                    Prüfung ob anmelden erfolgreich durchgeführt wurde und erstellen der Tabelle im if-Zweig
                    bzw. setzen von ausgabe zur Fehlermeldung wenn im else-Zweig
                     */
                    if(!tabelleArray[0][0].equals("Fehler")){
                        model = new DefaultTableModel(tabelleArray,Daten.spaltenGetList);
                        table.setModel(model);
                        table.setEnabled(false);
                    }
                    else{
                        ausgabe.setText("");
                        ausgabe.setText(fehlermeldung);
                    }
                    /*
                    Ausnahmebehandlung durch Ausgabe der Fehlermeldung auf die interne Ausgabefläche ausgabe
                     */
                } catch (Exception exception) {
                    /*
                     Diese drei Anweisungen zusammen wandeln den StackTrace in ein String-Format um, für die spätere Ausgabe
                     Die Methode printStackTrace ist überladen und gibt die Fehlermeldung an den Printwriter weiter, welcher
                     writer als Parameter verwendet und schlussendlich wird über toString() die Fehlermeldung in String umgewandelt
                     und stackTrace zugewiesen
                     */
                    StringWriter writer = new StringWriter();
                    exception.printStackTrace(new PrintWriter(writer));
                    String stackTrace = writer.toString();
                    /*
                     zusätzliche kleine Erläuterung, welche zu der Fehlermeldung angehängt wird
                     */
                    String erläuterung = "Bitte Überprüfen Sie Ihre Konfiguration, es ist folgende Exception aufgetreten:";
                    /*
                    Löschen des alten Inhalts von ausgabe und setzen der Fehlermeldung mit Erläuterung
                     */
                    ausgabe.setText("");
                    ausgabe.setText(erläuterung+"\n"+stackTrace);
                }
            }
        });
        /*
        Vorgehen analog zu GetList
         */
        getDetailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    ausgabe.setText("");
                    String objversWert = objversComboBox.getSelectedItem().toString();
                    /*
                     Der Variable wird der aktuelle Wert vom Parameter INFOOBJCAT zugewiesen
                     */
                    String infoobjectcatWert = infoobjectcatText.getText();
                    Daten.BAPI_NAME = "BAPI_IOBC_GETDETAIL";
                    tabelleArray = new String[1][1];
                    tabelleArray[0][0] = "Fehler";
                    Methoden.anmelden(Daten.parameter[0],Daten.parameter[2],objversWert,infoobjectcatWert,Daten.selectTable[1],3,Daten.spaltenGetDetail);

                    if(!tabelleArray[0][0].equals("Fehler")){
                        model = new DefaultTableModel(tabelleArray,Daten.spaltenGetDetail);
                        table.setModel(model);
                        table.setEnabled(false);
                    }
                    else{
                        ausgabe.setText("");
                        ausgabe.setText(fehlermeldung);
                    }

                } catch (Exception exception) {
                    StringWriter writer = new StringWriter();
                    exception.printStackTrace(new PrintWriter(writer));
                    String stackTrace = writer.toString();
                    String erläuterung = "Bitte Überprüfen Sie Ihre Konfiguration, es ist folgende Exception aufgetreten:";
                    ausgabe.setText("");
                    ausgabe.setText(erläuterung+"\n"+stackTrace);
                }
            }
        })
        ;
        };
    };

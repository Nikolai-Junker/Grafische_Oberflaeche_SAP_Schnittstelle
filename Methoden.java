package bis222;

import com.sap.conn.jco.*;

/**
 * Diese Klasse enthaelt eine Methode zur Anmeldung an SAP und eine zur Verarbeitung der Rueckgabewerte
 * @author Nikolai Junker
 */
public class Methoden {
    /**
     * Methode zum Anmelden an SAP und zum Ausfuehren des BAPI-Aufrufs
     * @param parameter1 Erster Parameter der benoetigt wird fuer den BAPI-Aufruf
     * @param parameter2 Zweiter Parameter der benoetigt wird fuer den BAPI-Aufruf
     * @param parameterWert1 Welchen Wert der Benutzer dem ersten Parameter zuweisen moechte
     * @param parameterWert2 Welchen Wert der Benutzer dem zweiten Parameter zuweisen moechte
     * @param selectTable Welche von den beiden Rueckgabetabellen selektiert werden soll
     * @param anzahlSpalten Die Anzahl der Spalten fuer die Anfrage
     * @param spalten Welche Spalten selektiert werden sollen fuer die Anfrage
     * @throws Exception Exception wird eine hierarchische Ebene weiter noch oben geleitet fuer Behandlung
     */
    public static void anmelden(String parameter1, String parameter2, String parameterWert1,String parameterWert2, String selectTable, int anzahlSpalten, String[] spalten)throws Exception{
        /*
        Erzeugen eines Funktionsobjekts, einer Parameterliste, Zuweisung der Parameter und ihrer Werte,
        danach Rückgabewerte des Aufrufs über gebeTabelleAus an das String-Array tabelleArray zuweisen oder Fehlermeldung an die Variable fehlermeldung zuweisen
         */
        JCoDestination destination = JCoDestinationManager.getDestination(Daten.KENNUNG);
        JCoFunction function = destination.getRepository().getFunctionTemplate(Daten.BAPI_NAME).getFunction();
        JCoParameterList eingaben = function.getImportParameterList();
        eingaben.setValue(parameter1,parameterWert1);
        eingaben.setValue(parameter2,parameterWert2);
        function.execute(destination);
        JCoStructure returnStruktur = function.getExportParameterList().getStructure("RETURN");
        String type = returnStruktur.getString("TYPE");
        String meldung = returnStruktur.getString("MESSAGE");

        if (type.equals("") || type.equals("S") ) {
            JCoParameterList tabellen = function.getTableParameterList();
            JCoTable tabelle = tabellen.getTable(selectTable);
            MainGUI.tabelleArray = Methoden.gebeTabelleAus(tabelle,anzahlSpalten, spalten);
        }
        else
            MainGUI.fehlermeldung = String.format("Es ist ein Fehler aufgetreten: %s%n%s%n", type, meldung);
    }

    /**
     * Methode zum Zuweisen der Rueckgabewerte des BAPI-Aufrufs an ein String-Array,
     * dieses bildet danach die Grundlage fuer die Ausgabe-Tabelle der grafischen Oberflaeche
     * @param tabelle Die Rueckgabetgabelle des BAPI-Aufrufs
     * @param anzahlSpalten Die Anzahl der Spalten der Rueckgabetabelle
     * @param spalten Welche Spalten die Rueckgabetabelle besitzt
     * @return Das String-Array data mit den zugewiesenen Rueckgabewerten des BAPI-Aufrufs wird zurueckgegeben
     */
    public static String [][] gebeTabelleAus(JCoTable tabelle, int anzahlSpalten, String[] spalten){
        /*
        Uebergabe an das String-Array data von allen Zeilen des Aufrufs per Spalte
         */
        int anzahlZeilen = tabelle.getNumRows();
        String[][] data = new String[anzahlZeilen][anzahlSpalten];

        for (int j = 0; j < anzahlSpalten; j++) {
            for (int i = 0; i < anzahlZeilen; i++) {
                tabelle.setRow(i);
                data[i][j] = tabelle.getString(spalten[j]);
            }
        }
        return data;
    }
}

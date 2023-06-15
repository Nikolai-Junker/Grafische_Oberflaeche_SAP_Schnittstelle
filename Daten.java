package bis222;

/**
 * Klasse die haeufig verwendete Stringelemente enthaelt, welche von den anderen beiden Klassen aufgerufen werden
 * @author Nikolai Junker
 */
public class Daten {
    /**
     * String-Array mit allen Parametern fuer beide BAPI-Aufrufe
     */
    static String[] parameter = {"OBJVERS","IOBJTP","INFOOBJCAT"};
    /**
     * String-Array mit Standardwerten fuer Parameter die Testzwecken dienen
     */
    static String[] parameterWerte = {"A","%","/CPD/AVR_CHA01"};
    /**
     * String-Array mit beiden moeglichen Tabellen der Rueckgabewerte eines der BAPI-Aufrufe
     */
    static String[] selectTable = {"INFOOBJCATLIST", "INFOOBJECTS"};
    /**
     * String-Array mit allen moeglichen Spalten der Rueckgabewerte eines GetList-Aufrufs des BO InfoObjectCatalog
     */
    static String[] spaltenGetList = {"INFOOBJCAT","OBJVERS","TEXTLONG", "IOBJTP", "OBJSTAT","INFOAREA"};
    /**
     * String-Array mit allen moeglichen Spalten der Rueckgabewerte eines GetDetail-Aufrufs des BO InfoObjectCatalog
     */
    static String[] spaltenGetDetail = {"INFOOBJCAT","OBJVERS","INFOOBJECT"};
    /**
     * String der den Pfad zu BIS222.jcoDestination angibt, wird benoetigt bei der Anmeldung an SAP
     */
    static String KENNUNG = "properties/BIS222";
    /**
     * String dem der Namen der aufzurufenden BAPI zugewiesen wird, benoetigt bei der Anmeldung an SAP, Standard ist Getlist
     *
     */
    static String BAPI_NAME = "BAPI_IOBC_GETLIST";
    /**
     * String welchem der Willkommenstext zugewiesen wird, in MainGUI wird dieser beim Starten der GUI fuer die JTextArea gesetzt
     */
    static String willkommensText = "Willkommen!" +
            "\nBitte stellen Sie sicher, dass KENNUNG und BAPI_NAME richtig konfiguriert sind und Ihre IP freigeschaltet wurde." +
            "\nDieses Programm ermöglicht den Aufruf der BAPIs GetList und GetDetail des BO InfoObjectCatalog von SAP." +
            "\nSie können die Ausgabe der BAPIs steuern durch die drei oben angegebenen Parameter." +
            "\nDie Parameter OBJVERS und IOBJTP steuern die Ausgabe von GetList und OBJVERS sowie INFOOBJCAT von GetDetail." +
            "\nBeispielwerte wurden zur Initialisierung der Parameter verwendet, sie können natürlich auch verändert werden.";
}

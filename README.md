# Grafische_Oberflaeche_SAP_Schnittstelle
Eine Schnittstelle zu SAP die Abrufe über eine grafische Oberfläche ermöglicht (Bachelor-Studium)

## Konkretisierung des allgemeinen Programmes

Dieses Java-Programm dient zur Interaktion mit SAP R/3 über das Java Connector (JCo) Framework. Insbesondere ermöglicht es die Nutzung der Business Application Programming Interfaces (BAPIs) `BAPI_IOBC_GETLIST` und `BAPI_IOBC_GETDETAIL`. Das Programm besteht aus den drei Klassen **Daten**, **Methoden** und **MainGUI**.

### Klasse: Daten

Die `Daten`-Klasse speichert die Konstanten und Parameter, die während der Programmausführung benötigt werden. Dazu gehören Verbindungsdetails wie die Kennung für die JCo-Destination, die Namen der aufzurufenden BAPIs und die Parameter, die an diese BAPIs übergeben werden.

### Klasse: Methoden

Die `Methoden`-Klasse enthält zwei statische Methoden: `anmelden` und `gebeTabelleAus`.

#### Methode: anmelden

Die `anmelden`-Methode ist für das Herstellen einer Verbindung zu SAP, das Ausführen des BAPI-Aufrufs und die Verarbeitung der Rückgabewerte verantwortlich.

Sie nimmt verschiedene Parameter an, darunter die Namen und Werte der Parameter, die an das BAPI übergeben werden sollen, sowie Informationen über die zu selektierenden Rückgabetabellen und Spalten.

Nach dem Aufbau der Verbindung und dem Ausführen des BAPI-Aufrufs überprüft die Methode, ob der Aufruf erfolgreich war. Wenn dies der Fall ist, wird die Rückgabewertetabelle an die Methode `gebeTabelleAus` übergeben und die Ergebnisse werden in einem Array von Strings gespeichert. Wenn ein Fehler aufgetreten ist, wird eine Fehlermeldung erstellt.

#### Methode: gebeTabelleAus

Die Methode `gebeTabelleAus` ist dafür verantwortlich, die von SAP zurückgegebene Tabelle zu verarbeiten und sie in ein 2D-String-Array umzuwandeln, das dann von der GUI zur Anzeige verwendet werden kann. Sie nimmt als Parameter die Rückgabetabelle, die Anzahl der Spalten und die Spaltennamen entgegen und gibt ein 2D-String-Array zurück, das die Tabellendaten repräsentiert.

### Klasse: MainGUI

Die `MainGUI`-Klasse ist für die Erstellung und Verwaltung der grafischen Benutzeroberfläche (GUI) verantwortlich. Sie verwendet das Swing-Framework von Java, um verschiedene GUI-Komponenten wie Buttons, Tabellen und Textfelder zu erstellen.

Zusätzlich zur Erstellung der GUI enthält die `MainGUI`-Klasse auch ActionListener für die beiden Buttons `getListButton` und `getDetailButton`. Diese Listener sind dafür verantwortlich, die `anmelden`-Methode aufzurufen und die GUI basierend auf dem Ergebnis dieses Aufrufs zu aktualisieren.

Wenn der BAPI-Aufruf erfolgreich ist, wird eine Tabelle mit den zurückgegebenen Daten erstellt und in der GUI angezeigt. Wenn ein Fehler aufgetreten ist, wird die Fehlermeldung in einer Textarea angezeigt. Wenn eine Ausnahme auftritt, wird der Stacktrace ebenfalls in der Textarea angezeigt, zusammen mit einer Aufforderung an den Benutzer, die Konfiguration zu überprüfen.

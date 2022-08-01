# AnfrageDerBetriebsstelleMittelsAbkuerzung

Aufgabe

Erstellen Sie ein Programm, das die Daten aus der CSV-Datei einliest und über einen REST-Endpoint zur Verfügung stellt. Der Endpunkt soll mit der Abkürzung einer Betriebsstelle angefragt werden und die Daten der Betriebsstelle als JSON-Objekt zurück liefern.

Tipp

Für die Lösung dieser Aufgabe kann man sehr gut Java und SpringBoot einsetzen. Sie können aber auch andere Technologien nutzen.

Den Code können Sie uns gerne in einem öffentlichen git-repo (z. B. Github) zur Verfügung stellen.

Beispiel-Request:

.../betriebsstelle/aamp

Beispiel-Response:

HTTP-STATUS: 200

{

  Name: Hamburg Anckelmannsplatz      

  Kurzname: Anckelmannsplatz  

  Typ: Üst

}

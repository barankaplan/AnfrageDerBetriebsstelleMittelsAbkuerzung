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


-Die Aufgabe im Bild unten wird im Projekt behandelt. Da es keine direkte
Anleitung zu Parametern wie Sicherheit und Datenbanken in CSV Daten und Code
gibt, sind die von mir entworfenen Add-Ons unten ausgeführt, und am Ende ist
einen weiteren Hinweis als "fortgeschrittene Studien" hinzugefügt
worden.



![image](https://user-images.githubusercontent.com/59101253/182144069-32834183-681f-4cbe-909f-e0106c22710e.png)





-CSV-Daten wurden untersucht und es wurde entschieden, mit "Set Data Struktur" zu arbeiten, da die Werte in der RL100-Code-Spalte eindeutig
sind und die Suche durch diese Spalte bearbeitet wird.




-Es gibt leere Werte in der Spalte mit dem Namen Typ Kurz, ich wollte Informationsverlust verhindern, indem ich in diesen Abschnitten Typ Lang
verwende.







<img width="628" alt="Screenshot 2022-08-01 at 14 19 33" src="https://user-images.githubusercontent.com/59101253/182146240-185c68e5-7d97-466d-acb7-c3a0195828d0.png">

 



-Ich habe eine oberflächliche Unit-Test-Methode über die OperationOfficeFactory-Klasse geschrieben, aus der wir die CSV Daten einlesen.
Obwohl es nicht direkt mit dem Konzept des Testens zusammenhängt, wurde die Methodenvielfalt durch die Gestaltung von Validierungsbestimmungen mit Unit-Test-Methoden erhöht, und gleichzeitig wurden "Stream-Operationen" erwähnt.






<img width="744" alt="Screenshot 2022-08-01 at 14 19 00" src="https://user-images.githubusercontent.com/59101253/182146166-32f7bc57-25b8-4157-8adb-b68d3c8d0f3c.png">


 



 



-Integrationstest wurde mit einer Datenbank auf AWS durchgeführt (Details folgen später).

<img width="745" alt="Screenshot 2022-08-01 at 14 19 14" src="https://user-images.githubusercontent.com/59101253/182146195-11340839-9b30-4d22-979a-bfd81a6dc008.png">








 



-Dank der Validierungen, die während "Get Request" laufen, konnte ich die Verschwendung von Ressourcen minimieren.




 <img width="737" alt="Screenshot 2022-08-01 at 14 18 48" src="https://user-images.githubusercontent.com/59101253/182146117-0d262d2c-b055-4944-b0ac-52ac497f1790.png">




-Collection link in Postman:  https://www.getpostman.com/collections/a259b863777eadeb1f6b




<img width="745" alt="Screenshot 2022-08-01 at 14 18 34" src="https://user-images.githubusercontent.com/59101253/182146075-3cab0328-f7d9-4c0a-824c-f1b32d54257e.png">




-Im ersten und einfachen Szenario werden csv-Dateien unabhängig von einer Datenbank mit dem Pojo-Objekt namens „OperationOffice“ verarbeitet. Diese Anfrage zielt darauf ab, die gewünschte Basisleistung im Projekt darzustellen, ohne dass eine
Sicherheit verlangt wird.



 


<img width="522" alt="Screenshot 2022-08-01 at 14 18 20" src="https://user-images.githubusercontent.com/59101253/182146044-e8d597d0-8edb-4538-b00f-71a695001e7d.png">



 



-Statt Fehlerdetails wie "timestamp-status-error-trace" usw.Durch "Exception Handling"-Mechanismen werden dem Benutzer
verschiedene Fehlermeldungen angezeigt.



 

<img width="491" alt="Screenshot 2022-08-01 at 14 18 09" src="https://user-images.githubusercontent.com/59101253/182146010-a10c210d-2283-459f-8807-6c7a2d0fdc1f.png">

 



-Im zweiten Szenario wird dieselbe OperationOffice-Klasse als Entität mit einem anderen Ansatz dargestellt, indem die Datenbank-, Authentifizierungs- und Autorisierungsprozesse in einer mehrschichtigen Architektur durchlaufen werden.



 



-Eine einfache Benutzerregistrierung und ein Login-Code wurden entwickelt. Das Passwort ist verschlüsselt und die APIs Connection wird mit JWT bereitgestellt.



<img width="800" alt="Screenshot 2022-08-01 at 14 17 27" src="https://user-images.githubusercontent.com/59101253/182145912-fd081b74-ed12-45d9-a84e-356c9c303df8.png">




-Der Benutzer kann ohne das Token nicht mit dem Server kommunizieren. Als Ergebnis erfolgreicher Anmeldungen mit dem Token zurückgegebene Objekte werden
in einer Datenbank gespeichert, die sich in der AWS-Cloud befindet! Ich erwähnte, dass ich einen einfachen Integrationstest über diese Datenbank geschrieben habe!



<img width="631" alt="Screenshot 2022-08-01 at 14 16 33" src="https://user-images.githubusercontent.com/59101253/182145776-f6d79fe1-e406-4e6b-b185-daadd76f84b8.png">





-Da es sich um den Primärschlüssel Code handelt, wird Daten Wiederholung vermieden! Also selbst wenn der Benutzer dieselbe Anfrage 100-mal zu
unterschiedlichen Zeiten schreibt, wird nur 1 aufgenommen!


![image](https://user-images.githubusercontent.com/59101253/182145259-42fc0b7f-9544-4c0d-99c5-7c3721018700.png)




zusätzliche Verbesserungen



- versioning and documentation of APIs 



- Dockerizing Jar files and run on AWS ECS




- Different Grant Types Using Oauth2



    
- Logging Operations


- creating custom annotations

     
- Spring MVC + Angular



 





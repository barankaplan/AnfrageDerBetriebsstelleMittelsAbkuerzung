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


![image](https://user-images.githubusercontent.com/59101253/182144173-9ef27037-a511-44ab-b190-7143de7979f3.png)

![image](https://user-images.githubusercontent.com/59101253/182144180-36f7442d-1bbd-481b-84a4-b34b7729fbec.png)





 



-Ich habe eine oberflächliche Unit-Test-Methode über die OperationOfficeFactory-Klasse geschrieben, aus der wir die CSV Daten einlesen.
Obwohl es nicht direkt mit dem Konzept des Testens zusammenhängt, wurde die Methodenvielfalt durch die Gestaltung von Validierungsbestimmungen mit Unit-Test-Methoden erhöht, und gleichzeitig wurden "Stream-Operationen" erwähnt.


![image](https://user-images.githubusercontent.com/59101253/182144247-21f707dc-d39f-461a-a310-b1434269edd3.png)

![image](https://user-images.githubusercontent.com/59101253/182144262-3cdf5f53-b7c4-4771-bf4a-4e4932eb9b58.png)





 



 



-Integrationstest wurde mit einer Datenbank auf AWS durchgeführt (Details folgen später).



 ![image](https://user-images.githubusercontent.com/59101253/182144284-fa707a67-e599-40c9-8fda-679efa9a6ae3.png)






 



-Dank der während "Request" laufenden Validierungen wollte ich die Verschwendung von Ressourcen minimieren.



![image](https://user-images.githubusercontent.com/59101253/182144310-19b8398f-4d36-4c97-8424-47778efe4361.png)

 



-Collection link in Postman:  https://www.getpostman.com/collections/a259b863777eadeb1f6b



 ![image](https://user-images.githubusercontent.com/59101253/182144398-6091eb78-b3dc-4d3f-886f-f065ca2ca42e.png)


![image](https://user-images.githubusercontent.com/59101253/182144410-ca55d434-284c-4e53-846f-18a9999af2bf.png)






-Im ersten und einfachen Szenario werden csv-Dateien unabhängig von einer Datenbank mit dem Pojo-Objekt namens „OperationOffice“ verarbeitet. Diese Anfrage zielt darauf ab, die gewünschte Basisleistung im Projekt darzustellen, ohne dass eine
Sicherheit verlangt wird.


![image](https://user-images.githubusercontent.com/59101253/182144537-f5dfaaac-c2ef-4cc8-83db-78c4789c59e0.png)

 





 



-Statt Fehlerdetails wie "timestamp-status-error-trace" usw.Durch "Exception Handling"-Mechanismen werden dem Benutzer
verschiedene Fehlermeldungen angezeigt.


![image](https://user-images.githubusercontent.com/59101253/182144626-75bea19e-b3a3-4dff-b8a8-86a57ed2df4b.png)

 


 



-Im zweiten Szenario wird dieselbe OperationOffice-Klasse als Entität mit einem anderen Ansatz dargestellt, indem die Datenbank-, Authentifizierungs- und Autorisierungsprozesse in einer mehrschichtigen Architektur durchlaufen werden.



 



-Eine einfache Benutzerregistrierung und ein Login-Code wurden entwickelt. Das Passwort ist verschlüsselt und die APIs Connection wird mit JWT bereitgestellt.

![image](https://user-images.githubusercontent.com/59101253/182144992-ab178a6a-8233-477f-bdea-7cbeeec95f65.png)

![image](https://user-images.githubusercontent.com/59101253/182145008-ed5aebd1-3cd8-40c7-b86b-fb5d41ccaecd.png)

![image](https://user-images.githubusercontent.com/59101253/182145020-569497e3-bf80-4638-a8d4-d5b71b600e47.png)

![image](https://user-images.githubusercontent.com/59101253/182145028-0e75b242-2d86-4ff8-bdb7-2bea46db2d6b.png)

![image](https://user-images.githubusercontent.com/59101253/182145042-b5d4127b-b7ae-4c7d-86c4-c900c088a9ee.png)

![image](https://user-images.githubusercontent.com/59101253/182145072-bb93e6e9-82b3-45c2-9531-f7187254035a.png)






-Der Benutzer kann ohne das Token nicht mit dem Server kommunizieren. Als Ergebnis erfolgreicher Anmeldungen mit dem Token zurückgegebene Objekte werden
in einer Datenbank gespeichert, die sich in der AWS-Cloud befindet! Ich erwähnte, dass ich einen einfachen Integrationstest über diese Datenbank geschrieben habe!


![image](https://user-images.githubusercontent.com/59101253/182145223-da627abc-3997-4320-8fc6-aa27f8db9823.png)



![image](https://user-images.githubusercontent.com/59101253/182145238-d845fa1e-5079-421d-a69c-2df5edb06468.png)





-Da es sich um den Primärschlüssel Code handelt, wird Daten Wiederholung vermieden! Also selbst wenn der Benutzer dieselbe Anfrage 100-mal zu
unterschiedlichen Zeiten schreibt, wird nur 1 aufgenommen!


![image](https://user-images.githubusercontent.com/59101253/182145259-42fc0b7f-9544-4c0d-99c5-7c3721018700.png)




zusätzliche Verbesserungen



·      
versioning and documentation of APIs 



·      
Dockerizing Jar files and run on AWS ECS



·      
Different Grant Types Using Oauth2



·      
Logging Operations



·      
Spring MVC + Angular



 





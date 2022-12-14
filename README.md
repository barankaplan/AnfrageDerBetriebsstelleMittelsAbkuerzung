# AnfrageDerBetriebsstelleMittelsAbkuerzung


![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/59101253/186783858-e200c6d5-756d-45b5-b9c6-7ef038a8e4f6.gif)

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


<img width="829" alt="Screenshot 2022-08-27 at 23 54 08" src="https://user-images.githubusercontent.com/59101253/187049271-05f359eb-2a18-4f92-9215-8464d0d8a01b.png">





-Im ersten und einfachen Szenario werden csv-Dateien unabhängig von einer Datenbank mit dem Pojo-Objekt namens „OperationOffice“ verarbeitet. Diese Anfrage zielt darauf ab, die gewünschte Basisleistung im Projekt darzustellen, ohne dass eine
Sicherheit verlangt wird.



 


<img width="522" alt="Screenshot 2022-08-01 at 14 18 20" src="https://user-images.githubusercontent.com/59101253/182146044-e8d597d0-8edb-4538-b00f-71a695001e7d.png">



 



-Statt Fehlerdetails wie "timestamp-status-error-trace" usw.Durch "Exception Handling"-Mechanismen werden dem Benutzer
verschiedene Fehlermeldungen angezeigt.



 

<img width="491" alt="Screenshot 2022-08-01 at 14 18 09" src="https://user-images.githubusercontent.com/59101253/182146010-a10c210d-2283-459f-8807-6c7a2d0fdc1f.png">

 



-Im zweiten Szenario wird dieselbe OperationOffice-Klasse als Entität mit einem anderen Ansatz dargestellt, indem die Datenbank-, Authentifizierungs- und Autorisierungsprozesse in einer mehrschichtigen Architektur durchlaufen werden.



 



-Eine einfache Benutzerregistrierung und ein Login-Code wurden entwickelt. Das Passwort ist verschlüsselt und die APIs Connection wird mit JWT bereitgestellt.


<img width="512" alt="Screenshot 2022-08-27 at 23 55 28" src="https://user-images.githubusercontent.com/59101253/187049298-dc9209cb-9867-4b0e-b923-bb26fd869fe9.png">

<img width="989" alt="Screenshot 2022-08-27 at 23 55 41" src="https://user-images.githubusercontent.com/59101253/187049304-f3d59632-2c99-4364-a951-9548c7f75e1c.png">




-Der Benutzer kann ohne das Token nicht mit dem Server kommunizieren. Als Ergebnis erfolgreicher Anmeldungen mit dem Token zurückgegebene Objekte werden
in einer Datenbank gespeichert, die sich in der AWS-Cloud befindet! Ich erwähnte, dass ich einen einfachen Integrationstest über diese Datenbank geschrieben habe!



<img width="631" alt="Screenshot 2022-08-01 at 14 16 33" src="https://user-images.githubusercontent.com/59101253/182145776-f6d79fe1-e406-4e6b-b185-daadd76f84b8.png">





-Da es sich um den Primärschlüssel Code handelt, wird Daten Wiederholung vermieden! Also selbst wenn der Benutzer dieselbe Anfrage 100-mal zu
unterschiedlichen Zeiten schreibt, wird nur 1 aufgenommen!


![image](https://user-images.githubusercontent.com/59101253/182145259-42fc0b7f-9544-4c0d-99c5-7c3721018700.png)


-In der Datei application.properties werden Logging-Level-Anpassungen für verschiedene Klassen über die Slf4j-API vorgenommen.


<img width="889" alt="Screenshot 2022-08-02 at 02 14 26" src="https://user-images.githubusercontent.com/59101253/182265613-36aee93d-ece8-4bcb-9858-31de6b8b5c2b.png">

-"Logging data" werden in einem benutzerdefinierten Format in operationOffice.log gespeichert. Die maximale Dateigröße, die maximale Gesamtgröße der Logging-Daten und die Aufbewahrungszeit werden angegeben. Der Logging-Verlauf wird bei jedem Programmstart gelöscht (er wird nicht gelöscht, wenn er innerhalb des nächsten Tages gestartet wird).

<img width="902" alt="Screenshot 2022-08-02 at 02 13 51" src="https://user-images.githubusercontent.com/59101253/182265562-d4794711-5954-4fe7-a7df-f8b8177d8fec.png">




-Es wird eine einfache Logging auf Methodenebene durchgeführt. Außerdem wird die Logging auf Methodenebene mithilfe des AOP-Ansatzes mit benutzerdefinierter Annotation entworfen!

<img width="953" alt="Screenshot 2022-08-02 at 02 12 55" src="https://user-images.githubusercontent.com/59101253/182265482-2361cb57-7ea5-431a-b877-401dfd307be3.png">


-04.08.22

-Am Anfang des Projekts stand die Idee, dass die csv-Daten sich bei jeder Abfrage möglicherweise ändern könnten, sodass wolte ich die csv-Datei direkt Zeile für Zeile einlesen, um die aktuellsten Daten zu übertragen.

<img width="717" alt="Screenshot 2022-08-05 at 00 07 06" src="https://user-images.githubusercontent.com/59101253/182960983-88bc722c-4be1-4932-beaf-9e660747cd99.png">



-Deswegen wurde nach dem einmaligen Auslesen der csv-Datei der Link http://localhost:8082/api/betriebsstelle/collection/{code} angelegt, um die Daten statisch zu halten und so die Daten in der Collection auszulesen.

<img width="675" alt="Screenshot 2022-08-05 at 00 07 29" src="https://user-images.githubusercontent.com/59101253/182961033-3bded8c1-4bdf-4073-bc59-71e1426fb3b6.png">


-Auf diese Weise wird ersichtlich, dass die Lesegeschwindigkeit durch Beobachten von Logging Daten zunimmt. Allerdings bleibt, wie anfangs erwähnt, der Status der erstmalig gelesenen Daten erhalten!

<img width="1094" alt="Screenshot 2022-08-05 at 00 07 59" src="https://user-images.githubusercontent.com/59101253/182961082-abbf3b53-d680-485a-b31d-1883cb94f18f.png">




Zusätzliche Verbesserungen



- Versioning and documentation of APIs 



- Dockerizing Jar files and run on AWS ECS



- Different Grant Types Using Oauth2

    
- Logging Operations and AOP Approach (added on 02.08.22)


- Creating custom annotations (added on 02.08.22)

     
- Spring MVC + Angular






# Graphenfärbung
### Problembeschreibung
Bei dem Problem der Graphenfärbung geht es darum jeder Kante eines Graphen eine Farbe zuzuweisen und dabei möglichst wenige Farben zu benutzen. Die einzige Einschränug ist, dass zwei verbundene Kanten nicht die gleiche Farbe haben dürfen.

![Alt-Text](https://upload.wikimedia.org/wikipedia/commons/f/fd/GolombGraphProperties.svg "Beispiel eines gefärbten Graphen")

(MathsPoetry [CC BY-SA 3.0 (https://creativecommons.org/licenses/by-sa/3.0)])

### Klassendiagramm:

![Alt-Text](https://github.com/Lucab2k/Graphenf-rbungUI/blob/master/Graphenf%C3%A4rbung/Klassendiagramm.png?raw=true "Klassendiagramm")
### Aufbau der UI:

##### landing Window
Das landing Windows dient als Eingabemaske für die Anzahl der Kanten des Graphen. Neben der Eingabe über ein FormattedTextField gibt es einen Button um eine zufällige Zahl in das FormattedTextField zu schreiben.
Mit dem Button "erzeugen" wird das Fenster geschlossen und ein Objekt der Klasse "Hauptfenster" erzeugt.
Weitere Features:
* Wenn die eingegebene zahl < 4 ist, wird statt dem Hauptfenster eine Meldung geöffnet, die darauf hinweist, das es sinvoll ist einen Graphen mit mehr als drei Kanten zu betrachten.
* In das FormattedTextField können nur maximal dreistellige Zahlen eingetragen werden. Außerdem werden führende Nullen automatisch ergänzt.

##### Hauptfenster
Das Hauptfenster dient zum definieren der Verbindungen der einzelnen Kanten und zum starten der Algorithmen. Bei der Erstellung eines Objektes der Klasse "Hauptfenster" wird ein Objekt der Klasse "Graph" erstellt. Diese beinhaltet ein zweidimensionales Array vom Typ Obejct. Ein JTable, das die gleiche Größe wie das Array besitzt, wird erstellt. Beim drücken einer Knöpfe für die Färbung durch einen der Algorithmen wird der gefüllte JTable in das Array gefüllt und alle leeren Felder werden mit einer Null befüllt. Nun wird der gewählte Algorithmus ausgeführt und ein Array vom Typ Integer mit der Länge der Anzahl an Knoten, das die Farben der Knoten enthält, wird zurückgegeben.
Weitere Features:
* Bei der Erstellung des JTabels werden die Verbindungen zwischen den gleichen Knoten, also 1/1, 2/2 usw..., automatisch mit 1 befüllt, da sie immer verbunden sind.
* Trägt man einen Wert in ein Feld ein und verlässt das Feld, wird dieser Wert automatisch in das symmetrische Feld eingefügt. Beim Eintragen des Wertes 1 in das Feld 0/3 wird 1 auch in das Feld 3/0 Eingetragen, da die Felder die gleiche Verbindung beschreiben.

#### Ergebnisfenster
Im Ergebnisfenster wird auch ein JTable angezeigt, das jedoch die dreifache Anzahl an Spalten hat, wie das Graphen-Array. Drei Spalten gehören immer zu einem Knoten. Dabei beschreibt die Erste der drei Spalten die Verbindung der Knoten (0 oder 1). Die Zweite Spalte beschreibt die Farbe des Knoten der y-Achse und die Dritte Spalte beschreibt die Farbe des Knoten auf der x-Achse.
Bsp: Im der Tabelle wird in der Zelle 1 / 2 folgendes angezeigt: 1 | 2 | 3 --> Es besteht eine Verbindung zwischen Knoten 1 und 2; Knoten 2 hat die Farbe 2; Knoten 1 hat die Farbe 3;
Außerdem wird die Anzahl der genutzen unterschiedlichen Farben ausgegeben.

### Funktionaler Algorithmus:
tofo

### Sequentieller Algorithmus:

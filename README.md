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

##### Hauptfenster
Das Hauptfenster dient zum initialisieren des Graphen, zum aufrufen einer der Algorithmen zur Färbung des Grpahen und zur Betrachtung der Färbung. Bei der Erstellung eines Objektes der Klasse "Hauptfenster" wird ein Objekt der Klasse "Graph" erstellt. Diese beinhaltet ein zweidimensionales Array vom Typ Obejct. Ein JTable wird von diesem Array gefüllt. Beim drücken einer Knöpfe für die Färbung durch einen der Algorithmen wird der gefüllte JTable wieder in das Array gefüllt und alle leeren Felder werden mit einer Null befüllt. 

### Funktionaler Algorithmus:

### Sequentieller Algorithmus:

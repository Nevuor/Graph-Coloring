# Färbung der Knoten eines planaren Graphen
### Problembeschreibung
Die Knoten eines planaren Graphen sollen so gefärbt werden, dass keine, durch eine Kante verbundenen Knoten, dieselbe Farbe haben. 

### Klassendiagramm:

![Alt-Text](https://github.com/Lucab2k/Graphenf-rbungUI/blob/master/Graphenf%C3%A4rbung/Klassendiagramm.png?raw=true "Klassendiagramm")
### Aufbau der UI:

##### Startfenster
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
Der Algorithmus löst das Problem, die Knoten eines planaren Graphen zu färben, sodass keine benachbarten, d.h. durch eine Kante verbundenen, Knoten dieselbe Farbe haben, funktional und mithilfe von Rekursion.  
Um den Algorithmus unabhängig vom GUI zu starten, müssen der Funktion faerbeNaechstenKnoten<int[], int[][]> als Input ein mit Nullen gefülltes int[]-Array, dessen Anzahl der Elemente der Anzahl der Knoten des Graphen entspricht, sowie ein zweidimensionales int[][]-Array, welches mit Einsen und Nullen gefüllt ist, über faerbeNaechstenKnoten.apply(int[], int[][]) übergeben werden.

Das zweidimensionale int[][]-Array stellt einen Graphen als Adjazenzmatrix dar, wobei eine Eins für eine vorhandene Kante und eine Null für die Abwesenheit einer Kante steht. Das int[][]-Array besitzt folglich in beiden Dimensionen auch genauso viele Elemente wie der Graph Knoten hat.
   
Rückgabewert der Funktion und somit des Algorithmus ist ein eindimensionales int[]-Array, welches mit Zahlen von 1 bis 4 gefüllt ist, wobei jede der Zahlen eine von vier Farben darstellt, mit denen der Graph gefärbt wurde. 
Daher bezeichne ich dieses Array hier und in den Kommentaren im Quellcode meist als Färbungs-Array. Der Vier-Farben-Satz besagt, dass es möglich ist, jeden beliebigen planaren Graphen mit maximal Vier Farben zu färben. Daher kann der Algorithmus auf alle planaren Graphen, angewendet werden. 
Um funktional zu arbeiten, verwendet der Algorithmus keine Schleifen, keine direkten Array-Zugriffe (Arrays werden ausschließlich mithilfe von Streams verarbeitet) und innerhalb der Funktionen keine, außer der durch Übergabeparameter übergebenen, Variablen.

Die Funktion faerbeNaechstenKnoten<int[], int[][]>  bevorzugt immer die bisher am seltensten verwendete Farbe zur Färbung eines Knotens. Es wird anschließend geprüft, ob diese Farbe hinsichtlich der Nachbarknoten möglich ist. Danach wird geprüft, ob es möglich ist, auf Basis der Färbung des Knotens auch die weiteren noch zu färbenden Knoten zu färben. 
Dazu ruft sich die Funktion faerbeNaechstenKnoten<int[], int[][]> selbst auf, wobei das übergebene Graph-Array gleich bleibt, nun aber ein neues Färbungs-Array übergeben wird. Dieses enthält nun auch die Farbe des aktuell gefärbt werdenden Knotens. Scheitert der Testlauf, also ist es nicht möglich auf Basis der gewählten Farbe den gesamten Graphen zu färben, so wird die am nächstseltensten genutzte Farbe ausprobiert. 
Falls keine der vier Farben möglich ist, so wird das Färbungs-Array an die vorhergehende Rekursionsstufe zurückgegeben und diese versucht eine neue passende Farbe zu finden. Der Algorithmus löst das Problem also durch Backtracking. 
Ist der Testlauf erfolgreich abgeschlossen, so wird der Knoten auch tatsächlich gefärbt, indem an die vorhergehende Rekursionsstufe das Ergebnis des rekursiven Funktionsaufrufes faerbeNaechstenKnoten<int[], int[][]>, übergeben wird, sodass, wenn der Graph bis zum letzten Knoten, also auf die tiefste Rekursionsstufe gefärbt worden ist, diese Färbung als Färbungs-Array bis auf die höchste Rekursionsstufe weitergereicht wird. 
Die Aufgaben und detaillierten Funktionsweisen aller Funktionen sind durch Kommentare direkt im Quellcode beschrieben.


### Sequentieller Algorithmus:

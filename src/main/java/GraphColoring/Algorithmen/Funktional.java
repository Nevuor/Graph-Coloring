package GraphColoring.Algorithmen;

import GraphColoring.Graph;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Funktional {

    public interface TriFunction<U, V, W, R> {

        R apply(U u, V v, W w);
    }

    public static TriFunction<int[], Integer, int[], Integer> nSeltensteFarbeInFaerbung;
    public static TriFunction<int[], int[], Integer, Boolean> pruefeAktuelleFarbe;
    public static BiFunction<int[], int[][], int[]> faerbeNaechstenKnoten;

    public static int[] Faerben(Graph graph, int AnzahlKnoten){

        int Farbe[] = new int[AnzahlKnoten];



        // gibt die Häufigkeit einer übergebenen Farbe in einem übergebenen Array als Long zurück
        // wird innerhalb der Funktion nSeltensteFarbeInFaerbung aufgerufen
        BiFunction<int[], Integer, Long> WieHaeüfigIstInputfarbeInFaerbung = (aktuelleFaerbung, Inputfarbe) -> (Arrays.stream(aktuelleFaerbung).filter(farbe ->   farbe == (Inputfarbe)).count());

        // gibt die am n-seltensten (0<n<5; also die am seltensten, zweitseltensten, drittseltensten oder viertseltensten) im übergebenen Färbungs-Array vorkommende Farbe zurück
        // sind mehrere Zahlen gleich häufig, so wird die kleinere zurückgegeben
        // Achtung: nur für n=1 wird direkt die selstenste Zahl als Long zurückgegeben. Andrenfalls wird Rekursion angewendet

        nSeltensteFarbeInFaerbung = (aktuelleFaerbung, n, darfNichtMehrGeprueftWerden) -> {

            // püfen, ob 1 am seltensten vorkommt
            if (Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==1).count()==0
                    &&
                    ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2))
                            || Arrays.stream(   darfNichtMehrGeprueftWerden).filter(x -> x ==2).count()==1)
                    && ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3)
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==3).count()==1))
                    && ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4)
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==4).count()==1)))
            {if (n == 1)
                return (1);

            else
                //Rekursion mit maximaler Rekursionstiefe 3. Die Funktion ruft sich
                //sich selbst auf, nun aber mit der Information im Übergabeparameter darfNichtMehrGeprüftWerden,
                // welche Farben (hier ist es die 1) zwar seltener vorkommen, aber nicht mehr gprüft werden dürfen,
                // da nicht die seltenste, sonder die 2.-,3.- oder 4.-seltenste Farbe gesucht wird
                return (nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, n - 1, IntStream.concat(Arrays.stream(darfNichtMehrGeprueftWerden), IntStream.of(1)).toArray()));

            }
            // prüfen, ob 2 am seltensten vorkommt
            if (Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==2).count()==0 &&
                    ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1))
                            || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==1).count()==1)
                    && ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3))
                    ||Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==3).count()==1)
                    && ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4))
                    ||Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==4).count()==1)
            ) {
                if (n == 1)
                    return (2);
                else
                    return (nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, n - 1,IntStream.concat(Arrays.stream(darfNichtMehrGeprueftWerden), IntStream.of(2)).toArray()));
            }

            // prüfen, ob 3 am seltensten vorkommt
            if (Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==3).count()==0 &&
                    ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1))
                            || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x ==1).count()==1)
                    && ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2))
                    ||Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==2).count()==1)
                    && ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4))
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==4).count()==1)
            ) {
                if (n == 1)
                    return (3);
                else
                    return (nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, n - 1,IntStream.concat(Arrays.stream(darfNichtMehrGeprueftWerden), IntStream.of(3)).toArray()));

            }

            //prüfen, ob 4 am seltensten vorkommt
            if (Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==4).count()==0
                    && ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2))
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==2).count()==1)
                    && ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3))
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x == 3).count()==1)
                    && ((WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4) <= WieHaeüfigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1))
                    ||Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x == 1).count()==1)
            ) {
                if (n == 1)
                    return (4);
                else
                    return (nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, n - 1,IntStream.concat(Arrays.stream(darfNichtMehrGeprueftWerden), IntStream.of(4)).toArray()));

            }
            return (1);
        };

        // gibt die Anzahl, der im übergebenen Färbungs-Array gefärbten Knoten aus
        Function<int[], Long> aktuellerKnoten = aktuelleFaerbung -> (Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0).count());

        // Hat der zu färbende Knoten keinen benachbarten Knoten, der dieselbe Farbe hat, wie er, wird true zurückgegeben.
        // Da die Farbe jedes Knotens einzeln mit der Farbe des zu färbenden Knotens verglichen werden muss und Schleifen
        // in der funktionalen Programmierung nicht erlaubt sind, wird hier wieder rekursiv gearbeitet
        pruefeAktuelleFarbe = (faerbung, verbindungen, aktuelleFarbe) -> {


            // Tritt ein, wenn alle Knoten verglichen wurden. ("Abbruchbedingung" der Rekursion)
            if (Arrays.stream(verbindungen).count()==0){
                return true;
            }

            // Prüfung ob die Farbe des aktuellen Knotens (= aktuelleFarbe) mit der des nun "ersten" Knotens im Array
            // übereinstimmt und der "erster" Knoten im Array ein Nachbar des aktuellen Knotens ist
            if (Arrays.equals(IntStream.of(aktuelleFarbe).limit(1).toArray(),IntStream.of(faerbung).limit(1).toArray())

                    &&
                    IntStream.of(verbindungen).limit(1).filter(x -> x==1).count()==1) {
                return false;


            } else
                // Rekursion, wobei nun jeweils das 1. Element aus dem Array, das die Färbungen beinhaltet (hier
                // extra nicht aktuelleFaerbung, sondern faerbung genannt, um klarzustellen, dass es sich bei den Elementen nur um eine Teilmenge
                // des aktuelleFaerbung-Arrays handelt) und dem Array, das die Information über die benachbarten Knoten beinhaltet entfernt wird,
                //sodass, im Verlauf der Rekursionsebenen alle Farben der Knoten mit der Färbung des aktuellen Knotens verglichen werden.
                return (pruefeAktuelleFarbe.apply(Arrays.stream(faerbung).skip(1).toArray(), Arrays.stream(verbindungen).skip(1).toArray(),aktuelleFarbe));

        };



        faerbeNaechstenKnoten = (aktuelleFaerbung, einGraph) -> {

            //Wenn alle Farben berechnet wurden, wird das Färbungs-Array zurückgegeben ("Abbruchbedingung" für die Rekursion)
            if(Arrays.stream(aktuelleFaerbung).filter(x -> x==0).count()==0){
                return aktuelleFaerbung;
            }
            //Es werden die vier Möglichkeiten, diesen Knoten zu färben getestet und falls der Test erfolgreich ist, angewendet.
            //Es handelt sich um einen Greedy-Algorithmus, d.h. es wird beim Testen mit der bisher im Graphen am seltensten vorkommenden Farbe begonnen

            if (//Test, ob die Färbung mit der seltensten Farbe funktioniert

                // pruefeAktuelleFarbe liefert einen Boolschen Wert, der true ist, wenn es für
                // den aktuell gefärbeten Knoten keinen angrenzenden Knoten gibt, der die Farbe hat, mit welcher der aktuell gefärbte
                // Knoten gerdae gefärbt wird (hier die seltenste Farbe).

                    pruefeAktuelleFarbe.apply(
                            //Es werden als Input benötigt: (a), b), c))
                            // a) das komplette Färbungs-Array mit der Farbe, auf die gerade getestet wird

                            //Bildlich gesprochen (natürlich nicht korrekt, da hier mit Streams operiert wird):
                            //aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht für den Knoten, der gerade gefärbt wird)
                            //wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                            IntStream.concat(
                                    // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                    IntStream.concat(
                                            // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gefärbt wurden
                                            Arrays.stream(
                                                    aktuelleFaerbung).
                                                    filter(farbe -> farbe != 0),
                                            // aus der am seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                            IntStream.of(
                                                    // berechnen der seltensten  Farbe, die bisher genutzt wird
                                                    nSeltensteFarbeInFaerbung.apply(
                                                            aktuelleFaerbung, 1,IntStream.of().toArray()))),
                                    // Es wird ein Stream aus 0en erzeugt, wobei jede 0 für einen noch nicht gefärbten Knoten steht (ausgenommen des Knotens der aktuell gefärbt werden soll)
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe == 0)
                                            .skip(1))
                                    .toArray(),


                            // b) Die Verbindungen des aktuell zu färbenden Knotens zu den Knoten, die bereits gefärbt sind
                            Arrays.stream(einGraph)
                                    //Hier liegt noch ein 2D-Array vor, daher werden alle Arrays, die im Array Graph vor dem, den aktuellen Knoten betreffenden Array, und nach diesem kommen
                                    // im Stream übersprungen bzw. durch die Festlegung eines Limits abgeschnitten
                                    .skip(aktuellerKnoten
                                            .apply(aktuelleFaerbung))
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .flatMapToInt(x -> Arrays.stream(x))
                                    //jetzt werden die Informationen über die noch nicht gefärbten Knoten und den Knoten selbst entfernt. Dies ist wichtig für die Abbruchbedingung von pruefeAktuelleFarbe
                                    .limit((aktuellerKnoten.apply(aktuelleFaerbung)))
                                    .toArray(),

                            // c) Die Farbe, in der der aktuelle Knoten gerade gefärbt wird
                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung,1, IntStream.of().toArray()))
                            &&
                            //Es wird rekursiv das Färbungs-Array angefordert, das sich ergibt, wenn man den gesamten Graphen auf Basis der Färbung des aktuellen
                            // Knotens mit der am n-seltensten verwendeten Farbe durchfärbt und aus diesem wird ein Stream gemacht
                            Arrays.stream(faerbeNaechstenKnoten.apply(

                                    // Bildlich gesprochen (natürlich nicht korrekt, da hier mit Streams operiert wird):
                                    // aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht für den Knoten, der gerade gefärbt wird)
                                    // wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                                    IntStream.concat(

                                            // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                            IntStream.concat(

                                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gefärbt wurden
                                                    Arrays.stream(aktuelleFaerbung)
                                                            .filter(farbe -> farbe != 0),

                                                    // aus der am n-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                                    IntStream.of(
                                                            // berechnen der n-seltensten Farbe, die bisher genutzt wird
                                                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 1, IntStream.of().toArray()))),

                                            // Es wird ein Stream aus 0en erzeugt, wobei jede 0 für einen noch nicht gefärbten Knoten steht (ausgenommen des Knotens der aktuell gefärbt werden soll), erzeugt
                                            Arrays.stream(aktuelleFaerbung)
                                                    .filter(farbe -> farbe == 0)
                                                    .skip(1))

                                            .toArray(),

                                    einGraph)

                                    //Muss vor folgendem Hintergrund betrachtet werden: kann die Färbung eines Knotens nicht erfolgreich durchgeführt werden,
                                    // wird das Färbungs-Array, mit dem als Übergabeparameter die Funktion aufgerufen wurde, wieder zurückgegeben.
                                    // Die Färbung war also nur genau dann erfolgreich, wenn das Färbungs-Array komplett mit Farben gefüllt wurde und nicht zwischenzeitig
                                    //zurückgegeben wurde.

                            ).filter(x -> x==0).count()==0

            )

            // return-Anweisung wird nur Ausgeführt, wenn
            //
            //a) Prüfung, ob alle zukünftigen Schritte auf Basis des aktuellen Färbungs-Schritts funktionieren
            //
            //                  und
            //
            //b) Prüfung, ob die Farbe, mit der in diesem Schritt gefärbt werden soll (hier ist es die seltenste Farbe) nicht dieselbe, wie die, eines angrenzenden Knotens ist
            //
            //mit true abgeschlossen wurden.

            {
                // rekursiver Aufruf
                // es wird das komplett fertig gefärbte Färbungs-Array (von dem wir uns aufgrund der beiden zuvor geprüften Bedingungen sicher sein können,
                // dass es eine korrekte Färbungs-Möglichkeit darstellt) zurückgegeben.


                return (

                        // Gleicher Code wie in der if-Bedingung (erste Aussage der &&-Verknüpfung), daher nur kompakter geschrieben und unkommentiert.
                        // Um uns an das Prinzip der Funktionalen Programmierung, innerhalb der Funktionen keine Variablen außer den Übergabeparametern zu nutzen,
                        // müssen wir die Funktion faerbeNaechstenKnoten zweimal aufrufen einmal zuvor zum überprüfen und nun zur tatsächlichen Berechnung und
                        // anschließenden Rückgabe. Da pro Knoten nur ein zusätzlicher Aufruf von faerbeNaechstenKnoten erforderlich ist, läuft die zusätzliche Laufzeit, die für
                        // größere und und strukturell schwieriger zu färbende Graphen benötig wird im im unendlichen gegen 0.
                        faerbeNaechstenKnoten.apply(IntStream.concat(IntStream.concat(Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0), IntStream.of(nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 1, IntStream.of().toArray()))), Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe == 0).skip(1)).toArray(), einGraph)
                );

            }

            if (//Test, ob die Färbung mit der 2.-seltensten Farbe funktioniert

                // die "Hilsfunktion" pruefeAktuelleFarbe liefert einen Boolschen Wert, der true ist, wenn es für
                // den aktuell gefärbeten Knoten keinen angrenzenden Knoten gibt, der die Farbe hat, mit welcher der aktuell gefärbte
                // Knoten gerdae gefärbt wird (hier die seltenste Farbe).

                    pruefeAktuelleFarbe.apply(
                            //Es werden als Input benötigt: (a), b), c))
                            // a) das kompletter Färbungs-Array mit der Farbe, auf die gerade getestet wir

                            //Bildlich gesprochen (natürlich nicht korrekt, da hier mit Streams operiert wird):
                            //aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht für den Knoten, der gerade gefärbt wird)
                            //wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                            IntStream.concat(
                                    // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                    IntStream.concat(
                                            // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gefärbt wurden
                                            Arrays.stream(
                                                    aktuelleFaerbung).
                                                    filter(farbe -> farbe != 0),
                                            // aus der am 2.-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                            IntStream.of(
                                                    // berechnen der 2.-seltensten Farbe, die bisher genutzt wird
                                                    nSeltensteFarbeInFaerbung.apply(
                                                            aktuelleFaerbung, 2, IntStream.of().toArray()))),
                                    // Es wird ein Stream aus 0en erzeugt, wobei jede 0 für einen noch nicht gefärbten Knoten steht (ausgenommen des Knotens der aktuell gefärbt werden soll)
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe == 0)
                                            .skip(1))
                                    .toArray(),


                            // b) Die Verbindungen des aktuell zu färbenden Knotens zu den Knoten, die bereits gefärbt sind
                            Arrays.stream(einGraph)
                                    //Hier liegt noch ein 2D-Array vor, daher werden alle Arrays, die im Array Graph vor dem, den aktuellen Knoten betreffenden Array, und nach diesem kommen
                                    // im Stream übersprungen bzw. durch die Festlegung eines Limits abgeschnitten
                                    .skip(aktuellerKnoten
                                            .apply(aktuelleFaerbung))
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .flatMapToInt(x -> Arrays.stream(x))
                                    //jetzt werden die Informationen über die noch nicht gefärbten Knoten und den Knoten selbst entfernt. Dies ist wichtig für die Abbruchbedingung von pruefeAktuelleFarbe
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .toArray(),

                            // c) Die Farbe, in der der aktuelle Knoten gerade gefärbt wird
                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung,2, IntStream.of().toArray()))

                            &&

                            //Es wird rekursiv das Färbungs-Array angefordert, das sich ergibt, wenn man den gesamten Graphen auf Basis der Färbung des aktuellen
                            // Knotens mit der am n-seltensten verwendeten Farbe durchfärbt und aus diesem wird ein Stream gemacht
                            Arrays.stream(faerbeNaechstenKnoten.apply(

                                    // Bildlich gesprochen (natürlich nicht korrekt, da hier mit Streams operiert wird):
                                    // aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht für den Knoten, der gerade gefärbt wird)
                                    // wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                                    IntStream.concat(

                                            // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                            IntStream.concat(

                                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gefärbt wurden
                                                    Arrays.stream(aktuelleFaerbung)
                                                            .filter(farbe -> farbe != 0),

                                                    // aus der am n-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                                    IntStream.of(
                                                            // berechnen der n-seltensten Farbe, die bisher genutzt wird
                                                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 1, IntStream.of().toArray()))),

                                            // Es wird ein Stream aus 0en erzeugt, wobei jede 0 für einen noch nicht gefärbten Knoten steht (ausgenommen des Knotens der aktuell gefärbt werden soll), erzeugt
                                            Arrays.stream(aktuelleFaerbung)
                                                    .filter(farbe -> farbe == 0)
                                                    .skip(1))

                                            .toArray(),

                                    einGraph)

                                    //Muss vor folgendem Hintergrund betrachtet werden: kann die Färbung eines Knotens nicht erfolgreich durchgeführt werden,
                                    // wird das Färbungs-Array, mit dem als Übergabeparameter die Funktion aufgerufen wurde, wieder zurückgegeben.
                                    // Die Färbung war also nur genau dann erfolgreich, wenn das Färbungs-Array komplett mit Farben gefüllt wurde und nicht zwischenzeitig
                                    //zurückgegeben wurde.


                            ).filter(x -> x==0).count()==0

            )
            // return-Anweisung wird nur Ausgeführt, wenn
            //
            //a) Prüfung, ob alle zukünftigen Schritte auf Basis des aktuellen Färbungs-Schritts funktionieren
            //
            //                  und
            //
            //b) Prüfung, ob die Farbe, mit der in diesem Schritt gefärbt werden soll (hier ist es die seltenste Farbe) nicht dieselbe, wie die, eines angrenzenden Knotens ist
            //
            //mit true abgeschlossen wurden.

            {
                // rekursiver Aufruf, es wird das komplett fertig gefärbte Färbungs-Array (von dem wir uns aufgrund der beiden zuvor geprüften Bedingungen sicher sein können,
                // dass es eine korrekte Färbungs-Möglichkeit darstellt) zurückgegeben.


                return (

                        // Gleicher Code wie in der if-Bedingung (erste Aussage der &&-Verknüpfung, nur ohne equals), daher nur kompakter geschrieben und unkommentiert.
                        // Um uns an das Prinzip der Funktionalen Programmierung, innerhalb der Funktionen keine Variablen außer den Übergabeparametern zu nutzen,
                        // müssen wir die Funktion faerbeNaechstenKnoten zweimal aufrufen einmal zuvor zum überprüfen und nun zur tatsächlichen Berechnung und
                        // anschließenden Rückgabe. Da pro Knoten nur ein zusätzlicher Aufruf von faerbeNaechstenKnoten erforderlich ist, läuft die zusätzliche Laufzeit, die für
                        // größere und und strukturell schwieriger zu färbende Graphen benötig wird im im unendlichen gegen 0.
                        faerbeNaechstenKnoten.apply(IntStream.concat(IntStream.concat(Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0), IntStream.of(nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 2, IntStream.of().toArray()))), Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe == 0).skip(1)).toArray(), einGraph)
                );

            }

            if (//Test, ob die Färbung mit der 3.-seltensten Farbe funktioniert

                // die "Hilsfunktion" pruefeAktuelleFarbe liefert einen Boolschen Wert, der true ist, wenn es für
                // den aktuell gefärbeten Knoten keinen angrenzenden Knoten gibt, der die Farbe hat, mit welcher der aktuell gefärbte
                // Knoten gerdae gefärbt wird (hier die seltenste Farbe).

                    pruefeAktuelleFarbe.apply(
                            //Es werden als Input benötigt: (a), b), c))
                            // a) das kompletter Färbungs-Array mit der Farbe, auf die gerade getestet wir

                            //Bildlich gesprochen (natürlich nicht korrekt, da hier mit Streams operiert wird):
                            //aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht für den Knoten, der gerade gefärbt wird)
                            //wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                            IntStream.concat(
                                    // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                    IntStream.concat(
                                            // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gefärbt wurden
                                            Arrays.stream(
                                                    aktuelleFaerbung).
                                                    filter(farbe -> farbe != 0),
                                            // aus der am seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                            IntStream.of(
                                                    // berechnen der seltensten (da der 2 Parameter gleich 1 ist) Farbe, die bisher genutzt wird
                                                    nSeltensteFarbeInFaerbung.apply(
                                                            aktuelleFaerbung, 3, IntStream.of().toArray()))),
                                    // Es wird ein Stream aus 0en erzeugt, wobei jede 0 für einen noch nicht gefärbten Knoten steht (ausgenommen des Knotens der aktuell gefärbt werden soll)
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe == 0)
                                            .skip(1))
                                    .toArray(),


                            // b) Die Verbindungen des aktuell zu färbenden Knotens zu den Knoten, die bereits gefärbt sind
                            Arrays.stream(einGraph)
                                    //Hier liegt noch ein 2D-Array vor, daher werden alle Arrays, die im Array Graph vor dem, den aktuellen Knoten betreffenden Array, und nach diesem kommen
                                    // im Stream übersprungen bzw. durch die Festlegung eines Limits abgeschnitten
                                    .skip(aktuellerKnoten
                                            .apply(aktuelleFaerbung))
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .flatMapToInt(x -> Arrays.stream(x))
                                    //jetzt werden die Informationen über die noch nicht gefärbten Knoten und den Knoten selbst entfernt. Dies ist wichtig für die Abbruchbedingung von pruefeAktuelleFarbe
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .toArray(),

                            // c) Die Farbe, in der der aktuelle Knoten gerade gefärbt wird
                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung,3, IntStream.of().toArray()))

                            &&

                            //Es wird rekursiv das Färbungs-Array angefordert, das sich ergibt, wenn man den gesamten Graphen auf Basis der Färbung des aktuellen
                            // Knotens mit der am n-seltensten verwendeten Farbe durchfärbt und aus diesem wird ein Stream gemacht
                            Arrays.stream(faerbeNaechstenKnoten.apply(

                                    // Bildlich gesprochen (natürlich nicht korrekt, da hier mit Streams operiert wird):
                                    // aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht für den Knoten, der gerade gefärbt wird)
                                    // wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                                    IntStream.concat(

                                            // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                            IntStream.concat(

                                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gefärbt wurden
                                                    Arrays.stream(aktuelleFaerbung)
                                                            .filter(farbe -> farbe != 0),

                                                    // aus der am n-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                                    IntStream.of(
                                                            // berechnen der n-seltensten Farbe, die bisher genutzt wird
                                                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 3, IntStream.of().toArray()))),

                                            // Es wird ein Stream aus 0en erzeugt, wobei jede 0 für einen noch nicht gefärbten Knoten steht (ausgenommen des Knotens der aktuell gefärbt werden soll), erzeugt
                                            Arrays.stream(aktuelleFaerbung)
                                                    .filter(farbe -> farbe == 0)
                                                    .skip(1))

                                            .toArray(),

                                    einGraph)

                                    //Muss vor folgendem Hintergrund betrachtet werden: kann die Färbung eines Knotens nicht erfolgreich durchgeführt werden,
                                    // wird das Färbungs-Array, mit dem als Übergabeparameter die Funktion aufgerufen wurde, wieder zurückgegeben.
                                    // Die Färbung war also nur genau dann erfolgreich, wenn das Färbungs-Array komplett mit Farben gefüllt wurde und nicht zwischenzeitig
                                    //zurückgegeben wurde.

                            ).filter(x -> x==0).count()==0

            )



            // return-Anweisung wird nur Ausgeführt, wenn
            //
            //a) Prüfung, ob alle zukünftigen Schritte auf Basis des aktuellen Färbungs-Schritts funktionieren
            //
            //                  und
            //
            //b) Prüfung, ob die Farbe, mit der in diesem Schritt gefärbt werden soll (hier ist es die seltenste Farbe) nicht dieselbe, wie die, eines angrenzenden Knotens ist
            //
            //mit true abgeschlossen wurden.

            {
                // rekursiver Aufruf, es wird das komplett fertig gefärbte Färbungs-Array (von dem wir uns aufgrund der beiden zuvor geprüften Bedingungen sicher sein können,
                // dass es eine korrekte Färbungs-Möglichkeit darstellt) zurückgegeben.


                return (

                        // Gleicher Code wie in der if-Bedingung (erste Aussage der &&-Verknüpfung), daher nur kompakter geschrieben und unkommentiert.
                        // Um uns an das Prinzip der Funktionalen Programmierung, innerhalb der Funktionen keine Variablen außer den Übergabeparametern zu nutzen,
                        // müssen wir die Funktion faerbeNaechstenKnoten zweimal aufrufen einmal zuvor zum überprüfen und nun zur tatsächlichen Berechnung und
                        // anschließenden Rückgabe. Da pro Knoten nur ein zusätzlicher Aufruf von faerbeNaechstenKnoten erforderlich ist, läuft die zusätzliche Laufzeit, die für
                        // größere und und strukturell schwieriger zu färbende Graphen benötig wird im im unendlichen gegen 0.
                        faerbeNaechstenKnoten.apply(IntStream.concat(IntStream.concat(Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0), IntStream.of(nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 3,IntStream.of().toArray()))), Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe == 0).skip(1)).toArray(), einGraph)
                );

            }

            if (//Test, ob die Färbung mit der 4.-seltensten Farbe funktioniert

                // die "Hilsfunktion" pruefeAktuelleFarbe liefert einen Boolschen Wert, der true ist, wenn es für
                // den aktuell gefärbeten Knoten keinen angrenzenden Knoten gibt, der die Farbe hat, mit welcher der aktuell gefärbte
                // Knoten gerdae gefärbt wird (hier die seltenste Farbe).

                    pruefeAktuelleFarbe.apply(
                            //Es werden als Input benötigt: (a), b), c))
                            // a) das kompletter Färbungs-Array mit der Farbe, auf die gerade getestet wir

                            //Bildlich gesprochen (natürlich nicht korrekt, da hier mit Streams operiert wird):
                            //aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht für den Knoten, der gerade gefärbt wird)
                            //wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                            IntStream.concat(
                                    // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                    IntStream.concat(
                                            // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gefärbt wurden
                                            Arrays.stream(
                                                    aktuelleFaerbung).
                                                    filter(farbe -> farbe != 0),
                                            // aus der am seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                            IntStream.of(
                                                    // berechnen der seltensten (da der 2 Parameter gleich 1 ist) Farbe, die bisher genutzt wird
                                                    nSeltensteFarbeInFaerbung.apply(
                                                            aktuelleFaerbung, 4,IntStream.of().toArray()))),
                                    // Es wird ein Stream aus 0en erzeugt, wobei jede 0 für einen noch nicht gefärbten Knoten steht (ausgenommen des Knotens der aktuell gefärbt werden soll)
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe == 0)
                                            .skip(1))
                                    .toArray(),


                            // b) Die Verbindungen des aktuell zu färbenden Knotens zu den Knoten, die bereits gefärbt sind
                            Arrays.stream(einGraph)
                                    //Hier liegt noch ein 2D-Array vor, daher werden alle Arrays, die im Array Graph vor dem, den aktuellen Knoten betreffenden Array, und nach diesem kommen
                                    // im Stream übersprungen bzw. durch die Festlegung eines Limits abgeschnitten
                                    .skip(aktuellerKnoten
                                            .apply(aktuelleFaerbung))
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .flatMapToInt(x -> Arrays.stream(x))
                                    //jetzt werden die Informationen über die noch nicht gefärbten Knoten und den Knoten selbst entfernt. Dies ist wichtig für die Abbruchbedingung von pruefeAktuelleFarbe
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .toArray(),

                            // c) Die Farbe, in der der aktuelle Knoten gerade gefärbt wird
                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung,4,IntStream.of().toArray()))

                            &&



                            //Es wird rekursiv das Färbungs-Array angefordert, das sich ergibt, wenn man den gesamten Graphen auf Basis der Färbung des aktuellen
                            // Knotens mit der am n-seltensten verwendeten Farbe durchfärbt und aus diesem wird ein Stream gemacht
                            Arrays.stream(faerbeNaechstenKnoten.apply(

                                    // Bildlich gesprochen (natürlich nicht korrekt, da hier mit Streams operiert wird):
                                    // aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht für den Knoten, der gerade gefärbt wird)
                                    // wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                                    IntStream.concat(

                                            // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                            IntStream.concat(

                                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gefärbt wurden
                                                    Arrays.stream(aktuelleFaerbung)
                                                            .filter(farbe -> farbe != 0),

                                                    // aus der am n-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                                    IntStream.of(
                                                            // berechnen der n-seltensten Farbe, die bisher genutzt wird
                                                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 4,IntStream.of().toArray()))),

                                            // Es wird ein Stream aus 0en erzeugt, wobei jede 0 für einen noch nicht gefärbten Knoten steht (ausgenommen des Knotens der aktuell gefärbt werden soll), erzeugt
                                            Arrays.stream(aktuelleFaerbung)
                                                    .filter(farbe -> farbe == 0)
                                                    .skip(1))

                                            .toArray(),

                                    einGraph)

                                    //Muss vor folgendem Hintergrund betrachtet werden: kann die Färbung eines Knotens nicht erfolgreich durchgeführt werden,
                                    // wird das Färbungs-Array, mit dem als Übergabeparameter die Funktion aufgerufen wurde, wieder zurückgegeben.
                                    // Die Färbung war also nur genau dann erfolgreich, wenn das Färbungs-Array komplett mit Farben gefüllt wurde und nicht zwischenzeitig
                                    //zurückgegeben wurde.

                            ).filter(x -> x==0).count()==0

            )



            // return-Anweisung wird nur Ausgeführt, wenn
            //
            //a) Prüfung, ob alle zukünftigen Schritte auf Basis des aktuellen Färbungs-Schritts funktionieren
            //
            //                  und
            //
            //b) Prüfung, ob die Farbe, mit der in diesem Schritt gefärbt werden soll (hier ist es die seltenste Farbe) nicht dieselbe, wie die, eines angrenzenden Knotens ist
            //
            //mit true abgeschlossen wurden.

            {
                // rekursiver Aufruf, es wird das komplett fertig gefärbte Färbungs-Array (von dem wir uns aufgrund der beiden zuvor geprüften Bedingungen sicher sein können,
                // dass es eine korrekte Färbungs-Möglichkeit darstellt) zurückgegeben.


                return (

                        // Gleicher Code wie in der if-Bedingung (erste Aussage der &&-Verknüpfung, daher nur kompakter geschrieben und unkommentiert.
                        // Um uns an das Prinzip der Funktionalen Programmierung, innerhalb der Funktionen keine Variablen außer den Übergabeparametern zu nutzen,
                        // müssen wir die Funktion faerbeNaechstenKnoten zweimal aufrufen einmal zuvor zum überprüfen und nun zur tatsächlichen Berechnung und
                        // anschließenden Rückgabe. Da pro Knoten nur ein zusätzlicher Aufruf von faerbeNaechstenKnoten erforderlich ist, läuft die zusätzliche Laufzeit, die für
                        // größere und und strukturell schwieriger zu färbende Graphen benötig wird im im unendlichen gegen 0.
                        faerbeNaechstenKnoten.apply(IntStream.concat(IntStream.concat(Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0), IntStream.of(nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 4,IntStream.of().toArray()))), Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe == 0).skip(1)).toArray(), einGraph)
                );

            }

            // Für den Fall, dass keine Färbung des Konten mit einer der 4 Farben erfolgreich getestet und dann auch angewendet wurde, wird das Färungs-Array aus der vorherigen
            // Rekursionsebene zurückgegeben. Dazu muss die Farbe des zuletzt gefärbten Elements "gelöscht" werden, damit in der verhergehenden Reskursionebene direkt weitere Färbungen getestet
            // werden können
            return (
                    IntStream.concat(


                            IntStream.concat(

                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gefärbt wurden
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe != 0),


                                    IntStream.of(
                                            //Füge 0 ein (als ersatz für die vorherige Farbe)
                                            0)),

                            // Noch nicht gefärbte Knoten werden durch 0en dargestellt and zum Stream hinzugefügt
                            Arrays.stream(aktuelleFaerbung)
                                    .filter(farbe -> farbe == 0)
                                    .skip(1))

                            .toArray());
        };

        return Farbe;
    }


}

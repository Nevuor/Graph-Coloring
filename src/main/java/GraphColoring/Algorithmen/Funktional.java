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

    public static int[] Faerben(Graph übergebenerGraph, int AnzahlKnoten){

        int Farben[] = new int[AnzahlKnoten];

        int graph[][] = übergebenerGraph.getIntArray();

        faerbeNaechstenKnoten.apply(Farben, graph);

        // gibt die H�ufigkeit einer �bergebenen Farbe in einem �bergebenen Array als Long zur�ck
        // wird innerhalb der Funktion nSeltensteFarbeInFaerbung aufgerufen
        BiFunction<int[], Integer, Long> WieHaeufigIstInputfarbeInFaerbung = (aktuelleFaerbung, Inputfarbe) -> (Arrays.stream(aktuelleFaerbung).filter(farbe ->   farbe == (Inputfarbe)).count());

        // gibt die am n-seltensten (0<n<5; also die am seltensten, zweitseltensten, drittseltensten oder viertseltensten) im �bergebenen F�rbungs-Array vorkommende Farbe zur�ck
        // sind mehrere Zahlen gleich h�ufig, so wird die kleinere zur�ckgegeben
        // Achtung: nur f�r n=1 wird direkt die selstenste Zahl als Long zur�ckgegeben. Andrenfalls wird Rekursion angewendet

        nSeltensteFarbeInFaerbung = (aktuelleFaerbung, n, darfNichtMehrGeprueftWerden) -> {

            // p�fen, ob 1 am seltensten vorkommt
            if (Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==1).count()==0
                    &&
                    ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2))
                            || Arrays.stream(   darfNichtMehrGeprueftWerden).filter(x -> x ==2).count()==1)
                    && ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3)
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==3).count()==1))
                    && ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4)
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==4).count()==1)))
            {if (n == 1)
                return (1);

            else
                //Rekursion mit maximaler Rekursionstiefe 3. Die Funktion ruft sich
                //sich selbst auf, nun aber mit der Information im �bergabeparameter darfNichtMehrGepr�ftWerden,
                // welche Farben (hier ist es die 1) zwar seltener vorkommen, aber nicht mehr gpr�ft werden d�rfen,
                // da nicht die seltenste, sonder die 2.-,3.- oder 4.-seltenste Farbe gesucht wird
                return (nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, n - 1, IntStream.concat(Arrays.stream(darfNichtMehrGeprueftWerden), IntStream.of(1)).toArray()));

            }
            // pr�fen, ob 2 am seltensten vorkommt
            if (Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==2).count()==0 &&
                    ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1))
                            || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==1).count()==1)
                    && ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3))
                    ||Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==3).count()==1)
                    && ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4))
                    ||Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==4).count()==1)
            ) {
                if (n == 1)
                    return (2);
                else
                    return (nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, n - 1,IntStream.concat(Arrays.stream(darfNichtMehrGeprueftWerden), IntStream.of(2)).toArray()));
            }

            // pr�fen, ob 3 am seltensten vorkommt
            if (Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==3).count()==0 &&
                    ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1))
                            || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x ==1).count()==1)
                    && ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2))
                    ||Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==2).count()==1)
                    && ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4))
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==4).count()==1)
            ) {
                if (n == 1)
                    return (3);
                else
                    return (nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, n - 1,IntStream.concat(Arrays.stream(darfNichtMehrGeprueftWerden), IntStream.of(3)).toArray()));

            }

            //pr�fen, ob 4 am seltensten vorkommt
            if (Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==4).count()==0
                    && ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 2))
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x==2).count()==1)
                    && ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 3))
                    || Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x == 3).count()==1)
                    && ((WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 4) <= WieHaeufigIstInputfarbeInFaerbung.apply(aktuelleFaerbung, 1))
                    ||Arrays.stream(darfNichtMehrGeprueftWerden).filter(x -> x == 1).count()==1)
            ) {
                if (n == 1)
                    return (4);
                else
                    return (nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, n - 1,IntStream.concat(Arrays.stream(darfNichtMehrGeprueftWerden), IntStream.of(4)).toArray()));

            }
            return (1);
        };

        // gibt die Anzahl, der im �bergebenen F�rbungs-Array gef�rbten Knoten aus
        Function<int[], Long> aktuellerKnoten = aktuelleFaerbung -> (Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0).count());

        // Hat der zu f�rbende Knoten keinen benachbarten Knoten, der dieselbe Farbe hat, wie er, wird true zur�ckgegeben.
        // Da die Farbe jedes Knotens einzeln mit der Farbe des zu f�rbenden Knotens verglichen werden muss und Schleifen
        // in der funktionalen Programmierung nicht erlaubt sind, wird hier wieder rekursiv gearbeitet
        pruefeAktuelleFarbe = (faerbung, verbindungen, aktuelleFarbe) -> {


            // Tritt ein, wenn alle Knoten verglichen wurden. ("Abbruchbedingung" der Rekursion)
            if (Arrays.stream(verbindungen).count()==0){
                return true;
            }

            // Pr�fung ob die Farbe des aktuellen Knotens (= aktuelleFarbe) mit der des nun "ersten" Knotens im Array
            // �bereinstimmt und der "erster" Knoten im Array ein Nachbar des aktuellen Knotens ist
            if (Arrays.equals(IntStream.of(aktuelleFarbe).limit(1).toArray(),IntStream.of(faerbung).limit(1).toArray())

                    &&
                    IntStream.of(verbindungen).limit(1).filter(x -> x==1).count()==1) {
                return false;


            } else
                // Rekursion, wobei nun jeweils das 1. Element aus dem Array, das die F�rbungen beinhaltet (hier
                // extra nicht aktuelleFaerbung, sondern faerbung genannt, um klarzustellen, dass es sich bei den Elementen nur um eine Teilmenge
                // des aktuelleFaerbung-Arrays handelt) und dem Array, das die Information �ber die benachbarten Knoten beinhaltet entfernt wird,
                //sodass, im Verlauf der Rekursionsebenen alle Farben der Knoten mit der F�rbung des aktuellen Knotens verglichen werden.
                return (pruefeAktuelleFarbe.apply(Arrays.stream(faerbung).skip(1).toArray(), Arrays.stream(verbindungen).skip(1).toArray(),aktuelleFarbe));

        };



        faerbeNaechstenKnoten = (aktuelleFaerbung, einGraph) -> {

            //Wenn alle Farben berechnet wurden, wird das F�rbungs-Array zur�ckgegeben ("Abbruchbedingung" f�r die Rekursion)
            if(Arrays.stream(aktuelleFaerbung).filter(x -> x==0).count()==0){
                return aktuelleFaerbung;
            }
            //Es werden die vier M�glichkeiten, diesen Knoten zu f�rben getestet und falls der Test erfolgreich ist, angewendet.
            //Es handelt sich um einen Greedy-Algorithmus, d.h. es wird beim Testen mit der bisher im Graphen am seltensten vorkommenden Farbe begonnen

            if (//Test, ob die F�rbung mit der seltensten Farbe funktioniert

                // pruefeAktuelleFarbe liefert einen Boolschen Wert, der true ist, wenn es f�r
                // den aktuell gef�rbeten Knoten keinen angrenzenden Knoten gibt, der die Farbe hat, mit welcher der aktuell gef�rbte
                // Knoten gerdae gef�rbt wird (hier die seltenste Farbe).

                    pruefeAktuelleFarbe.apply(
                            //Es werden als Input ben�tigt: (a), b), c))
                            // a) das komplette F�rbungs-Array mit der Farbe, auf die gerade getestet wird

                            //Bildlich gesprochen (nat�rlich nicht korrekt, da hier mit Streams operiert wird):
                            //aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht f�r den Knoten, der gerade gef�rbt wird)
                            //wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                            IntStream.concat(
                                    // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                    IntStream.concat(
                                            // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gef�rbt wurden
                                            Arrays.stream(
                                                    aktuelleFaerbung).
                                                    filter(farbe -> farbe != 0),
                                            // aus der am seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                            IntStream.of(
                                                    // berechnen der seltensten  Farbe, die bisher genutzt wird
                                                    nSeltensteFarbeInFaerbung.apply(
                                                            aktuelleFaerbung, 1,IntStream.of().toArray()))),
                                    // Es wird ein Stream aus 0en erzeugt, wobei jede 0 f�r einen noch nicht gef�rbten Knoten steht (ausgenommen des Knotens der aktuell gef�rbt werden soll)
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe == 0)
                                            .skip(1))
                                    .toArray(),


                            // b) Die Verbindungen des aktuell zu f�rbenden Knotens zu den Knoten, die bereits gef�rbt sind
                            Arrays.stream(einGraph)
                                    //Hier liegt noch ein 2D-Array vor, daher werden alle Arrays, die im Array Graph vor dem, den aktuellen Knoten betreffenden Array, und nach diesem kommen
                                    // im Stream �bersprungen bzw. durch die Festlegung eines Limits abgeschnitten
                                    .skip(aktuellerKnoten
                                            .apply(aktuelleFaerbung))
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .flatMapToInt(x -> Arrays.stream(x))
                                    //jetzt werden die Informationen �ber die noch nicht gef�rbten Knoten und den Knoten selbst entfernt. Dies ist wichtig f�r die Abbruchbedingung von pruefeAktuelleFarbe
                                    .limit((aktuellerKnoten.apply(aktuelleFaerbung)))
                                    .toArray(),

                            // c) Die Farbe, in der der aktuelle Knoten gerade gef�rbt wird
                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung,1, IntStream.of().toArray()))
                            &&
                            //Es wird rekursiv das F�rbungs-Array angefordert, das sich ergibt, wenn man den gesamten Graphen auf Basis der F�rbung des aktuellen
                            // Knotens mit der am n-seltensten verwendeten Farbe durchf�rbt und aus diesem wird ein Stream gemacht
                            Arrays.stream(faerbeNaechstenKnoten.apply(

                                    // Bildlich gesprochen (nat�rlich nicht korrekt, da hier mit Streams operiert wird):
                                    // aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht f�r den Knoten, der gerade gef�rbt wird)
                                    // wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                                    IntStream.concat(

                                            // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                            IntStream.concat(

                                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gef�rbt wurden
                                                    Arrays.stream(aktuelleFaerbung)
                                                            .filter(farbe -> farbe != 0),

                                                    // aus der am n-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                                    IntStream.of(
                                                            // berechnen der n-seltensten Farbe, die bisher genutzt wird
                                                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 1, IntStream.of().toArray()))),

                                            // Es wird ein Stream aus 0en erzeugt, wobei jede 0 f�r einen noch nicht gef�rbten Knoten steht (ausgenommen des Knotens der aktuell gef�rbt werden soll), erzeugt
                                            Arrays.stream(aktuelleFaerbung)
                                                    .filter(farbe -> farbe == 0)
                                                    .skip(1))

                                            .toArray(),

                                    einGraph)

                                    //Muss vor folgendem Hintergrund betrachtet werden: kann die F�rbung eines Knotens nicht erfolgreich durchgef�hrt werden,
                                    // wird das F�rbungs-Array, mit dem als �bergabeparameter die Funktion aufgerufen wurde, wieder zur�ckgegeben.
                                    // Die F�rbung war also nur genau dann erfolgreich, wenn das F�rbungs-Array komplett mit Farben gef�llt wurde und nicht zwischenzeitig
                                    //zur�ckgegeben wurde.

                            ).filter(x -> x==0).count()==0

            )

            // return-Anweisung wird nur Ausgef�hrt, wenn
            //
            //a) Pr�fung, ob alle zuk�nftigen Schritte auf Basis des aktuellen F�rbungs-Schritts funktionieren
            //
            //                  und
            //
            //b) Pr�fung, ob die Farbe, mit der in diesem Schritt gef�rbt werden soll (hier ist es die seltenste Farbe) nicht dieselbe, wie die, eines angrenzenden Knotens ist
            //
            //mit true abgeschlossen wurden.

            {
                // rekursiver Aufruf
                // es wird das komplett fertig gef�rbte F�rbungs-Array (von dem wir uns aufgrund der beiden zuvor gepr�ften Bedingungen sicher sein k�nnen,
                // dass es eine korrekte F�rbungs-M�glichkeit darstellt) zur�ckgegeben.


                return (

                        // Gleicher Code wie in der if-Bedingung (erste Aussage der &&-Verkn�pfung), daher nur kompakter geschrieben und unkommentiert.
                        // Um uns an das Prinzip der Funktionalen Programmierung, innerhalb der Funktionen keine Variablen au�er den �bergabeparametern zu nutzen,
                        // m�ssen wir die Funktion faerbeNaechstenKnoten zweimal aufrufen einmal zuvor zum �berpr�fen und nun zur tats�chlichen Berechnung und
                        // anschlie�enden R�ckgabe. Da pro Knoten nur ein zus�tzlicher Aufruf von faerbeNaechstenKnoten erforderlich ist, l�uft die zus�tzliche Laufzeit, die f�r
                        // gr��ere und und strukturell schwieriger zu f�rbende Graphen ben�tig wird im im unendlichen gegen 0.
                        faerbeNaechstenKnoten.apply(IntStream.concat(IntStream.concat(Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0), IntStream.of(nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 1, IntStream.of().toArray()))), Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe == 0).skip(1)).toArray(), einGraph)
                );

            }

            if (//Test, ob die F�rbung mit der 2.-seltensten Farbe funktioniert

                // die "Hilsfunktion" pruefeAktuelleFarbe liefert einen Boolschen Wert, der true ist, wenn es f�r
                // den aktuell gef�rbeten Knoten keinen angrenzenden Knoten gibt, der die Farbe hat, mit welcher der aktuell gef�rbte
                // Knoten gerdae gef�rbt wird (hier die seltenste Farbe).

                    pruefeAktuelleFarbe.apply(
                            //Es werden als Input ben�tigt: (a), b), c))
                            // a) das kompletter F�rbungs-Array mit der Farbe, auf die gerade getestet wir

                            //Bildlich gesprochen (nat�rlich nicht korrekt, da hier mit Streams operiert wird):
                            //aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht f�r den Knoten, der gerade gef�rbt wird)
                            //wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                            IntStream.concat(
                                    // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                    IntStream.concat(
                                            // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gef�rbt wurden
                                            Arrays.stream(
                                                    aktuelleFaerbung).
                                                    filter(farbe -> farbe != 0),
                                            // aus der am 2.-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                            IntStream.of(
                                                    // berechnen der 2.-seltensten Farbe, die bisher genutzt wird
                                                    nSeltensteFarbeInFaerbung.apply(
                                                            aktuelleFaerbung, 2, IntStream.of().toArray()))),
                                    // Es wird ein Stream aus 0en erzeugt, wobei jede 0 f�r einen noch nicht gef�rbten Knoten steht (ausgenommen des Knotens der aktuell gef�rbt werden soll)
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe == 0)
                                            .skip(1))
                                    .toArray(),


                            // b) Die Verbindungen des aktuell zu f�rbenden Knotens zu den Knoten, die bereits gef�rbt sind
                            Arrays.stream(einGraph)
                                    //Hier liegt noch ein 2D-Array vor, daher werden alle Arrays, die im Array Graph vor dem, den aktuellen Knoten betreffenden Array, und nach diesem kommen
                                    // im Stream �bersprungen bzw. durch die Festlegung eines Limits abgeschnitten
                                    .skip(aktuellerKnoten
                                            .apply(aktuelleFaerbung))
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .flatMapToInt(x -> Arrays.stream(x))
                                    //jetzt werden die Informationen �ber die noch nicht gef�rbten Knoten und den Knoten selbst entfernt. Dies ist wichtig f�r die Abbruchbedingung von pruefeAktuelleFarbe
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .toArray(),

                            // c) Die Farbe, in der der aktuelle Knoten gerade gef�rbt wird
                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung,2, IntStream.of().toArray()))

                            &&

                            //Es wird rekursiv das F�rbungs-Array angefordert, das sich ergibt, wenn man den gesamten Graphen auf Basis der F�rbung des aktuellen
                            // Knotens mit der am n-seltensten verwendeten Farbe durchf�rbt und aus diesem wird ein Stream gemacht
                            Arrays.stream(faerbeNaechstenKnoten.apply(

                                    // Bildlich gesprochen (nat�rlich nicht korrekt, da hier mit Streams operiert wird):
                                    // aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht f�r den Knoten, der gerade gef�rbt wird)
                                    // wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                                    IntStream.concat(

                                            // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                            IntStream.concat(

                                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gef�rbt wurden
                                                    Arrays.stream(aktuelleFaerbung)
                                                            .filter(farbe -> farbe != 0),

                                                    // aus der am n-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                                    IntStream.of(
                                                            // berechnen der n-seltensten Farbe, die bisher genutzt wird
                                                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 1, IntStream.of().toArray()))),

                                            // Es wird ein Stream aus 0en erzeugt, wobei jede 0 f�r einen noch nicht gef�rbten Knoten steht (ausgenommen des Knotens der aktuell gef�rbt werden soll), erzeugt
                                            Arrays.stream(aktuelleFaerbung)
                                                    .filter(farbe -> farbe == 0)
                                                    .skip(1))

                                            .toArray(),

                                    einGraph)

                                    //Muss vor folgendem Hintergrund betrachtet werden: kann die F�rbung eines Knotens nicht erfolgreich durchgef�hrt werden,
                                    // wird das F�rbungs-Array, mit dem als �bergabeparameter die Funktion aufgerufen wurde, wieder zur�ckgegeben.
                                    // Die F�rbung war also nur genau dann erfolgreich, wenn das F�rbungs-Array komplett mit Farben gef�llt wurde und nicht zwischenzeitig
                                    //zur�ckgegeben wurde.


                            ).filter(x -> x==0).count()==0

            )
            // return-Anweisung wird nur Ausgef�hrt, wenn
            //
            //a) Pr�fung, ob alle zuk�nftigen Schritte auf Basis des aktuellen F�rbungs-Schritts funktionieren
            //
            //                  und
            //
            //b) Pr�fung, ob die Farbe, mit der in diesem Schritt gef�rbt werden soll (hier ist es die seltenste Farbe) nicht dieselbe, wie die, eines angrenzenden Knotens ist
            //
            //mit true abgeschlossen wurden.

            {
                // rekursiver Aufruf, es wird das komplett fertig gef�rbte F�rbungs-Array (von dem wir uns aufgrund der beiden zuvor gepr�ften Bedingungen sicher sein k�nnen,
                // dass es eine korrekte F�rbungs-M�glichkeit darstellt) zur�ckgegeben.


                return (

                        // Gleicher Code wie in der if-Bedingung (erste Aussage der &&-Verkn�pfung, nur ohne equals), daher nur kompakter geschrieben und unkommentiert.
                        // Um uns an das Prinzip der Funktionalen Programmierung, innerhalb der Funktionen keine Variablen au�er den �bergabeparametern zu nutzen,
                        // m�ssen wir die Funktion faerbeNaechstenKnoten zweimal aufrufen einmal zuvor zum �berpr�fen und nun zur tats�chlichen Berechnung und
                        // anschlie�enden R�ckgabe. Da pro Knoten nur ein zus�tzlicher Aufruf von faerbeNaechstenKnoten erforderlich ist, l�uft die zus�tzliche Laufzeit, die f�r
                        // gr��ere und und strukturell schwieriger zu f�rbende Graphen ben�tig wird im im unendlichen gegen 0.
                        faerbeNaechstenKnoten.apply(IntStream.concat(IntStream.concat(Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0), IntStream.of(nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 2, IntStream.of().toArray()))), Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe == 0).skip(1)).toArray(), einGraph)
                );

            }

            if (//Test, ob die F�rbung mit der 3.-seltensten Farbe funktioniert

                // die "Hilsfunktion" pruefeAktuelleFarbe liefert einen Boolschen Wert, der true ist, wenn es f�r
                // den aktuell gef�rbeten Knoten keinen angrenzenden Knoten gibt, der die Farbe hat, mit welcher der aktuell gef�rbte
                // Knoten gerdae gef�rbt wird (hier die seltenste Farbe).

                    pruefeAktuelleFarbe.apply(
                            //Es werden als Input ben�tigt: (a), b), c))
                            // a) das kompletter F�rbungs-Array mit der Farbe, auf die gerade getestet wir

                            //Bildlich gesprochen (nat�rlich nicht korrekt, da hier mit Streams operiert wird):
                            //aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht f�r den Knoten, der gerade gef�rbt wird)
                            //wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                            IntStream.concat(
                                    // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                    IntStream.concat(
                                            // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gef�rbt wurden
                                            Arrays.stream(
                                                    aktuelleFaerbung).
                                                    filter(farbe -> farbe != 0),
                                            // aus der am seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                            IntStream.of(
                                                    // berechnen der seltensten (da der 2 Parameter gleich 1 ist) Farbe, die bisher genutzt wird
                                                    nSeltensteFarbeInFaerbung.apply(
                                                            aktuelleFaerbung, 3, IntStream.of().toArray()))),
                                    // Es wird ein Stream aus 0en erzeugt, wobei jede 0 f�r einen noch nicht gef�rbten Knoten steht (ausgenommen des Knotens der aktuell gef�rbt werden soll)
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe == 0)
                                            .skip(1))
                                    .toArray(),


                            // b) Die Verbindungen des aktuell zu f�rbenden Knotens zu den Knoten, die bereits gef�rbt sind
                            Arrays.stream(einGraph)
                                    //Hier liegt noch ein 2D-Array vor, daher werden alle Arrays, die im Array Graph vor dem, den aktuellen Knoten betreffenden Array, und nach diesem kommen
                                    // im Stream �bersprungen bzw. durch die Festlegung eines Limits abgeschnitten
                                    .skip(aktuellerKnoten
                                            .apply(aktuelleFaerbung))
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .flatMapToInt(x -> Arrays.stream(x))
                                    //jetzt werden die Informationen �ber die noch nicht gef�rbten Knoten und den Knoten selbst entfernt. Dies ist wichtig f�r die Abbruchbedingung von pruefeAktuelleFarbe
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .toArray(),

                            // c) Die Farbe, in der der aktuelle Knoten gerade gef�rbt wird
                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung,3, IntStream.of().toArray()))

                            &&

                            //Es wird rekursiv das F�rbungs-Array angefordert, das sich ergibt, wenn man den gesamten Graphen auf Basis der F�rbung des aktuellen
                            // Knotens mit der am n-seltensten verwendeten Farbe durchf�rbt und aus diesem wird ein Stream gemacht
                            Arrays.stream(faerbeNaechstenKnoten.apply(

                                    // Bildlich gesprochen (nat�rlich nicht korrekt, da hier mit Streams operiert wird):
                                    // aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht f�r den Knoten, der gerade gef�rbt wird)
                                    // wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                                    IntStream.concat(

                                            // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                            IntStream.concat(

                                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gef�rbt wurden
                                                    Arrays.stream(aktuelleFaerbung)
                                                            .filter(farbe -> farbe != 0),

                                                    // aus der am n-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                                    IntStream.of(
                                                            // berechnen der n-seltensten Farbe, die bisher genutzt wird
                                                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 3, IntStream.of().toArray()))),

                                            // Es wird ein Stream aus 0en erzeugt, wobei jede 0 f�r einen noch nicht gef�rbten Knoten steht (ausgenommen des Knotens der aktuell gef�rbt werden soll), erzeugt
                                            Arrays.stream(aktuelleFaerbung)
                                                    .filter(farbe -> farbe == 0)
                                                    .skip(1))

                                            .toArray(),

                                    einGraph)

                                    //Muss vor folgendem Hintergrund betrachtet werden: kann die F�rbung eines Knotens nicht erfolgreich durchgef�hrt werden,
                                    // wird das F�rbungs-Array, mit dem als �bergabeparameter die Funktion aufgerufen wurde, wieder zur�ckgegeben.
                                    // Die F�rbung war also nur genau dann erfolgreich, wenn das F�rbungs-Array komplett mit Farben gef�llt wurde und nicht zwischenzeitig
                                    //zur�ckgegeben wurde.

                            ).filter(x -> x==0).count()==0

            )



            // return-Anweisung wird nur Ausgef�hrt, wenn
            //
            //a) Pr�fung, ob alle zuk�nftigen Schritte auf Basis des aktuellen F�rbungs-Schritts funktionieren
            //
            //                  und
            //
            //b) Pr�fung, ob die Farbe, mit der in diesem Schritt gef�rbt werden soll (hier ist es die seltenste Farbe) nicht dieselbe, wie die, eines angrenzenden Knotens ist
            //
            //mit true abgeschlossen wurden.

            {
                // rekursiver Aufruf, es wird das komplett fertig gef�rbte F�rbungs-Array (von dem wir uns aufgrund der beiden zuvor gepr�ften Bedingungen sicher sein k�nnen,
                // dass es eine korrekte F�rbungs-M�glichkeit darstellt) zur�ckgegeben.


                return (

                        // Gleicher Code wie in der if-Bedingung (erste Aussage der &&-Verkn�pfung), daher nur kompakter geschrieben und unkommentiert.
                        // Um uns an das Prinzip der Funktionalen Programmierung, innerhalb der Funktionen keine Variablen au�er den �bergabeparametern zu nutzen,
                        // m�ssen wir die Funktion faerbeNaechstenKnoten zweimal aufrufen einmal zuvor zum �berpr�fen und nun zur tats�chlichen Berechnung und
                        // anschlie�enden R�ckgabe. Da pro Knoten nur ein zus�tzlicher Aufruf von faerbeNaechstenKnoten erforderlich ist, l�uft die zus�tzliche Laufzeit, die f�r
                        // gr��ere und und strukturell schwieriger zu f�rbende Graphen ben�tig wird im im unendlichen gegen 0.
                        faerbeNaechstenKnoten.apply(IntStream.concat(IntStream.concat(Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0), IntStream.of(nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 3,IntStream.of().toArray()))), Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe == 0).skip(1)).toArray(), einGraph)
                );

            }

            if (//Test, ob die F�rbung mit der 4.-seltensten Farbe funktioniert

                // die "Hilsfunktion" pruefeAktuelleFarbe liefert einen Boolschen Wert, der true ist, wenn es f�r
                // den aktuell gef�rbeten Knoten keinen angrenzenden Knoten gibt, der die Farbe hat, mit welcher der aktuell gef�rbte
                // Knoten gerdae gef�rbt wird (hier die seltenste Farbe).

                    pruefeAktuelleFarbe.apply(
                            //Es werden als Input ben�tigt: (a), b), c))
                            // a) das kompletter F�rbungs-Array mit der Farbe, auf die gerade getestet wir

                            //Bildlich gesprochen (nat�rlich nicht korrekt, da hier mit Streams operiert wird):
                            //aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht f�r den Knoten, der gerade gef�rbt wird)
                            //wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                            IntStream.concat(
                                    // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                    IntStream.concat(
                                            // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gef�rbt wurden
                                            Arrays.stream(
                                                    aktuelleFaerbung).
                                                    filter(farbe -> farbe != 0),
                                            // aus der am seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                            IntStream.of(
                                                    // berechnen der seltensten (da der 2 Parameter gleich 1 ist) Farbe, die bisher genutzt wird
                                                    nSeltensteFarbeInFaerbung.apply(
                                                            aktuelleFaerbung, 4,IntStream.of().toArray()))),
                                    // Es wird ein Stream aus 0en erzeugt, wobei jede 0 f�r einen noch nicht gef�rbten Knoten steht (ausgenommen des Knotens der aktuell gef�rbt werden soll)
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe == 0)
                                            .skip(1))
                                    .toArray(),


                            // b) Die Verbindungen des aktuell zu f�rbenden Knotens zu den Knoten, die bereits gef�rbt sind
                            Arrays.stream(einGraph)
                                    //Hier liegt noch ein 2D-Array vor, daher werden alle Arrays, die im Array Graph vor dem, den aktuellen Knoten betreffenden Array, und nach diesem kommen
                                    // im Stream �bersprungen bzw. durch die Festlegung eines Limits abgeschnitten
                                    .skip(aktuellerKnoten
                                            .apply(aktuelleFaerbung))
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .flatMapToInt(x -> Arrays.stream(x))
                                    //jetzt werden die Informationen �ber die noch nicht gef�rbten Knoten und den Knoten selbst entfernt. Dies ist wichtig f�r die Abbruchbedingung von pruefeAktuelleFarbe
                                    .limit(aktuellerKnoten.apply(aktuelleFaerbung))
                                    .toArray(),

                            // c) Die Farbe, in der der aktuelle Knoten gerade gef�rbt wird
                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung,4,IntStream.of().toArray()))

                            &&



                            //Es wird rekursiv das F�rbungs-Array angefordert, das sich ergibt, wenn man den gesamten Graphen auf Basis der F�rbung des aktuellen
                            // Knotens mit der am n-seltensten verwendeten Farbe durchf�rbt und aus diesem wird ein Stream gemacht
                            Arrays.stream(faerbeNaechstenKnoten.apply(

                                    // Bildlich gesprochen (nat�rlich nicht korrekt, da hier mit Streams operiert wird):
                                    // aktuelleFaerbung wird (durch Filter) "getrennt", die erste 0 (sie steht f�r den Knoten, der gerade gef�rbt wird)
                                    // wird durch die aktuell am seltensten verwendete Farbe ersetzt, und aktuelleFaerbung wird wieder zusammengesetzt
                                    IntStream.concat(

                                            // Es wird ein Stream, bestehend aus den bisher genutzten Farben, gefolgt von der bisher am seltensten verwendeten Farbe erzeugt
                                            IntStream.concat(

                                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gef�rbt wurden
                                                    Arrays.stream(aktuelleFaerbung)
                                                            .filter(farbe -> farbe != 0),

                                                    // aus der am n-seltensten genutzten Farbe wird ein Stream bestehend aus einem Integer gemacht
                                                    IntStream.of(
                                                            // berechnen der n-seltensten Farbe, die bisher genutzt wird
                                                            nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 4,IntStream.of().toArray()))),

                                            // Es wird ein Stream aus 0en erzeugt, wobei jede 0 f�r einen noch nicht gef�rbten Knoten steht (ausgenommen des Knotens der aktuell gef�rbt werden soll), erzeugt
                                            Arrays.stream(aktuelleFaerbung)
                                                    .filter(farbe -> farbe == 0)
                                                    .skip(1))

                                            .toArray(),

                                    einGraph)

                                    //Muss vor folgendem Hintergrund betrachtet werden: kann die F�rbung eines Knotens nicht erfolgreich durchgef�hrt werden,
                                    // wird das F�rbungs-Array, mit dem als �bergabeparameter die Funktion aufgerufen wurde, wieder zur�ckgegeben.
                                    // Die F�rbung war also nur genau dann erfolgreich, wenn das F�rbungs-Array komplett mit Farben gef�llt wurde und nicht zwischenzeitig
                                    //zur�ckgegeben wurde.

                            ).filter(x -> x==0).count()==0

            )



            // return-Anweisung wird nur Ausgef�hrt, wenn
            //
            //a) Pr�fung, ob alle zuk�nftigen Schritte auf Basis des aktuellen F�rbungs-Schritts funktionieren
            //
            //                  und
            //
            //b) Pr�fung, ob die Farbe, mit der in diesem Schritt gef�rbt werden soll (hier ist es die seltenste Farbe) nicht dieselbe, wie die, eines angrenzenden Knotens ist
            //
            //mit true abgeschlossen wurden.

            {
                // rekursiver Aufruf, es wird das komplett fertig gef�rbte F�rbungs-Array (von dem wir uns aufgrund der beiden zuvor gepr�ften Bedingungen sicher sein k�nnen,
                // dass es eine korrekte F�rbungs-M�glichkeit darstellt) zur�ckgegeben.


                return (

                        // Gleicher Code wie in der if-Bedingung (erste Aussage der &&-Verkn�pfung, daher nur kompakter geschrieben und unkommentiert.
                        // Um uns an das Prinzip der Funktionalen Programmierung, innerhalb der Funktionen keine Variablen au�er den �bergabeparametern zu nutzen,
                        // m�ssen wir die Funktion faerbeNaechstenKnoten zweimal aufrufen einmal zuvor zum �berpr�fen und nun zur tats�chlichen Berechnung und
                        // anschlie�enden R�ckgabe. Da pro Knoten nur ein zus�tzlicher Aufruf von faerbeNaechstenKnoten erforderlich ist, l�uft die zus�tzliche Laufzeit, die f�r
                        // gr��ere und und strukturell schwieriger zu f�rbende Graphen ben�tig wird im im unendlichen gegen 0.
                        faerbeNaechstenKnoten.apply(IntStream.concat(IntStream.concat(Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe != 0), IntStream.of(nSeltensteFarbeInFaerbung.apply(aktuelleFaerbung, 4,IntStream.of().toArray()))), Arrays.stream(aktuelleFaerbung).filter(farbe -> farbe == 0).skip(1)).toArray(), einGraph)
                );

            }

            // F�r den Fall, dass keine F�rbung des Konten mit einer der 4 Farben erfolgreich getestet und dann auch angewendet wurde, wird das F�rungs-Array aus der vorherigen
            // Rekursionsebene zur�ckgegeben. Dazu muss die Farbe des zuletzt gef�rbten Elements "gel�scht" werden, damit in der verhergehenden Reskursionebene direkt weitere F�rbungen getestet
            // werden k�nnen
            return (
                    IntStream.concat(


                            IntStream.concat(

                                    // Erzeugung eines Streams, der nur die Farben beinhaltet, mit denen schon Knoten gef�rbt wurden
                                    Arrays.stream(aktuelleFaerbung)
                                            .filter(farbe -> farbe != 0),


                                    IntStream.of(
                                            //F�ge 0 ein (als ersatz f�r die vorherige Farbe)
                                            0)),

                            // Noch nicht gef�rbte Knoten werden durch 0en dargestellt and zum Stream hinzugef�gt
                            Arrays.stream(aktuelleFaerbung)
                                    .filter(farbe -> farbe == 0)
                                    .skip(1))

                            .toArray());
        };

        return Farben;
    }


}

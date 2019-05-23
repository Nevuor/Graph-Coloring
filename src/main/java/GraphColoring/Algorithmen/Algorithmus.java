package GraphColoring.Algorithmen;

import GraphColoring.Graph;

import java.util.Arrays;

public class Algorithmus {

    public static void Berechnen(boolean funktional, Graph graph, int AnzahlKnoten){

        int[] Farben;

        if (funktional == false){

            Farben = Objektorientiert.Faerben(graph, AnzahlKnoten);

        }else {

            Farben = Funktional.Färben(graph, AnzahlKnoten);

        }
        //Farbe der Knoten wird ausgegeben
        for (int Knoten = 0; Knoten < AnzahlKnoten; Knoten++){
            System.out.println("Knoten " + (Knoten + 1) + " hat die Farbe " + Farben[Knoten]);
        }

        //Hier werden die Farbanzahl ausgegeben; jedoch werden die Werte dort sortiert, daher nachdem die Farbe der Knoten ausgelesen wurde
        System.out.println("Der Graph hat minimal " + AnzahlFarben(Farben) + " Farben.");

    }

    //Auswertung der Anzahl der unterschiedliche Farben im Farben-Array
    private static int AnzahlFarben(int Farben[]){

        int Anzahl = 1;
        //Hier wird der Array sortiert
        Arrays.sort(Farben);
        for (int Zähler = 1; Zähler <= Farben.length - 1; Zähler++){
            //Hier wird der Array nacheinander durchgegangen, und wenn sich die einzelnen Werte ändern, wird der Anzahlzähler der Farben erhöht
            if (Farben[Zähler] != Farben[Zähler - 1]) {
                Anzahl++;
            }
        }
        //Anzahl der Farben zurückgegeben
        return Anzahl;
    }
/*
    public static void main(String[] args) {

        int AnzahlKnoten = 5;

        Graph graph = new Graph(AnzahlKnoten);

        graph.VerbindungenHinzufügen(0, 1);
        graph.VerbindungenHinzufügen(0, 2);
        graph.VerbindungenHinzufügen(1, 2);
        graph.VerbindungenHinzufügen(1, 3);
        graph.VerbindungenHinzufügen(2, 3);
        graph.VerbindungenHinzufügen(3, 4);
        graph.VerbindungenHinzufügen(0, 3);
        graph.VerbindungenHinzufügen(0, 4);

        Berechnen(false, graph, AnzahlKnoten);

    }

 */

}

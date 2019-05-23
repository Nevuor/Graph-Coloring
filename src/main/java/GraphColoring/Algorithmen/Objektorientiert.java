package GraphColoring.Algorithmen;

import GraphColoring.Graph;

public class Objektorientiert {

    public static int[] Faerben(Graph graph, int AnzahlKnoten){

        //Erstellt einen Array mit den Farben der Knoten (Bsp. : Farben[0] = Farbe des erstem Knotens
        int[] Farben = new int[AnzahlKnoten];
        //In dieser Methode werden die Knoten "gefärbt"
        FaerbungsUtil(graph, Farben, 0);

        return Farben;
    }

    private static boolean FaerbungsUtil(Graph graph, int[] Farben, int Knoten){

        //Falls man am Ende des Graphen angekommen ist, hat man alle Knoten gefärbt; Hier jedoch nicht Knoten + 1, da der letzte Knoten immer noch mit seinen Nachbarn verglichen werden muss.
        if (Knoten == graph.getAnzahlKnoten()){
            return true;
        } else {
            //Farbvorschlag, Falls er nicht verarbeitet werden konnte, wird ein neuer gegeben
            //Um es effizienter zu machen kann der Farbvorschlag nur den maximal den Knotenwert erreichen, da man nicht mehr Farben erreichen kann als man Knoten hat. (da Array bei 0 beginnt, hier: Knoten + 1)
            for (int Farbvorschlag = 1; Farbvorschlag <= Knoten + 1; Farbvorschlag++){
                //Hier wird überprüft ob Nachbarn des Knoten die gleiche Farbe haben
                if (Sicher(graph, Farben, Knoten, Farbvorschlag)){
                    //Falls eine mögliche Farbe gefunden wurde wird diese zunächst eingetragen
                    Farben[Knoten] = Farbvorschlag;
                    //Hier werden dann dem nächsten Knoten ein Vorschlag gemacht und dieser mit den Farben seinen Nachbarn verglichen;
                    //Dies hier ist ein rekursiver Call, somit geht dies so weiter mit dem nächsten Knoten (da Knoten + 1), bis der Letzte Knoten erreicht ist (siehe IF Bedingung).
                    if (FaerbungsUtil(graph, Farben, Knoten + 1)){
                        //Falls der Letzte Knoten seine Farbe gefunden hat werden alle Aufrufe nacheinander wahr und man beendet den rekursiven Call
                        return true;
                    } else {
                        Farben[Knoten] = 0;
                    }
                }
            }
            return false;
        }
    }

    //Hier werden zwei Nachbarknoten nach gleicher Farben untersucht
    private static boolean Sicher(Graph graph, int[] Farben, int Knoten1, int Farbvorschlag){

        for (int Knoten2 = 0; Knoten2 < graph.getAnzahlKnoten(); Knoten2++){
            //Hier wird überprüft, ob zwei Knoten Nachbarn sind, und wenn ja ob Sie auch die gleiche Farbe haben
            if (graph.sindVerbunden(Knoten1, Knoten2) && Farbvorschlag == Farben[Knoten2]){
                //Falls Sie die gleiche Farbe haben ist dies ein Problem  und es muss in der Util nach einen neuen Farbe gesucht werden
                return false;
            }
        }
        //Falls es keine Gleichheit der Farben der Nachbarn gibt, kann diese Farbe angenommen werden
        return true;
    }



}
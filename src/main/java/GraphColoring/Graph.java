package GraphColoring;

public class Graph {

    int AnzahlKnoten;
    int[][] Verbindungen;

    public Graph(int AnzahlKnoten) {
        this.AnzahlKnoten = AnzahlKnoten;
        Verbindungen = new int[AnzahlKnoten][AnzahlKnoten];
    }

    public void VerbindungenHinzufügen(int Knoten1, int Knoten2){

        Verbindungen[Knoten1][Knoten2] = 1;
        Verbindungen[Knoten2][Knoten1] = 1;
    }

    public int getAnzahlKnoten() {

        return AnzahlKnoten;
    }

    public int sindVerbunden(int Knoten1, int Knoten2){

        return Verbindungen[Knoten1][Knoten2];
    }

    public int[][] getGraphArray() {
        return Verbindungen;
    }

}

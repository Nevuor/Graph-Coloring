package GraphColoring;

public class Graph {

    int AnzahlKnoten;
    boolean[][] Verbindungen;

    public Graph(int AnzahlKnoten) {
        this.AnzahlKnoten = AnzahlKnoten;
        Verbindungen = new boolean[AnzahlKnoten][AnzahlKnoten];
    }

    public void VerbindungenHinzufügen(int Knoten1, int Knoten2){

        Verbindungen[Knoten1][Knoten2] = true;
        Verbindungen[Knoten2][Knoten1] = true;
    }

    public int getAnzahlKnoten() {

        return AnzahlKnoten;
    }

    public boolean sindVerbunden(int Knoten1, int Knoten2){

        return Verbindungen[Knoten1][Knoten2];
    }

    public boolean[][] getGraphArray() {
        return Verbindungen;
    }

}

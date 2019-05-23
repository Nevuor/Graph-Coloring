package GraphColoring;

public class Graph {

    private int AnzahlKnoten;
    static Object[][] ObjectArray;
    static Boolean[][] BooleanArray;

    public Graph(int AnzahlKnoten) {
        this.AnzahlKnoten = AnzahlKnoten;
        ObjectArray = new Object[AnzahlKnoten][AnzahlKnoten];
        BooleanArray = new Boolean[AnzahlKnoten][AnzahlKnoten];
    }

    public int getAnzahlKnoten() {
        return AnzahlKnoten;
    }

    public boolean sindVerbunden(int Knoten1, int Knoten2){

        return BooleanArray[Knoten1][Knoten2];
    }

    public static Object[][] getGraphObjectArray() {
        return ObjectArray;
    }

    public static Boolean[][] getGraphBooleanArray() {
        return BooleanArray;
    }

}

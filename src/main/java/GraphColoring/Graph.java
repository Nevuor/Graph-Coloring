package GraphColoring;

public class Graph {

    private int AnzahlKnoten;
    private static Object[][] ObjectArray;
    private static int[][] intArray;

    public Graph(int AnzahlKnoten) {
        this.AnzahlKnoten = AnzahlKnoten;
        ObjectArray = new Object[AnzahlKnoten][AnzahlKnoten];
        intArray = new int[AnzahlKnoten][AnzahlKnoten];
    }

    public int getAnzahlKnoten() {
        return AnzahlKnoten;
    }

    public boolean sindVerbunden(int Knoten1, int Knoten2){
        return ObjectArray[Knoten1][Knoten2].equals("1");
    }

    public static Object[][] getGraphObjectArray() {
        return ObjectArray;
    }

    public static int[][] getIntArray(){ return intArray; }

}

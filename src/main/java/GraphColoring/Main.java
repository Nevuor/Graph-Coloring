package GraphColoring;

import GraphColoring.UI.landingWindow;

import java.text.ParseException;

public class Main {

    public static landingWindow frame1;

    public static landingWindow frame;

    public static void main(String[] args) {
        frame = null;
        try {
            frame = new landingWindow();
            frame.setVisible(true);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}

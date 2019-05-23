package GraphColoring.UI;

import GraphColoring.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class Startfenster extends JFrame{

    private static int AnzahlKnoten;
    private static int Zufallszahl;

    public Startfenster(){

        //Deklarierung
        super("Graph-Coloring Algorithmus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 150);

        JPanel pane = new JPanel();
        pane.setBorder(new EmptyBorder(5, 5, 5, 5));
        pane.setLayout(new BorderLayout(0, 0));
        setContentPane(pane);
        SpinnerModel Anzahl = new SpinnerNumberModel(AnzahlKnoten, 0, 100, 1);

        JPanel north = new JPanel();
        JPanel center = new JPanel();
        JPanel south = new JPanel();

        //Befüllung Pane
        pane.add(north, BorderLayout.NORTH);
        //Befüllung north
        JLabel Text = new JLabel("Wählen Sie die Anzahl der AnzahlKnoten des Graphen");
        north.add(Text);

        pane.add(center, BorderLayout.CENTER);
        //Befüllung center
        final JSpinner Knoten = new JSpinner(Anzahl);
        center.add(Knoten);
        JLabel oder = new JLabel("oder");
        center.add(oder);
        JButton zufaellig = new JButton("zufällig");
        center.add(zufaellig);

        pane.add(south, BorderLayout.SOUTH);
        //Befüllung south
        JButton erzeugen = new JButton("erzeugen");
        south.add(erzeugen);

        //ActionListener
        //Erstellung von "zufälligen" Zahlen
        zufaellig.addActionListener(e -> {
            Random random = new Random();
            Zufallszahl = random.nextInt(21);
            Knoten.setValue(Zufallszahl);
        });

        //Überleitung auf da nächste Fenster
        erzeugen.addActionListener(e -> {
            AnzahlKnoten = (Integer)Knoten.getValue();
            Hauptfenster hauptfenster = new Hauptfenster();
            hauptfenster.setVisible(true);
            Main.startfenster.dispose();
        });
    }

    static int getAnzahlKnoten(){

        return AnzahlKnoten;
    }
}

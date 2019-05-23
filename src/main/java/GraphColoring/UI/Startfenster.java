package GraphColoring.UI;

import GraphColoring.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Startfenster extends JFrame{

    private static int AnzahlKnoten;

    static int getAnzahlKnoten() {
        return AnzahlKnoten;
    }

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

        JLabel Text = new JLabel("Wählen Sie die Anzahl der AnzahlKnoten des Graphen");
        JLabel oder = new JLabel("oder");
        JButton zufaellig = new JButton("zufällig");
        JButton erzeugen = new JButton("erzeugen");
        final JSpinner Knoten = new JSpinner(Anzahl);

        //Befüllung Pane
        pane.add(north, BorderLayout.NORTH);

        north.add(Text);

        pane.add(center, BorderLayout.CENTER);

        center.add(Knoten);
        center.add(oder);
        center.add(zufaellig);

        pane.add(south, BorderLayout.SOUTH);

        south.add(erzeugen);

        //ActionListener
        //Erstellung von "zufälligen" Zahlen
        zufaellig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                AnzahlKnoten = random.nextInt(21);
                Knoten.setValue(AnzahlKnoten);
            }
        });

        //Überleitung auf da nächste Fenster
        erzeugen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Hauptfenster hauptfenster = new Hauptfenster();
                hauptfenster.setVisible(true);
                Main.startfenster.dispose();
            }
        });

    }

}

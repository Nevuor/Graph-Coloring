package GraphColoring.UI;

import GraphColoring.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        JLabel Text = new JLabel("W\u00e4hlen Sie die Anzahl der Knoten des Graphen");
        north.add(Text);

        pane.add(center, BorderLayout.CENTER);
        //Befüllung center
        NumberFormat nmf = new DecimalFormat("000");
        NumberFormatter nf = new NumberFormatter(nmf);
        nf.setAllowsInvalid(false);
        nf.setOverwriteMode(true);
        JFormattedTextField KnotenAnzahlFeld = new JFormattedTextField(nf);
        KnotenAnzahlFeld.setColumns(3);
        center.add(KnotenAnzahlFeld);
        JLabel oder = new JLabel("oder");
        center.add(oder);
        JButton zufaellig = new JButton("zuf\u00e4llig");
        center.add(zufaellig);

        pane.add(south, BorderLayout.SOUTH);
        //Befüllung south
        JButton erzeugen = new JButton("erzeugen");
        south.add(erzeugen);

        //ActionListener
        //Erstellung von "zufälligen" Zahlen
        zufaellig.addActionListener(e -> {
            Random random = new Random();
            Zufallszahl = random.nextInt(20)+4;
            KnotenAnzahlFeld.setValue(Zufallszahl);
        });

        //Überleitung auf da nächste Fenster
        erzeugen.addActionListener(e -> {
        	AnzahlKnoten = ((Number) KnotenAnzahlFeld.getValue()).intValue();
            if (AnzahlKnoten < 4) {
            	JOptionPane.showMessageDialog(null, "Mit einem Grafen, der nur " + AnzahlKnoten + " Knoten hat macht das ganze doch gar keinen Spa�! ", "Achtung", JOptionPane.INFORMATION_MESSAGE);
            }else {
            Hauptfenster hauptfenster = new Hauptfenster();
            hauptfenster.setVisible(true);
            Main.startfenster.dispose();
            }
        });
    }

    static int getAnzahlKnoten(){

        return AnzahlKnoten;
    }
}

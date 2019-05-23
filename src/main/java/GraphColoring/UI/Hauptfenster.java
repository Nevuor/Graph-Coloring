package GraphColoring.UI;

import GraphColoring.Algorithmen.Objektorientiert;
import GraphColoring.Faerbungsmoeglichkeit;
import GraphColoring.Graph;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Hauptfenster extends JFrame {

    private JTable table;
    private int AnzahlKnoten = Startfenster.getAnzahlKnoten();
    private Graph graph = new Graph(AnzahlKnoten);

    public Hauptfenster() {

        //Deklarierung
        super("Graph-Coloring Algorithmus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        setContentPane(contentPane);

        JPanel mainPanel = new JPanel();
        JPanel Ergebnis = new JPanel();
        JPanel Farben = new JPanel();
        JPanel Zwischehalter1 = new JPanel();
        JPanel Zwischehalter2 = new JPanel();
        JPanel TabellenPanel = new JPanel();

        //Pane
        contentPane.add(mainPanel);
        //mainPanel
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        //TabellenPanel
        mainPanel.add(TabellenPanel);
            TabellenPanel.setLayout(new BorderLayout(0, 0));

                DefaultTableModel tableModel = new DefaultTableModel(Graph.getGraphObjectArray(), AnzahlKnoten);

                    table = new JTable(tableModel);
                TabellenPanel.add(table, BorderLayout.CENTER);

                    DefaultTableModel tableModelWest = new DefaultTableModel(AnzahlKnoten, 1);
        JTable tableWest = new JTable(tableModelWest);
                    tableWest.setBorder(new LineBorder(new Color(0, 0, 0)));
                TabellenPanel.add(tableWest, BorderLayout.WEST);

                    DefaultTableModel tableModelNorth = new DefaultTableModel(1, AnzahlKnoten + 1);
        JTable tableNorth = new JTable(tableModelNorth);
                    tableNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
                TabellenPanel.add(tableNorth, BorderLayout.NORTH);

                TableColumn column;
                for (int i = 0; i < AnzahlKnoten; i++) {
                    column = table.getColumnModel().getColumn(i);
                    column.setPreferredWidth(20);
                    tableNorth.setValueAt(i + 1, 0, i + 1);
                    tableWest.setValueAt(i + 1, i, 0);
                }
                for (int i = 0; i < AnzahlKnoten + 1; i++) {
                    column = tableNorth.getColumnModel().getColumn(i);
                    column.setPreferredWidth(20);
                }

                column = tableWest.getColumnModel().getColumn(0);
                column.setPreferredWidth(20);

        //ErgebnisPanel
        mainPanel.add(Ergebnis);
            Ergebnis.setLayout(new GridLayout(5, 0, 0, 0));

                JButton BerechneObjektorientiert = new JButton("Objektorientiert berechnen");
            Ergebnis.add(BerechneObjektorientiert);

            Ergebnis.add(Zwischehalter1);

                JButton BerechneFunktional = new JButton("Funktional berechnen");
            Ergebnis.add(BerechneFunktional);

            Ergebnis.add(Zwischehalter2);

                JLabel genutzteFarben = new JLabel("genutzte Farben:");
                Farben.add(genutzteFarben);
                JLabel lblNewLabel = new JLabel("-"); //TODO Anzahl Farben nach Berechnung anzeigen lassen
                Farben.add(lblNewLabel);
            Ergebnis.add(Farben);

        //ActionListener
        BerechneObjektorientiert.addActionListener(arg0 -> {

            table.setEnabled(false); //TODO

            //TODO Tabellenextrator in Methode packen, da Funktional auch benötigt

            for (int i = 0; i < AnzahlKnoten; i++) {
                for (int a = 0; a < AnzahlKnoten; a++) {
                    if (table.getValueAt(i, a) == null) table.setValueAt("0", i, a);
                }
            }

            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            for (int i = 0; i < AnzahlKnoten; i++)
                for (int j = 0; j < AnzahlKnoten; j++)
                    Graph.getGraphObjectArray()[i][j] = dtm.getValueAt(i, j);
/*
            // Übertragung des Inhalts des Object Array in das Boolean Array

            for (int i = 0; i < AnzahlKnoten; i++) {
                for (int a = 0; a < AnzahlKnoten; a++) {
                    Graph.getGraphBooleanArray()[i][a] = table.getValueAt(i, a).equals("1");
                }
            }
 */

            System.out.println(Arrays.deepToString(Graph.getGraphObjectArray()));
            //System.out.println(Arrays.deepToString(Graph.getGraphBooleanArray()));

            // Färbung
            Faerbungsmoeglichkeit faerbungsmoeglichkeit = new Faerbungsmoeglichkeit();
            faerbungsmoeglichkeit.Knotenfarben = Objektorientiert.Faerben(graph, AnzahlKnoten);
            System.out.println(Arrays.toString(faerbungsmoeglichkeit.Knotenfarben));

            try {
                Ergebnis frame = new Ergebnis(Graph.getGraphObjectArray(), faerbungsmoeglichkeit.Knotenfarben);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pack();
    }

}
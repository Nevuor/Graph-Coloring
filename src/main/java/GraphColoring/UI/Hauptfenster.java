package GraphColoring.UI;

import GraphColoring.Algorithmen.Objektorientiert;
import GraphColoring.Färbungsmöglichkeit;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hauptfenster extends JFrame {

    private JTable table;
    private JTable tableNorth;
    private JTable tableWest;
    private int AnzahlKnoten = Startfenster.getAnzahlKnoten();
    public Graph graph = new Graph(AnzahlKnoten);

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
        JPanel panel = new JPanel();
        JPanel panel_2 = new JPanel();

        //Befüllung Pane
        contentPane.add(mainPanel);
        //Befüllung mainPane
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


        JButton BerechneObjektorientiert = new JButton("Objektorientiert berechnen");
        JButton BerechneFunktional = new JButton("Funktional berechnen");


        Ergebnis.add(BerechneObjektorientiert);

        Ergebnis.add(panel);
        ((FlowLayout) panel.getLayout()).setHgap(2);
        ((FlowLayout) panel.getLayout()).setVgap(2);

        Ergebnis.add(BerechneFunktional);


        FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
        flowLayout_1.setHgap(2);
        flowLayout_1.setVgap(2);
        Ergebnis.add(panel_2);

        Ergebnis.add(Farben);

        JLabel genutzteFarben = new JLabel("genutzte Farben:");
        Farben.add(genutzteFarben);

        JLabel lblNewLabel = new JLabel("-");
        Farben.add(lblNewLabel);


        //Object Array[][] = new Object[main.frame.KnotenAnzahl][main.frame.KnotenAnzahl];

        //Graph = new Graph(main.landingWindow.KnotenAnzahl);

        DefaultTableModel tableModel = new DefaultTableModel(graph.getGraphObjectArray(), AnzahlKnoten);

        table = new JTable(tableModel);


        DefaultTableModel tableModelWest = new DefaultTableModel(AnzahlKnoten, 1);
        DefaultTableModel tableModelNorth = new DefaultTableModel(1, AnzahlKnoten + 1);

        tableWest = new JTable(tableModelWest);
        tableWest.setBorder(new LineBorder(new Color(0, 0, 0)));
        tableNorth = new JTable(tableModelNorth);
        tableNorth.setBorder(new LineBorder(new Color(0, 0, 0)));

        TableColumn column = null;
        for (int i = 0; i < AnzahlKnoten; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(20);
            tableNorth.setValueAt(i, 0, i + 1);
            tableWest.setValueAt(i, i, 0);
        }
        for (int i = 0; i < AnzahlKnoten + 1; i++) {
            column = tableNorth.getColumnModel().getColumn(i);
            column.setPreferredWidth(20);

        }


        column = tableWest.getColumnModel().getColumn(0);
        column.setPreferredWidth(20);

        JPanel TabellenPanel = new JPanel();
        mainPanel.add(TabellenPanel);
        TabellenPanel.setLayout(new BorderLayout(0, 0));

        TabellenPanel.add(table, BorderLayout.CENTER);
        TabellenPanel.add(tableWest, BorderLayout.WEST);
        TabellenPanel.add(tableNorth, BorderLayout.NORTH);


        mainPanel.add(Ergebnis);
        Ergebnis.setLayout(new GridLayout(5, 0, 0, 0));

        BerechneObjektorientiert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {


                table.setEnabled(false); //TODO

                for (int i = 0; i < AnzahlKnoten; i++) {
                    for (int a = 0; a < AnzahlKnoten; a++) {
                        if (table.getValueAt(i, a) == null) table.setValueAt("0", i, a);
                    }
                }

                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                for (int i = 0; i < AnzahlKnoten; i++)
                    for (int j = 0; j < AnzahlKnoten; j++)
                        Graph.getGraphObjectArray()[i][j] = dtm.getValueAt(i, j);

                // Übertragung des Inhalts des Object Array in das Boolean Array

                for (int i = 0; i < AnzahlKnoten; i++) {
                    for (int a = 0; a < AnzahlKnoten; a++) {
                        if (table.getValueAt(i, a).equals("1")) {
                            Graph.getGraphBooleanArray()[i][a] = true;
                        } else {
                            Graph.getGraphBooleanArray()[i][a] = false;
                        }
                    }
                }



                System.out.println(Arrays.deepToString(Graph.getGraphObjectArray()));
                //System.out.println(Arrays.deepToString(Graph.getGraphBooleanArray()));


                // Färbung

                Färbungsmöglichkeit Färbungsmöglichkeit = new Färbungsmöglichkeit();
                Objektorientiert ObjektorientierteFärbung = new Objektorientiert();
                Färbungsmöglichkeit.Knotenfarben = ObjektorientierteFärbung.Färben(graph, AnzahlKnoten);
                System.out.println(Arrays.toString(Färbungsmöglichkeit.Knotenfarben));


                try {
                    Ergebnis frame = new Ergebnis(Graph.getGraphObjectArray(), Färbungsmöglichkeit.Knotenfarben);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });


        pack();
    }
}
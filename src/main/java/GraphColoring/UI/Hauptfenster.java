package GraphColoring.UI;

import GraphColoring.Graph;
import GraphColoring.Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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

    private JPanel contentPane;
    private JTable table;
    private JTable tableNorth;
    private JTable tableWest;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Hauptfenster frame = new Hauptfenster();
                    frame.setVisible(true);
                    Main.frame.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Hauptfenster() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1);

        setTitle("Graphenfärbung");
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        //Object Array[][] = new Object[main.frame.KnotenAnzahl][main.frame.KnotenAnzahl];

        final Graph Graph = new Graph(Main.frame.KnotenAnzahl);



        DefaultTableModel tableModel = new DefaultTableModel(Graph.getGraphArray(), Main.frame.KnotenAnzahl);

        table = new JTable(tableModel);



        DefaultTableModel tableModelWest = new DefaultTableModel(Main.frame.KnotenAnzahl, 1);
        DefaultTableModel tableModelNorth = new DefaultTableModel(1, Main.frame.KnotenAnzahl + 1);

        tableWest = new JTable(tableModelWest);
        tableWest.setBorder(new LineBorder(new Color(0, 0, 0)));
        tableNorth = new JTable(tableModelNorth);
        tableNorth.setBorder(new LineBorder(new Color(0, 0, 0)));

        TableColumn column = null;
        for (int i = 0; i < Main.frame.KnotenAnzahl; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(20);
        }
        for (int i = 0; i < Main.frame.KnotenAnzahl +1; i++) {
            column = tableNorth.getColumnModel().getColumn(i);
            column.setPreferredWidth(20);
        }
        column = tableWest.getColumnModel().getColumn(0);
        column.setPreferredWidth(20);

        JPanel TabellenPanel = new JPanel();
        panel_1.add(TabellenPanel);
        TabellenPanel.setLayout(new BorderLayout(0, 0));

        TabellenPanel.add(table, BorderLayout.CENTER);
        TabellenPanel.add(tableWest, BorderLayout.WEST);
        TabellenPanel.add(tableNorth, BorderLayout.NORTH);

        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3);
        panel_3.setLayout(new GridLayout(5, 0, 0, 0));

        JButton btnBerechneMglicheFrbungen = new JButton("Berechne mögliche Färbungen");
        btnBerechneMglicheFrbungen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                for (int i = 0; i < Main.frame.KnotenAnzahl; i++) {
                    for(int a = 0; a < Main.frame.KnotenAnzahl; a++) {
                        if(table.getValueAt(i, a) == null) table.setValueAt("0", i, a);
                    }
                }

                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                for (int i = 0 ; i < Main.frame.KnotenAnzahl ; i++)
                    for (int j = 0 ; j < Main.frame.KnotenAnzahl ; j++)
                        //Graph.getGraphArray()[i][j] = dtm.getValueAt(i,j);


                System.out.println(Arrays.deepToString(Graph.getGraphArray()));
                //System.out.println(Array[0][0]);
            }

        });
        panel_3.add(btnBerechneMglicheFrbungen);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setVgap(2);
        flowLayout.setHgap(2);
        panel_3.add(panel);

        JButton button = new JButton("Berechne mögliche Färbungen");
        panel_3.add(button);

        JPanel panel_2 = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
        flowLayout_1.setHgap(2);
        flowLayout_1.setVgap(2);
        panel_3.add(panel_2);

        JPanel panel_7 = new JPanel();
        panel_3.add(panel_7);

        JLabel lblGenutzteFarben = new JLabel("genutzte Farben:");
        panel_7.add(lblGenutzteFarben);

        JLabel lblNewLabel = new JLabel("-");
        panel_7.add(lblNewLabel);

        pack();
    }

}
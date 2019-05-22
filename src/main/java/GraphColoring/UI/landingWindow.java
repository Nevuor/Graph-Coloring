package GraphColoring.UI;

import GraphColoring.Graph;

import java.awt.BorderLayout;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class landingWindow extends JFrame {

    private JPanel contentPane;
    public int KnotenAnzahl;
    public ArrayList[][] kanten;
    public Graph UsedGraph;


    public landingWindow() throws ParseException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 318, 149);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblWhlenSieDie = new JLabel("Wählen Sie die Anzahl der Knoten des Graphen");
        panel.add(lblWhlenSieDie);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.CENTER);

        NumberFormat nmf = new DecimalFormat("0");
        NumberFormatter nf = new NumberFormatter(nmf);
        nf.setAllowsInvalid(false);
        nf.setOverwriteMode(true);

        final JFormattedTextField KnotenAnzahlFeld = new JFormattedTextField(nf);
        //breite des Textfeldes setzen
        KnotenAnzahlFeld.setColumns(3);
        panel_1.add(KnotenAnzahlFeld);

        JLabel lblOder = new JLabel("oder");
        panel_1.add(lblOder);

        JButton btnZufllig = new JButton("zufällig");
        btnZufllig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                // Zufallszahl erzeugen und in TextField schreiben

                Random rand = new Random();
                int n = rand.nextInt(18);
                KnotenAnzahlFeld.setText(Integer.toString(n+4));

            }
        });
        panel_1.add(btnZufllig);

        JPanel panel_3 = new JPanel();
        contentPane.add(panel_3, BorderLayout.SOUTH);

        JButton btnErzeugen = new JButton("erzeugen");
        btnErzeugen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //erzeugen Button ActionListener


                //Auslesen des Feldes KnotenAnzahlFeld und Cast um es in Integer Variable zu speichern
                String TextFieldInhalt = KnotenAnzahlFeld.getText();
                TextFieldInhalt = TextFieldInhalt.replaceAll("\\s+","");

                if (TextFieldInhalt.isBlank()) return;

                KnotenAnzahl = Integer.parseInt(TextFieldInhalt);

                UsedGraph = new Graph(KnotenAnzahl);

                //�ffnet Hauptfenster durch aufruf der main Methode
                Hauptfenster.main(null);


            }
        });
        panel_3.add(btnErzeugen);
    }

    public Graph getUsedGraph() {
        return UsedGraph;
    }
}

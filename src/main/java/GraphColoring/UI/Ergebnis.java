package GraphColoring.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Arrays;

class Ergebnis extends JFrame {

	Ergebnis(final Object[][] ObjectArray, int[] knotenfarben) {
		setTitle("Ergebnis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		int anzahlKnoten = Startfenster.getAnzahlKnoten();
		Object[][] ergebnisArray = new Object[anzahlKnoten * 3][anzahlKnoten];

		DefaultTableModel tableModel = new DefaultTableModel(ergebnisArray, anzahlKnoten);

		JTable ergebnistabelle = new JTable(tableModel);

		int j = -3;
		int x = -2;
		int y = -1;
	    for (int i = 0; i < Startfenster.getAnzahlKnoten(); i++) {
	    	j = j + 3; x = x + 3; y = y + 3;
			for(int a = 0; a < anzahlKnoten; a++) {
				ergebnistabelle.setValueAt(ObjectArray[i][a], a, j);
				if(ObjectArray[i][a].equals("1") && a != i) {
				ergebnistabelle.setValueAt(knotenfarben[a], a, x);
				ergebnistabelle.setValueAt(knotenfarben[i], a, y);
				}
				}
			}
		
		DefaultTableModel tableModelWest = new DefaultTableModel(anzahlKnoten, 1);
		DefaultTableModel tableModelNorth = new DefaultTableModel(1, anzahlKnoten + 1);

		JTable tabelleWest = new JTable(tableModelWest);
		tabelleWest.setBorder(new LineBorder(new Color(0, 0, 0)));
		JTable tabelleNorth = new JTable(tableModelNorth);
		tabelleNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
			
		TableColumn column;
		TableColumn columnNorth;
		TableColumn columnWest;
		for (int i = 0; i < anzahlKnoten * 3; i++) {
		    column = ergebnistabelle.getColumnModel().getColumn(i);
		    column.setMaxWidth(15);
		}
		for (int i = 0; i < anzahlKnoten; i++) {
		    tabelleWest.setValueAt(i, i, 0);
		}
		for (int i = 0; i < anzahlKnoten +1; i++) {
		    columnNorth = tabelleNorth.getColumnModel().getColumn(i);
		    columnNorth.setMaxWidth(45);
		    columnNorth.setPreferredWidth(45);
		}
		for (int i = 0; i < anzahlKnoten; i++) {
		    tabelleNorth.setValueAt(i, 0, i+1);
		}
		
		
		columnWest = tabelleWest.getColumnModel().getColumn(0);
	    columnWest.setMaxWidth(45);
	    columnWest.setPreferredWidth(45);		
		contentPane.add(ergebnistabelle, BorderLayout.CENTER);
		contentPane.add(tabelleNorth, BorderLayout.NORTH);
		contentPane.add(tabelleWest, BorderLayout.WEST);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		
		 int groessteZahlImArray = 1;
		    for (int i = 0; i < knotenfarben.length; i++) {
		        if (knotenfarben[i] > groessteZahlImArray) {
		        	groessteZahlImArray = knotenfarben[i];
		        }
		    }
		JLabel anzahlFarben = new JLabel("Anzahl genutzer Farben:" + groessteZahlImArray);
		contentPane.add(anzahlFarben, BorderLayout.SOUTH);
		
		pack();
	}

}

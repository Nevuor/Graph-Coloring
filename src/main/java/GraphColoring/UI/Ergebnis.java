package GraphColoring.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Arrays;

public class Ergebnis extends JFrame {

	private JPanel contentPane;
	private JTable Ergebnistabelle;
	private JTable TabelleNorth;
	private JTable TabelleWest;
	private Object[][] ErgebnisArray;
	private int AnzahlKnoten = Startfenster.getAnzahlKnoten();
	private JPanel panel;
	private JLabel AnzahlFarben;


	public Ergebnis(final Object[][] ObjectArray, int[] knotenfarben) {
		setTitle("Ergebnis");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ErgebnisArray = new Object[AnzahlKnoten * 3][AnzahlKnoten];

		DefaultTableModel tableModel = new DefaultTableModel(ErgebnisArray, AnzahlKnoten);
		
		Ergebnistabelle = new JTable(tableModel);
		
		class ColorRenderer extends DefaultTableCellRenderer {
			private int i;
			   public ColorRenderer(int i) {
				this.i = i-1;
			}

			public Component getTableCellRendererComponent(
			            JTable table, Object value, boolean isSelected,
			            boolean hasFocus, int row, int column)
			   {
			      
				   if(ObjectArray[0][i].equals("1")) {
						setBackground(Color.GREEN);
					}
					else {
						setBackground(Color.RED);
					}
			  
			      return super.getTableCellRendererComponent(table, value, isSelected,
			                                                 hasFocus, row, column);
			   }
		}
	
		int j = -3;
		int x = -2;
		int y = -1;
	    for (int i = 0; i < Startfenster.getAnzahlKnoten(); i++) {
	    	j = j + 3; x = x + 3; y = y + 3;
			for(int a = 0; a < AnzahlKnoten; a++) {
				Ergebnistabelle.setValueAt(ObjectArray[i][a], a, j);
				if(ObjectArray[i][a].equals("1") && a != i) {
				Ergebnistabelle.setValueAt(knotenfarben[a], a, x);
				Ergebnistabelle.setValueAt(knotenfarben[i], a, y);
				}
				}
			}
		
		DefaultTableModel tableModelWest = new DefaultTableModel(AnzahlKnoten, 1);
		DefaultTableModel tableModelNorth = new DefaultTableModel(1, AnzahlKnoten+ 1);
		
		TabelleWest = new JTable(tableModelWest);
		TabelleWest.setBorder(new LineBorder(new Color(0, 0, 0)));
		TabelleNorth = new JTable(tableModelNorth);
		TabelleNorth.setBorder(new LineBorder(new Color(0, 0, 0)));
			
		TableColumn column = null;
		TableColumn columnNorth = null;
		TableColumn columnWest = null;
		for (int i = 0; i < AnzahlKnoten * 3; i++) {
		    column = Ergebnistabelle.getColumnModel().getColumn(i);
		    column.setMaxWidth(15);
		    
		}
		for (int i = 0; i < AnzahlKnoten; i++) {
		    TabelleWest.setValueAt(i, i, 0);
		}
		for (int i = 0; i < AnzahlKnoten; i++) {
		    columnNorth = TabelleNorth.getColumnModel().getColumn(i);
		    columnNorth.setMaxWidth(45);
		}
		for (int i = 0; i < AnzahlKnoten; i++) {
		    TabelleNorth.setValueAt(i, 0, i+1);
		}
		
		
		columnWest = TabelleWest.getColumnModel().getColumn(0);
	    columnWest.setMaxWidth(45);
	    		
		contentPane.add(Ergebnistabelle, BorderLayout.CENTER);
		contentPane.add(TabelleNorth, BorderLayout.NORTH);
		contentPane.add(TabelleWest, BorderLayout.WEST);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		System.out.println(Arrays.toString(knotenfarben));
		
		 int groessteZahlImArray = 1;
		    for (int i = 0; i < knotenfarben.length; i++) {
		        if (knotenfarben[i] > groessteZahlImArray) {
		        	groessteZahlImArray = knotenfarben[i];
		        }
		    }
		AnzahlFarben = new JLabel("Anzahl genutzer Farben:" + groessteZahlImArray);
		contentPane.add(AnzahlFarben, BorderLayout.SOUTH);
		
		pack();
	}

}

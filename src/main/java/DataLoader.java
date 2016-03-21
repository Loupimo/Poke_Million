import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

public class DataLoader {

	// ################################################
	// ################# Attributs ####################
	// ################################################

	private static final Logger LOGGER = Logger.getLogger(DataLoader.class);
	protected File data;
	protected LinkedList<Pokemon> listePoke;
	private List<String> lignes;

	// ################################################
	// ############### Constructeurs ##################
	// ################################################

	public DataLoader() {
		this.listePoke = new LinkedList<Pokemon>();
	}

	// ################################################
	// ############## Methodes diverses ###############
	// ################################################

	private List<String> getLignesFromFile() throws Exception {

		List<String> lignes = new ArrayList<String>();

		// Permet de lire les caractères spéciaux (exemple: é, è, ♀, ♂)
		// Pour celà il faut que le fichier lu ait été sauvegardé avec
		// l'encodage "Unicode"
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.data), "Unicode"));

		for (String ligne = br.readLine(); ligne != null; ligne = br.readLine()) {

			// Suppression des espaces en trop
			ligne = ligne.trim();

			// Filtre des lignes vides
			if (ligne.isEmpty()) {
				continue;
			}

			// Filtre des lignes de commentaire
			if (ligne.startsWith("#")) {
				continue;
			}

			LOGGER.debug(ligne);
			lignes.add(ligne);
		}

		br.close();
		// fr.close();

		return lignes;
	}

	public void printDataInArray() { //Affiche la liste des pokémons de la base de donnée avec leurs caractéristiques
		
		JFrame f = new JFrame("Pokédex");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setPreferredSize(new Dimension(550, 422));

		String[] entetes = { "Id", "Nom", "Image", "Type" };

		DefaultTableModel model = new DefaultTableModel(null, entetes);

		// Remplit le tableau de données de façon dynamique
		for (Pokemon count : this.listePoke) {
			Vector<Object> donnees = new Vector<Object>();
			donnees.add(count.getId());
			donnees.add(count.getName());
			donnees.add(count.getSprite());
			donnees.add(count.getTypeList());
			model.addRow(donnees);
		}

		JTable tableau = new JTable(model);
		tableau.setFont(new Font("Lucida", 0, 15));

		tableau.getColumnModel().getColumn(3).setCellRenderer(new PokeTypeRenderer());
		tableau.getColumnModel().getColumn(2).setCellRenderer(new ColorCellRenderer());
		tableau.getColumnModel().getColumn(1).setCellRenderer(new ColorCellRenderer());
		tableau.getColumnModel().getColumn(0).setCellRenderer(new ColorCellRenderer());
		
		tableau.getColumnModel().getColumn(0).setMaxWidth(60);
		tableau.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableau.getColumnModel().getColumn(2).setPreferredWidth(60);
		tableau.setRowHeight(60);
		tableau.setModel(model);
		f.getContentPane().add(tableau.getTableHeader(), BorderLayout.NORTH);
		f.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		f.pack();

		f.setVisible(true);
		f.setLocationRelativeTo(null); // Centre la fenêtre

	}

	// ################################################
	// ############# Getters et setters ###############
	// ################################################

	public void init(File file) {
		this.data = file;
		try {
			this.setPokeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setPokeList() throws Exception {
		if (this.data != null) {
			this.lignes = getLignesFromFile();
			this.lignes.remove(0);
			String separator = ";";

			for (String ligne : this.lignes) {
				String[] values = ligne.split(separator);
				LinkedList<String> typeList = new LinkedList<String>();
				String[] tempType = values[3].split(",");
				for (String type : tempType) {
					typeList.add(type);
				}
				this.listePoke.add(new Pokemon(Integer.parseInt(values[0]), values[1], values[2], typeList));
			}
		}
	}

	public void changePokeList(File file)
	{
		this.listePoke.clear();
		this.init(file);
		
	}
	public LinkedList<Pokemon> getPokelist() {
		return this.listePoke;
	}

	// ################################################
	// ################ Private Class #################
	// ################################################

	public class PokeTypeRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
		@SuppressWarnings(value = "unchecked")
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			
			this.setHorizontalAlignment(SwingConstants.CENTER);
			List<String> types = (List<String>) value;

			StringBuilder sb = new StringBuilder();
			boolean first = true;
			for (String type : types) {
				if (!first) {
					sb.append(", ");
				}
				else
				{
					//Définit la couleur de la cellule en fonction du type principale du pokémon
					if (type.equals("Plante")) this.setBackground(new Color (0, 200, 0));
			        else if (type.equals("Glace")) this.setBackground(new Color (0, 200, 255));
			        else if (type.equals("Feu")) this.setBackground(new Color (255, 0, 0));
			        else if (type.equals("Normal")) this.setBackground(new Color (160, 160, 160));
			        else if (type.equals("Insecte")) this.setBackground(new Color (200, 230, 0));
			        else if (type.equals("Poison")) this.setBackground(new Color (175, 75, 180));
			        else if (type.equals("Ténèbres")) this.setBackground(new Color (85, 60, 90));
			        else if (type.equals("Acier")) this.setBackground(new Color (200, 200, 200));
			        else if (type.equals("Vol")) this.setBackground(new Color (190, 220, 255));
			        else if (type.equals("Sol")) this.setBackground(new Color (210, 165, 95));
			        else if (type.equals("Combat")) this.setBackground(new Color (165, 20, 20));
			        else if (type.equals("Roche")) this.setBackground(new Color (220, 200, 150));
			        else if (type.equals("Psy")) this.setBackground(new Color (225, 25, 180));
			        else if (type.equals("Dragon")) this.setBackground(new Color (90, 30, 160));
			        else if (type.equals("Eau")) this.setBackground(new Color (0, 100, 250));
			        else if (type.equals("Electrik")) this.setBackground(new Color (255, 240, 75));
				}
				sb.append(type);
				first = false;
			}
			setText(sb.toString());

			return this;
		}
	}
	
	public class ColorCellRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		@Override
		@SuppressWarnings(value = "unchecked")
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	        
	        //Récupère l'index de la colone des "Type"
	        int size =  table.getColumnCount();
	        int count;
	        for (count = 0; count < size; count ++) if (table.getColumnName(count).equals("Type")) break;
	        
	        LinkedList<String> type = (LinkedList<String>) table.getValueAt(row, count);
	        
	        //Définit la couleur de la cellule en fonction du type principale du pokémon
	        if (type.get(0).equals("Plante")) this.setBackground(new Color (0, 200, 0));
	        else if (type.get(0).equals("Glace")) this.setBackground(new Color (0, 200, 255));
	        else if (type.get(0).equals("Feu")) this.setBackground(new Color (255, 0, 0));
	        else if (type.get(0).equals("Normal")) this.setBackground(new Color (160, 160, 160));
	        else if (type.get(0).equals("Insecte")) this.setBackground(new Color (200, 230, 0));
	        else if (type.get(0).equals("Poison")) this.setBackground(new Color (175, 75, 180));
	        else if (type.get(0).equals("Ténèbres")) this.setBackground(new Color (85, 60, 90));
	        else if (type.get(0).equals("Acier")) this.setBackground(new Color (200, 200, 200));
	        else if (type.get(0).equals("Vol")) this.setBackground(new Color (190, 220, 255));
	        else if (type.get(0).equals("Sol")) this.setBackground(new Color (210, 165, 95));
	        else if (type.get(0).equals("Combat")) this.setBackground(new Color (165, 20, 20));
	        else if (type.get(0).equals("Roche")) this.setBackground(new Color (220, 200, 150));
	        else if (type.get(0).equals("Psy")) this.setBackground(new Color (225, 25, 180));
	        else if (type.get(0).equals("Dragon")) this.setBackground(new Color (90, 30, 160));
	        else if (type.get(0).equals("Eau")) this.setBackground(new Color (0, 100, 250));
	        else if (type.get(0).equals("Electrik")) this.setBackground(new Color (255, 240, 75));
	        
	        //Fait apparaître l'image s'il s'agit de la colone des image
	        if(table.getColumnName(column).equals("Image")) 
        	{
	        	this.setText(null);
        		this.setIcon((ImageIcon)table.getValueAt(row, column));
        	}
        	else this.setValue(value);
	        
        	this.setHorizontalAlignment(SwingConstants.CENTER); //Centre le texte/image de la cellule
			
	        return this;
	    }
	}
}

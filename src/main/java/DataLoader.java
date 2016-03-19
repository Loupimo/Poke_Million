import java.awt.BorderLayout;
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
		f.setPreferredSize(new Dimension(500, 422));

		String[] entetes = { "Id", "Nom", "Image" };

		// Un modèle pour le JTable permettant d'afficher des image dans les
		// cellules
		DefaultTableModel model = new DefaultTableModel(null, entetes) {
			private static final long serialVersionUID = 1L;

			// Permet de définir le type de valeur attendu pour chaque colone
			@Override
			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		};

		// Remplit le tableau de données de façon dynamique
		for (Pokemon count : this.listePoke) {
			Vector<Object> donnees = new Vector<Object>();
			donnees.add(count.getId());
			donnees.add(count.getName());
			donnees.add(count.getSprite());
			model.addRow(donnees);
		}

		JTable tableau = new JTable(model);
		tableau.setFont(new Font("Lucida", 0, 15));
		tableau.setDefaultRenderer(Integer.class, new PokedexRenderer());
		tableau.setDefaultRenderer(String.class, new PokedexRenderer());

		tableau.getColumnModel().getColumn(0).setMaxWidth(60);
		tableau.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableau.getColumnModel().getColumn(2).setPreferredWidth(60);
		tableau.setRowHeight(60);

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
				this.listePoke.add(new Pokemon(Integer.parseInt(values[0]), values[1], values[2]));
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

	private class PokedexRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		private PokedexRenderer() {
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
}

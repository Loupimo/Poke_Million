import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainFrame() 
	{
		this.setSize(new Dimension(1000, 700));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();

		JMenu menu1 = new JMenu("Fichier");
		JMenuItem ouvrir = new JMenuItem(new LoadCSV());
		// JMenuItem sauver = new JMenuItem(new SaveCSV("Sauver"));
		// JMenuItem quitter = new JMenuItem(new QuitterAction("Quitter"));
		menu1.add(ouvrir);
		// menu1.add(sauver);
		menu1.addSeparator();
		// menu1.add(quitter);
		menuBar.add(menu1);

		JMenu menu2 = new JMenu("Pokédex");
		JMenuItem printPokedex = new JMenuItem(new Pokedex("Afficher le Pokédex"));
		menu2.add(printPokedex);
		JMenuItem changePokedex = new JMenuItem(new Pokedex("Changer de Pokédex"));
		menu2.add(changePokedex);
		menuBar.add(menu2);

		this.setJMenuBar(menuBar);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// ################################################
	// ################ Private Class #################
	// ################################################

	private static class LoadCSV extends AbstractAction {

		private static final long serialVersionUID = 1L;

		private LoadCSV() {
			super("Ouvrir");
		}

		public void actionPerformed(ActionEvent e) {

			CSVLoader load = new CSVLoader();
			App.myCSV = load.loadFile();
			JTable myTable = App.myCSV.printCSVFile();
			App.mainFrame.add(myTable);
			App.mainFrame.getContentPane().add(myTable.getTableHeader(), BorderLayout.NORTH);
			App.mainFrame.getContentPane().add(new JScrollPane(myTable), BorderLayout.CENTER);
			App.mainFrame.setVisible(true);
		}
	}

	private static class Pokedex extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private String action;

		private Pokedex(String actionName) {
			super(actionName);
			this.action = actionName;
		}

		public void actionPerformed(ActionEvent e) {

			if (this.action.equals("Afficher le Pokédex")) {
				App.data.printDataInArray();
			} 
			
			else {
				CSVLoader load = new CSVLoader();
				JFileChooser fileChooser = new JFileChooser();

				// Personnalisation de la fenêtre de sélection
				Box vBox = Box.createVerticalBox();
				Component[] cp = fileChooser.getComponents();
				vBox.add(cp[2]);

				JTextArea text = new JTextArea("\n\n\nLe fichier doit avoir l'encodage \"Unicode\" et doit posséder 4 champs séparés par des \";\" !!! ");
				text.setEditable(false); // Empêche la réécriture de la zone de texte
				text.setFocusable(false); // Le texte n'est pas sélectionnable
				text.setFont(new Font("Arial", Font.BOLD, 15));
				text.setForeground(new Color(255, 0, 0)); // Couleur du texte en rouge
				text.setBackground(null); // Couleur d'arrière plan transparente

				vBox.add(text);
				fileChooser.add(vBox);

				fileChooser.setDialogTitle("Choisissez le fichier à charger");
				App.filtre.addFiltre(fileChooser); // On définit les formats de
													// sauvegardes autorisés
				int userSelection = fileChooser.showOpenDialog(null);

				if (userSelection == JFileChooser.APPROVE_OPTION) // L'utilisateur à cliqué sur ouvrir
				{
					App.data.changePokeList(load.getSelectedFileWithExtension(fileChooser));
				}
			}
		}
	}
	
	// ################################################
	// ################### Getters ####################
	// ################################################
	
	public Component findComponentByName(Container container, String componentName) 
	{
		for (Component component: container.getComponents()) 
		{
			System.out.println(component.getClass().getName());
			if (componentName.equals(component.getClass().getName())) 
			{
		      return component;
		    }
		    if (component instanceof Container) 
		    {
		      Container newContainer = (Container)component;
		      return findComponentByName(newContainer, componentName);
		    }
		}
		return null;
	}
}

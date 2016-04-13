import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Menu extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image de fond
	private CustomButton play, tuto, quitter, options; //Les différents boutons du menu
	private AudioEngine musique; //Moteur de gestion de la musique
	
	public Menu(){
		super();
		
		//Obtention de l'image de fond
		try {
			img = ImageIO.read(new File("./src/Images/fond2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Utilisation du GridBagLayout pour une meilleure portabilité
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		//On construit les différents boutons
		this.play = new CustomButton("./src/Images/BoutonPokeballJouer.png", 120, 120);
		this.options = new CustomButton("./src/Images/BoutonSuperballOption.png", 120, 120);
		this.tuto = new CustomButton("./src/Images/BoutonHyperballAide.png", 120, 120);
		this.quitter = new CustomButton("./src/Images/BoutonMasterballQuitter.png", 120, 120);
		
		
		//On ajoute les listeners aux boutons
		play.addActionListener(this);
		tuto.addActionListener(this);
		quitter.addActionListener(this);
		options.addActionListener(this);
		
		
		
		c.weightx = 0;
		c.weighty = 1;
		
		//Fixe les boutons à l'ouest
		c.anchor = GridBagConstraints.SOUTH;
		
		//Définit les marges autour des boutons
		c.insets = new Insets(50,50,50,50);
		
		//Positionnement du bouton play
		c.gridx = 0;
		c.gridy = 0;
		this.add(play, c);
		
		//Positionnement du bouton tuto
		c.gridx = 1;
		c.gridy = 0;
		this.add(options, c);
		
		//Positionnement du bouton options
		c.gridx = 2;
		c.gridy = 0;
		this.add(tuto, c);
		
		//Positionnement du bouton quitter
		c.gridx = 3;
		c.gridy = 0;
		this.add(quitter, c);
		
		
		//Lancement de la musique du menu
		this.musique = new AudioEngine("Menu.wav");
		musique.start();
		
	}
	
	
	
	//Gère les actions des boutons
	@Override
	public void actionPerformed(ActionEvent e){
		
		/***Les boutons "jouer", "options" et "aide" effacent le jpanel "menu" et le remplacent par un nouveau jpanel***/
		
		
		if (e.getSource() == play){ //Actions du bouton "jouer"
			if (App.myCSV == null)
			{ //Propose de charger un CSV si aucun CSV est dans la base de données
				CSVLoader load = new CSVLoader();
				App.myCSV = load.loadFile();
			}
			if (App.myCSV != null) 
			{ //Démarre une partie si un CSV est chargé
				MainFrame parent = (MainFrame) SwingUtilities.getWindowAncestor(this);
				this.musique.stopMusic();
				parent.getContentPane().remove(this);
				System.out.println("Lancement du jeu");
				new Combat (parent, 6);
			}
		}
		else if (e.getSource() == tuto){ //Action du bouton "aide"
			MainFrame parent = (MainFrame) SwingUtilities.getWindowAncestor(this);
			this.musique.stopMusic();
			parent.getContentPane().remove(this);
			parent.getContentPane().add(new Tutopanel());
			parent.getContentPane().invalidate();
			parent.getContentPane().validate();
		}
		else if (e.getSource() == quitter){ //Action du bouton "quitter"
			System.exit(0); //Ferme le programme
		}
		else if (e.getSource() == options){ //Action du bouton "options"
			//TODO : ouvrir la fenêtre de gestion des options
		}
	}
	
	
	//Affiche l'image de fond
	public void paintComponent(Graphics g) 
	  { 
		Settings set = new Settings(); //Donne des infos sur la taille de l'écran de l'utilisateur
	    g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null);
	    repaint(); 
	  } 
}

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
	private BufferedImage img; //Image de fond
	private CustomButton play, tuto, quitter, options;
	private Settings set = new Settings();
	private int rows, cols;
	private AudioEngine musique;
	
	public Menu(){
		super();
		this.rows = 3;
		this.cols = 6;
		try {
			img = ImageIO.read(new File("./src/Images/Menu.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.play = new CustomButton("./src/Images/BoutonPokeball.png", 50, 50);
		this.tuto = new CustomButton("./src/Images/BoutonSuperball.png", 50, 50);
		this.quitter = new CustomButton("./src/Images/BoutonHyperball.png", 50, 50);
		this.options = new CustomButton("./src/Images/BoutonMasterball.png", 50, 50);
		play.addActionListener(this);
		tuto.addActionListener(this);
		quitter.addActionListener(this);
		options.addActionListener(this);
		
		c.weightx = 1;
		
		//Fixe les boutons à l'ouest
		c.anchor = GridBagConstraints.WEST;
		
		//Définit les marges autour des boutons
		c.insets = new Insets(20,200,20,20);
		
		//Positionnement du bouton play
		c.gridx = 0;
		c.gridy = 0;
		this.add(play, c);
		
		//Positionnement du bouton tuto
		c.gridx = 0;
		c.gridy = 1;
		this.add(tuto, c);
		
		//Positionnement du bouton options
		c.gridx = 0;
		c.gridy = 2;
		this.add(options, c);
		
		//Positionnement du bouton quitter
		c.gridx = 0;
		c.gridy = 3;
		this.add(quitter, c);
		
		this.musique = new AudioEngine("Pokecenter.wav");
		musique.start();
		
	}
	
	
	
	//Gère les actions des boutons
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource() == play){
			//TODO : ouvrir la fenêtre de jeu	
			System.out.println("Lancement du jeu");
		}
		else if (e.getSource() == tuto){
			//TODO : ouvrir la fenêtre de tuto
		}
		else if (e.getSource() == quitter){
			//TODO : quitter le jeu
		}
		else if (e.getSource() == options){
			//TODO : ouvrir la fenêtre de gestion des options
		}
	}
	
	
	//Affiche l'image de fond
	public void paintComponent(Graphics g) 
	  { 
		//TODO : redimensionner image
		Settings set = new Settings();
	    g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null);
	    repaint(); 
	  } 
}

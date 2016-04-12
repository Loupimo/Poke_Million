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
	private CustomButton play, tuto, quitter, options;
	private AudioEngine musique;
	
	public Menu(){
		super();
		try {
			img = ImageIO.read(new File("./src/Images/fond2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		this.play = new CustomButton("./src/Images/BoutonPokeballJouer.png", 120, 120);
		this.options = new CustomButton("./src/Images/BoutonSuperballOption.png", 120, 120);
		this.tuto = new CustomButton("./src/Images/BoutonHyperballAide.png", 120, 120);
		this.quitter = new CustomButton("./src/Images/BoutonMasterballQuitter.png", 120, 120);
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
		
		
		
		this.musique = new AudioEngine("Menu.wav");
		musique.start();
		
	}
	
	
	
	//Gère les actions des boutons
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource() == play){
			MainFrame parent = (MainFrame) SwingUtilities.getWindowAncestor(this);
			this.musique.stopMusic();
			parent.getContentPane().remove(this);
			parent.getContentPane().add(new Combat(true));
			parent.getContentPane().invalidate();
			parent.getContentPane().validate();
			System.out.println("Lancement du jeu");
		}
		else if (e.getSource() == tuto){
			//TODO : ouvrir la fenêtre de tuto
			MainFrame parent = (MainFrame) SwingUtilities.getWindowAncestor(this);
			this.musique.stopMusic();
			parent.getContentPane().remove(this);
			parent.getContentPane().add(new Tutopanel());
			parent.getContentPane().invalidate();
			parent.getContentPane().validate();
		}
		else if (e.getSource() == quitter){
			//quitter le jeu
			System.exit(0);
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

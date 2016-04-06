import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
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
	private CustomButton play, tuto, quitter;
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
		//this.setLayout(null);
		this.play = new CustomButton("./src/Images/BoutonPokeball.png", 50, 50);
		this.tuto = new CustomButton("./src/Images/BoutonSuperball.png", 50, 50);
		this.quitter = new CustomButton("./src/Images/BoutonHyperball.png", 50, 50);
		//play.setLocation(30, 30);
		play.addActionListener(this);
		tuto.addActionListener(this);
		quitter.addActionListener(this);
		
		this.add(play);
		this.add(tuto);
		this.add(quitter);
		
		this.musique = new AudioEngine("Pokecenter.wav");
		musique.start();
		
	}
	
	
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
	}
	
	
	public void paintComponent(Graphics g) 
	  { 
		//TODO : redimensionner image
		Settings set = new Settings();
	    g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null);
	    repaint(); 
	  } 
}

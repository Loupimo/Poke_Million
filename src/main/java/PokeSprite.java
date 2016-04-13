import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PokeSprite extends JLabel{
	
	private BufferedImage img; //Image de la sprite
	private int width, height;
	
	public PokeSprite(String path, int width, int height){	
		super();
		this.width = width;
		this.height = height;
		//Obtention de l'image de fond
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//Affiche l'image de fond
	public void paintComponent(Graphics g) 
	{ 
		Settings set = new Settings(); //Donne des infos sur la taille de l'écran de l'utilisateur
	    g.drawImage(img, 0, 0, width, height, null);
		repaint(); 
	 }

}

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Combat  extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image de fond
	private BufferedImage pokemon1; // Image du pokémon1
	private BufferedImage pokemon2; // Image du pokémon2
	
	public Combat(boolean vainqueur){
		System.out.println("Lancement du combat");
		String path = "./src/Images/arene.png"; //chemin de l'image
		String pathpoke1 = "./PokemonData/Sprites/back/55.png"; //chemin de l'image
		String pathpoke2 = "./PokemonData/Sprites/49.png"; //chemin de l'image
		try {
			img = ImageIO.read(new File(path));
			pokemon1 = ImageIO.read(new File(pathpoke1));;
			pokemon2 = ImageIO.read(new File(pathpoke2));;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		

		}
	
		public void paintComponent(Graphics g) 
		{ 
		
			Settings set = new Settings();
		
			g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null); 
			g.drawImage(pokemon1, set.getWidth()/10, set.getHeight() - (set.getHeight()/2),  set.getWidth()/3, set.getHeight()/2, null); 
			g.drawImage(pokemon2, set.getWidth() - ((set.getWidth()/5)*2), set.getHeight()/4,  set.getWidth()/3, set.getHeight()/3 , null); 
	    	repaint(); 
		} 
			
}

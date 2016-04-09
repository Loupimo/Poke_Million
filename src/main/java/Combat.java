import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Combat  extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image de fond
	
	public Combat(boolean vainqueur){
 
		String path = "./src/Images/arene.png"; //chemin de l'image
		try {
			img = ImageIO.read(new File(path));
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
	    	repaint(); 
		} 
			
}

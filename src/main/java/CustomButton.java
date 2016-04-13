import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**Bouton personnalisé**/

public class CustomButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image du bouton
	
	
	/*Constructeur*/
	public CustomButton(String path, int height, int width){
		super();
		
		//Ouverture de l'image de fond
		try {
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setPreferredSize(new Dimension(width, height)); //Redimensionnement de l'image en fonction des paramètres donnés au constructeur
		this.setVisible(true);
		
	}
	

	//Affichage de l'image
	public void paintComponent(Graphics g){
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    repaint();
	}

}

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Menu extends JPanel{
	private BufferedImage img; //Image de fond
	private CustomButton play, tuto, quitter;
	private Settings set = new Settings();
	private int rows, cols;
	
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
		//this.setLayout(new GridLayout(rows, cols));
		
		this.play = new CustomButton("./src/Images/BoutonPokeball.png", 30, 30);
		this.tuto = new CustomButton("./src/Images/BoutonSuperball.png", 30, 30);
		this.quitter = new CustomButton("./src/Images/BoutonHyperball.png", 30, 30);
		this.add(play);
		this.add(tuto);
		this.add(quitter);
	}
	
	
	
	
	
	public void paintComponent(Graphics g) 
	  { 
		//TODO : redimensionner image
		Settings set = new Settings();
	    g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null); 
	    repaint(); 
	  } 
}

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class CustomButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	
	public CustomButton(String path, int height, int width){
		super();
		try {
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Erreur chargement img");
			e.printStackTrace();
		}
		this.setPreferredSize(new Dimension(width, height));
		this.setVisible(true);
		
	}
	

	public void paintComponent(Graphics g){
	    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    repaint();
	}

}

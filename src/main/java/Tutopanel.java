import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Tutopanel extends JPanel implements ActionListener{
	private BufferedImage img;
	private CustomButton retour;
	private AudioEngine audio;
	
	
	public Tutopanel(){
		super();
		try {
			img = ImageIO.read(new File("./src/Images/tuto.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.audio = new AudioEngine("tuto.wav");
		audio.start();
		
		this.retour = new CustomButton("./src/Images/BoutonRetour.png", 300, 500);
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 1;
		c.weighty = 1;
		
		c.anchor = GridBagConstraints.NORTHEAST;
		
		c.gridx = 0;
		c.gridy = 0;
		
		this.add(retour, c);
		
		retour.addActionListener(this);
		
	}
	
	//Affiche l'image de fond
	public void paintComponent(Graphics g) { 
		Settings set = new Settings();
		g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null);
		repaint(); 
	} 

	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource() == retour){
			MainFrame parent = (MainFrame) SwingUtilities.getWindowAncestor(this);
			this.audio.stopMusic();
			parent.getContentPane().remove(this);
			parent.getContentPane().add(new Menu());
			parent.getContentPane().invalidate();
			parent.getContentPane().validate();
		}
	}
	
}

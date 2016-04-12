

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



//JPanel gérant toute l'intro
public class StartingWindow extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image de fond
	private MusicIntroControler controler;
	private CustomButton play, titre;
	
	
	public StartingWindow(){
		super();	
		this.play = new CustomButton("./src/Images/boutonJouer.png", 350, 550); //Bouton start
		this.titre = new CustomButton("./src/Images/logoPokemon.png", 200, 500); //titre (lance aussi le jeu)
		play.addActionListener(this);
		titre.addActionListener(this);
		
		String path = "./src/Images/blastoise.jpg"; //chemin de l'image
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weighty = 1;
		c.weightx = 1;
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(titre, c);
		
		c.gridy = 1;
		this.add(play, c);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource() == play || e.getSource() == titre){
			//Coupe la musique et ouvre le menu
			IntroFrame parent = (IntroFrame) SwingUtilities.getWindowAncestor(this);
			controler.stopMusic();
			controler.stop();
			parent.closeFrame();
			
		}
	}
	
	public void paintComponent(Graphics g) 
	  { 
		//TODO : redimensionner image
		Settings set = new Settings();
	    g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null); 
	    repaint(); 
	  } 
	
	public void ignition(){ //appel au thread de gestion de la musique
		this.controler = new MusicIntroControler();
		controler.start();
	}
	
	public StartingWindow getStartingWindow(){
		return this;
	}

	
	
	

}

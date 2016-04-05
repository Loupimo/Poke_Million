

import java.awt.Graphics;
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
	private CustomButton play;
	
	
	public StartingWindow(){
		super();	
		this.play = new CustomButton("./src/Images/logoPokemon.png", 200, 500); //Bouton start
		play.addActionListener(this);
		this.add(play);
		String path = "./src/Images/blastoise.jpg"; //chemin de l'image
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		/*controler.changeFlag();
		System.out.println("Thread killed");*/
		
		if (e.getSource() == play){
			//TODO : ouvrir le menu principal
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

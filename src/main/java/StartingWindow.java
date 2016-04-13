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
	private MusicIntroControler controler; //Classe de gestion de la séquence de musique de l'intro (enchaînement de 2 musiques)
	private CustomButton play, titre; //Les deux boutons permettant d'aller au menu principal. Le titre est cliquable.
	
	
	public StartingWindow(){
		super();	
		this.play = new CustomButton("./src/Images/boutonJouer.png", 350, 550); //Bouton start
		this.titre = new CustomButton("./src/Images/logoPokemon.png", 200, 500); //titre (lance aussi le jeu)
		
		//Ajout des listener sur le titre et le bouton "jouer"
		play.addActionListener(this);
		titre.addActionListener(this);
		
		String path = "./src/Images/blastoise.jpg"; //chemin de l'image de fond
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Utilisation du GridBagLayout pour positionner les composants (meilleure portabilité)
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Paramètres de base pour le positionnement
		c.weighty = 1;
		c.weightx = 1;
		c.anchor = GridBagConstraints.CENTER; //On centre les éléments
		
		
		//Positionnement du titre
		c.gridx = 0;
		c.gridy = 0;
		this.add(titre, c);
		
		
		//Positionnement du bouton "jouer"
		c.gridy = 1;
		this.add(play, c);
		
	}
	
	@SuppressWarnings("deprecation")
	//Définition des actions des boutons
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource() == play || e.getSource() == titre){
			//Coupe la musique et ouvre le menu
			IntroFrame parent = (IntroFrame) SwingUtilities.getWindowAncestor(this); //Récupère la fenêtre contenant le jpanel
			controler.stopMusic(); //On fait appel à la méthode d'arrêt de la musique
			controler.stop(); //On stoppe le thread
			parent.closeFrame(); //On ferme la fenêtre 
			
		}
	}
	
	
	//Permet d'afficher une image de fond
	public void paintComponent(Graphics g) 
	  { 
		Settings set = new Settings(); //Permet d'obtenir des infos sur la taille de l'écran de l'utilisateur
	    g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null); //Définit l'image de fond
	    repaint(); 
	  } 
	
	
	//appel au thread de gestion de la musique
	public void ignition(){
		this.controler = new MusicIntroControler();
		controler.start();
	}
	

	
	
	

}

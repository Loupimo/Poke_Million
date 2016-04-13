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


/**JPanel de tutoriel**/
public class Tutopanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image de fond
	private CustomButton retour; //Bouton de retour au menu
	private AudioEngine audio; //Musique
	
	
	public Tutopanel(){
		super();
		
		//Récupération de l'image de fond
		try {
			img = ImageIO.read(new File("./src/Images/tuto.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Lancement de la musique
		this.audio = new AudioEngine("tuto.wav");
		audio.start();
		
		//Création du bouton de retour
		this.retour = new CustomButton("./src/Images/BoutonRetour.png", 300, 500);
		
		
		//Définition du gridbaglayout
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 1;
		c.weighty = 1;
		
		c.anchor = GridBagConstraints.NORTHEAST;
		
		
		//Positionnement du bouton retour
		c.gridx = 0;
		c.gridy = 0;
		
		this.add(retour, c);
		
		retour.addActionListener(this); //Listener
		
		
		//TODO : Ajouter texte explicatif
		
	}
	
	//Affiche l'image de fond
	public void paintComponent(Graphics g) { 
		Settings set = new Settings();
		g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null);
		repaint(); 
	} 

	
	
	//Gestion du listener
	@Override
	public void actionPerformed(ActionEvent e){
		
		if (e.getSource() == retour){
			MainFrame parent = (MainFrame) SwingUtilities.getWindowAncestor(this); //Récupère la fenêtre contenant le jpanel
			this.audio.stopMusic(); //Arrête la musique
			parent.getContentPane().remove(this); //Enlève ce jpanel de la fenêtre
			parent.getContentPane().add(new Menu()); //Ajoute un jpanel menu à la fenêtre
			parent.getContentPane().invalidate();
			parent.getContentPane().validate();
		}
	}
	
}

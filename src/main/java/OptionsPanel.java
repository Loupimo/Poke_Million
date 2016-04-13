import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**JPanel de tutoriel**/
public class OptionsPanel extends JPanel implements ActionListener, ChangeListener{
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image de fond
	private CustomButton retour; //Bouton de retour au menu
	private AudioEngine audio; //Musique
	private JSlider teamSize;
	
	
	public OptionsPanel(){
		super();
		
		//Récupération de l'image de fond
		try {
			img = ImageIO.read(new File("./src/Images/Options.png"));
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
		
		c.anchor = GridBagConstraints.CENTER;
		teamSize = new JSlider (JSlider.HORIZONTAL, 3, 6, 3);
		teamSize.setMajorTickSpacing(3);
        teamSize.setMinorTickSpacing(1);
        teamSize.setLabelTable(teamSize.createStandardLabels(1));
        teamSize.setPaintTrack(true);
		teamSize.setPaintTicks(true);
		teamSize.setPaintLabels(true);
		teamSize.setBorder(BorderFactory.createTitledBorder("Nombre de pokémons par équipe"));
        Font font = new Font("Serif", Font.ITALIC, 15);
        teamSize.setFont(font);
		
		teamSize.addChangeListener(this);
		
		this.add(teamSize, c);
		
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

	@Override
	public void stateChanged(ChangeEvent arg0) {
		App.teamSize = this.teamSize.getValue();
	}
	
}

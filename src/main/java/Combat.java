import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Combat extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Dresseur player, opponent [] = new Dresseur [3];
	private MainFrame plateau;
	private JTextField nameGetter;
	private JPanel teamPrinter, ennemyPrinter [] = new JPanel [3];
	private JButton oui = new JButton ("Oui"), non = new JButton("Non"), affronter [] = new JButton [3];
	private int teamSize;
	private AudioEngine audio;
	private BufferedImage img;
	
	public Combat(int p_teamSize) {
		super();
		//Obtention de l'image de fond
		try {
			this.img = ImageIO.read(new File("./src/Images/Versus.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.teamSize = p_teamSize;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		
		this.teamPrinter = new JPanel ();
		this.teamPrinter.setBackground(new Color (150,150,150,150));
		this.teamPrinter.setLayout(new BoxLayout (this.teamPrinter, BoxLayout.PAGE_AXIS));
		this.teamPrinter.setBounds(200, this.getHeight()/2-90, 56*teamSize+20, 180);
		
		this.nameGetter = new JTextField();
		this.nameGetter.setText("Entrez votre nom de Dresseur");
		this.nameGetter.addActionListener (this);
		this.teamPrinter.add(this.nameGetter);
		this.teamPrinter.setBorder (BorderFactory.createTitledBorder("Votre équipe"));
		
		this.setBegin ();
		printDresseurEquipeInJPanel(this.player, this.teamPrinter);
		
		
		Box horizontal = Box.createHorizontalBox();
		horizontal.add(new JLabel ("Voulez-vous garder cette équipe: "));
		oui.addActionListener (this);
		non.addActionListener (this);
		horizontal.add(oui);
		horizontal.add(non);
		this.teamPrinter.add(horizontal);
		
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(20,40,20,40);
		
		c.gridx = 0;
		c.gridy = 1;
		this.add(this.teamPrinter, c);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.EAST;
		for (int i = 0; i < 3; i++)
		{
			this.ennemyPrinter[i] = new JPanel ();
			this.ennemyPrinter[i].setBackground(new Color (150,150,150,150));
			this.ennemyPrinter[i].setLayout(new BoxLayout (this.ennemyPrinter[i], BoxLayout.PAGE_AXIS));
			this.ennemyPrinter[i].setBounds(this.getWidth()-56*teamSize-200,  this.getHeight()/2+(-270+i*180), 56*teamSize+20, 180);
			printDresseurEquipeInJPanel(this.opponent[i], this.ennemyPrinter[i]);
			this.ennemyPrinter[i].setBorder (BorderFactory.createTitledBorder(this.opponent[i].getName()));
			c.gridy = i;
			affronter[i] = new JButton ("Affronter");
			affronter[i].addActionListener (this);
			this.ennemyPrinter[i].add(affronter[i]);
			this.add(ennemyPrinter[i], c);
		}
		
		
		this.setPreferredSize(new Dimension (this.getWidth(), this.getHeight()));
		
		
		this.audio = new AudioEngine("battle.wav");
		audio.start();
		this.setVisible(true);
	}

	private void printDresseurEquipeInJPanel (Dresseur dresseur, JPanel container)
	{ //Affiche les pokémon d'une équipe dans un JPanel

		
		Box horizontal = Box.createHorizontalBox();
		horizontal.setSize(56*teamSize, 200);
		
		for (int i = 0; i < dresseur.GetEquipe().size(); i++)
		{ //Affiche chaque pokémon de l'équipe
			Box pokemonContainer = Box.createVerticalBox();
			Pokemon poke = dresseur.GetEquipe().get(i);
			pokemonContainer.add(new JLabel (poke.getSprite()));
			pokemonContainer.add(new JLabel("PV:" + poke.getHealthPoint()));
			pokemonContainer.add(new JLabel("Deg: " + poke.getAttack()));
			pokemonContainer.add(new JLabel("Def: " + poke.getDefense()));
			pokemonContainer.add(new JLabel("Vit: " + poke.getSpeed()));
			horizontal.add(pokemonContainer);
		}
		container.add(horizontal);
	}
	
	private void setBegin ()
	{ //Initialise les attributs nécessaires au début du combat
		this.player = new Dresseur ("Dresseur");
		this.player.setRandomEquipe(this.teamSize);
		this.opponent[0] = new Dresseur ("Dresseur 1");
 		this.opponent[0].setRandomEquipe(this.teamSize);
 		this.opponent[1] = new Dresseur ("Dresseur 2");
 		this.opponent[1].setRandomEquipe(this.teamSize);
 		this.opponent[2] = new Dresseur ("Dresseur 3");
		this.opponent[2].setRandomEquipe(this.teamSize);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == this.oui)
		{ // Bloque l'équipe
			if (!this.nameGetter.equals("") && !this.nameGetter.equals(this.player.getName())) this.player.setName (this.nameGetter.getText()); //Change le nom du dresseur
			this.oui.setEnabled(false);
			this.non.setEnabled(false);
		}
		else if (evt.getSource() == this.non)
		{ // Met à jour l'équipe du joueur
			this.teamPrinter.removeAll();
			this.player.setRandomEquipe(this.teamSize);
			this.teamPrinter.add(this.nameGetter);
			this.teamPrinter.setBorder (BorderFactory.createTitledBorder("Votre équipe"));
			
			printDresseurEquipeInJPanel(this.player, this.teamPrinter);
	
			Box horizontal = Box.createHorizontalBox();
			horizontal.add(new JLabel ("Voulez-vous garder cette équipe: "));
			horizontal.add(oui);
			horizontal.add(non);
			this.teamPrinter.add(horizontal);
			//this.repaint();
			this.setVisible(true);
		}
		else if (evt.getSource() == this.affronter[0] || evt.getSource() == this.affronter[1] || evt.getSource() == this.affronter[2])
		{
			if (!this.nameGetter.equals("") && !this.nameGetter.equals(this.player.getName())) this.player.setName (this.nameGetter.getText()); //Change le nom du dresseur
			MainFrame parent = (MainFrame) SwingUtilities.getWindowAncestor(this);
			audio.stopMusic();
			parent.getContentPane().remove(this);
			parent.getContentPane().add(new TourDeCombat(this.player, this.opponent[0]));
			parent.getContentPane().revalidate();
		}
	}
	
	//Affiche l'image de fond
	public void paintComponent(Graphics g) 
	{ 
		Settings set = new Settings(); //Donne des infos sur la taille de l'écran de l'utilisateur
		g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null);
		repaint(); 
	} 
}

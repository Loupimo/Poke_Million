import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Combat extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Dresseur player, opponent [] = new Dresseur [3];
	private MainFrame plateau;
	private JTextField nameGetter;
	private JPanel teamPrinter, ennemyPrinter [] = new JPanel [3];
	private JButton oui = new JButton ("Oui"), non = new JButton("Non"), affronter [] = new JButton [3];
	private int teamSize;
	private AudioEngine audio;

	public Combat(MainFrame p_plateau, int p_teamSize) {

		this.teamSize = p_teamSize;
		this.plateau = p_plateau;
		this.plateau.setLayout(null);
		this.audio = new AudioEngine("battle.wav");
		audio.start();
		
		
		this.teamPrinter = new JPanel ();
		this.teamPrinter.setBackground(new Color (150,150,150,150));
		this.teamPrinter.setLayout(new BoxLayout (this.teamPrinter, BoxLayout.PAGE_AXIS));
		this.teamPrinter.setBounds(200, this.plateau.getHeight()/2-90, 56*teamSize+20, 180);
		
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
		this.plateau.add(this.teamPrinter);
		
		for (int i = 0; i < 3; i++)
		{
			this.ennemyPrinter[i] = new JPanel ();
			this.ennemyPrinter[i].setBackground(new Color (150,150,150,150));
			this.ennemyPrinter[i].setLayout(new BoxLayout (this.ennemyPrinter[i], BoxLayout.PAGE_AXIS));
			this.ennemyPrinter[i].setBounds(this.plateau.getWidth()-56*teamSize-200,  this.plateau.getHeight()/2+(-270+i*180), 56*teamSize+20, 180);
			printDresseurEquipeInJPanel(this.opponent[i], this.ennemyPrinter[i]);
			this.ennemyPrinter[i].setBorder (BorderFactory.createTitledBorder(this.opponent[i].getName()));
			
			affronter[i] = new JButton ("Affronter");
			affronter[i].addActionListener (this);
			this.ennemyPrinter[i].add(affronter[i]);
			this.plateau.add(ennemyPrinter[i]);
		}
		
		/*this.ennemyPrinter[0] = new JPanel ();
		this.ennemyPrinter[0].setLayout(new BoxLayout (this.ennemyPrinter[0], BoxLayout.PAGE_AXIS));
		this.ennemyPrinter[0].setBounds(this.plateau.getWidth()-56*teamSize-200,  this.plateau.getHeight()/2-270, 56*teamSize+20, 180);
		printDresseurEquipeInJPanel(this.opponent[0], this.ennemyPrinter[0]);
		this.ennemyPrinter[0].setBorder (BorderFactory.createTitledBorder(this.opponent[0].getName()));
		
		JButton affronter0 = new JButton ("Affronter");
		this.ennemyPrinter[0].add(affronter0);
		
		this.ennemyPrinter[1] = new JPanel ();
		this.ennemyPrinter[1].setLayout(new BoxLayout (this.ennemyPrinter[1], BoxLayout.PAGE_AXIS));
		this.ennemyPrinter[1].setBounds(this.plateau.getWidth()-56*teamSize-200, this.plateau.getHeight()/2-90, 56*teamSize+20, 180);
		printDresseurEquipeInJPanel(this.opponent[1], this.ennemyPrinter[1]);
		this.ennemyPrinter[1].setBorder (BorderFactory.createTitledBorder(this.opponent[1].getName()));
		
		JButton affronter1 = new JButton ("Affronter");
		this.ennemyPrinter[1].add(affronter1);
		
		this.ennemyPrinter[2] = new JPanel ();
		this.ennemyPrinter[2].setLayout(new BoxLayout (this.ennemyPrinter[2], BoxLayout.PAGE_AXIS));
		this.ennemyPrinter[2].setBounds(this.plateau.getWidth()-56*teamSize-200, this.plateau.getHeight()/2+90, 56*teamSize+20, 180);
		printDresseurEquipeInJPanel(this.opponent[2], this.ennemyPrinter[2]);
		this.ennemyPrinter[2].setBorder (BorderFactory.createTitledBorder(this.opponent[2].getName()));
		
		JButton affronter2 = new JButton ("Affronter");
		this.ennemyPrinter[2].add(affronter2);
		
		this.plateau.add(ennemyPrinter[0]);
		this.plateau.add(ennemyPrinter[1]);
		this.plateau.add(ennemyPrinter[2]);*/
		/*loadImgPoke (player.getPokemon(0), opponent.getPokemon(0));
		while (nbTours > 0) {
			tour();
		}*/
		this.plateau.pack();
		this.plateau.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.plateau.setPreferredSize(new Dimension (this.plateau.getWidth(), this.plateau.getHeight()));
		JLabel back = new JLabel (new ImageIcon("./src/Images/Versus.png"));
		back.setBounds(0, 0, this.plateau.getWidth(), this.plateau.getHeight());
		this.plateau.add(back);
		this.plateau.setVisible(true);
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
			this.player.setRandomEquipe(this.teamSize);
			
			this.teamPrinter.removeAll();
			this.teamPrinter.add(this.nameGetter);
			this.teamPrinter.setBorder (BorderFactory.createTitledBorder("Votre équipe"));
			
			printDresseurEquipeInJPanel(this.player, this.teamPrinter);
	
			Box horizontal = Box.createHorizontalBox();
			horizontal.add(new JLabel ("Voulez-vous garder cette équipe: "));
			horizontal.add(oui);
			horizontal.add(non);
			this.teamPrinter.add(horizontal);
			this.plateau.pack();
			this.plateau.setVisible(true);
		}
		else if (evt.getSource() == this.affronter[0] || evt.getSource() == this.affronter[1] || evt.getSource() == this.affronter[2])
		{
			if (!this.nameGetter.equals("") && !this.nameGetter.equals(this.player.getName())) this.player.setName (this.nameGetter.getText()); //Change le nom du dresseur
			this.plateau.dispose(); //Ferme la fenêtre courrante
			new TourDeCombat (this.player, this.opponent[0]);
		}
	}
}

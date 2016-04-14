import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class AffichageCombat  extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image de fond
	private BufferedImage imgpokemon1; // Image du pokémxon1
	private BufferedImage imgpokemon2; // Image du pokémon2 d
	private TourDeCombat tdc;
	private JButton nextTurn;
	public JLabel pvPlayer, pvOpponent;
	
	public AffichageCombat(Pokemon joueur, Pokemon adversaire, TourDeCombat tdc){
		System.out.println("affichage image");//d
		this.setLayout(null);
		this.setVisible(true);
		try {
			img = ImageIO.read(new File("./src/Images/Arene.png"));
			imgpokemon1 = ImageIO.read(new File("./PokemonData/Sprites/back/" + joueur.getId() + ".png"));
			imgpokemon2 = ImageIO.read(new File("./PokemonData/Sprites/" + adversaire.getId() + ".png"));
		
		}
		catch (IOException e) {
			e.printStackTrace();	
		}
		this.tdc = tdc;
		nextTurn = new JButton ("Tour suivant");
		nextTurn.addActionListener(this);
		nextTurn.setBounds(0, tdc.getHeight()/2, 150, 50);
		this.add(nextTurn);
		
		tdc.maxPvJoueur = joueur.getHealthPoint();
		pvPlayer = new JLabel ("Défense: " + joueur.getDefense() + ", Vitesse: " + joueur.getSpeed() + ", Attaque: " + joueur.getAttack() + ", PV: " + joueur.getHealthPoint() + "/" + tdc.maxPvJoueur);
		pvPlayer.setBounds(tdc.getWidth()/9, tdc.getHeight()-300, 250, 50);
		pvPlayer.setForeground(new Color (255,0,0));
		
		tdc.maxPvEnnemi = adversaire.getHealthPoint(); 
		pvOpponent = new JLabel ("Défense: " + adversaire.getDefense() + ", Vitesse: " + adversaire.getSpeed() + ", Attaque: " + adversaire.getAttack() + ", PV: " + adversaire.getHealthPoint() + "/" + tdc.maxPvEnnemi);
		pvOpponent.setBounds(tdc.getWidth() - ((tdc.getWidth()/5)*2), tdc.getHeight()/5, 250, 50);
		pvOpponent.setForeground(new Color (255,0,0));
		
		this.add(pvPlayer);
		this.add(pvOpponent);
	}
	

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (evt.getSource() == this.nextTurn)
		{ // Effectue un tour de combat

			System.out.println("Début du tour");
			if (tdc.combat(tdc.joueur, tdc.adversaire) != 0)
			{
				this.nextTurn.setEnabled(false);
				this.add (new JLabel ("Partie Terminée"));
			}
			System.out.println("Fin du tour");
		}
	}

		public void setImgPokemon1(int id)
		{ // Affiche l'image du pokémon du joueur (donc de dos)
			try{
				imgpokemon1 = ImageIO.read(new File("./PokemonData/Sprites/back/" + tdc.joueur.getPokemon(id).getId() + ".png"));
				tdc.maxPvJoueur = tdc.joueur.getPokemon(id).getHealthPoint();
				pvPlayer.setText("Défense: " + tdc.joueur.getPokemon(id).getDefense() + ", Vitesse: " + tdc.joueur.getPokemon(id).getSpeed() + ", Attaque: " + tdc.joueur.getPokemon(id).getAttack() + ", PV: " + tdc.joueur.getPokemon(id).getHealthPoint() + "/" + tdc.joueur.getPokemon(id).getHealthPoint());
			}
			catch (IOException e) {
				e.printStackTrace();	
			}
			repaint();
		}
	
		public void setImgPokemon2(int id)
		{ // Affiche l'image du pokemon nnemi (donc de face)
			try{
				imgpokemon2 = ImageIO.read(new File("./PokemonData/Sprites/" + tdc.adversaire.getPokemon(id).getId() + ".png"));
				tdc.maxPvEnnemi = tdc.adversaire.getPokemon(id).getHealthPoint(); 
				pvOpponent.setText("Défense: " + tdc.adversaire.getPokemon(id).getDefense() + ", Vitesse: " + tdc.adversaire.getPokemon(id).getSpeed() + ", Attaque: " + tdc.adversaire.getPokemon(id).getAttack() + ", PV: " + tdc.adversaire.getPokemon(id).getHealthPoint() + "/" + tdc.adversaire.getPokemon(id).getHealthPoint());
			}
			catch (IOException e) {
				e.printStackTrace();	
			}	
			repaint();
		}
		
		public void paintComponent(Graphics g) 
		{ 
		
			Settings set = new Settings();
			g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null); 
			g.drawImage(imgpokemon1, set.getWidth()/10, set.getHeight() - ((set.getHeight()/5)*2),  set.getWidth()/3, set.getHeight()/2, null); 
			g.drawImage(imgpokemon2, set.getWidth() - ((set.getWidth()/5)*2), set.getHeight()/4,  set.getWidth()/3, set.getHeight()/3 , null);
			repaint();
		}	
}

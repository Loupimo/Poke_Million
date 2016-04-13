import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class TourDeCombat extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Dresseur joueur, adversaire;
	private BufferedImage img;
	private AudioEngine audio;
	
	public TourDeCombat (Dresseur p_joueur, Dresseur p_adversaire)
	{
		super();
		
		this.audio = new AudioEngine("battle2.wav");
		audio.start();
		
		//Obtention de l'image de fond
		try {
			img = ImageIO.read(new File("./src/Images/Arene.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.joueur = p_joueur;
		this.adversaire = p_adversaire;

		loadImgPoke(joueur.GetEquipe().get(0), adversaire.GetEquipe().get(0));
		

	}	
	
	private void loadImgPoke (Pokemon joueur, Pokemon adversaire)
	{ //Charge les images des pokémons sur le terrain
		/*Settings set = new Settings();
		ImageIcon img = new ImageIcon("./PokemonData/Sprites/back/" + joueur.getId() + ".png");
		imgpokemon1.setIcon((Icon)img.getImage().getScaledInstance(set.getWidth()/10, set.getHeight()/2, Image.SCALE_DEFAULT));
		imgpokemon2.setIcon(new ImageIcon("./PokemonData/Sprites/" + adversaire.getId() + ".png"));
		
		//imgpokemon1.setBounds(arene.getWidth() / 10, arene.getHeight() - (arene.getHeight() / 2), arene.getWidth() / 3, arene.getHeight() / 2);	
		imgpokemon2.setBounds (arene.getWidth() - ((arene.getWidth() / 5) * 2), arene.getHeight() / 4, arene.getWidth() / 3,arene.getHeight() / 3);
		
		this.arene.add(imgpokemon1);
		this.arene.add(imgpokemon2);*/
		
		
		/*Settings set = new Settings();
		BufferedImage img1;
		try {
			img1 = ImageIO.read(new File("./PokemonData/Sprites/back/" + joueur.getId() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		img1 = (BufferedImage) img1.getScaledInstance(set.getWidth(), set.getHeight(), Image.SCALE_SMOOTH);*/
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Settings set = new Settings();
		
		c.weightx = 1;
		c.weighty = 1;
		
		/*c.gridwidth = 2;
		c.gridheight = 3;*/
		
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 1;
		
		PokeSprite sprite = new PokeSprite("./PokemonData/Sprites/back/" + joueur.getId() + ".png", set.getWidth()/2, set.getHeight()/2);
		this.add(sprite, c);
		
		sprite = new PokeSprite("./PokemonData/Sprites/" + adversaire.getId() + ".png", set.getWidth()/5, set.getHeight()/3);
		c.gridx = 1;
		c.gridy = 0;
		//c.anchor = GridBagConstraints.LINE_END;
		this.add(sprite, c);
		
	}
	
	private void combat(Dresseur J1, Dresseur ennemy)
	{
		int id_joueur = 0, id_ennemy = 0;
		Pokemon J1p, E1p;
		try
		{
		
		if (J1.hasPokemon()&&ennemy.hasPokemon())
		{

			J1p = J1.getPokemon(id_joueur);
			E1p = ennemy.getPokemon(id_ennemy);
			if (J1p.getSpeed() > E1p.getSpeed()) //pokemon 1 plus rapide
			{
				E1p.takeDamage(J1p.getAttack());
				
				if (E1p.getHealthPoint() <= 0)
				{ //Le pokémon ennemi n'a plus de vie, on change de pokémon
					id_ennemy++;
				}
				else
				{ //Le pokémon ennemi a toujours de la vien il attaque
					J1p.takeDamage(E1p.getAttack());
					
					if (J1p.getHealthPoint() <= 0)
					{ //Le pokémon du joueur n'a plus de vie, on change de pokémon
						id_joueur++;
					}
				}
			}
			else
			{ //Le pokémon ennemi attaque en premier 
				J1p.takeDamage(E1p.getAttack());
				if (J1p.getHealthPoint() <= 0)
				{ //Le pokémon du joueur n'a plus de vie, on change de pokémon
					id_joueur++;
				}
				else
				{ //Le pokémon du joueur a toujours de la vie, il attaque
					E1p.takeDamage(J1p.getAttack());
					if (E1p.getHealthPoint() <= 0)
					{ //Le pokémon ennemi n'a plus de vie, on change de pokémon
						id_ennemy++;
					}
				}
				
			}
		}
		
		if (J1.hasPokemon())
		{
			///////////////////////////////////// joueur 1 gagne soin des pokemon
			J1.soin();
			
		}
		else 
		{
			//////////////////////////////////// joueur 1 perd
			
		}
		}catch (IndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//Affiche l'image de fond
	public void paintComponent(Graphics g) 
	{ 
		Settings set = new Settings(); //Donne des infos sur la taille de l'écran de l'utilisateur
		g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null);

		repaint(); 
	}
	
	
}

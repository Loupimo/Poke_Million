import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TourDeCombat extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	private String name;
	private Dresseur J1;
	private int fighter;
	*/
	private MainFrame arene;
	private JLabel imgpokemon1 = new JLabel(); // Image du pokémon1
	private JLabel imgpokemon2 = new JLabel(); // Image du pokémon2
	private Dresseur joueur, adversaire;
	
	public TourDeCombat (MainFrame p_arene, Dresseur p_joueur, Dresseur p_adversaire)
	{
		this.arene = p_arene;
		this.joueur = p_joueur;
		this.adversaire = p_adversaire;

		this.arene.setLayout(null);
		
		loadImgPoke(joueur.GetEquipe().get(0), adversaire.GetEquipe().get(0));

		this.arene.pack();
		this.arene.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.arene.setPreferredSize(new Dimension (this.arene.getWidth(), this.arene.getHeight()));
		JLabel back = new JLabel (new ImageIcon("./src/Images/Arene.png"));
		back.setBounds(0, 0, arene.getWidth(), arene.getHeight());
		this.arene.add(back);
		
		this.arene.setVisible(true);
	}	
	
	private void loadImgPoke (Pokemon joueur, Pokemon adversaire)
	{ //Charge les images des pokémons sur le terrain
		imgpokemon1.setIcon(new ImageIcon("./PokemonData/Sprites/back/" + joueur.getId() + ".png"));
		imgpokemon2.setIcon(new ImageIcon("./PokemonData/Sprites/" + adversaire.getId() + ".png"));
		
		imgpokemon1.setBounds(arene.getWidth() / 10, arene.getHeight() - (arene.getHeight() / 2), arene.getWidth() / 3, arene.getHeight() / 2);	
		imgpokemon2.setBounds (arene.getWidth() - ((arene.getWidth() / 5) * 2), arene.getHeight() / 4, arene.getWidth() / 3,arene.getHeight() / 3);
		
		this.arene.add(imgpokemon1);
		this.arene.add(imgpokemon2);
	}
	
	private void combat(Dresseur J1, Dresseur ennemy)
	{
		int id_joueur = 0, id_ennemy = 0,coef1 =1,coef2 =1;
		Pokemon J1p, E1p;
		try
		{
		
		if (J1.hasPokemon()&&ennemy.hasPokemon())
		{

			J1p = J1.getPokemon(id_joueur);
			E1p = ennemy.getPokemon(id_ennemy);
			for (String type : E1p.getTypeList())
			{
				if (J1p.getWeeknessList().contains(type))
				{
				coef1*=2;	
				}
			
			}
			for (String type : J1p.getTypeList())
			{
				if (E1p.getWeeknessList().contains(type))
				{
				coef2*=2;	
				}
			
			}
			if (J1p.getSpeed() > E1p.getSpeed()) //pokemon 1 plus rapide
			{
				E1p.takeDamage(J1p.getAttack()*coef2);
				
				if (E1p.getHealthPoint() <= 0)
				{ //Le pokémon ennemi n'a plus de vie, on change de pokémon
					id_ennemy++;
					coef1 =1;
					coef2=1;
				}
				else
				{ //Le pokémon ennemi a toujours de la vien il attaque
					J1p.takeDamage(E1p.getAttack()*coef1);
					
					if (J1p.getHealthPoint() <= 0)
					{ //Le pokémon du joueur n'a plus de vie, on change de pokémon
						id_joueur++;
						coef1 =1;
						coef2=1;
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
	
	
}

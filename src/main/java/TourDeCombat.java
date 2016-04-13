import javax.swing.JFrame;

import org.apache.log4j.Logger;



public class TourDeCombat extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(TourDeCombat.class);
	private AffichageCombat affichage;
	public Dresseur joueur, adversaire;
	public int id_joueur = 0, id_ennemy = 0, maxPvJoueur, maxPvEnnemi;
	
	public TourDeCombat (Dresseur p_joueur, Dresseur p_adversaire)
	{
		super ("Poké Million");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setLayout(null);
		
		this.joueur = p_joueur;
		this.adversaire = p_adversaire;
		
		affichage = new AffichageCombat(joueur.GetEquipe().get(0), adversaire.GetEquipe().get(0), this);
		affichage.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		this.add(affichage);
		this.setVisible(true);
	}	
		
	public int combat(Dresseur J1, Dresseur ennemy)
	{
		LOGGER.debug("Début Combat");
		int coef1 = 1, coef2 = 1;
		Pokemon J1p, E1p;
		
		if (J1.hasPokemon()&&ennemy.hasPokemon())
		{ // Les 2 joueurs on encore des pokémons

			J1p = J1.getPokemon(id_joueur);
			E1p = ennemy.getPokemon(id_ennemy);
			for (String type : E1p.getTypeList())
			{ // Augmente les dégats d'attaque de l'ennemi si le pokemon du joueur a une faiblesse qui correspond a un type du pokemon ennemi
				if (J1p.getWeeknessList().contains(type))
				{
					coef1*=2;	
				}
			
			}
			for (String type : J1p.getTypeList())
			{ // Augmente les dégats d'attaque du joueur si le pokemon de l'ennemi a une faiblesse qui correspond a un type du pokemon du joueur
				if (E1p.getWeeknessList().contains(type))
				{
					coef2*=2;	
				}
			}
			if (J1p.getSpeed() > E1p.getSpeed()) //pokemon du joueur plus rapide
			{
				LOGGER.debug("Joueur plus rapide");
				
				E1p.takeDamage(J1p.getAttack()*coef2);
				affichage.pvOpponent.setText("Défense: " + E1p.getDefense() + ", Vitesse: " + E1p.getSpeed() + ", Attaque: " + E1p.getAttack() + ", PV: " + E1p.getHealthPoint() + "/" + maxPvEnnemi);
				
				LOGGER.debug("Attaque\nPV Ennemi: " + E1p.getHealthPoint());
				
				if (E1p.getHealthPoint() <= 0)
				{ //Le pokémon ennemi n'a plus de vie, on change de pokémon
					
					if (!ennemy.hasPokemon()) return 1; // Le joueur gagne
					id_ennemy++;
					coef1 = 1;
					coef2 = 1;
					
					LOGGER.debug("Ennemi change de pokémon");
					
					affichage.setImgPokemon2(id_ennemy); // on affiche l'image du nouveau pokemon
				}
				else
				{ //Le pokémon ennemi a toujours de la vie, il attaque
					
					J1p.takeDamage(E1p.getAttack()*coef1);
					affichage.pvPlayer.setText("Défense: " + J1p.getDefense() + ", Vitesse: " + J1p.getSpeed() + ", Attaque: " + J1p.getAttack() + ", PV: " + J1p.getHealthPoint() + "/" + maxPvJoueur);
					
					LOGGER.debug("Attaque\nPV Joueur: " + J1p.getHealthPoint());
					
					if (J1p.getHealthPoint() <= 0)
					{ //Le pokémon du joueur n'a plus de vie, on change de pokémon
						
						if (!J1.hasPokemon()) return -1; // Le joueur perd
						id_joueur++;
						coef1 = 1;
						coef2 = 1;
						
						LOGGER.debug("Joueur change de pokémon");
						
						affichage.setImgPokemon1(id_joueur);// on affiche l'image du nouveau pokemon
					}
				}
			}
			else
			{ //Le pokémon ennemi attaque en premier
				
				LOGGER.debug("Ennemi plus rapide");
				
				J1p.takeDamage(E1p.getAttack());
				affichage.pvPlayer.setText("Défense: " + J1p.getDefense() + ", Vitesse: " + J1p.getSpeed() + ", Attaque: " + J1p.getAttack() + ", PV: " + J1p.getHealthPoint() + "/" + maxPvJoueur);
				
				LOGGER.debug("Attaque\nPV Joueur: " + J1p.getHealthPoint());
				
				if (J1p.getHealthPoint() <= 0)
				{ //Le pokémon du joueur n'a plus de vie, on change de pokémon
					if (!J1.hasPokemon()) return -1;
					id_joueur++;
					coef1 = 1;
					coef2 = 1;
					
					LOGGER.debug("Joueur change de pokémon");
					
					affichage.setImgPokemon1(id_joueur);// on affiche l'image du nouveau pokemon
				}
				else
				{ //Le pokémon du joueur a toujours de la vie, il attaque
					
					E1p.takeDamage(J1p.getAttack());
					affichage.pvOpponent.setText("Défense: " + E1p.getDefense() + ", Vitesse: " + E1p.getSpeed() + ", Attaque: " + E1p.getAttack() + ", PV: " + E1p.getHealthPoint() + "/" + maxPvEnnemi);
					
					LOGGER.debug("Attaque\nPV Ennemi: " + E1p.getHealthPoint());
					
					if (E1p.getHealthPoint() <= 0)
					{ //Le pokémon ennemi n'a plus de vie, on change de pokémon
						
						if (!ennemy.hasPokemon()) return 1;
						id_ennemy++;
						coef1 = 1;
						coef2 = 1;
						
						LOGGER.debug("Ennemi change de pokémon");
						
						affichage.setImgPokemon2(id_ennemy);// on affiche l'image du nouveau pokemon
					}
				}
				
			}
		}
		return 0;
	}
}

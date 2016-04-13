import javax.swing.JFrame;



public class TourDeCombat extends JFrame{

	private static final long serialVersionUID = 1L;
	private AffichageCombat affichage;
	/*
	private String name;
	private Dresseur J1;
	private int fighter;x
	*/
	
	private Dresseur joueur, adversaire;
	
	public TourDeCombat (Dresseur p_joueur, Dresseur p_adversaire)
	{
		super ("Poké Million");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		
		this.joueur = p_joueur;
		this.adversaire = p_adversaire;
		
		affichage = new AffichageCombat(joueur.GetEquipe().get(0), adversaire.GetEquipe().get(0));
		this.add(affichage);
		this.setVisible(true);
		
		combat(p_joueur, p_adversaire);
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
					
					 affichage.setImgPokemon2(id_ennemy); // on affiche l'image du nouveau pokemon
				}
				else
				{ //Le pokémon ennemi a toujours de la vie, il attaque
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
					
					 affichage.setImgPokemon1(id_joueur);// on affiche l'image du nouveau pokemon
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
	
}

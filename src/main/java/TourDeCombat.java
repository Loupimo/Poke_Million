import java.util.LinkedList;


public class TourDeCombat {
	
	private String name;
	private Dresseur J1;
	
	public TourDeCombat (String nom)
	{
		name = nom;
	}
	
	
	public void nouvellePartie()
	{
		/////////////////////////////////////////// récupérer le nom du joueur 
		if (name.equals(null)) name = "joueur 1";
		
		J1 = new Dresseur(name);
		
		
		//////////////////////////////////////////  Génération d'un équipe random le joueur choisis si il la prend ou relance une génération
		boolean choix = true;
		 while (choix)
		 {
			 LinkedList<Pokemon> equipe = J1.getRandomEquipe(3);
			 
			 //////////////////////////////////// afficher l'équipe de pokemon et demander si l'équipe convient au dresseur
			 choix = false;
		 }
		
		
		 ////////////////////////////////////////  génere 3 potentiel énnemis et le joueur choisis lequel affontrer
		 
		 Dresseur ennemi[] ={ new Dresseur ("ennemei 1", 3), new Dresseur ("ennemei 2", 3), new Dresseur ("ennemei 3", 3)};
		 int fighter =0;
		 
		 //////////////////////////////////////// fighter représente le numéro de l'ennemi 0-1-2 l'utilisateur doit paver choisir
		 fighter = 1;
		 
		 
		 combat(J1,ennemi[fighter]);
		 
		 
		 
		
	}
	private void combat(Dresseur J1, Dresseur ennemy)
	{
		int id_joueur=0,id_ennemy=0;
		Pokemon J1p, E1p;
		try
		{
		
		while (J1.hasPokemon()&&ennemy.hasPokemon())
		{

			J1p=J1.getPokemon(id_joueur);
			E1p=ennemy.getPokemon(id_ennemy);
			if (J1p.getSpeed()>E1p.getSpeed())/////////////////////////////pokemon 1 plus rapide
			{
				E1p.takeDamage(J1p.getAttack());
				if (E1p.getHealthPoint()<=0)
				{
					id_ennemy++;
				}
				else
				{
					J1p.takeDamage(E1p.getAttack());
					if (J1p.getHealthPoint()<=0)
					{
						id_joueur++;
					}
				}
			}
			else
			{
				J1p.takeDamage(E1p.getAttack());
				if (J1p.getHealthPoint()<=0)
				{
					id_joueur++;
				}
				else
				{
					E1p.takeDamage(J1p.getAttack());
					if (E1p.getHealthPoint()<=0)
					{
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

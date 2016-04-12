import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Combat  extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image de fond
	private BufferedImage imgpokemon1; // Image du pokémon1
	private BufferedImage imgpokemon2; // Image du pokémon2
	private int nbTours =  (int)((Math.random()*66)%3)+1; //nombre de tours du combat restants entre 1 et 3
	private int initiative = (int)((Math.random()*60)%2); // numéro du pokémon qui commencera
	private int pdvPokemon1 = 100; // points de vie du premier pokemon
	private int pdvPokemon2 = 100; // points de vie du second pokemon
	private boolean gagnant; // définit le vainqueur du combat, le pokemon1 gagne si c'est à true
	private String idPokemon1 = "84"; // Integer.toString(pokemon1.p_id)
	private String idPokemon2 = "78"; // Integer.toString(pokemon2.p_id)
	
	public Combat(boolean vainqueur /*,Pokemon pokemon1, Pokemon pokemon2*/){
		
		gagnant = vainqueur;

		
		
		
		String path = "./src/Images/arene.png"; //chemin de l'image de fond
		String pathpoke1 = "./PokemonData/Sprites/back/".concat(idPokemon1).concat(".png"); //chemin de l'image du pokemon1
		String pathpoke2 = "./PokemonData/Sprites/".concat(idPokemon2).concat(".png"); //chemin de l'image du pokemon2
		try {
			img = ImageIO.read(new File(path));
			imgpokemon1 = ImageIO.read(new File(pathpoke1));;
			imgpokemon2 = ImageIO.read(new File(pathpoke2));;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		while(nbTours > 0)
		{
			tour();
		}
		}
	
		private void tour()
		{
			int degats;
			
			nbTours --;
			
			if(nbTours == 0)
			{
				  if(initiative == 1)
				  {
					  if(gagnant == true )
					  {
						  System.out.println("Pokemon 1 inflige ".concat(Integer.toString(pdvPokemon2)).concat(" points de dégat"));
						  pdvPokemon2 = 0;		
						  System.out.println("Pokemon 1 gagne");
					  }
					  else
					  {
						  degats = (int)((Math.random()*100*pdvPokemon2)%pdvPokemon2);
						  System.out.println("Pokemon 1 inflige ".concat(Integer.toString(degats)).concat(" points de dégat"));
						  pdvPokemon2 -= degats ;
						  
						  System.out.println("Pokemon 2 inflige ".concat(Integer.toString(pdvPokemon1)).concat(" points de dégat"));
						  pdvPokemon1 = 0;		
						  System.out.println("Pokemon 2 gagne");
						  
					  }
				  }
			
				  else
				  {
					  if(gagnant == false )
					  {
						  System.out.println("Pokemon 2 inflige ".concat(Integer.toString(pdvPokemon1)).concat(" points de dégat"));
						  pdvPokemon1 = 0;		
						  System.out.println("Pokemon 2 gagne");
					  }
					  else
					  {
						  degats = (int)((Math.random()*100*pdvPokemon1)%pdvPokemon1);  
						  System.out.println("Pokemon 2 inflige ".concat(Integer.toString(degats)).concat(" points de dégat"));
						  pdvPokemon1 -= degats; 
						  
						  System.out.println("Pokemon 1 inflige ".concat(Integer.toString(pdvPokemon2)).concat(" points de dégat"));
						  pdvPokemon2 = 0;		
						  System.out.println("Pokemon 1 gagne");
					  }
				  }
			} 
      
			else
			{
			    	
					  if(initiative == 1)
					  {
						  degats = (int)((Math.random()*100*pdvPokemon2)%pdvPokemon2);
						  System.out.println("Pokemon 1 inflige ".concat(Integer.toString(degats)).concat(" points de dégat"));
						  pdvPokemon2 -= degats ; 
						  
						  degats = (int)((Math.random()*100*pdvPokemon1)%pdvPokemon1);  
						  System.out.println("Pokemon 2 inflige ".concat(Integer.toString(degats)).concat(" points de dégat"));
						  pdvPokemon1 -= degats; 
						  
						  
					  }
					  else
					  {
						  degats = (int)((Math.random()*100*pdvPokemon1)%pdvPokemon1);  
						  System.out.println("Pokemon 2 inflige ".concat(Integer.toString(degats)).concat(" points de dégat"));
						  pdvPokemon1 -= degats; 
						  
						  degats = (int)((Math.random()*100*pdvPokemon2)%pdvPokemon2);
						  System.out.println("Pokemon 1 inflige ".concat(Integer.toString(degats)).concat(" points de dégat"));
						  pdvPokemon2 -= degats ; 
					  }
			    	
	      

			}
			
		}
	
		public void paintComponent(Graphics g) 
		{ 
		
			Settings set = new Settings();
		
			g.drawImage(img, 0, 0, set.getWidth(), set.getHeight() - 50, null); 
			g.drawImage(imgpokemon1, set.getWidth()/10, set.getHeight() - (set.getHeight()/2),  set.getWidth()/3, set.getHeight()/2, null); 
			g.drawImage(imgpokemon2, set.getWidth() - ((set.getWidth()/5)*2), set.getHeight()/4,  set.getWidth()/3, set.getHeight()/3 , null); 
	    	repaint(); 
		} 
			
}

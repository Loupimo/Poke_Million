import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class AffichageCombat  extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img; //Image de fond
	private BufferedImage imgpokemon1; // Image du pokémxon1
	private BufferedImage imgpokemon2; // Image du pokémon2 d
//	private BufferedImage bouleup; 
//	private BufferedImage bouledown;
//	private int boulex ;
//	private int bouley ;
	
	public AffichageCombat(Pokemon joueur, Pokemon adversaire){
		System.out.println("affichage image");//d
		this.setVisible(true);
		try {
			img = ImageIO.read(new File("./src/Images/Arene.png"));
			imgpokemon1 = ImageIO.read(new File("./PokemonData/Sprites/back/" + joueur.getId() + ".png"));
			imgpokemon2 = ImageIO.read(new File("./PokemonData/Sprites/" + adversaire.getId() + ".png"));
			//bouleup = ImageIO.read(new File(pathbouleup));
			//bouledown = ImageIO.read(new File(pathbouledown));
			}
		catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
	/*	private void bouleAttaque(int attaquant) 		
		{
			Settings set = new Settings();
			int tmpboulex;
			attaque = attaquant;
			
			if(attaque == 1)
			{
				boulex = set.getWidth()/10 ;
				bouley =  set.getHeight() - (set.getHeight()/2)  ; 
				
				while(( boulex != set.getWidth()/10 && bouley != set.getHeight() - (set.getHeight()/2)) || (boulex != set.getWidth() - ((set.getWidth()/5)*2) && bouley != set.getHeight()/4))
				{
					tmpboulex = boulex;
					boulex = bouley/boulex ;
					bouley = boulex/bouley ;
					
					repaint();
				}
				
			}
			else
			{
				boulex = set.getWidth() - ((set.getWidth()/5)*2) - 1 ;
				bouley = set.getHeight()/4 + 1;
				
				while(( boulex != set.getWidth()/10 && bouley != set.getHeight() - (set.getHeight()/2)) || (boulex != set.getWidth() - ((set.getWidth()/5)*2) && bouley != set.getHeight()/4))
				{
					tmpboulex = boulex;
					boulex = bouley/boulex ;
					bouley = boulex/bouley ;
					
					repaint();
				}
			}
			
			

			
		}*/
	

		
		public void setImgPokemon1(int id)
		{
			try{
				imgpokemon1 = ImageIO.read(new File("./PokemonData/Sprites/back/" + id + ".png"));
			}
			catch (IOException e) {
				e.printStackTrace();	
			}
			repaint();
		}
	
		public void setImgPokemon2(int id)
		{
			try{
				imgpokemon2 = ImageIO.read(new File("./PokemonData/Sprites/back/" + id + ".png"));
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

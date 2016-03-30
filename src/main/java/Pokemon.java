import java.util.LinkedList;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

public class Pokemon {
	
	// ################################################
	// ################# Attributs ####################
	// ################################################
	
	private static final Logger LOGGER = Logger.getLogger(Pokemon.class);
	private int Id;
	private String name;
	private ImageIcon sprite;
	private LinkedList<String> typeList;
	private LinkedList<String> weeknessList;
	private int healthPoint;
	private int attack;
	private int speed;
	private int defense;

	// ################################################
	// ############### Constructeurs ##################
	// ################################################
	
	public Pokemon(int p_Id, String p_name, String spritePath, LinkedList<String> p_type, LinkedList<String> p_weekness)
	{
		this.Id = p_Id;
		this.name = p_name;
		this.sprite = new ImageIcon(spritePath);
		this.typeList = p_type;
		this.weeknessList = p_weekness;
	}

	public Pokemon(int p_Id, String p_name, ImageIcon p_sprite, String p_type, String p_weekness)
	{
		this.Id = p_Id;
		this.name = p_name;
		this.sprite = p_sprite;
		this.typeList = new LinkedList<String> ();
		this.typeList.add(p_type);
		this.weeknessList = new LinkedList<String> ();
		this.weeknessList.add(p_type);	
	}
	
	public Pokemon(Pokemon poke)
	{
		this.Id = poke.getId();
		this.name = poke.getName();
		this.sprite = poke.getSprite();
		this.typeList = new LinkedList<String> (poke.getTypeList());
		this.weeknessList = new LinkedList<String> (poke.getWeeknessList());
		this.speed = (int) App.myCSV.getValueOfAList(1,0);
		LOGGER.error(speed);
		this.defense = 25;
	}
	
	// ################################################
	// ############## Methodes diverses ###############
	// ################################################
	
	public int giveDamageByWeekness (int originalDamage, Pokemon target)
	{ //Multiplie les dégâts que la cible subit si les types du pokémon l'attaquant sont les mêmes que ses faiblesses
		int totalDamage = originalDamage;
		for (String targetWeekness : target.getWeeknessList())
		{
			LOGGER.debug ("Faiblesse du Pokémon cible : " + targetWeekness);
			for (String thisType : this.typeList)
			{
				LOGGER.debug ("Type du Pokémon : " + thisType);
				if (targetWeekness.equals(thisType))
				{
					totalDamage *= 2;
					break;
				}
			}
		}
		return totalDamage;
	}
	
	// ################################################
	// ################### Getters ####################
	// ################################################
	
	public int getId() {
		return this.Id;
	}

	public String getName() {
		return this.name;
	}

	public ImageIcon getSprite() {
		return this.sprite;
	}
	
	public LinkedList<String> getTypeList ()
	{
		return this.typeList;
	}
	
	public LinkedList<String> getWeeknessList ()
	{
		return this.weeknessList;
	}
	
	public int getHealthPoint ()
	{
		return this.healthPoint;
	}
	
	public int getAttack ()
	{
		return this.attack;
	}
	
	// ################################################
	// ################### Setters ####################
	// ################################################
	
	public void setHealthPoint (int p_healthPoint)
	{
		this.healthPoint = p_healthPoint;
	}
	
	public void setAttack (int p_Attack)
	{
		this.attack = p_Attack;
	}
}

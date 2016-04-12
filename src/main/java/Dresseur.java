import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;


public class Dresseur {

	private LinkedList <Pokemon> equipe;
	private String nom;

	private static final Logger LOGGER = Logger.getLogger(Dresseur.class);
	
	
	
	/**********CONSTRUCTEUR STANDART***************/
	public Dresseur (String nom)
	{
		LOGGER.debug("Constructeur Dresseur");
		equipe = new LinkedList<Pokemon>();
		this.nom=nom;
	}
	
	public Dresseur (String nom,int n)
	{
		LOGGER.debug("Constructeur Dresseur");
		equipe = new LinkedList<Pokemon>();
		equipe = getRandomEquipe(n);
		this.nom=nom;
	}
	
	
	/***********CONSTRUCTEUR PERSO TAB[](NUMERO,ATTACK,DEFENSE,SPEED,HP)************/
	public Dresseur (String nom,int size,int tab[][])
	{
		LOGGER.debug("Constructeur Dresseur");
		equipe = new LinkedList<Pokemon>();
		this.nom=nom;
		getEquipe(size,tab);
	}
	
	public  LinkedList<Pokemon> GetEquipe()
	{
		LOGGER.debug("GetEquipe");
		return equipe;
	}
	
	
	public LinkedList<Pokemon> getRandomEquipe(int size) throws IllegalArgumentException
	{
		LOGGER.debug("getRandaomEquipe");
		LinkedList<Pokemon> result = new LinkedList<Pokemon>();
		if (size <6 && size >0)
		{
			int i;
			for (i=0;i<size;i++)
			{
				result.add(GetRandomPokemon());
			}
			return result;
		}
		throw new IllegalArgumentException("taille trop grande");
	}
	
	private void getEquipe(int size, int[][]tab) throws IllegalArgumentException
	{
		LOGGER.debug("getEquipe");
		if (size <6 && size >0)
		{
			int i;
			for (i=0;i<size;i++)
			{
				equipe.add(GetPokemon(tab[i][0],tab[i][1],tab[i][2],tab[i][3],tab[i][4]));
			}
			return;
		}
		throw new IllegalArgumentException("taille trop grande");
	}

	
	private Pokemon GetRandomPokemon() {
		List<Integer> randomIndex= new ArrayList<>();
		for(int i = 0; i<222;i++)
		{
			randomIndex.add(i);
		}
		Collections.shuffle(randomIndex);
		int index = (int) Math.random()*221;
		index = randomIndex.get(index);
		Pokemon result = new Pokemon(App.data.getPokelist().get((int)App.myCSV.getValueOfAList(0, index)%151));
		
		int attack = (int) Math.random()*221;
		attack = randomIndex.get(attack);
		attack = (int)App.myCSV.getValueOfAList(1, attack);
		
		int HP = (int) Math.random()*221;
		HP = randomIndex.get(HP);
		HP = 3*(int)App.myCSV.getValueOfAList(2, HP);
		
		int speed = (int) Math.random()*221;
		speed = randomIndex.get(speed);
		speed = (int)App.myCSV.getValueOfAList(3, speed);
		
		result.setCaract(attack, 10, speed, HP);
		return result;
	}
	
	
	private Pokemon GetPokemon(int num,int HP,int attack,int speed,int defense) throws IllegalArgumentException
	{
		if(num>-1&&num<151)
		{
		Pokemon result = new Pokemon(App.data.getPokelist().get(num));
		
		result.setCaract(attack, defense, speed, HP);
		return result;
		}
		throw new IllegalArgumentException ("mauvais ID");
	}

	public void InfoEquipe()
	{
		int size = equipe.size();
		System.out.println("\nEQUIPE DRESSEUR "+nom+"\nNombre de POKEMON :"+size);
		for (int i =0; i <size;i++)
		{
			System.out.printf("===============Pokemon n°"+(i+1)+"===============");
			System.out.println("\n"+equipe.get(i).getName()+"\t\tHP :"+equipe.get(i).getHealthPoint()+"\tAttack :"+equipe.get(i).getAttack()+ "\tDefense :"+equipe.get(i).getDefense()+"\tSpeed : "+equipe.get(i).getSpeed());
		}
	}


	public boolean hasPokemon() {
		boolean result = false;
		for (Pokemon curr : equipe)
		{
			if (curr.getHealthPoint() > 0)
			{
				return true;
			}
		}
		return result;
	}


	public Pokemon getPokemon(int id) throws IndexOutOfBoundsException {
		if(equipe.isEmpty())
		{
			throw new IndexOutOfBoundsException("plus de pokemon");
		}
		return equipe.get(id);
	}


	public void deleteFirst() {
		equipe.poll();
	}

	public String getName()
	{
		return this.nom;
	}

	public void soin() {
		for (Pokemon curr : equipe)
		{
			curr.resetHP();
		}
		
	}
}

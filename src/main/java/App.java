import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class App {
	protected static String[] suffixes = { "csv" };
	protected static String[] descriptions = { "Fichiers CSV (.csv)" };
	public static DataLoader data;
	public static CSVFile myCSV;
	public static CSVFilter filtre = new CSVFilter(suffixes, descriptions);
	public static MainFrame mainFrame;

	public static void main(String[] args) {
		
		try {
			//Définit le style de la fenêtre de sélection avec celui du system
			//
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		data = new DataLoader ();
		data.init(new File ("PokemonData/Pokedex.csv"));
		
		mainFrame = new MainFrame();
		myCSV= new CSVFile();
		myCSV.init(new File("PokemonData/euromillions_3.csv"));
		
		
		/*
		//***************************** TESTER QUE TOUTES LES VALEUR SONT POSSIBLE (0-150)
		LinkedList<?> tata = myCSV.getListByIndex(0);
		
		int valeur=0;
		ListIterator<Integer> ITata = (ListIterator<Integer>) tata.listIterator();
		
		while (ITata.hasNext())
		{
			valeur = ITata.next()%151;
			ITata.set(valeur);
			System.out.println(""+valeur);
		}
		//********************************************/
		
		
		
		//Pokemon premier = new Pokemon(data.getPokelist().get(1));
		/************generation aleatoire************/
		/*Dresseur sacha = new Dresseur ("sacha");
		sacha.InfoEquipe();
		
		/**************génération sélectionné-***********/
		/*int tab[][]={{0,10,10,10,10},{150,15,32,15,2}};
		Dresseur regis = new Dresseur("regis",2,tab);
		regis.InfoEquipe();
		
		*/
		//TourDeCombat ligue = new TourDeCombat("sacha");
		
		
			
		
	}

	
}

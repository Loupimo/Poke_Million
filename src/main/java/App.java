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
		new Pokemon(data.getPokelist().get(1));
	
		
	}

	
}

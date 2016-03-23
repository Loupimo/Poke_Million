import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;

public class CSVFile 
{
	
	// ################################################
	// ################# Attributs ####################
	// ################################################
	
	private LinkedList<LinkedList<?>> memAllList;
	private File csvFile;
	private LinkedList<String> entetes;
	private static final Logger LOGGER = Logger.getLogger(CSVFileTest.class);
	
	// ################################################
	// ############### Constructeurs ##################
	// ################################################
	
	public CSVFile ()
	{
		this.memAllList = new LinkedList<LinkedList<?>> ();
	}
	
	// ################################################
	// ############## Methodes diverses ###############
	// ################################################
	
	public JTable printCSVFile ()
	{
		if (this.csvFile == null) return null;
		JTable myTable = new JTable ();
		String[] finalEntetes = new String[this.entetes.size()];
		int count = 0;
		for (String entete : this.entetes)
		{
			//On met la première lettre en majuscule et on remplace les "_" par des espaces
			finalEntetes[count] = ((entete.charAt(0)+"").toUpperCase() + entete.substring(1)).replace("_", " ");
			count++;
		}
		DefaultTableModel model = new DefaultTableModel(null, finalEntetes)
		{
			//Permet de récupérer le type de données de la colone (exemple: pour effectuer un tri selon des entiers)
			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
			}
		};
		
		//Définit le nombre de lignes
		for (int i = 0; i < this.memAllList.get(0).size(); i ++)
		{
			Vector<Object> donnees = new Vector<Object>();
			model.addRow(donnees);
		}
		
		myTable.setModel(model);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(myTable.getModel());
		myTable.setRowSorter(sorter);
		
		// Remplit le tableau de données de façon dynamique
		int i = 0, j;
		for (LinkedList <?> list : this.memAllList)
		{
			for (j = 0; j < list.size(); j++)
			{
				LOGGER.debug ("i = " + i + ", j = " + j + ", value = " + this.getValueOfAList(list, j));
				myTable.setValueAt(this.getValueOfAList(list, j), j, i);
			}
			i++;
		}
		myTable.setVisible(true);
		
		return myTable;
	}
	
	// ################################################
	// ################### Setters ####################
	// ################################################
		
	@SuppressWarnings("unchecked")
	public void init (File p_csvFile)
	{ //Récupère certains champs du fichier CSV placé en paramètre afin de créer la base de donnée pour le combat
		this.csvFile = p_csvFile;
		try {
			List<String> lignes = this.getLignesFromFile();
			String separator = ";";
			LinkedList<Integer> intList; //Permet de modifier les listes de type "int" de la liste memAllListe
			LinkedList<Float> floatList; //Permet de modifier les listes de type "float" de la liste memAllListe
			this.entetes = new LinkedList<String>();
			
			String[] values = lignes.get(0).split(separator);
			
			//Des listes d'indexes
			LinkedList<Integer> indexOfIntValue = new LinkedList<Integer> (), 
								indexOfFloatValue = new LinkedList<Integer> ();
			
			//Des compteurs
			int intCounter = 0, floatCounter = 0;
			
			for (int i = 0; i < values.length; i++)
			{ //Ici on récupère toutes les entêtes identiques à celles des "if" ainsi que leur index dans le fichier CSV
				
				if (values[i].equals("annee_numero_de_tirage") || values[i].equals("boule_1") || values[i].equals("boule_2") || values[i].equals("boule_3"))
				{ //On mémorise l'index des colones de type "int"
					entetes.add(values[i]);
					indexOfIntValue.add(i);
				}
				
				else if(values[i].equals("rapport_du_rang6") || values[i].equals("rapport_du_rang7") || values[i].equals("rapport_du_rang8"))
				{ //On mémorise l'index de colones de type "float"
					entetes.add(values[i]);
					indexOfFloatValue.add(i);
				}
			}
			
			//Des itérateurs pour parcourir les listes d'indexes
			ListIterator<Integer> intIterator = indexOfIntValue.listIterator(),
							  	  floatIterator = indexOfFloatValue.listIterator();
			
			while (intIterator.hasNext())
			{ //Ici on créer le nombre de listes de type "int" dont on a besoin
				intIterator.next();
				this.memAllList.add(new LinkedList<Integer>());
			}

			while (floatIterator.hasNext())
			{ //Ici on créer le nombre de listes de type "float" dont on a besoin
				floatIterator.next();
				this.memAllList.add(new LinkedList<Float>());
			}
			
			lignes.remove(0);
			
			for (String ligne : lignes) 
			{ //Parcourt toutes les lignes restantes du fichier CSV
				
				values = ligne.split(separator);
				
				//On réinitialise les compteurs et les itérateurs à chaque boucles afin de tout initialiser correctement
				intCounter = 0;
				intIterator = indexOfIntValue.listIterator();
				
				while (intIterator.hasNext())
				{
					intList = (LinkedList<Integer>) this.memAllList.get(intCounter);
					intList.add(Integer.parseInt(values[intIterator.next()]));
					intCounter++;
				}

				floatCounter = intCounter;
				floatIterator = indexOfFloatValue.listIterator();
				
				while (floatIterator.hasNext())
				{
					floatList = (LinkedList<Float>) this.memAllList.get(floatCounter);
					floatList.add(Float.parseFloat(values[floatIterator.next()]));
					floatCounter++;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private List<String> getLignesFromFile() throws Exception {

		List<String> lignes = new ArrayList<String>();
		
		FileReader fr = new FileReader (this.csvFile);
		BufferedReader br = new BufferedReader(fr);

		for (String ligne = br.readLine(); ligne != null; ligne = br.readLine()) {

			// Suppression des espaces en trop
			ligne = ligne.trim();

			// Filtre des lignes vides
			if (ligne.isEmpty()) {
				continue;
			}

			// Filtre des lignes de commentaire
			if (ligne.startsWith("#")) {
				continue;
			}
			
			//Remplace toutes "," par des "." pour pouvoir effectuer une conversion en float plus tard
			ligne = ligne.replace(",", ".");

			//LOGGER.debug(ligne);
			lignes.add(ligne);
		}

		br.close();

		return lignes;
	}
	
	// ################################################
	// ################### Getters ####################
	// ################################################
	
	public LinkedList<?> getListByIndex (int index)
	{
		return this.memAllList.get(index);	
	}
	
	public Object getValueOfAList (LinkedList<?> list, int index)
	{
		if (list.get(index) instanceof Integer)
		{
			int result = (int) list.get(index);
			return result;
		}
		else if (list.get(index) instanceof Float)
		{
			float result = (float) list.get(index);
			return result;
		}
		return null;
	}
	
	public Object getValueOfAList (int indexList, int index)
	{
		LinkedList<?> list = this.memAllList.get(indexList);
		if (list.get(index) instanceof Integer)
		{
			int result = (int) list.get(index);
			return result;
		}
		else if (list.get(index) instanceof Float)
		{
			float result = (float) list.get(index);
			return result;
		}
		return null;
	}
	
	public LinkedList<?> getMemAllList ()
	{
		return this.memAllList;
	}
	
	public LinkedList<String> getEntetesList ()
	{
		return this.entetes;
	}
	
}

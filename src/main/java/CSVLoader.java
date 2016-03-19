import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;

public class CSVLoader {
	
	public CSVLoader ()
	{
	}
	
	public File getSelectedFileWithExtension(JFileChooser fileChooser) //Ajoute l'extension du filtre sélectionné si l'utilisateur ne l'a pas spécifié
	{
	    File file = fileChooser.getSelectedFile();
	    if (fileChooser.getFileFilter() instanceof FileNameExtensionFilter) //Vérifie que le fileChooser possède au moins un filtre
	    {
	        String[] exts = ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions();
	        String nameLower = file.getName().toLowerCase();
	        for (String ext : exts) //Vérifie s'il possède déjà une extension valide
	        { 
	            if (nameLower.endsWith('.' + ext.toLowerCase())) 
	            {
	                return file; //On renvoie le fichier tel quel
	            }
	        }
	        //Sinon on lui ajoute l'extension sélectionnée
	        file = new File(file.toString() + '.' + exts[0]);
	    }
	    return file;
	}
	
	public void saveFile() //Fonction de sauvegarde
	{
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setDialogTitle("Choisissez l'emplacement de sauvegarde");
		App.filtre.addFiltre(fileChooser); //On définit les formats de sauvegardes autorisés
		fileChooser.setAcceptAllFileFilterUsed(false); //On empêche la sélection de "tous les formats" 
		int userSelection = fileChooser.showSaveDialog(null); //Affiche une nouvelle fenêtre permettant de choisir l'emplacement ainsi que le nom de la sauvegarde
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) //L'utilisateur à cliqué sur enregistrer
		{
		    File fileToSave = getSelectedFileWithExtension(fileChooser); //On récupère le nom du fichier
		    System.out.println("Save as file: " + fileToSave.getName());
		 
			try 
			{
	    		PrintWriter pw;
				pw = new PrintWriter (new BufferedWriter(new FileWriter(fileToSave)));
				
				//do something
		    	
		    	pw.close(); //Ferme le printer et libère donc l'accès au fichier
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}			    	
		}
	}
	
	public void loadFile() //Fonction de chargement
	{
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setDialogTitle("Choisissez la partie à charger");
		App.filtre.addFiltre(fileChooser); //On définit les formats autorisés
		fileChooser.setAcceptAllFileFilterUsed(false); //On empêche la sélection de "tous les formats"
		int userSelection = fileChooser.showOpenDialog(null); //Affiche une nouvelle fenêtre permettant de choisir le fichier à charger
		
		if (userSelection == JFileChooser.APPROVE_OPTION) //L'utilisateur à cliqué sur ouvrir
		{
		    File fileToLoad = getSelectedFileWithExtension(fileChooser); //On récupère le nom du fichier
		    System.out.println("Loaded file: " + fileToLoad.getName());
		    
			try 
			{
				BufferedReader br = new BufferedReader (new FileReader (fileToLoad)); //On utilise un buffer pour lire dans le fichier
				 // do something
		    	br.close(); //ferme le buffer et libère donc l'accès au fichier
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}			    	
		}
	}
}

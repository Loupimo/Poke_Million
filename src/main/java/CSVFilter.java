import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CSVFilter extends FileFilter
{	
	protected String [] lesSuffixes;
	protected String [] laDescription;
	
	public CSVFilter (String []lesSuffixes, String []laDescription)
	{
		this.lesSuffixes = lesSuffixes;
	    this.laDescription = laDescription;
	}
	
	public void addFiltre (JFileChooser fileChooser) //Ajoute le filtre à la fenêtre de choix des fichiers
	{
		for (int i = 0; i < lesSuffixes.length; i++)
	    {
	    	FileNameExtensionFilter Ext = new FileNameExtensionFilter(laDescription[i], lesSuffixes[i]);
	    	fileChooser.addChoosableFileFilter(Ext);
	    }
	}
	
	boolean appartient (String suffixe) //Vérifie si l'extension en paramètre fait partie du filtre 
	{
	      for (int i = 0; i < lesSuffixes.length; i++) if(suffixe.equals(lesSuffixes[i])) return true; //On compare l'extension placée en paramètre avec celles du filtre
	      return false;
	}
	
	public boolean accept(File f) //Définit les extensions autorisées
	{
		if (f.isDirectory())  return true;
		String suffixe = null;
	    String s = f.getName(); //Récupère le nom du fichier
	    int i = s.lastIndexOf('.'); //L'index du dernier "." du nom du fichier
	    if(i > 0 &&  i < s.length() - 1) suffixe = s.substring(i+1).toLowerCase(); //Récupère l'extension entrée s'il y en a une
	    return suffixe != null && appartient(suffixe); //Définit si l'extension récupérée est valide ou non
	}
	   
	public String getDescription() //Récupère la description de l'extension
	{
		return laDescription[0];
	}    
}

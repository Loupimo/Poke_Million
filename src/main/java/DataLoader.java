import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataLoader {
	
	// ################################################
	// ################# Attributs ####################
	// ################################################
	
	protected File data;
	protected LinkedList<Pokemon> listePoke;
	private List<String> lignes;
	
	// ################################################
	// ############### Constructeurs ##################
	// ################################################
	
	public DataLoader ()
	{
		this.listePoke = new LinkedList<Pokemon> ();
	}
	
	// ################################################
	// ############## Methodes diverses ###############
	// ################################################
	
	public void init (File file)
	{
		this.data = file;
	}
	
	private List<String> getLignesFromFile() throws Exception {

		final List<String> lignes = new ArrayList<String>();

		final FileReader fr = new FileReader(this.data);
		final BufferedReader br = new BufferedReader(fr);

		for (String ligne = br.readLine(); ligne != null; ligne = br.readLine()) {

            // Suppression des espaces en trop
            ligne = ligne.trim();

            // Filtre des lignes vides
            if(ligne.isEmpty()) {
                continue;
            }

            // Filtre des lignes de commentaire
            if(ligne.startsWith("#")) {
                continue;
            }

            lignes.add(ligne);
        }
		
		br.close();
        fr.close();
        
		return lignes;
	}
	
	// ################################################
	// ############# Getters et setters ###############
	// ################################################
	
	public void setPokeList () throws Exception
	{
		if (this.data != null)
		{
			this.lignes = getLignesFromFile();
			this.lignes.remove(0);
			String separator = ";";
			
			for (String ligne: this.lignes)
			{
				String[] values = ligne.split(separator);
				this.listePoke.add(new Pokemon (Integer.parseInt(values[0]), values[1], values[2]));
			}
		}
	}
	
	public LinkedList<Pokemon> getPokelist()
	{
		return this.listePoke;
	}
}

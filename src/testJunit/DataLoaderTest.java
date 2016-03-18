import static junit.framework.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;


public class DataLoaderTest {

	private final static String RESOURCES_PATH = "PokemonData/";
	private final static String POKEDEX_FILE_NAME = "Pokedex.csv";
	
	
	protected DataLoader data;
	
	@Before
    public void doBefore() throws Exception {
        System.out.println("doBefore Debut");

        File file = new File(RESOURCES_PATH + POKEDEX_FILE_NAME);
        if (file != null) 
        {
        	System.out.println(file.getPath());
        }
        data = new DataLoader();
        data.init(file);
        data.setPokeList();

        System.out.println("doBefore Fin");
    }
	
	@Test
	public void poke151Test ()
	{
		assertEquals(151, data.getPokelist().size());
	}
	
	@Test
	public void premierPokeEstBulBizarre ()
	{
		final int index = 0;
		
		assertEquals("Bulbizarre", data.getPokelist().get(index).getName());
	}
	
	@Test
	public void poke50EstTaupiqueur ()
	{
		final int index = 49;
		
		assertEquals("Taupiqueur", data.getPokelist().get(index).getName());
	}
	
	@Test
	public void dernierPokeEstMew ()
	{
		final int index = 150;
		
		assertEquals("Mew", data.getPokelist().get(index).getName());
	}
}

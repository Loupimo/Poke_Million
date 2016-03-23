import static junit.framework.Assert.assertEquals;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class PokemonTest {

	private final static String RESOURCES_PATH = "PokemonData/";
	private final static String POKEDEX_FILE_NAME = "Pokedex.csv";
	
	private static final Logger LOGGER = Logger.getLogger(DataLoaderTest.class);
	
	
	protected DataLoader data;
	
	@Before
    public void doBefore() throws Exception {
		LOGGER.debug("doBefore Debut");

        File file = new File(RESOURCES_PATH + POKEDEX_FILE_NAME);
        if (file != null) 
        {
        	LOGGER.debug(file.getPath());
        }
        data = new DataLoader();
        data.init(file);

        LOGGER.debug("doBefore Fin");
    }
	
	@Test
	public void testFightBetween2PokemonNoWeekness ()
	{
		Pokemon attaquant = data.getPokelist().get(0);
		assertEquals(20, attaquant.giveDamageByWeekness(20, data.getPokelist().get(150)));
	}
	
	@Test
	public void testFightBetween2PokemonWith1Weekness ()
	{
		Pokemon attaquant = data.getPokelist().get(0);
		assertEquals(40, attaquant.giveDamageByWeekness(20, data.getPokelist().get(26)));
	}
	
	@Test
	public void testFightBetween2PokemonWith2Weekness ()
	{
		Pokemon attaquant = data.getPokelist().get(94);
		assertEquals(80, attaquant.giveDamageByWeekness(20, data.getPokelist().get(3)));
	}
}

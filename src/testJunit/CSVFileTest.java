import static junit.framework.Assert.assertEquals;

import java.io.File;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class CSVFileTest {
private static final Logger LOGGER = Logger.getLogger(CSVFileTest.class);
	
	
	protected CSVFile myCSV;
	
	@Before
    public void doBefore() throws Exception {
		LOGGER.debug("doBefore Debut");

        File file = new File("PokemonData/euromillions_3.csv");
        if (file != null) 
        {
        	LOGGER.debug(file.getPath());
        }
        myCSV = new CSVFile ();
        myCSV.init(file);
        LOGGER.debug("doBefore Fin");
    }
	
	@Test
	public void testSizeMemAllList ()
	{
		assertEquals(7, myCSV.getMemAllList().size());
	}
	
	@Test
	public void testResultOfFirstList ()
	{
		int expectedResult = 2016022, result;
		@SuppressWarnings("unchecked")
		LinkedList<Integer> myIntList = (LinkedList<Integer>) myCSV.getListByIndex(0);
		result = myIntList.get(0);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testResultOfLastList ()
	{
		float expectedResult = 20.8f, result;
		@SuppressWarnings("unchecked")
		LinkedList<Float> myFloatList = (LinkedList<Float>) myCSV.getListByIndex(6);
		result = myFloatList.get(0);
		assertEquals(expectedResult, result);
	}
}

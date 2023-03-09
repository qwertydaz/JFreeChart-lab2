package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities 
{
	private Values2D values2D;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception 
	{
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
	}

	@After
	public void tearDown() throws Exception 
	{
		values2D = null;
	}

	@Test
	public void testValidDataAndColumnColumnTotal() 
	{
		assertEquals("Wrong sum returned. It should be 5.0",
						5.0, DataUtilities.calculateColumnTotal(values2D,  0), 0.0000001d);
	}
	
	@Test // getCumlativePrecentages Function
	public void testgetCumlativePercenetages() 
	{
		// setup
		
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 6.0);
		keyvalues.addValue((Comparable) 1.0, 11.0);
		keyvalues.addValue((Comparable) 2.0, 3.0);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages((KeyedValues) keyvalues);
		
		assertEquals((double) object_under_test.getValue(2), 1.0, .000000001d);
	}
	
	@Test // calculateColumnTotal function
	public void testNullDataColumnTotal()
	{
		try
		{
			DataUtilities.calculateColumnTotal(null,  0);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
					 			
		}
		catch (Exception e)
		{
			assertTrue("Incorrest exception type thrown",
					e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test // calculateRowTotal
	public void testNullDataRowTotal()
	{
		try
		{
			DataUtilities.calculateRowTotal(null,  0);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
					 			
		}
		catch (Exception e)
		{
			assertTrue("Incorrest exception type thrown",
					e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test // createNumberArray 
	public void testNullcreateNumberArray()
	{
		try
		{
			DataUtilities.createNumberArray(null);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
					 			
		}
		catch (Exception e)
		{
			assertTrue("Incorrest exception type thrown",
					e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test // createNumberArray2D 
	public void testNullcreateNumberArray2D()
	{
		try
		{
			DataUtilities.createNumberArray2D(null);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
					 			
		}
		catch (Exception e)
		{
			assertTrue("Incorrest exception type thrown",
					e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test // getCumulativePercentages 
	public void testNullgetCumulativePercentages()
	{
		try
		{
			DataUtilities.getCumulativePercentages(null);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
					 			
		}
		catch (Exception e)
		{
			assertTrue("Incorrest exception type thrown",
					e.getClass().equals(IllegalArgumentException.class));
		}
	}

}

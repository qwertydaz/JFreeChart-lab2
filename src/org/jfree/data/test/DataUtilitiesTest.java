package org.jfree.data.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

import org.jfree.data.category.DefaultCategoryDataset;

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
	
	@Test // calculateColumnTotal function
	public void testZeroBasedColumnIndex()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(6, "Row 2", "Column 2");
		dataset.addValue(7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int column = 0;
		double expectedTotal = 14.0;
		double delta = 0.0001;
		
		double actualTotal = DataUtilities.calculateColumnTotal(dataset, column);
		
		assertEquals(expectedTotal, actualTotal, delta);
	}
	
	@Test // calculateColumnTotal function 
	public void testOutOfBoundsColumnIndex()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(6, "Row 2", "Column 2");
		dataset.addValue(7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int columnIndex = 4;
		
		try {
	        DataUtilities.calculateColumnTotal(dataset, columnIndex);
	        fail("Expected IndexOutOfBoundsException was not thrown");
	    } catch (IndexOutOfBoundsException e) {
	        
	    }
	}
	
	@Test // calculateColumnTotal function
	public void testValidInput()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(6, "Row 2", "Column 2");
		dataset.addValue(7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int column = 2;
		double expectedTotal = 13.0;
		double delta = 0.0001;
		
		double actualTotal = DataUtilities.calculateColumnTotal(dataset, column);
		
		assertEquals(expectedTotal, actualTotal, delta);
	}
	
	@Test // calculateColumnTotal function 
	public void testOutOfBoundsColumnNegativeIndex()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(6, "Row 2", "Column 2");
		dataset.addValue(7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int columnIndex = -3;
		
		try {
	        DataUtilities.calculateColumnTotal(dataset, columnIndex);
	        fail("Expected IndexOutOfBoundsException was not thrown");
	    } catch (IndexOutOfBoundsException e) {
	        
	    }
	}
	
	@Test // calculateColumnTotal function
	public void testTableWithNegatives()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(-6, "Row 2", "Column 2");
		dataset.addValue(-7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int column = 1;
		double expectedTotal = 1.0;
		double delta = 0.0001;
		
		double actualTotal = DataUtilities.calculateColumnTotal(dataset, column);
		
		assertEquals(expectedTotal, actualTotal, delta);
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
	
	@Test // calculateRowTotal function
	public void testZeroBasedRowIndex()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(6, "Row 2", "Column 2");
		dataset.addValue(7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int row = 0;
		double expectedTotal = 6.0;
		double delta = 0.0001;
		
		double actualTotal = DataUtilities.calculateRowTotal(dataset, row);
		
		assertEquals(expectedTotal, actualTotal, delta);
	}
	
	@Test // calculateRowTotal function
	public void testOutOfBoundsRowIndex()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(6, "Row 2", "Column 2");
		dataset.addValue(7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int rowIndex = 3;
		
		try {
	        DataUtilities.calculateRowTotal(dataset, rowIndex);
	        fail("Expected IndexOutOfBoundsException was not thrown");
	    } catch (IndexOutOfBoundsException e) {
	        
	    }
	}
	
	@Test // calculateRowTotal function
	public void testValidRowInput()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(6, "Row 2", "Column 2");
		dataset.addValue(7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int rowIndex = 2;
		double expectedTotal = 16.0;
		double delta = 0.0001;
		
		double actualTotal = DataUtilities.calculateRowTotal(dataset, rowIndex);
		
		assertEquals(expectedTotal, actualTotal, delta);
	}
	
	@Test // calculateRowTotal function 
	public void testOutOfBoundsRowNegativeIndex()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(6, "Row 2", "Column 2");
		dataset.addValue(7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int rowIndex = -3;
		
		try {
	        DataUtilities.calculateRowTotal(dataset, rowIndex);
	        fail("Expected IndexOutOfBoundsException was not thrown");
	    } catch (IndexOutOfBoundsException e) {
	        
	    }
	}
	
	@Test // calculateColumnTotal function
	public void testTableWithNegativesRow()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1, "Row 1", "Column 1");
		dataset.addValue(2, "Row 1", "Column 2");
		dataset.addValue(3, "Row 1", "Column 3");
		dataset.addValue(5, "Row 2", "Column 1");
		dataset.addValue(-6, "Row 2", "Column 2");
		dataset.addValue(-7, "Row 2", "Column 3");
		dataset.addValue(8, "Row 3", "Column 1");
		dataset.addValue(5, "Row 3", "Column 2");
		dataset.addValue(3, "Row 3", "Column 3");
		int rowIndex = 1;
		double expectedTotal = -8.0;
		double delta = 0.0001;
		
		double actualTotal = DataUtilities.calculateRowTotal(dataset, rowIndex);
		
		assertEquals(expectedTotal, actualTotal, delta);
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
	
	@Test // createNumberArray
	public void testValidCreateNumberArray()
	{
		double[] data = {1.0, 2.0, 3.4, 5.6};
		Number[] expected = {1.0, 2.0, 3.4, 5.6};
	    Number[] actual = DataUtilities.createNumberArray(data);
	    assertArrayEquals(expected, actual);
	}
	
	@Test // createNumberArray
	public void testLetterInputCreateNumberArray()
	{
		double[] data = {'a','b','c','d'};
		
		try
		{
			DataUtilities.createNumberArray(data);
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
	
	@Test // createNumberArray2D
	public void testValidCreateNumberArray2d()
	{
		double[][] data = {{1.0, 2.0}, {3.4, 5.6}, {7.8,9.6}};
		Number[][] expected = {{1.0, 2.0}, {3.4, 5.6}, {7.8,9.6}};
	    Number[][] actual = DataUtilities.createNumberArray2D(data);
	    assertArrayEquals(expected, actual);
	}
	
	@Test // createNumberArray2D
	public void testLetterInputCreateNumberArray2D()
	{
		double[][] data = {{'a','b'},{'c','d'}};
		
		try
		{
			DataUtilities.createNumberArray2D(data);
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

	@Test // getCumlativePercentages()
	public void testValidGetCumulativePercentage()
	{
		KeyedValues values = new DefaultKeyedValues();
	    ((DefaultKeyedValues) values).addValue("A", 1);
	    ((DefaultKeyedValues) values).addValue("B", 2);
	    ((DefaultKeyedValues) values).addValue("C", 3);
	    ((DefaultKeyedValues) values).addValue("D", 4);
	    ((DefaultKeyedValues) values).addValue("E", 5);
	    
	    KeyedValues expected = new DefaultKeyedValues();
	    ((DefaultKeyedValues) expected).addValue("A", 0.2);
	    ((DefaultKeyedValues) expected).addValue("B", 0.4);
	    ((DefaultKeyedValues) expected).addValue("C", 0.6);
	    ((DefaultKeyedValues) expected).addValue("D", 0.8);
	    ((DefaultKeyedValues) expected).addValue("E", 1.0);
	    
	    
	    KeyedValues actual = DataUtilities.getCumulativePercentages(values);
	    
	    for (int i = 0; i < expected.getItemCount(); i++) {
	        Comparable key = expected.getKey(i);
	        double expectedValue = expected.getValue(key).doubleValue();
	        double actualValue = actual.getValue(key).doubleValue();
	        assertEquals(expectedValue, actualValue, 0.0001);
	    }
	
	}	
		
	}

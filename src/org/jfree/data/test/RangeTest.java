package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jfree.data.Range;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest
{
	private Range rangeObjectUnderTest;
	private Range rangeObjectUnderTest2;

	@Before
	public void setUp() throws Exception
	{
		rangeObjectUnderTest = new Range(-1, 1);
		rangeObjectUnderTest2 = new Range(4, 9);
	}

	@After
	public void tearDown() throws Exception
	{
	}
	
	// EXAMPLE TESTS
	
	@Test
	public void testCentralValueShouldBeZero()
	{
		assertEquals("The central value of -1 and 1 should be 0",
				0,
				rangeObjectUnderTest.getCentralValue(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthBothPositiveAndEqual()
	{
		Range r1 = new Range(2, 2);
		assertEquals("getLength: Did not return the expected output",
				0.0,
				r1.getLength(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthBothPositiveAndNotEqual()
	{
		Range r2 = new Range(4, 9);
		assertEquals("getLength: Did not return the expected output",
				5.0,
				r2.getLength(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthBothNegativeAndEqual()
	{
		Range r3 = new Range(-99, -99);
		assertEquals("getLength: Did not return the expected output",
				0.0,
				r3.getLength(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthBothNegativeAndNotEqual()
	{
		Range r4 = new Range(-11, -4);
		assertEquals("getLength: Did not return the expected output",
				7.0,
				r4.getLength(),
				0.000000001d);
	}
	
	@Test
	public void testGetLengthOnePositiveOneNegative()
	{
		Range r5 = new Range(-5, 3);
		assertEquals("getLength: Did not return the expected output",
				8.0,
				r5.getLength(),
				0.000000001d);
	}
	
	// ADDED TESTS
	
	// Method Under Test: intersects
	
	@Test
	public void testIntersectsLowerAndUpperIsSameAsObject()
	{
		assertEquals("intersects: Did not return the expected output",
				true,
				rangeObjectUnderTest2.intersects(4, 9));
	}
	
	@Test
	public void testIntersectsLowerIsJustBelowLowerBound()
	{
		assertEquals("intersects: Did not return the expected output",
				true,
				rangeObjectUnderTest2.intersects(3, 4));
	}
	
	@Test
	public void testIntersectsUpperIsJustAboveUpperBound()
	{
		assertEquals("intersects: Did not return the expected output",
				true,
				rangeObjectUnderTest2.intersects(9, 10));
	}
	
	@Test
	public void testIntersectsSmallPossibleRange()
	{
		assertEquals("intersects: Did not return the expected output",
				false,
				rangeObjectUnderTest2.intersects(0, 0));
	}
	
	@Test
	public void testIntersectsLargestPossibleRange()
	{
		assertEquals("intersects: Did not return the expected output",
				true,
				rangeObjectUnderTest2.intersects(Double.MIN_VALUE,
						Double.MAX_VALUE));
	}
	
	// Method Under Test: constrain
	
	@Test
	public void testConstrainValueEqualToLowerBound()
	{
		assertEquals("contrain: Did not return the expected output",
				4,
				rangeObjectUnderTest2.constrain(4),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueEqualToUpperBound()
	{
		assertEquals("contrain: Did not return the expected output",
				9,
				rangeObjectUnderTest2.constrain(9),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueJustBelowLowerBound()
	{
		assertEquals("contrain: Did not return the expected output",
				4,
				rangeObjectUnderTest2.constrain(3),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueJustAboveUpperBound()
	{
		assertEquals("contrain: Did not return the expected output",
				9,
				rangeObjectUnderTest2.constrain(10),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueFarBelowLowerBound()
	{
		assertEquals("contrain: Did not return the expected output",
				4,
				rangeObjectUnderTest2.constrain(-99),
				0.000000001d);
	}
	
	@Test
	public void testConstrainValueFarAboveUpperBound()
	{
		assertEquals("contrain: Did not return the expected output",
				9,
				rangeObjectUnderTest2.constrain(99),
				0.000000001d);
	}
	
	// Method Under Test: contains
	
	@Test
	public void testContainsValueEqualToLowerBound()
	{
		assertTrue("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(4));
	}
	
	@Test
	public void testContainsValueEqualToUpperBound()
	{
		assertTrue("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(9));
	}
	
	@Test
	public void testContainsValueJustBelowLowerBound()
	{
		assertFalse("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(3));
	}
	
	@Test
	public void testContainsValueJustAboveUpperBound()
	{
		assertFalse("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(10));
	}
	
	@Test
	public void testContainsValueFarBelowLowerBound()
	{
		assertFalse("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(-99));
	}
	
	@Test
	public void testContainsValueFarAboveUpperBound()
	{
		assertFalse("contains: Did not return the expected output",
				rangeObjectUnderTest2.contains(99));
	}
}

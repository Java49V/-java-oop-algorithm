package telran.algorithm.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.algorithm.recursion.LineRecursion;

class LineRecursionTest {

	@Test
	@Disabled
	void test() {
		f(6);
	}

	private void f(int a) {
		if (a > 0) {
			System.out.println(a);
			f(a - 1);
		}
		
	}
	@Test
	void factorialTest () {
		assertEquals(6, LineRecursion.factorial(3));
		assertEquals(24, LineRecursion.factorial(4));
		assertEquals(1, LineRecursion.factorial(0));
	}
	@Test
	void powerTest () {
		assertEquals(10, LineRecursion.power(10, 1));
		assertEquals(0, LineRecursion.power(0, 2));
		assertEquals(100, LineRecursion.power(10, 2));
		assertEquals(100, LineRecursion.power(-10, 2));
		assertEquals(1000, LineRecursion.power(10, 3));
		assertEquals(-1000, LineRecursion.power(-10, 3));
	}
	@Test
	void sumTest() {
		assertEquals(21, LineRecursion.sum(new int[] {1, 2, 3, 4, 5, 6}));
	}
	@Test
	void reverseTest () {
		int array[] = {1, 2, 3, 4, 5, 6};
		int array1[] = {1, 2, 3, 4, 5, 6, 7};
		int expected[] = {6, 5, 4, 3, 2, 1};
		int expected1[] = {7, 6, 5, 4, 3, 2, 1};
		assertArrayEquals(expected, LineRecursion.reverse(array));
		assertArrayEquals(expected1, LineRecursion.reverse(array1));		
	}
	@Test 
	void squareTest(){
		assertEquals(0, LineRecursion.square(0));
		assertEquals(1, LineRecursion.square(1));
		assertEquals(9, LineRecursion.square(3));
		assertEquals(16, LineRecursion.square(4));
		assertEquals(25, LineRecursion.square(5));
		assertEquals(25, LineRecursion.square(-5));
		assertEquals(9, LineRecursion.square(-3));
	}
	@Test
	void isSubstringTest () {
		assertTrue(LineRecursion.isSubstring("Hello","lo"));
		assertFalse(LineRecursion.isSubstring("Hello", "abs"));
	}
//	@Test
//	void factorialTest ()
//	{
//		assertEquals(6, LineRecursion.factorial(3));
//		assertEquals(24, LineRecursion.factorial(4));
//		assertEquals(1, LineRecursion.factorial(0));
//		
//	}
//	@Test
//	void powTest () {
//		assertEquals(100, LineRecursion.pow(10, 2));
//		assertEquals(100, LineRecursion.pow(-10, 2));
//		assertEquals(1000, LineRecursion.pow(10, 3));
//		assertEquals(-1000, LineRecursion.pow(-10, 3));
////		assertEquals(1, LineRecursion.pow(2, 0));
////        assertEquals(2, LineRecursion.pow(2, 1));
////        assertEquals(8, LineRecursion.pow(2, 3));
////        assertEquals(16, LineRecursion.pow(2, 4));
////        assertEquals(243, LineRecursion.pow(3, 5));
////        assertEquals(1, LineRecursion.pow(10, 0));
////        assertEquals(100, LineRecursion.pow(10, 2));
////        assertEquals(1000000, LineRecursion.pow(10, 6));
////        assertThrows(IllegalArgumentException.class, () -> LineRecursion.pow(2, -1));
//	}
//	@Test
//	void sumTest() {
//		assertEquals(21, LineRecursion.sum(new int[] {1 , 2, 3, 4, 5, 6}));
//	}
//	@Test
//	void reverseTest() {
//		int array[] = {1 , 2, 3, 4, 5, 6};
//		int array1[] = {1 , 2, 3, 4, 5, 6, 7};
//		int expected[] = {6, 5, 4, 3, 2, 1};
//		int expected1[] = {7, 6, 5, 4, 3, 2, 1};
//		assertArrayEquals(expected, LineRecursion.reverse(array));
//		assertArrayEquals(expected1, LineRecursion.reverse(array1));
//	}
//	
//	 @Test
//	    void testPower() {
//	        assertEquals(1, LineRecursion.power(2, 0));
//	        assertEquals(2, LineRecursion.power(2, 1));
//	        assertEquals(8, LineRecursion.power(2, 3));
//	        assertEquals(16, LineRecursion.power(2, 4));
//	        assertEquals(243, LineRecursion.power(3, 5));
//	        assertThrows(IllegalArgumentException.class, () -> LineRecursion.power(2, -1));
//	    }
//	    
//	    @Test
//	    void testSquare() {
//	        assertEquals(0, LineRecursion.square(0));
//	        assertEquals(1, LineRecursion.square(1));
//	        assertEquals(4, LineRecursion.square(2));
//	        assertEquals(9, LineRecursion.square(3));
//	        assertEquals(16, LineRecursion.square(4));
//	        assertEquals(25, LineRecursion.square(5));
//	        assertThrows(IllegalArgumentException.class, () -> LineRecursion.square(-1));
//	    }
//	    
//	    @Test
//	    void testIsSubstring() {
//	        assertTrue(LineRecursion.isSubstring("Hello", ""));
//	        assertTrue(LineRecursion.isSubstring("Hello", "H"));
//	        assertTrue(LineRecursion.isSubstring("Hello", "o"));
//	        assertTrue(LineRecursion.isSubstring("Hello", "ello"));
//	        assertTrue(LineRecursion.isSubstring("Hello", "Hello"));
//	        assertFalse(LineRecursion.isSubstring("Hello", "L"));
//	        assertFalse(LineRecursion.isSubstring("Hello", "World"));
//	        assertFalse(LineRecursion.isSubstring("Hello", "Hood"));
//	    }

}


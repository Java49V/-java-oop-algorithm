package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import org.junit.jupiter.api.*;

import telran.util.*;


public class TreeSetTest extends SortedSetTest {
   TreeSet<Integer> treeSet;
   @BeforeEach
   @Override
   void setUp() {
	   super.setUp();
	   treeSet = (TreeSet<Integer>) set;
   }
	@Override
	protected <T> Set<T> getSet() {		
		return new TreeSet<>() ;
	}
	
	@Test
	void displayTree () {
		treeSet.setInitialLevel(5);
		treeSet.setSpacesPerLevel(3);
		treeSet.displayRotated();
	}
	@Test
	void widthTest () {
		assertEquals(3, treeSet.width());
		
	}
	@Test
	void heightTest() {
		assertEquals(3, treeSet.height());
	}
	@Test
	void balanceTest() {
		TreeSet<Integer> treeBalanced = new TreeSet<>();
		int[] array = getRandomArray(255);
		fillCollection(treeBalanced, array);
		treeBalanced.balance();
		assertEquals(8, treeBalanced.height());
		assertEquals(128, treeBalanced.width());
	}
	@Test
	void balanceTestFromSorted() {
		int height = 22;
		int nNumbers =  (int) Math.pow(2, height); // 1 << height
		int [] array = new int[nNumbers - 1];
		for(int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		TreeSet<Integer> treeBalanced = new TreeSet<>();
		
		balanceOrder(array);
		fillCollection(treeBalanced, array);
		
		assertEquals(height, treeBalanced.height());
		assertEquals(nNumbers / 2, treeBalanced.width());
	}
	
	private void balanceOrder(int[] array) {	
		//reorder array such that adding to tree will get a balanced tree
		ArrayList<Integer> resArray = new ArrayList<>();		
		balanceOrder(array, resArray,  0, array.length - 1);
		int index = 0;
		for(int num : resArray) {
			array[index++] = num;
		}
	}
	
	private void balanceOrder(int[] array, ArrayList<Integer> resArray,  int left, int right) {
		if(left <= right) {
			int midIndex = (left + right)/2;
			resArray.add(array[midIndex]);
			balanceOrder(array, resArray, left , midIndex - 1);
			balanceOrder(array, resArray, midIndex + 1, right);			
		}
	}
	@Test
	void inversionTreeTest() {
		Integer[] expected = {100, 50, 30, 10, 7, -20};
		Integer[] res1 = treeSet.toArray(new Integer[0]);
		
		treeSet.inversion();
		Integer[] res = treeSet.toArray(new Integer[0]);		
		assertArrayEquals(expected, res );
		assertTrue(treeSet.contains(100));
	}

}

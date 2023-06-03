package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

//import java.util.SortedSet;
import telran.util.SortedSet;

import org.junit.jupiter.api.Test;


public abstract class SortedSetTest extends SetTest {

	@Override
	protected Integer[] getActual (Integer[] array, int size) {
		System.out.println("Sorted test");
		return array;
	}
	@Test
	void firstTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(-20, sortedSet.first());		
	}
	@Test
	void lastTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
		assertEquals(100, sortedSet.last());
	}
	@Test
	void ceilingTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
		assertEquals(100, sortedSet.ceiling(100));
		assertEquals(100, sortedSet.ceiling(90));
		assertEquals(null, sortedSet.ceiling(110));
	}
	@Test
	void floorTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>) set;
		assertEquals(50, sortedSet.floor(50));
		assertEquals(30, sortedSet.floor(40));
		assertEquals(null, sortedSet.floor(-40));
	}
}
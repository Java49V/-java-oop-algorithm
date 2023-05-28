package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import java.util.NoSuchElementException;

public abstract class CollectionTest {
	protected Collection<Integer> collection;
	protected Integer[] numbers = { 10, -20, 7, 50, 100, 30 };
	protected static final int BIG_LENGTH = 100000;
	@BeforeEach
	void setUp() {
		collection = getCollection();
		for (int i = 0; i < numbers.length; i++) {
			collection.add(numbers[i]);
		}
	}

	protected abstract Collection<Integer> getCollection();

	@Test
	void testAdd() {
		assertTrue(collection.add(numbers[0]));
		assertEquals(numbers.length + 1, collection.size());
	}

	@Test
	void testRemovePattern() {
		Integer [] expectedNo10 = { -20, 7, 50, 100, 30};
		Integer [] expectedNo10_50 = { -20, 7,  100, 30};
		Integer [] expectedNo10_50_30 = { -20, 7,  100};
		assertTrue(collection.remove(numbers[0]));
		runTest(expectedNo10);
		Integer objToRemove = 50;
		assertTrue(collection.remove(objToRemove));
		runTest(expectedNo10_50);
		assertTrue(collection.remove((Integer)30));
		runTest(expectedNo10_50_30);
		assertFalse(collection.remove((Integer)50));
		Integer no1 = 1;
		assertFalse(collection.remove(no1));
		//assertFalse(collection.remove(null));
	}

	@Test
	void testToArray() {
		Integer[] expectedList = { 10, -20, 7, 50, 100, 30 };
		Integer[] expectedSpareSize = { 10, -20, 7, 50, 100, 30, null };

		Integer[] arrListSize = new Integer[collection.size()];
		Integer[] arrLessSize = new Integer[collection.size() - 1];
		Integer[] arrSpareSize = new Integer[collection.size() + 1];

		assertArrayEquals(expectedList, collection.toArray(arrListSize));
		assertArrayEquals(expectedList, collection.toArray(arrLessSize));
		assertArrayEquals(expectedSpareSize, collection.toArray(arrSpareSize));
	}
	
	@Test
	@Disabled
	void testToArrayExeption() {
		Integer[] expectedList = { 10, -20, 7, 50, 100, 30 };
		assertArrayEquals(expectedList, collection.toArray(null));
	}

	@Test
	void testRemoveIfAll() {
		assertTrue(collection.removeIf(a -> true));
		assertEquals(0, collection.size());
	}

	@Test
	void testRemoveIf() {
		Integer[] expected = {10, -20,  50, 100, 30};
		assertFalse(collection.removeIf(a -> a == null));
		assertFalse(collection.removeIf(a -> a % 2 != 0
				&& a >= 10));
		assertEquals(6, collection.size());
		assertTrue(collection.removeIf(a -> a % 2 != 0));
		assertFalse(collection.removeIf(CollectionTest::predicateOddRemove));
		assertEquals(5, collection.size());
		assertFalse(collection.contains((Integer)7));
		Integer [] actual = collection.toArray(new Integer[0]);
		for(int i=0; i< actual.length; i++) {
			System.out.println(actual[i]);
		}
		runTest(expected);
	}
	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for(int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = collection.toArray(bigArray);
		int size = collection.size();
		for(int i = 0; i < size; i++) {
			assertEquals(numbers[i], actualArray[i]);
		}
		assertNull(actualArray[size]);
		assertTrue(bigArray == actualArray);
	}
	@Test
	void testToArrayForEmptyArray() {
		Integer actualArray[] =
				collection.toArray(new Integer[0]);
		assertArrayEquals(numbers, actualArray);
	}
	@Test 
	void testIterator() {
		Iterator<Integer> it1 = collection.iterator();
		Iterator<Integer> it2 = collection.iterator();
		while(it2.hasNext()) {
			it2.next();
		}
		it1.next();
		assertEquals(numbers[1], it1.next());
		assertTrue(collection.contains(it1.next()));
		assertThrowsExactly(NoSuchElementException.class,()-> it2.next());
		
	}
	
	@Test
	void testIteratorRemove() {
		Iterator<Integer> it = collection.iterator();
		Integer[] expectedFirst = {  -20, 7, 50, 100, 30 };
		Integer[] expectedLast = {  -20, 7, 50, 100 };
		assertThrowsExactly(IllegalStateException.class, ()-> it.remove());
		it.next();
		it.remove();
		runTest(expectedFirst);
		assertEquals(5, collection.size());
		assertThrowsExactly(IllegalStateException.class, ()-> it.remove());
		while(it.hasNext()) {
			it.next();
		}
		it.remove();
		runTest(expectedLast);		
	}
	
	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertTrue(collection.contains(numbers[3]));
		assertTrue(collection.contains(numbers[numbers.length - 1]));
		assertFalse(collection.contains(1000000));
		
	}
	
	@Test
	void clearFunctionalTest() {
		collection.clear();
		assertEquals(0, collection.size());
	}

	static private boolean predicateOddRemove(Integer a) {
		return Math.abs(a) % 2 == 1 ? true : false;
	}
	public static int evenOddCompare(Integer a, Integer b) {
		int res = Math.abs(a % 2) - Math.abs(b % 2);
		if (res == 0) {
			res = a % 2 == 0 ? a - b : b - a;
		}
		return res;
	}
	
	protected void runTest(Integer[] expected) {
		Integer[] actual = collection.toArray(new Integer[0]);
		assertArrayEquals(expected, actual);
	}
	

}

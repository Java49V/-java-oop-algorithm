package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import java.util.NoSuchElementException;
import java.util.Random;

public abstract class CollectionTest {
    // move tests of methods from interface collection
	// from ListTest
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
		assertFalse(collection.removeIf(a -> a % 2 != 0
				&& a >= 10));
		assertTrue(collection.removeIf(a -> a % 2 != 0));
		runTest(expected);
		
	}

	protected abstract Integer[] getActual(Integer[] actual, int size);
	protected abstract Integer[] getExpected(Integer[] expected);
	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for(int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = getActual(collection.toArray(bigArray), collection.size());
		Integer[] expected = getExpected(numbers);
		
		int size = collection.size();
		for(int i = 0; i < size; i++) {
			assertEquals(expected[i], actualArray[i]);
		}
		assertNull(actualArray[size]);
		assertTrue(bigArray == actualArray);
	}
	@Test
	void testToArrayForEmptyArray() {
		Integer actualArray[] = getActual(collection.toArray(new Integer[0]), collection.size());
		Integer[] expected = getExpected(numbers);
		assertArrayEquals(expected, actualArray);
	}
	@Test 
	void testIterator() {
		Iterator<Integer> it1 = collection.iterator();
		Iterator<Integer> it2 = collection.iterator();
		it1.next();
		while(it2.hasNext()) {
			it2.next();
		}
		assertTrue(collection.contains(it1.next()));
		assertThrowsExactly(NoSuchElementException.class,()-> it2.next());		
	}
	
	@Test
	void testIteratorRemove() {
		Iterator<Integer> it = collection.iterator();
		Integer[] expectedFirst = {  -20, 7, 50, 100, 30 };
		Integer[] expectedLast = {  -20, 7, 50, 100 };
		assertThrowsExactly(IllegalStateException.class, ()-> it.remove());
		Integer removed = it.next();
		assertTrue(collection.contains(removed));
		it.remove();
		assertFalse(collection.contains(removed));
		assertThrowsExactly(IllegalStateException.class, ()-> it.remove());
		while(it.hasNext()) {
			removed = it.next();
		}
		assertTrue(collection.contains(removed));
		it.remove();
		assertFalse(collection.contains(removed));		
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

	@Test
	void clearPerformance() {
		Collection<Integer> bigCollection = getCollection();
		Random gen = new Random();
//		for(int i = 0; i < 1000000; i++) {
//			bigCollection.add(gen.nextInt());
//		}
		int[] array = getRandomArray(1_000_000);
		fillCollection(bigCollection, array);
		bigCollection.clear();
		assertEquals(0, bigCollection.size());
	}
	@Test
	void sumEvenNumbers() {
		assertEquals(170,collection.stream().filter(a-> a%2==0)
				.mapToInt(n -> n).sum());
		
	}
	@Test
	void positiveEvenNumbersTest() {
		int[] expected = {10, 30, 50, 100};
		assertArrayEquals(expected, collection.stream().filter( a -> a > 0 && a % 2 == 0).sorted()
				.mapToInt(a -> a).toArray());
	}
	
	protected int[] getRandomArray(int length) {
		Random gen = new Random();
		int[] res = new int[length];
		for(int i = 0; i < length; i++) {
			res[i] = gen.nextInt();
		}
		return res;
	}
	protected void fillCollection(Collection<Integer> collection, int[] array) {
		for(int num: array) {
			collection.add(num);
		}
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

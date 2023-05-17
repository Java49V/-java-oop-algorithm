package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class CollectionTest {
	protected Collection<Integer> collection;
	protected Integer[] numbers = { 10, -20, 7, 50, 100, 30 };

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
		Integer[] expectedNo7 = { 10, -20, 50, 100, 30 };
		Integer[] expectedNo7_10 = { -20, 50, 100, 30 };
		Integer[] expectedNo7_10_30 = { -20, 50, 100 };
		Integer no7 = 7;
		Integer no10 = 10;
		Integer no30 = 30;
		assertTrue(collection.remove(no7));
		runTest(expectedNo7);
		assertTrue(collection.remove(no10));
		runTest(expectedNo7_10);
		assertTrue(collection.remove(no30));
		runTest(expectedNo7_10_30);
		Integer no1 = 1;
		assertFalse(collection.remove(no1));
		assertFalse(collection.remove(null));
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
		assertFalse(collection.removeIf(a -> a == null));
		assertTrue(collection.removeIf(a -> (a * 3) % 2 != 0));
		assertEquals(5, collection.size());
		assertFalse(collection.removeIf(CollectionTest::predicateOddRemove));
		assertEquals(5, collection.size());
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
		Integer[] actual = new Integer[expected.length];
		actual = collection.toArray(actual);
		assertArrayEquals(expected, actual);
	}

}

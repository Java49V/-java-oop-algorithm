package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.Set;

public abstract class SetTest extends CollectionTest {
   Set<Integer> set = getSet();
   abstract protected <T> Set<T> getSet();
   @Override
   @Test
	void testAdd() {
	   System.out.println(collection.contains(numbers[0]));
		assertFalse(collection.add(numbers[0]));
		assertEquals(numbers.length, collection.size());
	}
	@Override
	protected Collection<Integer> getCollection() {
		
		return set;
	}
	protected void runTest(Integer[] expected) {
		Integer [] actual = collection.toArray(new Integer[0]);
		Integer expectedCopy[] = Arrays.copyOf(expected, expected.length);
		Arrays.sort(expectedCopy);
		Arrays.sort(actual);
		
		assertArrayEquals(expectedCopy, actual);
		
	}
	@Test
	void testToArrayForBigArray() {
		Integer bigArray[] = new Integer[BIG_LENGTH];
		for(int i = 0; i < BIG_LENGTH; i++) {
			bigArray[i] = 10;
		}
		Integer actualArray[] = collection.toArray(bigArray);
		Arrays.sort(actualArray,0,collection.size());
		int size = collection.size();
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		for(int i = 0; i < size; i++) {
			assertEquals(expected[i], actualArray[i]);
		}
		assertNull(actualArray[size]);
		assertTrue(bigArray == actualArray);
	}
	@Test
	void testToArrayForEmptyArray() {
		Integer actualArray[] =
				collection.toArray(new Integer[0]);
		Arrays.sort(actualArray);
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, actualArray);
	}
	
	@Test
	void testIterator() {
		Iterator<Integer> it1 = collection.iterator();
		Iterator<Integer> it2 = collection.iterator();
		while(it2.hasNext()) {
			it2.next();
		}
		it1.next();
		//assertEquals(numbers[1], it1.next());
		assertTrue(collection.contains(it1.next()));
		assertThrowsExactly(NoSuchElementException.class,()-> it2.next());
		
	}
	@Test
	void testToArray() {
		Integer[] expectedList = { 10, -20, 7, 50, 100, 30 };
		Integer[] expectedSpareSize = { 10, -20, 7, 50, 100, 30, null };
		Arrays.sort(expectedList);

		Integer[] arrListSize = new Integer[collection.size()];		
		Integer[] arrLessSize = new Integer[collection.size() - 1];
		Integer[] arrSpareSize = new Integer[collection.size() + 1];
		
		Integer[] actualSize = collection.toArray(arrListSize);
		Integer[] actualLessSize = collection.toArray(arrLessSize);
		Integer[] actualSpareSize = collection.toArray(arrSpareSize);
		
		Integer[] actualCopy =  Arrays.copyOf(actualSize, actualSize.length);
		Arrays.sort(actualCopy);
		Arrays.sort(actualLessSize);

		assertArrayEquals(expectedList, actualCopy);
		assertArrayEquals(expectedList, actualLessSize);
		assertEquals(null, actualSpareSize[collection.size()]);

	}
	@Override
	@Test
	void testIteratorRemove() {
		Iterator<Integer> it = collection.iterator();
		Integer[] expectedFirst = {  -20, 7, 50, 100, 30 };
		Integer[] expectedLast = {  -20, 7, 50, 100 };
		assertThrowsExactly(IllegalStateException.class, ()-> it.remove());
		Integer currentObj = it.next();
		assertTrue(collection.contains(currentObj));
		it.remove();
		assertFalse(collection.contains(currentObj));
		assertEquals(5, collection.size());
		assertThrowsExactly(IllegalStateException.class, ()-> it.remove());
		while(it.hasNext()) {
			currentObj = it.next();
		}
		assertTrue(collection.contains(currentObj));
		it.remove();
		assertFalse(collection.contains(currentObj));
		assertEquals(4, collection.size());
	}

}

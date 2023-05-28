package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

import telran.util.Range;

class RangeTest {
Range range = new Range(10, 14);
	@Test
	void constructorExeptionTest() {
		assertThrowsExactly(IllegalArgumentException.class, ()-> new Range(10,10));
	}
	@Test
	void toArrayTest() {
		Integer[] expected = {10,11,12,13};
		assertArrayEquals(expected, range.toArray());
	}
	@Test
	void iteratorTest() {
		Iterator<Integer> it1 = range.iterator();
		Iterator<Integer> it2 = range.iterator();
		it2.next(); it2.next(); it2.next();it2.next();
		assertEquals(10, it1.next());
		assertThrowsExactly(NoSuchElementException.class,()-> it2.next());
	}
	
	@Test
	void iteratorRemoveTest() {
		Iterator<Integer> it1 = range.iterator();
		Integer[] expectedFirst = {11,12,13};
		Integer[] expectedLast = {11,12};
		assertThrowsExactly(IllegalStateException.class, () -> it1.remove());
		it1.next();
		it1.remove();
		assertArrayEquals(expectedFirst, range.toArray());
		assertThrowsExactly(IllegalStateException.class, () -> it1.remove());
		while(it1.hasNext()) {
			it1.next();
		}
		it1.remove();
		assertArrayEquals(expectedLast, range.toArray());
	}	
	
	@Test
	void removeIfRangeTest1() {
		Iterator<Integer> it = range.iterator();
		Integer[] expected = {10,11};
		it.next();   it.next();  it.next(); it.remove();
		 it.next(); it.remove();
		assertArrayEquals(expected, range.toArray());
	}

	@Test
	void removeIfRangeTest() {
		Range range1 = new Range(1, 4);
		range1.removeIf(num -> num % 2 != 0);
		assertArrayEquals(new Integer[] { 2 }, range1.toArray());
	}
	@Test
	void removeIfAllTest() {
		Range range1 = new Range(0, 5);
		assertTrue(range1.removeIf(num -> true));
		assertEquals(0, range1.toArray().length);
	}
}

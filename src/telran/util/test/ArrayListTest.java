package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.*;

class ArrayListTest {
	List<Integer> list;
	Integer[] numbers = { 10, -20, 7, 50, 100, 30 };

	@BeforeEach
	void setUp() {
		list = new ArrayList<>();
		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
	}

	@Test
	void testAdd() {
		assertTrue(list.add(numbers[0]));
		assertEquals(numbers.length + 1, list.size());
	}

	@Test
	void testAddIndex() {
		Integer[] expected0_500 = { 500, 10, -20, 7, 50, 100, 30 };
		Integer[] expected0_500_3_700 = { 500, 10, -20, 700, 7, 50, 100, 30 };
		Integer[] expected0_500_3_700_8_300 = { 500, 10, -20, 700, 7, 50, 100, 30, 300 };
		list.add(0, 500);
		runTest(expected0_500);
		list.add(3, 700);
		runTest(expected0_500_3_700);
		list.add(8, 300);
		runTest(expected0_500_3_700_8_300);
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(-1, 1));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, 1));

	}

	@Test
	void testRemoveIndex() {
		Integer[] expectedNo10 = { -20, 7, 50, 100, 30 };
		Integer[] expectedNo10_50 = { -20, 7, 100, 30 };
		Integer[] expectedNo10_50_30 = { -20, 7, 100 };
		assertEquals(10, list.remove(0));
		runTest(expectedNo10);
		assertEquals(50, list.remove(2));
		runTest(expectedNo10_50);
		assertEquals(30, list.remove(3));
		runTest(expectedNo10_50_30);
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(list.size()));

	}

	@Test
	void testGetIndex() {
		assertEquals(10, list.get(0));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(list.size()));
	}

	@Test
	void testIndexOf() {
		list.add(3, 10);
		assertEquals(0, list.indexOf(10));
		assertEquals(-1, list.indexOf((Integer) null));
	}

	@Test
	void testLastIndexOf() {
		list.add(1, 100);
		list.add(4, 100);
		assertEquals(6, list.lastIndexOf(100));
		assertEquals(-1, list.lastIndexOf((Integer) null));
	}

	@Test
	void testRemovePattern() {
		Integer[] expectedNo7 = { 10, -20, 50, 100, 30 };
		Integer[] expectedNo7_10 = { -20, 50, 100, 30 };
		Integer[] expectedNo7_10_30 = { -20, 50, 100 };
		Integer no7 = 7;
		Integer no10 = 10;
		Integer no30 = 30;
		assertTrue(list.remove(no7));
		runTest(expectedNo7);
		assertTrue(list.remove(no10));
		runTest(expectedNo7_10);
		assertTrue(list.remove(no30));
		runTest(expectedNo7_10_30);
		Integer no1 = 1;
		assertFalse(list.remove(no1));
		assertFalse(list.remove(null));
	}

	@Test
	void testToArray() {
		Integer[] expectedList = { 10, -20, 7, 50, 100, 30 };
		Integer[] expectedSpareSize = { 10, -20, 7, 50, 100, 30, null };

		Integer[] arrListSize = new Integer[list.size()];
		Integer[] arrLessSize = new Integer[list.size() - 1];
		Integer[] arrSpareSize = new Integer[list.size() + 1];
		assertArrayEquals(expectedList, list.toArray(arrListSize));
		assertArrayEquals(expectedList, list.toArray(arrLessSize));
		assertArrayEquals(expectedSpareSize, list.toArray(arrSpareSize));
	}

	@Test
	@Disabled
	void testToArrayExeption() {
		Integer[] expectedList = { 10, -20, 7, 50, 100, 30 };
		assertArrayEquals(expectedList, list.toArray(null));

	}

	@Test
	void testSort() {
		Integer expected[] = { -20, 7, 10, 30, 50, 100 };
		list.sort();
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}

	@Test
	void testSortPerson() {
		List<Person> persons = new ArrayList<>();
		Person p1 = new Person(123, 25, "Vasia");
		Person p2 = new Person(124, 20, "Asaf");
		Person p3 = new Person(120, 50, "Arkady");
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		Person expected[] = { p3, p1, p2 };
		persons.sort();
		assertArrayEquals(expected, persons.toArray(new Person[0]));

	}

	@Test
	void testSortPersonsByAge() {
		List<Person> persons = new ArrayList<>();
		Person p1 = new Person(123, 25, "Vasia");
		Person p2 = new Person(124, 20, "Asaf");
		Person p3 = new Person(120, 50, "Arkady");
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		Person expected[] = { p3, p1, p2 };
		// persons.sort(new PersonsAgeComporator());
		persons.sort((prs1, prs2) -> Integer.compare(prs2.getAge(), prs1.getAge()));
		assertArrayEquals(expected, persons.toArray(new Person[0]));
	}

	@Test
	void testSortEvenOdd() {
		list.add(-17);
		list.add(-21);
		list.add(-100);
		list.add(19);
		list.sort(ArrayListTest::evenOddCompare);
		list.toMyString();
		Integer expected[] = { -100, -20, 10, 30, 50, 100, 19, 7, -17, -21 };
		assertArrayEquals(expected, list.toArray(new Integer[0]));

	}

	@Test
	void testIndexOfPredicate() {
		assertEquals(1, list.indexOf(a -> a < 0));
		list.add(-17);
		assertEquals(-1, list.indexOf(a -> a % 2 != 0 && a > 7));
	}

	@Test
	void testLastIndexOfPredicate() {
		list.add(35);
		assertEquals(5, list.lastIndexOf(a -> a < 35));
		assertEquals(-1, list.lastIndexOf(a -> a * 2 == 0));
	}

	@Test
	void testRemoveIfAll() {
		assertTrue(list.removeIf(a -> true));
		assertEquals(0, list.size());
	}

	@Test
	void testRemoveIf() {
		assertFalse(list.removeIf(a -> a == null));
		assertTrue(list.removeIf(a -> (a * 3) % 2 != 0));
		assertEquals(5, list.size());
		assertFalse(list.removeIf(ArrayListTest::predicateOddRemove));
		assertEquals(5, list.size());
	}

	private void runTest(Integer[] expected) {
		int size = list.size();
		Integer[] actual = new Integer[expected.length];

		for (int i = 0; i < size; i++) {
			actual[i] = list.get(i);
		}
		assertArrayEquals(expected, actual);

	}

	static private int evenOddCompare(Integer a, Integer b) {
		int res = Math.abs(a % 2) - Math.abs(b % 2);
		if (res == 0) {
			res = a % 2 == 0 ? a - b : b - a;
		}
		return res;
	}

	static private boolean predicateOddRemove(Integer a) {

		return Math.abs(a) % 2 == 1 ? true : false;
	}

}

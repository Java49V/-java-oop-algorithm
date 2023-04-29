package telran.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InitialAlgorithmTest {

	@Test
	void testGetMaxPositiveWithNegativeReflect() {
		short[] numbers = { -50, -20, 7, 10, 30, 20, 50, 100, 0 };
		assertEquals(50, InitialAlgorithms.getMaxPositiveWithNegativeReflect(numbers));

		short[] numbersNoMatches_Neg = { 0, -1, -2, -3, -4, -5 };
		assertEquals(-1, InitialAlgorithms.getMaxPositiveWithNegativeReflect(numbersNoMatches_Neg));

		short[] numbersNoMatches_Pos = { 1, 2, 3, 4, 5 };
		assertEquals(-1, InitialAlgorithms.getMaxPositiveWithNegativeReflect(numbersNoMatches_Pos));

	}

	@Test
	void testIsSum2() {
		short[] numbers = { 0, 5, 1, 3, 2, 4 };
		assertTrue(InitialAlgorithms.isSum2(numbers, (short) 5));

		short[] numbersNoSum = { 0, 1, 2, 3, 4, 5 };
		assertFalse(InitialAlgorithms.isSum2(numbersNoSum, (short) 0));

		short[] numbersSumZero = { 0, 1, 2, 3, 4, 0 };
		assertTrue(InitialAlgorithms.isSum2(numbersSumZero, (short) 0));

		short[] numbersMiddleSum = { 8, 1, 2, 3, 3 };
		assertTrue(InitialAlgorithms.isSum2(numbersMiddleSum, (short) 6));
	}

	@Test
	void testBubbleSort() {
		short[] randomArray = getRandomArray(10000);
		InitialAlgorithms.bubbleSort(randomArray);
		assertTrue(runSortedAscendingArrayTest(randomArray));
	}

	@Test
	void testSortShortPositive() {
		short[] randomArray = getRandomArray(1000000);
		InitialAlgorithms.sortShortPositive(randomArray);
		assertTrue(runSortedAscendingArrayTest(randomArray));
	}

	private boolean runSortedAscendingArrayTest(short[] array) {
		int counter = 0;
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				counter++;
			}
		}

		return counter == 0;

	}

	private short[] getRandomArray(int size) {
		short[] randomArray = new short[size];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = (short) (Math.random() * Short.MAX_VALUE);
		}
		return randomArray;
	}

}

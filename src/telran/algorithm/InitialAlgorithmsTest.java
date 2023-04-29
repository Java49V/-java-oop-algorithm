package telran.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

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
		
		short[] array = {-3, 1, 4, -1, 3, -4};
        short result = InitialAlgorithms.getMaxPositiveWithNegativeReflect(array);
        System.out.println("getMaxPositiveWithNegativeReflect test:");
        System.out.println(Arrays.toString(array) + " => " + result);

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
		
		short[] array = {1, 2, 3, 4, 5};
        short sum = 7;
        boolean result = InitialAlgorithms.isSum2(array, sum);
        System.out.println("isSum2 test:");
        System.out.println(Arrays.toString(array) + ", sum = " + sum + " => " + result);
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
	
	@Test
	void testSortMethods() {
        short[] array = getRandomArray(100_000);
        System.out.println("Sorting 100,000 numbers:");
        runSortedAscendingArrayTest(array);

        array = getRandomArray(1_000_000);
        System.out.println("\nSorting 1,000,000 numbers:");
        runSortedAscendingArrayTest(array);
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

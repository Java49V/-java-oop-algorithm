package telran.util.stream.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import telran.util.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static telran.util.stream.PrimitiveStreams.*;

class PrimitiveStreamTest {

	private static final int MIN_NUMBER = 1;
	private static final int MAX_NUMBER = 2_000_000;
	private static final int N_NUMBERS = 1000;
	private static final int N_RUNS = 10;

	@Test
	void uniqueRandomTest() {
		int[] arrayPrev = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);

		runUniqueTest(arrayPrev);
		for (int i = 0; i < N_RUNS; i++) {
			int[] arrayNext = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
			runUniqueTest(arrayNext);
			runArrayNotEqualTest(arrayPrev, arrayNext);
			arrayPrev = arrayNext;
		}
	}
	
	@Test
	void shuffleTest() {
		int[] arrayPrev = randomUnique(N_NUMBERS, MIN_NUMBER, MAX_NUMBER);
		for(int i = 0; i < N_RUNS; i++) {
			int[] arrayShuffled = shuffle(arrayPrev);
			runArrayNotEqualTest(arrayPrev, arrayShuffled);
			runArraysIsSame(arrayPrev,arrayShuffled );
			arrayPrev = arrayShuffled;
		}
		
	}

	private void runArraysIsSame(int[] arrayPrev, int[] arrayShuffled) {
		assertTrue(arrayPrev.length == arrayShuffled.length);
		HashSet<Integer> set = new HashSet<>();
		for(int num: arrayShuffled) {			
			set.add(num);
		}
		for(int num: arrayPrev) {
			assertTrue(set.contains(num));
		}	
	}

	private void runArrayNotEqualTest(int[] arrayPrev, int[] arrayNext) {
		int index = 0;
		if (arrayPrev.length == arrayNext.length) {
			while (index < arrayPrev.length && arrayPrev[index] == arrayNext[index]) {
				index++;
			}
		}
		assertTrue(index < arrayPrev.length);
	}

	private void runUniqueTest(int[] array) {
		HashSet<Integer> set = new HashSet<>();
		for(int num: array) {
			set.add(num);
		}
		assertEquals(array.length, set.size());
		
	}

}

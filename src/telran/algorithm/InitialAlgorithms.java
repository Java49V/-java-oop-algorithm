package telran.algorithm;

public class InitialAlgorithms {
	public static void sortShortPositive(short[] array) {
		int[] helper = new int[Short.MAX_VALUE];
		// helper[index] => count of occurences for number index in array
		// helper[1000] = 2 => number 1000 occurs twice times in the source array
		// helper[2] = 0 => there is no number 2 in the given array;
		for (int i = 0; i < array.length; i++) {
			helper[array[i]]++;
		}
		int ind = 0;
		for (int i = 0; i < helper.length; i++) {
			for (int j = 0; j < helper[i]; j++) {
				array[ind++] = (short) i;
			}
		}
	}

	public static boolean isSum2(short[] array, short sum) {
		boolean res = false;
		int[] helper = new int[sum + 1];
		int index = 0;
		while (index < array.length && !res) {
			int indexHelper = array[index];
			if (indexHelper <= sum) {
				if (helper[indexHelper] == 0) {
					helper[sum - indexHelper] = 1;
				} else {
					res = true;
				}	
			}
			index++;
		}
		return res;
	}

	public static short getMaxPositiveWithNegativeReflect(short[] array) {
		short res = -1;
		int[] helper = new int[Short.MAX_VALUE];

		for (int i = 0; i < array.length; i++) {

			int indexHelper = Math.abs(array[i]);

			if (helper[indexHelper] == 0) {
				helper[indexHelper] = array[i] > 0 ? 1 : -1;

			} else if (helper[indexHelper] > 0) {
				res = (array[i] < 0 && res < indexHelper) ? (short) indexHelper : res;

			} else {
				res = (array[i] > 0 && res < indexHelper) ? (short) indexHelper : res;

			}
		}
		return res;
	}

	public static void bubbleSort(short[] array) {
		int n = array.length;
		boolean flUnSort = true;
		do {
			flUnSort = false;
			n--;
			for (int i = 0; i < n; i++) {
				if (array[i] > array[i + 1]) {
					short number = array[i];
					array[i] = array[i + 1];
					array[i + 1] = number;
					flUnSort = true;
				}
			}
		} while (flUnSort);
	}

}

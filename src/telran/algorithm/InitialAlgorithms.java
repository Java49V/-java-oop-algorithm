package telran.algorithm;

import java.util.Arrays;

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
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == sum) {
                    return true;
                }
            }
        }
        return false;
    }

    public static short getMaxPositiveWithNegativeReflect(short[] array) {
        short maxPositive = -1;
        for (short num : array) {
            if (num > 0 && Arrays.binarySearch(array, (short) -num) >= 0) {
                maxPositive = num > maxPositive ? num : maxPositive;
            }
        }
        return maxPositive;
    }

    public static void bubbleSort(short[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    short temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

}

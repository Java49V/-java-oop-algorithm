package telran.algorithm;

import java.util.Arrays;

public class MemoryService {
    public static long getMaxAvailableSize() {
        long low = 0;
        long high = Integer.MAX_VALUE;

        while (low <= high) {
            long middle = low + (high - low) / 2;
            long[] array;

            try {
                array = new long[(int) middle];
                low = middle + 1;
            } catch (OutOfMemoryError e) {
                high = middle - 1;
            }
        }

        return high;
    }

    public static void main(String[] args) {
        long maxAvailableSize = getMaxAvailableSize();
        System.out.println("Max Available Size: " + maxAvailableSize);
    }
}



//public class MemoryService {
//public static int getMaxAvailableSize() {
//	int res = Integer.MAX_VALUE;
//	boolean running = true;
//	while (running) {
//		try {
//			byte[] array = new byte[res];
//			running = false;
//		} catch(OutOfMemoryError e) {
//			res /= 2;
//		}
//	}
//	return res;
//	
//}
//}

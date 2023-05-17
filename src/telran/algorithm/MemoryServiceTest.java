package telran.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;


public class MemoryServiceTest {
    @Test
    public void testGetMaxAvailableSize() {
        long maxAvailableSize = MemoryService.getMaxAvailableSize();
        System.out.println("Max Available Size: " + maxAvailableSize);

        Assertions.assertTrue(maxAvailableSize > 0, "Max Available Size should be greater than 0");

        // Test small array allocation
        long smallArraySize = maxAvailableSize / 10;
        try {
            long[] smallArray = new long[(int) smallArraySize];
            Assertions.assertEquals(smallArraySize, smallArray.length, "Small array size should match requested size");
        } catch (OutOfMemoryError e) {
            Assertions.fail("Failed to allocate small array within available memory");
        }

        // Test large array allocation
        long largeArraySize = maxAvailableSize * 2;
        try {
            long[] largeArray = new long[(int) largeArraySize];
            Assertions.fail("Allocating large array should have resulted in OutOfMemoryError");
        } catch (OutOfMemoryError e) {
            // Allocation should fail due to insufficient memory
            Assertions.assertTrue(true);
        }
    }
}


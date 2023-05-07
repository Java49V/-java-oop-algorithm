package telran.algorithm;

public class MemoryService {
public static int getMaxAvailableSize() {
	int res = Integer.MAX_VALUE;
	boolean running = true;
	while (running) {
		try {
			byte[] array = new byte[res];
			running = false;
		} catch(OutOfMemoryError e) {
			res /= 2;
		}
	}
	return res;
	
}
}

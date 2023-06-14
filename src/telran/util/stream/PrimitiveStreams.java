package telran.util.stream;

import java.util.Random;

import telran.util.ArrayList;

public class PrimitiveStreams {
	public static int[] randomUnique(int nNumbers, int minInclusive, int maxExclusive) {
		if(maxExclusive - minInclusive < nNumbers) {
			throw new IllegalArgumentException("impossible to get the given amount of unique random numbers");
		}
		return new Random().ints(minInclusive, maxExclusive)
				.distinct().limit(nNumbers).toArray();
	}
	public static int[] shuffle (int[] array) {
		return new Random().ints(0, array.length).distinct()
				.limit(array.length).map(a -> array[a])				
				.toArray();
	}
	
}

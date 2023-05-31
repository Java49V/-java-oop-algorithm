package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import telran.util.*;

class TreeSetTest extends SetTest {
	
	@Override
	protected <T> Set<T> getSet() {
		return new TreeSet<>();
//		return new TreeSet<>((Comparator<T>) Comparator.naturalOrder());
	}
}


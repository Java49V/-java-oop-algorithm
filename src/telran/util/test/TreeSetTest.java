package telran.util.test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import telran.util.*;


public class TreeSetTest extends SortedSetTest {
   
	@Override
	protected <T> Set<T> getSet() {		
		return new TreeSet<>() ;
	}
	
}



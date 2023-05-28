package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
	private int min;
	private int max;
	private LinkedList<Integer> removedNum;

	public Range(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("min must be less than max");
		}
		this.min = min;
		this.max = max;
		removedNum = new LinkedList<>();
	}

	private class RangeIterator implements Iterator<Integer> {
		int current = min;
		int prev = current;
		boolean flNext = false;
		

		@Override
		public boolean hasNext() {	
				while(removedNum.contains(current)&& current < max ) {
					current++;
				}
			return  current < max  ;
				
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}			
			prev = current;
			flNext = true;
			return current++;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			removedNum.add(prev);
			flNext = false;
		}
	}

	@Override
	public Iterator<Integer> iterator() {

		return new RangeIterator();
	}

	public Integer[] toArray() {
		Integer[] array = new Integer[getSize()];
		int index = 0;
		Iterator<Integer> it = iterator();
		while (it.hasNext()) {
			array[index++] = it.next();
		}
		return array;
	}

	public boolean removeIf(Predicate<Integer> predicate) {
		Iterator<Integer> it = iterator();
		int oldSize = removedNum.size();
		while (it.hasNext()) {
			Integer obj = it.next();
			if (predicate.test(obj)) {
				it.remove();
			}
		}		
		return oldSize < removedNum.size();
	}
	
	private int getSize() {
		return max - min - removedNum.size();
	}

}

package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPASITY = 16;
	private T[] array;
	private int size;
	
	private class ArrayListIterator implements Iterator<T>{
		int currentIndex = 0;
		boolean flNext = false;

		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return array[currentIndex++];
		}
		@Override
		public void remove() {
			if(!flNext) {
			throw new IllegalStateException();
			}
			ArrayList.this.remove(--currentIndex);
			flNext = false;
		}
		
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPASITY);
	}

	@Override
	public boolean add(T obj) {
		if (size == array.length) {
			reallocate();
		}
		array[size] = obj;
		size++;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(int index, T obj) {
		if (size == array.length) {
			reallocate();
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}

	@Override
	public T remove(int index) {
		T removedObj = null;
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		removedObj = array[index];
		size--;
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return removedObj;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		T res = array[index];
		return res;
	}

	@Override
	public void sort(Comparator<T> comp) {
		boolean flSort = true;
		for (int i = 0; i < size && flSort; i++) {
			flSort = false;
			for (int y = 0; y < size - i - 1; y++) {
				if (comp.compare(array[y], array[y + 1]) > 0) {
					T obj = array[y];
					array[y] = array[y + 1];
					array[y + 1] = obj;
					flSort = true;
				}
			}
		}
	}

	public void toMyString() {
		for (int i = 0; i < size; i++) {
			System.out.print(array[i] + " ");
		}
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int res = -1;
		int index = 0;
		while (index < size && res == -1) {
			if (predicate.test(array[index])) {
				res = index;
			}
			index++;
		}
		return res;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int res = -1;
		int index = size;
		while (--index >= 0 && res == -1) {
			if (predicate.test(array[index])) {
				res = index;
			}
		}

		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		//rewrite the removeIf method of ArrayList for optimization( O[N]
		int oldSize = size;
		int indexRemove = 0;
		for(int indexCurrent = 0; indexCurrent < oldSize; indexCurrent++) {
			if(predicate.test(array[indexCurrent])) {
				size--;
			} else {
				array[indexRemove++] = array[indexCurrent];
			}
		}
		for(int i = size; i < oldSize; i++) {
			array[i] = null;
		}
		return oldSize > size;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

}


package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);
	int size();
	boolean remove(T pattern);
	void toMyString();
	default boolean removeIf(Predicate<T> predicate) {
		int oldSize = size();
		Iterator<T> it = iterator();
		while(it.hasNext()) {
			T obj = it.next();
			if(predicate.test(obj)) {
				it.remove();
			}
		}
		return oldSize > size();
	};
	
	default public T[] toArray(T[] array) {
		int size = size();
		if (array.length < size) {
			array = Arrays.copyOf(array, size);
		}
		Iterator<T> it = iterator();
		for(int i = 0; i < size; i++) {
			array[i] = it.next();
		}
		if (array.length > size) {
			array[size] = null;
		}
		return array;
	}
	
	default boolean isEqual(T object, T pattern) {

		return pattern == null ? object == pattern : pattern.equals(object);
	}
	default void clear() {
		removeIf(element -> true);
	}
	
	boolean contains(T pattern);

}

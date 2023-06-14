package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
	
 boolean contains(T pattern) ;
	
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
	default Stream<T> stream(){
	return	StreamSupport.stream(spliterator(), false);
	}
	default Stream<T> parallelStream(){
		return	StreamSupport.stream(spliterator(), true);
	}

}

//package telran.util;
//
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.function.Predicate;
//
//public interface Collection<T> extends Iterable<T>{
//boolean add(T obj);
//int size();
//boolean remove(T pattern);
//default T[] toArray(T[] ar) {
//	int size = size();
//	if (ar.length < size) {
//		ar = Arrays.copyOf(ar, size);
//	}
//	int index = 0;
//	for(T obj: this) {
//		ar[index++] = obj;
//	}
//	if (ar.length > size) {
//		ar[size] = null;
//	}
//
//	return ar;
//}
//default boolean removeIf(Predicate<T> predicate) {
//	int oldSize = size();
//	Iterator<T> it = iterator();
//	while(it.hasNext()) {
//		T obj = it.next();
//		if(predicate.test(obj)) {
//			it.remove();
//		}
//	}
//	return oldSize > size();
//}
//boolean contains(T pattern);
//default  boolean isEqual(T object, T pattern) {
//
//	return pattern == null  ? object == pattern : pattern.equals(object);
//}
//default void clear() {
//	removeIf(element -> true);
//};
//}

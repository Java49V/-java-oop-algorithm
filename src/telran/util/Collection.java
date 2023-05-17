package telran.util;

import java.util.function.Predicate;

public interface Collection<T> {
	boolean add(T obj);
	int size();
	boolean remove(T pattern);
	T[] toArray(T[] array);
	void toMyString();
	boolean removeIf(Predicate<T> predicate);

}
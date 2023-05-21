package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T>{
boolean add(T obj);
int size();
boolean remove(T pattern);
default T[] toArray(T[] ar) {
    if (ar.length < size()) {
        ar = Arrays.copyOf(ar, size());
    }
    Iterator<T> iterator = iterator();
    int index = 0;
    while (iterator.hasNext()) {
        ar[index++] = iterator.next();
    }
    if (ar.length > size()) {
        ar[size()] = null;
    }
    return ar;
}

boolean removeIf(Predicate<T> predicate);
boolean contains(T pattern);
default  boolean isEqual(T object, T pattern) {

	return pattern == null  ? object == pattern : pattern.equals(object);
}
}

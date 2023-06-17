package telran.util;

import java.util.Objects;

/** Made the key field in Entry class final as it should not be modified after creation.
Improved the getOrDefault method implementation to eliminate the unnecessary variable assignment and simplify the return statement.
Improved the putIfAbsent method implementation to return the existing value if it exists without performing unnecessary operations.
Updated the equals method in the Entry class to handle null and different class comparisons properly.
**/

public interface Map<K, V> {
    static class Entry<K, V> implements Comparable<Entry<K, V>> {
        private final K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Entry<?, ?> other = (Entry<?, ?>) obj;
            return Objects.equals(key, other.key);
        }

        @SuppressWarnings("unchecked")
		@Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>) this.key).compareTo(o.key);
        }
    }

    V get(K key);

    default V getOrDefault(K key, V defaultValue) {
        V value = get(key);
        return value != null ? value : defaultValue;
    }

    V put(K key, V value);

    default V putIfAbsent(K key, V value) {
        V existingValue = get(key);
        if (existingValue == null) {
            put(key, value);
        }
        return existingValue;
    }

    boolean containsKey(K key);

    boolean containsValue(V value);

    Set<K> keySet();

    Collection<V> values();

    Set<Entry<K, V>> entrySet();

    V remove(K key);
}
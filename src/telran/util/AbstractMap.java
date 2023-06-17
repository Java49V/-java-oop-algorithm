package telran.util;

public abstract class AbstractMap<K, V> implements Map<K, V> {
	protected Set<Entry<K, V>> set;
	@Override
	public V get(K key) {
		Entry<K, V> entry = set.get(new Entry<>(key, null));
		
		return entry == null ? null : entry.getValue();
	}

	@Override
	public V put(K key, V value) {
	    if (key == null) {
	        throw new NullPointerException("put(): null key is prohibited");
	    }
	    if (value == null) {
	        throw new NullPointerException("put(): null value is prohibited");
	    }

	    Entry<K, V> entry = set.get(new Entry<>(key, null));
	    V res = null;
	    if (entry != null) {
	        res = entry.getValue();
	        entry.setValue(value);
	    } else {
	        set.add(new Entry<>(key, value));
	    }
	    return res;
	}

	@Override
	public boolean containsKey(K key) {
		if(key == null) {
			throw new NullPointerException("containsKey(): null is prohibited");
		}
		return set.contains(new Entry<>(key, null));
	}

	@Override
	public boolean containsValue(V value) {
	    if (value == null) {
	        throw new NullPointerException("containsValue(): null is prohibited");
	    }

	    return set.stream().anyMatch(entry -> entry.getValue().equals(value));
	}

	@Override
	public Set<K> keySet() {
	    Set<K> res = getKeySet();
	    set.stream().map(Entry::getKey).forEach(res::add);
	    return res;
	}


	abstract protected Set<K> getKeySet();

	@Override
	public Collection<V> values() {
        List<V> values = new LinkedList<>();
        set.stream().map(Entry::getValue).forEach(values::add);
        return values;
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return set;
	}
	
	@Override
	public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("remove(): null key is prohibited");
        }
        V removedValue = get(key);
        if (!set.remove(new Entry<>(key, null))) {
            removedValue = null;
        }
        return removedValue;
	}

}

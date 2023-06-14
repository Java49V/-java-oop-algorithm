package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_HASH_TABLE_SIZE = 16;
	private LinkedList<T>[] hashTable;
	private int size;
	
	private class HashSetIterator implements Iterator<T> {
		Integer currentIteratorIndex;
		Iterator<T> currentIterator;
		Iterator<T> prevIterator;
		boolean flNext = false;
		HashSetIterator() {
			initialState();
		}
		private void initialState() {
			currentIteratorIndex = getCurrentIteratorIndex(-1);
			if(currentIteratorIndex > -1) {
				currentIterator = hashTable[currentIteratorIndex].iterator();
			}
		}
		private int getCurrentIteratorIndex(int currentIndex) {
			currentIndex++;
			while(currentIndex < hashTable.length && 
					(hashTable[currentIndex] == null || hashTable[currentIndex].size() == 0)) {
				currentIndex++;
			}
			return currentIndex < hashTable.length ? currentIndex : -1;
		}
		@Override
		public boolean hasNext() {
			
			return currentIteratorIndex >= 0;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = currentIterator.next();
			prevIterator = currentIterator;
			updateState();
			flNext = true;
			return res;
		}
		private void updateState() {
			if(!currentIterator.hasNext()) {
				currentIteratorIndex =
						getCurrentIteratorIndex(currentIteratorIndex);
				if(currentIteratorIndex >= 0) {
					currentIterator = hashTable[currentIteratorIndex].iterator();
				}
			}			
		}
		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			prevIterator.remove();
			size--;
			flNext = false;
		}		
	}
	@SuppressWarnings("unchecked")
	public HashSet(int hashTableSize) {
		hashTable = new LinkedList[hashTableSize];
	}
	public HashSet() {
		this(DEFAULT_HASH_TABLE_SIZE);
	}
	@Override
	public Iterator<T> iterator() {
		
		return new HashSetIterator();
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if(size >= 0.75 * hashTable.length) {
			recreation();
		}
		int index = getHashTableIndex(obj);
		if(hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		if(!hashTable[index].contains(obj)) {
			hashTable[index].add(obj);
			size++;
			res = true;
		}
		
		return res;
	}

	private int getHashTableIndex(T obj) {
		
		return Math.abs(obj.hashCode()) % hashTable.length;
	}
	private void recreation() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				for (T obj : hashTable[i]) {
					tmp.add(obj);
				} 
			}
		}
		this.hashTable = tmp.hashTable;
		
	}
	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = getHashTableIndex(pattern);
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			System.out.println("Res"+res);
			if (res) {
				size--;
			}
			if(hashTable[index].size()==0) {
				hashTable[index] = null;
			}
		}
		return res;
	}

	@Override
	public boolean contains(T pattern) {
		int index = getHashTableIndex(pattern);
		return hashTable[index] != null && hashTable[index].contains(pattern);
	}
//	@Override
	//FIXME method should be removed after writing iterator
//	public T[] toArray(T[] ar) {
//		int size = size();
//		if (ar.length < size) {
//			ar = Arrays.copyOf(ar, size);
//		}
//		int index = 0;
//		for(int i = 0; i < hashTable.length; i++) {
//			LinkedList<T> list = hashTable[i];			
//			if(list != null) {
//				for(T obj: list) {
//				System.out.println("List:"+list+ " obj:"+obj);	
//					ar[index++] = obj;
//				}
//			}			
//		}
//		if (ar.length > size) {
//			ar[size] = null;
//		}
//
//		return ar;
//	}
	@Override
	public void toMyString() {
		// TODO Auto-generated method stub
		
	}

}

//int indexHashTable = -1;
//int indexList = 0;
//LinkedList<T> currentList = getCurrentList();
//LinkedList<T> prevList = null;
//T currentObj = null;
//boolean flNext = false;
//int currentInd = 0;
//
//@Override
//public boolean hasNext() {
//	
//	return currentList != null && indexHashTable < hashTable.length;
//}		
//
//@Override
//public T next() {
//	if(!hasNext()) {
//		throw new NoSuchElementException();
//	}
//	prevList = currentList;
//	currentInd = indexHashTable;
//	currentObj = getCurrentObj(currentList);
//	flNext = true;
//	return currentObj;			
//}
//
//@Override
//public void remove() {
//	if(!flNext) {
//		throw new IllegalStateException();
//	}	
//    prevList.remove(currentObj);   
//	if(prevList.size() == 0) {
//		hashTable[currentInd] = null;
//	    indexList = 0;
//	} else {
//		indexList--;
//	}			
//	size--;
//	flNext = false;
//}
//
//private LinkedList<T> getCurrentList() {
//	indexHashTable++;
//	while(indexHashTable < hashTable.length && hashTable[indexHashTable] == null  ) {
//		indexHashTable++;
//	}
//	return indexHashTable < hashTable.length ? hashTable[indexHashTable]: null;
//}
//
//private T getCurrentObj(LinkedList<T> list) {
//	T obj = list.get(indexList++);
//	if(indexList == list.size()) {
//		indexList = 0;
//		currentList = getCurrentList();
//	}
//	return obj;
//}
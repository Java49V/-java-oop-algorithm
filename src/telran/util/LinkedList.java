package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	Node<T> head;
	Node<T> tail;
	int size;

	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;

		Node(T obj) {
			this.obj = obj;
		}
	}

	@Override
	public boolean add(T obj) {
		add(size, obj);
		return true;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public boolean remove(T pattern) {
		boolean res = false;
		int index = indexOf(pattern);
		if (index > -1) {
			res = true;
			remove(index);
		}
		return res;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Node<T> current = head;
		int index = 0;
		while (current != null) {
			ar[index++] = current.obj;
			current = current.next;
		}
		if (ar.length > size) {
			ar[size] = null;
		}
		return ar;
	}

	@Override
	public void add(int index, T obj) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		Node<T> node = new Node<>(obj);
		addNode(index, node);
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		Node<T> node = getNode(index);
		removeNode(node);
		T res = node.obj;
		node.obj = null;
		return res;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		return getNode(index).obj;
	}

	@Override
	public int indexOf(T pattern) {
		return indexOf(obj -> isEqual(obj, pattern));
	}

	@Override
	public int lastIndexOf(T pattern) {
		return lastIndexOf(obj -> isEqual(obj, pattern));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sort() {
//		sort(null);
		sort((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public void sort(Comparator<T> comp) {
		if (size <= 1) {
	        return; // Already sorted
	    }
        @SuppressWarnings("unchecked")
		T[] array = toArray((T[]) new Object[size]);
        // Use Arrays.sort() to sort the array
        if (comp == null) {
            Arrays.sort(array);
        } else {
            Arrays.sort(array, comp);
        }
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            current.obj = array[index++];
            current = current.next;
        }
//        return array;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int index = 0;
		Node<T> current = head;
		while (current != null && !predicate.test(current.obj)) {
			current = current.next;
			index++;
		}
		return current == null ? -1 : index;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int index = size - 1;
		Node<T> current = tail;
		while (current != null && !predicate.test(current.obj)) {
			current = current.prev;
			index--;
		}
		return current == null ? -1 : index;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		Node<T> current = head;
		Node<T> next = null;
		int oldSize = size;
		while (current != null) {
			next = current.next;
			if (predicate.test(current.obj)) {
				removeNode(current);
			}
			current = next;
		}
		return oldSize > size;
	}

	@Override
	public void toMyString() {
		// TODO Auto-generated method stub

	}

	private void addNode(int index, Node<T> node) {
		if (head == null) {
			head = tail = node;
		} else {
			if (index == 0) {
				addNodeHead(node);
			} else if (index == size) {
				addNodeTail(node);
			} else {
				addNodeMiddle(index, node);
			}
		}
		size++;
	}

	private void addNodeHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;
	}

	private void addNodeTail(Node<T> node) {
		node.prev = tail;
		tail.next = node;
		tail = node;
	}

	private void addNodeMiddle(int index, Node<T> node) {
		Node<T> nodeA = getNode(index);
		Node<T> nodeBefore = nodeA.prev;
		node.prev = nodeBefore;
		node.next = nodeA;
		nodeBefore.next = node;
		nodeA.prev = node;
	}

	private Node<T> getNode(int index) {

		return index > size / 2 ? getNodeFromRight(index) : getNodeFromLeft(index);
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private void removeNode(Node<T> current) {
		if (current == head) {
			removeNodeHead();
		} else if (current == tail) {
			removeNodeTail();
		} else {
			removeMiddleNode(current);
		}
		size--;
	}

	private void removeMiddleNode(Node<T> current) {
		Node<T> nodeBefore = current.prev;
		Node<T> nodeAfter = current.next;
		nodeBefore.next = nodeAfter;
		nodeAfter.prev = nodeBefore;
		current.next = current.prev = null;
	}

	private void removeNodeTail() {
		Node<T> newTail = tail.prev;
		if (newTail != null) {
			newTail.next = null;
		}
		tail.prev = null;
		tail = newTail;

	}

	private void removeNodeHead() {
		Node<T> newHead = head.next;
		if (newHead != null) {
			newHead.prev = null;
		}
		head.next = null;
		head = newHead;

	}

	private boolean isEqual(T object, T pattern) {

		return pattern == null ? object == pattern : pattern.equals(object);
	}

}

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
        Node<T> current = head;
        while (current != null) {
            if (current.obj.equals(pattern)) {
                removeNode(current);
                return true;
            }
            current = current.next;
        }
        return false;
	}
	
	private void removeNode(Node<T> node) {
	    if (node == head) {
	        head = node.next;
	    } else {
	        node.prev.next = node.next;
	    }
	    if (node == tail) {
	        tail = node.prev;
	    } else {
	        node.next.prev = node.prev;
	    }
	    size--;
	}

	@Override
	public T[] toArray(T[] ar) {
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Node<T> current = head;
		int index = 0;
		while(current != null) {
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
        return node.obj;
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
		Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current.obj.equals(pattern)) {
                return i;
            }
            current = current.next;
        }
        return -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		Node<T> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (current.obj.equals(pattern)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
	}

	@Override
	public void sort() {
		// no implement

	}

	@Override
	public void sort(Comparator<T> comp) {
		// no implement

	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		 Node<T> current = head;
	        for (int i = 0; i < size; i++) {
	            if (predicate.test(current.obj)) {
	                return i;
	            }
	            current = current.next;
	        }
	        return -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		 Node<T> current = tail;
	        for (int i = size - 1; i >= 0; i--) {
	            if (predicate.test(current.obj)) {
	                return i;
	            }
	            current = current.prev;
	        }
	        return -1;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		 Node<T> current = head;
	        boolean removed = false;
	        while (current != null) {
	            Node<T> next = current.next;
	            if (predicate.test(current.obj)) {
	                removeNode(current);
	                removed = true;
	            }
	            current = next;
	        }
	        return removed;
	    
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
		
		return index > size / 2 ? getNodeFromRight(index) :
			getNodeFromLeft(index);
	}

	private Node<T> getNodeFromLeft(int index) {
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		for(int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

}

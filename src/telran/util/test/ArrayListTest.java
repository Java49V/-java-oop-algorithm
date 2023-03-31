package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

//import telran.util.test.*;

class ArrayListTest {

	@Test
	void testAdd() {
		ArrayList<Integer> numbers = new ArrayList<>();
		ArrayList<String> strings = new ArrayList<>();
		numbers.add(5);
		numbers.add(10);
		strings.add("ABC");
		assertEquals(2, numbers.size());
		assertEquals(1, strings.size());
	}
	
	@Test
	public void testAddIndex() {
	    ArrayList<Integer> list = new ArrayList<>();
	    assertTrue(list.add(10));
	    assertTrue(list.add(20));
	    assertTrue(list.add(30));
	    assertTrue(list.add(40));
	    assertTrue(list.add(50));
	    list.add(0, 5);
	    list.add(3, 35);
	    list.add(list.size(), 55);
	    assertEquals(8, list.size());
	    assertEquals(5, (int) list.get(0));
	    assertEquals(35, (int) list.get(3));
	    assertEquals(55, (int) list.get(list.size() - 1));
	}
	
	@Test
	public void testRemoveIndex() {
	    ArrayList<Integer> list = new ArrayList<>();
	    assertTrue(list.add(10));
	    assertTrue(list.add(20));
	    assertTrue(list.add(30));
	    assertTrue(list.add(40));
	    assertTrue(list.add(50));
	    assertEquals(50, (int) list.remove(list.size() - 1));
	    assertEquals(10, (int) list.remove(0));
	    assertEquals(30, (int) list.remove(1));
	    assertEquals(2, list.size());
	    assertEquals(20, (int) list.get(0));
	    assertEquals(40, (int) list.get(1));
	}
	
	


}

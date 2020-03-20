package eg.edu.alexu.csd.datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SingleLinkedListTest {

	@Test
	void test() {
		LinkedList test = new LinkedList();
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		assertEquals(test.get(0),3);
		assertEquals(test.get(1),4);
		assertEquals(test.get(2),5);
		assertEquals(test.get(3),6);
		assertNull(test.get(256));//index out of bounds returns null.
		//adding a number in middle of list
		test.add(test.size/2, 10);
		// using get method to find that 10 had been added in right place.
		assertEquals(test.get(2),10);
		//using set method on second node with value 4 and index 1 to change it to 7
		test.set(1, 7);
		assertEquals(test.get(1),7);
		ILinkedList temp= test.sublist(1,2 );//7 and 5
		assertEquals(temp.get(0),7);
		test.remove(3);//size should be four elements after removing on element.5 is removed
		assertEquals(test.size(),4);
		assertTrue(test.contains(3));//should be true
		assertFalse(test.contains(1000));//should be false
		test.clear();
		assertTrue(test.isEmpty());
	}

}

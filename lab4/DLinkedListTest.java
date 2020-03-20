package eg.edu.alexu.csd.datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DLinkedListTest {
  @Test
  void test() {
    DLinkedList test = new DLinkedList();
    test.add(1);
    test.add(2);
    test.add(3);
    test.add(4);
    assertEquals(test.get(0),1);
    assertEquals(test.get(1),2);
    assertEquals(test.get(2),3);
    assertEquals(test.get(3),4);
    assertNull(test.get(150));//index out of bounds returns null.
    //adding a number in middle of list
    test.add(test.size/2, 15);
    // using get method to find that 10 had been added in right place.
    assertEquals(test.get(2),15);
    //using set method on second node with value 2 and index 1 to change it to 9
    test.set(1, 9);
    assertEquals(test.get(1),9);
    ILinkedList temp= test.sublist(1,2);//9 and 3
    assertEquals(temp.get(0),9);
    test.remove(3);//size should be four elements after removing on element.4 is removed
    assertEquals(test.size(),4);
    assertTrue(test.contains(15));//should be true
    assertFalse(test.contains(1000));//should be false
    test.clear();
    assertTrue(test.isEmpty());
  }

}
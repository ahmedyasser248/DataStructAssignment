package eg.edu.alexu.csd.datastructure.Queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PriorityQueueTest {

  @Test
  void QueueTest() {
    PriorityQueue test=new PriorityQueue();
    //trying to insert a negative key (1 is highest priority)
    assertThrows(RuntimeException.class,()->{test.insert(30, -1);});
    //start to insert
    test.insert(1, 1);
    test.insert(2, 2);
    test.insert(3, 3);
    test.insert(4, 1);
    test.insert(5,2);
    //check size
    assertEquals(5,test.size());
    assertFalse(test.isEmpty());
    // min will be element which have key 3(smallest key)
    assertEquals(3,test.min());
    assertEquals(3,test.removeMin());
    assertEquals(5,test.removeMin());
    assertEquals(2,test.removeMin());
    assertEquals(4,test.removeMin());
    assertEquals(1,test.removeMin());
  }

}
package eg.edu.alexu.csd.datastructure.Queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class QueueArrayBasedTest {

  @Test
  void queueTest() {

    // negative number as size of array	used
    assertThrows(RuntimeException.class,()->{
      QueueArrayBased test=new QueueArrayBased(-4);});
    //
    QueueArrayBased test=new QueueArrayBased(4);
    assertEquals(true, test.isEmpty());

    //dequeue while queue is empty
    test.enqueue(1);
    test.enqueue(2);
    test.enqueue(3);
    test.enqueue(4);

    assertEquals(false, test.isEmpty());

    //size supposed to be four
    assertEquals(4,test.size());
    //queue now is full
    assertThrows(RuntimeException.class,()->{test.enqueue(5);});
    assertEquals(1,test.dequeue());
    assertEquals(2,test.dequeue());
    assertEquals(3,test.dequeue());
    assertEquals(4,test.dequeue());
    //dequeue while queue is empty
    assertThrows(RuntimeException.class,()->{test.dequeue();});
  }

}
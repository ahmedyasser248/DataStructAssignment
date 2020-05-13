package eg.edu.alexu.csd.datastructure.stack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;
import org.junit.jupiter.api.Test;
class StackTest {
  @Test
  void testMethods() {
    Stack testObject = new Stack();

    testObject.push("abc");
    testObject.push('X');
    testObject.push(123);
    testObject.push("final");
    assertEquals(4,testObject.size());
    assertEquals("final",testObject.peek());
    assertEquals("final",testObject.pop());
    assertEquals(123,testObject.pop());
    assertEquals('X',testObject.pop());
    assertEquals("abc",testObject.pop());
    assertThrows(EmptyStackException.class,()->{testObject.pop();});
    assertThrows(EmptyStackException.class,()->{testObject.peek();});
    assertTrue(testObject.isEmpty());
    assertEquals(0,testObject.size());
  }
}
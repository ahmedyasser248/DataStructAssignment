package eg.edu.alexu.csd.datastructure.stack;

import eg.edu.alexu.csd.datastructure.linkedlist.DLinkedList;
import java.util.EmptyStackException;

/**
 * @author Mohamed Osama Ahmed Elsamni
 */
public class Stack implements IStack{
  DLinkedList stack = new DLinkedList();
  int size = 0;
  @Override
  public Object pop() {
    if(size==0)
      throw new EmptyStackException();
    Object data = stack.get(size-1);
    stack.remove(--size);
    return data;
  }

  @Override
  public Object peek() {
    if(size==0)
      throw new EmptyStackException();
    return stack.get(size-1);
  }

  @Override
  public void push(Object element) {
    if(element==null)throw new RuntimeException("Can't push null Object to Stack");
    stack.add(element);
    size++;
  }

  @Override
  public boolean isEmpty() {
    if(size == 0)
      return true;
    return false;
  }

  @Override
  public int size() {
    return size;
  }
}

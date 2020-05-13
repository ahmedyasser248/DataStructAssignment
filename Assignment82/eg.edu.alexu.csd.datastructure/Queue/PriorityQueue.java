package eg.edu.alexu.csd.datastructure.Queue;

public class PriorityQueue implements IPriorityQueue {
  class Node{
    Object element;
    int key;
    Node next;
  }
  private int size;
  private Node head;
  private Node tail;
  @Override
  public void insert(Object item, int key) {
    if(key <= 0) {
      throw new RuntimeException("highest priority is 1 ");
    }
    Node newNode=new Node();
    newNode.key=key;
    newNode.element=item;
    if(size==0) {
      head=tail=newNode;
    } else {
      if(head.key>key) {
        newNode.next=head;
        head=newNode;
      }else {
        Node temp=head;
        while(temp.next!=null&& key>=temp.next.key) {
          temp=temp.next;
        }
        newNode.next=temp.next;
        temp.next=newNode;
        if(newNode.next==null) {
          tail=newNode;
        }
      }
    }
    size++;
    return;
  }
  @Override
  public Object removeMin() {
    if(size==0) {
      throw new RuntimeException("the queue is empty");
    }
    Object min;
    if(size==1) {
      min=head.element;
      head=tail=null;
    }else {
      Node temp=head;
      while(temp.next!=tail) {
        temp=temp.next;
      }
      min=tail.element;
      tail=temp;
      temp.next=null;
    }
    size--;
    return min;
  }
  @Override
  public Object min() {
    if(size==0) {
      throw new RuntimeException("the queue is empty");
    }
    Object min;
    if(size==1) {
      min=head.element;
    }else {
      Node temp=head;
      while(temp.next!=tail) {
        temp=temp.next;
      }
      min=tail.element;
    }
    return min;
  }
  public boolean isEmpty() {
    return size==0;
  }
  @Override
  public int size() {
    return size;
  }

  public  void print() {
    Node temp = head;
    while(temp!=null) {
      System.out.println(temp.element);
      temp=temp.next;
    }
    return;
  }

  public static void main(String[]args) {
    PriorityQueue obj=new PriorityQueue();
    obj.insert(3, 1);
    obj.insert(2, 5);
    obj.insert(4, 1);
    obj.insert(6, 1);
    obj.insert(5,10);
    obj.removeMin();
    obj.removeMin();
    obj.print();
  }
}

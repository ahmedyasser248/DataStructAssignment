import com.sun.jdi.InternalException;
import java.sql.SQLOutput;

public class DLinkedList implements ILinkedList {
  private int size;
  private Node head ;
  private Node tail ;
  public DLinkedList(){
    size = 0;
  }
  public  DLinkedList(Node head,Node tail,int size){

    head.prev=null;
    tail.next=null;
    this.size = size;

  }
  private static class Node{

    private Node next;
    private Node prev;
    private Object element;
    private Node(Object element){
      this.element = element;
    }
    private Node(){}
  }
  @Override
  public void add(int index, Object element) {
    if(index>size-1)
      return;
    if(index==size-1) {
      add(element);
      return;
    }
    if (index==0){
      Node newNode = new Node(element);
      head.prev = newNode;
      newNode.next = head;
      head = newNode;
    }
    Node temp = head;
    Node tempPrev=temp;
    Node newNode = new Node(element);
    for (int i = 0; i < index ; i++) {
      tempPrev=temp;
      temp = temp.next;
    }
    newNode.next = tempPrev.next;
    newNode.prev = temp.prev;
    tempPrev.next=newNode;
    temp.prev=newNode;
    size++;
  }

  @Override
  public void add(Object element) {
    Node newNode = new Node(element);
    if(size==0){
      head = newNode;
      tail = newNode;
      size++;
      return;
    }
    Node temp = tail.prev;
    tail.next = newNode;
    newNode.next = null; newNode.prev = tail;
    tail = newNode;
    size++;
  }

  @Override
  public Object get(int index) {
    Node temp = head;
    for (int i = 0; i < index ; i++) {
      temp = temp.next;
    }
    return temp.element;
  }

  @Override
  public void set(int index, Object element) {
    if(index>size-1)
      return;
    Node temp = head;
    for (int i = 0; i < index ; i++) {
      temp = temp.next;
    }
    temp.element = element;
  }

  @Override
  public void clear() {
    head.next.prev=null;head.next=null;head=null;
    tail.prev.next=null;tail.prev=null;tail=null;
    size=0;
  }

  @Override
  public boolean isEmpty() {
    if (size==0)return true;
    return false;
  }

  @Override
  public void remove(int index) {
    if(index>size-1)
      return;
    if(index==0){
      Node temp = head.next;
      head.next = null;
      temp.prev = null;
      head = temp;
      size--;return;
    }
    if(index==size-1){
      Node temp = tail.prev;
      tail.prev = null;
      temp.next = null;
      tail = temp;
      size--;return;
    }
    Node temp = head;
    Node tempPrev=temp;

    for (int i = 0; i < index ; i++) {
      tempPrev=temp;
      temp = temp.next;
    }
    Node tempNext = temp.next;
    tempPrev.next = temp.next;
    tempNext.prev=tempPrev;
    size--;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public ILinkedList sublist(int fromIndex, int toIndex) {
    Node tempBeg = head;
    Node tempEnd = tail;
    int sizeSubList = toIndex - fromIndex;
    for (int i = 0; i <= fromIndex ; i++) {
      tempBeg = tempBeg.next;
    }
    for (int i = size; i > toIndex ; i--) {
      tempEnd = tempEnd.prev;
    }

    return new DLinkedList(tempBeg,tempEnd,sizeSubList);
  }

  @Override
  public boolean contains(Object o) {
    Node temp = head;
    while(temp != null) {
      if(temp.element==o)return true;
      temp = temp.next;
    }
    return false;
  }

  @Override
  public String toString() {
    return "DLinkedList{" +
        "size=" + size +
        ", head=" + head.element +
        ", tail=" + tail.element +
        '}';
  }
  public static void printList(DLinkedList list)
  {
    Node currNode = list.head;

    System.out.print("LinkedList: ");

    // Traverse through the LinkedList
    while (currNode.next != null) {
      // Print the data at current node
      System.out.print(currNode.element + " ");

      // Go to next node
      currNode = currNode.next;
    }

    System.out.println();
  }

  public static void main(String[] args) {
    DLinkedList trialList = new DLinkedList();
    trialList.add(3);
    trialList.add(4);
    trialList.add(5);
    trialList.add(6);
    trialList.add(7);
    trialList.remove(3);
    trialList.add(3,8);
    trialList.add(9);

    System.out.println(trialList.contains(9));
    System.out.println(trialList.isEmpty());
    //System.out.println();
    trialList.set(4,20);
    //DLinkedList subListtrial = (DLinkedList) trialList.sublist(1,3);
    //printList(trialList);
    //printList(subListtrial);
    System.out.println(trialList.toString());
  }
}

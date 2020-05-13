package eg.edu.alexu.csd.datastructure.linkedlist;

public class DLinkedList implements ILinkedList {
  public int size;
  public Node head ;
  public Node tail ;
  public DLinkedList(){
    size = 0;
  }
  public  DLinkedList(Node head,Node tail,int size){
    head.prev=null;
    tail.next=null;
    this.size = size;
  }
  public class Node{

    public Node next;
    public Node prev;
    public Object element;
    public Node(Object element){
      this.element = element;
    }
    public Node(){}
  }


  @Override
  public void add(int index, Object element) {
    if(index>size-1 || index < 0)
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
      return;
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
    tail.next = newNode;
    newNode.next = null; newNode.prev = tail;
    tail = newNode;
    size++;
  }

  @Override
  public Object get(int index) {
    if(index>size-1){
      return null;
    }
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
    if(size==0){
      return;
    }
    if(size==1){
      head=null;
      tail=null;
      size=0;
      return;
    }
    Node temp1 = head.next;
    Node temp2 = tail.prev;
    temp1.prev = null;
    temp2.next = null;
    head = null;tail = null;

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
      if(size==1){
        head = tail = null;
        size--;
        return;
      }
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
    DLinkedList temp=new DLinkedList();
    if (toIndex < fromIndex || toIndex >= size || fromIndex < 0 || toIndex < 0) {
      return null;
    }
    Node fromNode=head;
    int counter=0;
    for(int i=0;i<fromIndex;i++) {
      fromNode=fromNode.next;
      counter++;
    }
    do {temp.add(fromNode.element);
      System.out.println(fromNode.element);
      fromNode=fromNode.next;
      counter++;
    }while(counter <= toIndex && fromNode != null);

    return temp;
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


}

package eg.edu.alexu.csd.datastructure.linkedlist;

public class LinkedList implements ILinkedList{
  int size;
  Node head;
  Node tail;
  public class Node{
    Object data;
    Node next;

    Node(Object data){
      this.data=data;//next is null by default.
    }


  }
  @Override
  public void add(int index, Object element) {
    Node newNode=new Node(element);
    if(index<0) {return;}
    if (size == 0) {
      head=tail=newNode;
      size++;
    }
    else if(index >= size) {
      tail.next=newNode;
      tail=newNode;
      size++;
    }
    else if(index==0) {
      newNode.next=head;
      head=newNode;
      size++;
    }
    else {Node prev =head;
      Node temp;
      for(int i=0;i<index-1;i++) {
        prev=prev.next;
      }
      temp=prev.next;
      prev.next=newNode;
      newNode.next=temp;
      size++;

    }
  }

  @Override
  public void add(Object element) {
    Node newNode=new Node(element);
    if (size == 0) {
      head=tail= newNode;

    }
    else {
      tail.next=newNode;
      tail=newNode;

    }
    size++;
  }

  @Override
  public Object get(int index) {
    if(index<size) {
      Node temp = head;
      int i = 0;
      while (i < index) {
        temp = temp.next;
        i++;
      }

      return temp.data;
    }
    else {
      return null;
    }

  }

  @Override
  public void set(int index, Object element) {

    if(index >= size) {
      add(index,element);
    }
    else {
      Node temp=head;
      for (int i = 0; i < index; i++) {
        temp=temp.next;
      }
      temp.data=element;

    }

  }

  @Override
  public void clear() {
    head=tail=null;
    size=0;

  }

  @Override
  public boolean isEmpty() {

    return (size==0);
  }

  @Override
  public void remove(int index) {
    if (size == 0||index >= size) {
      return;
    }
    else {Node previous =head;
      for(int i=0;i<index-1;i++) {
        previous=previous.next;
      }
      if(index==size-1) {
        tail=previous;
      }
      previous.next=(previous.next).next;
      size--;

    }



  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public ILinkedList sublist(int fromIndex, int toIndex) {
    LinkedList temp=new LinkedList();
    if (toIndex < fromIndex || toIndex >= size || fromIndex < 0 || toIndex < 0) {
      return null;
    }
    Node fromNode=head;
    int counter=0;
    for(int i=0;i<fromIndex;i++) {
      fromNode=fromNode.next;
      counter++;
    }
    do {temp.add(fromNode.data);
      System.out.println(fromNode.data);
      fromNode=fromNode.next;
      counter++;
    }while(counter <= toIndex && fromNode != null);

    return temp;
  }

  @Override
  public boolean contains(Object o) {
    boolean found =false;
    Node temp = head;
    while(temp.next!=null) {
      if(temp.data==o) {
        found=true;
        break;
      }
      else {
        temp=temp.next;
      }
    }
    return found;
  }


  public void print(Node head) {
    if(head==null&&tail==null) {
      System.out.println("the list is empty");
    }
    Node temp=head;
    while(temp!=null) {
      System.out.println(temp.data);
      temp=temp.next;
    }


  }






}

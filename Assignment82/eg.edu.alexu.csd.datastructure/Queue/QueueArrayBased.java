package eg.edu.alexu.csd.datastructure.Queue;

import java.util.Scanner;

public class QueueArrayBased implements IQueue, IArrayBased
{

  private int front;
  private int rear;
  private int numOfElements;
  private int size;

  private Object[] elements;


  public QueueArrayBased(int size)
  {
    if(size <= 0)
      throw new RuntimeException("Size must be greater than 0");

    front = 0;
    rear = 0;
    numOfElements = 0;
    this.size = size;
    elements = new Object[size];
  }

  public void enqueue(Object item)
  {
    if(numOfElements == size)
      throw new RuntimeException("Queue is full");

    numOfElements++;

    elements[rear] = item;
    rear = (rear+1)%size;
  }


  public Object dequeue()
  {
    if(isEmpty())
      throw new RuntimeException("Queue is Empty");

    numOfElements--;

    Object data = elements[front];
    elements[front] = null;

    front = (front + 1)%size;
    return data;
  }


  public boolean isEmpty()
  {
    return numOfElements == 0;
  }

  public int size()
  {
    return numOfElements;
  }


  // For Debugging purposes
  public void traverse()
  {
    if(isEmpty())
      System.out.print("Queue is Empty");

    for(int i = front, q =0;q < size();i=(i+1)%size, q++)
      System.out.print((int)elements[i] + " ");

    System.out.println();
  }

  public static void main(String[] args) {
    QueueArrayBased q = new QueueArrayBased(5);

    Scanner cin = new Scanner(System.in);
    while(true)
    {
      int in = cin.nextInt();
      if(in == 1)
        q.enqueue(cin.nextInt());
      if(in == 2)
        q.dequeue();
      if(in == 3)
        break;
      q.traverse();
    }
  }

}
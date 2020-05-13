package classes;

import eg.edu.alexu.csd.datastructure.linkedlist.DLinkedList;
import eg.edu.alexu.csd.datastructure.stack.Stack;

public class SortandSearch {

  public static int  partition(int []arr,int start,int end) {
    int pivot=arr[end];
    int pivotIndex=start;
    int temp;
    for(int i=start;i<end;i++) {
      if(arr[i]<=pivot) {
        temp=arr[i];
        arr[i]=arr[pivotIndex];
        arr[pivotIndex]=temp;
        pivotIndex++;
      }
    }
    temp=arr[pivotIndex];
    arr[pivotIndex]=arr[end];
    arr[end]=temp;
    return pivotIndex;
  }

  public static void quickSort(int []arr, DLinkedList index) {

    int start=0;
    int end=arr.length-1;
    Stack stack=new Stack();
    stack.push(start);
    stack.push(end);
    while(!stack.isEmpty()) {
      end =(int) stack.pop();//end
      start =(int) stack.pop();//start

      int pivot=partition(arr,start,end);

      if(pivot-1>start) {
        stack.push(start);
        stack.push(pivot-1);
      }
      if(pivot+1<end) {
        stack.push(pivot+1);
        stack.push(end);
      }

    }

  }
}
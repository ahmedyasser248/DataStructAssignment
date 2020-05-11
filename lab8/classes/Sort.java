package classes;

//public class Sort implements ISort {
//  boolean sort;//we will use it if sorting is checked on.
//  public static int  partition(int []arr,int start,int end) {
//    int pivot=arr[end];
//    int pivotIndex=start;
//    int temp;
//    for(int i=start;i<end;i++) {
//      if(arr[i]<=pivot) {
//        temp=arr[i];
//        arr[i]=arr[pivotIndex];
//        arr[pivotIndex]=temp;
//        pivotIndex++;
//      }
//    }
//    temp=arr[pivotIndex];
//    arr[pivotIndex]=
//        arr[end];
//    arr[end]=temp;
//    return pivotIndex;
//  }
//
//  public static void quickSort(DLinkedList list) {
//
//    String[]arr=new String[list.size()];
//    Node temp=list.head;
//    for(int i=0;i<list.size();i++) {//moving data to array
//      arr[i]=temp.element+"";
//      temp=temp.next;
//    }
//
//    int start=0;
//    int end=arr.length-1;
//    Stack stack=new Stack();
//    stack.push(start);
//    stack.push(end);
//    while(!stack.isEmpty()) {
//      end =(int) stack.pop();//end
//      start =(int) stack.pop();//start
//
//      int pivot=partition(arr,start,end);
//
//      if(pivot-1>start) {
//        stack.push(start);
//        stack.push(pivot-1);
//      }
//      if(pivot+1<end) {
//        stack.push(pivot+1);
//        stack.push(end);
//      }
//
//    }
//
//  }
//  public static void main(String[]args) {
//    int []arr= {1,2,24,8,3,6};
//    //quickSort(arr);
//    for(int i=0;i<arr.length;i++) {
//      System.out.println(arr[i]);
//    }
//  }
//
//}

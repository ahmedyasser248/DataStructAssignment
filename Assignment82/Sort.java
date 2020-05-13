package classes;

import eg.edu.alexu.csd.datastructure.Queue.PriorityQueue;
import eg.edu.alexu.csd.datastructure.linkedlist.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedlist.DLinkedList.Node;
import eg.edu.alexu.csd.datastructure.stack.Stack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sort implements ISort {
  boolean sort;//we will use it if sorting is checked on.
  String sortType;
  public Sort(String sortType){
    this.sortType = sortType;
  }
  public  int  partitionByDate(Mail []arr,int start,int end) {
    Mail pivot=arr[end];
    int pivotIndex=start;
    Mail temp;
    for(int i=start;i<end;i++) {
      if(arr[i].getDateReceived().compareTo(pivot.getDateReceived())<0 ||arr[i].getDateReceived().compareTo(pivot.getDateReceived())==0 ) { //arr[i]<=pivot
        temp=arr[i];     //temp=arr[i]
        arr[i]=arr[pivotIndex];   //arr[i]=arr[pivotIndex]
        arr[pivotIndex]=temp;    //arr[pivotIndex]=temp
        pivotIndex++;
      }
    }
    temp=arr[pivotIndex];
    arr[pivotIndex]= arr[end];
    arr[end]=temp;
    return pivotIndex;
  }
  public  int  partitionBySubject(Mail []arr,int start,int end) {
    Mail pivot=arr[end];
    int pivotIndex=start;
    Mail temp;
    for(int i=start;i<end;i++) {
      if(arr[i].getSubject().compareToIgnoreCase(pivot.getSubject())<0 ||arr[i].getSubject().compareToIgnoreCase(pivot.getSubject())==0 ) { //arr[i]<=pivot
        temp=arr[i];     //temp=arr[i]
        arr[i]=arr[pivotIndex];   //arr[i]=arr[pivotIndex]
        arr[pivotIndex]=temp;    //arr[pivotIndex]=temp
        pivotIndex++;
      }
    }
    temp=arr[pivotIndex];
    arr[pivotIndex]= arr[end];
    arr[end]=temp;
    return pivotIndex;
  }
  public  int  partitionBySender(Mail []arr,int start,int end) {
    Mail pivot=arr[end];
    int pivotIndex=start;
    Mail temp;
    for(int i=start;i<end;i++) {
      if(arr[i].getSender().compareToIgnoreCase(pivot.getSender())<0 ||arr[i].getSender().compareToIgnoreCase(pivot.getSender())==0 ) { //arr[i]<=pivot
        temp=arr[i];     //temp=arr[i]
        arr[i]=arr[pivotIndex];   //arr[i]=arr[pivotIndex]
        arr[pivotIndex]=temp;    //arr[pivotIndex]=temp
        pivotIndex++;
      }
    }
    temp=arr[pivotIndex];
    arr[pivotIndex]= arr[end];
    arr[end]=temp;
    return pivotIndex;
  }



  public  Mail[] quickSort(DLinkedList list) {
    if(list.size()==1){
      Node temp=list.head;
      Mail[]arr=new Mail[list.size()];
      for(int i=0;i<list.size();i++) {//moving data to array
        arr[i]= (Mail) temp.element;
        temp=temp.next;
      }
      return arr;
    }
    if(list.size()==0){
      return null;
    }
    Mail[]arr=new Mail[list.size()];
    Node temp=list.head;
    for(int i=0;i<list.size();i++) {//moving data to array
      arr[i]= (Mail) temp.element;
      temp=temp.next;
    }
    if(sortType=="Priority"){
      PriorityQueue queue = new PriorityQueue();
      for (int i = 0; i < arr.length; i++) {
        queue.insert(arr[i],Integer.parseInt(arr[i].getImportance())) ;
      }
      for (int i = 0; i <arr.length ; i++) {
        arr[arr.length-i-1] = (Mail)queue.removeMin();
      }
      return arr;
    }

    int start=0;
    int end=arr.length-1;
    Stack stack=new Stack();
    stack.push(start);
    stack.push(end);
    while(!stack.isEmpty()) {
      end =(int) stack.pop();//end
      start =(int) stack.pop();//start
      int pivot=0;
      if(sortType==null){
        pivot=partitionByDate(arr,start,end);
      }
      else{
      switch (sortType) {
        case "By subject":
          pivot = partitionBySubject(arr, start, end);
          break;
        case "By sender":
          pivot = partitionBySender(arr, start, end);
          break;
        case "By date":
          pivot = partitionByDate(arr, start, end);
          break;
      }
      }


      if(pivot-1>start) {
        stack.push(start);
        stack.push(pivot-1);
      }
      if(pivot+1<end) {
        stack.push(pivot+1);
        stack.push(end);
      }

    }
    for (int i = 0; i < arr.length/2 ; i++) {
      Mail temp1 = arr[i];
      arr[i] = arr[arr.length-i-1];
      arr[arr.length-i-1] = temp1;
    }
    return arr;
//    DLinkedList newDLL = new DLinkedList();
//    for (int i = 0; i < arr.length ; i++) {
//      newDLL.add(arr[i]);
//    }
//    list = newDLL;
//    Object temp1;
//    int size = list.size;
//    for (int i = 0; i < list.size/2 ; i++) {
//      temp1 = list.get(i);
//      list.set(i,list.get(size-i-1));
//      list.set(size-i-1, temp1);
//    }

  }

  public ObservableList<String> setSortComboBox(){
    ObservableList<String> listOfSortTypes = FXCollections.observableArrayList();

    listOfSortTypes.add("By subject");
    listOfSortTypes.add("By date");
    listOfSortTypes.add("By sender");
    listOfSortTypes.add("Priority");
    return listOfSortTypes;
  }


}

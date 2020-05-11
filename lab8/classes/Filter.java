package classes;

import eg.edu.alexu.csd.datastructure.linkedlist.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedlist.DLinkedList.Node;

public class Filter implements IFilter {
  private String filterWord;
  Filter(String filterWord){
    this.filterWord = filterWord;
  }

  public Filter() {
    filterWord = null;
  }

  public DLinkedList filter(DLinkedList mailList,String filterWord){
    Node current = mailList.head;
    DLinkedList filteredList = new DLinkedList();
    for (int i = 0; i < mailList.size(); i++) {
      String[] currentMail = current.element.toString().split(" ");
      if(currentMail[1].equals(filterWord)){
        filteredList.add(current.element);
      }
    }
    return filteredList;
  }


}

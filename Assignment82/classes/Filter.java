package classes;

import eg.edu.alexu.csd.datastructure.linkedlist.DLinkedList;
import eg.edu.alexu.csd.datastructure.stack.Stack;

public class Filter implements IFilter {

  private String filterWord;
  public String filterType;

  public Filter(String filterWord,String filterType) {
    this.filterWord = filterWord;
    this.filterType = filterType;
  }

  public Filter() {
    filterWord = "";
  }

//  public DLinkedList filter(DLinkedList mailList, String filterWord) {
//    Node current = mailList.head;
//    DLinkedList filteredList = new DLinkedList();
//    for (int i = 0; i < mailList.size(); i++) {
//      String[] currentMail = current.element.toString().split(" ");
//      if (currentMail[1].equals(filterWord)) {
//        filteredList.add(current.element);
//      }
//    }
//    return filteredList;
//  }

  public Mail[] filter(DLinkedList sortedMails) {

    Stack stack = new Stack();
    Mail[] filteredMail = new Mail[sortedMails.size()];
    int numberOfFilteredMails = 0;
    int start = 0;
    int end = sortedMails.size() - 1;
    stack.push(start);
    stack.push(end);
    int mid = 0;
    int found1 = 0;
    int found = 0;
    if(filterWord.compareTo("")==0){
      for (int i = 0; i < sortedMails.size(); i++) {
        filteredMail[i] = (Mail)sortedMails.get(i);
      }
      return filteredMail;
    }
    if(filterType==null) {
      while (start <= end) {
        if (found == 1)
          break;
        end = (int) stack.pop();
        start = (int) stack.pop();
        mid = start + (end - start) / 2;
        Mail temp = (Mail) sortedMails.get(mid);
        String currentSubject = temp.getSubject();
        if (filterWord.compareToIgnoreCase(currentSubject) == 0) {
          filteredMail[numberOfFilteredMails] = temp;
          numberOfFilteredMails++;
          found1 = 1;
        }
        if (found1 == 1) {
          int lower = mid - 1, higher = mid + 1;
          while (lower >= start) {
            Mail temp2 = (Mail) sortedMails.get(lower);
            if (filterWord.compareToIgnoreCase(temp2.getSubject()) == 0) {
              filteredMail[numberOfFilteredMails] = temp2;
              numberOfFilteredMails++;
            } else
              break;
            lower--;
          }
          while (higher <= end) {
            Mail temp2 = (Mail) sortedMails.get(higher);

            if (filterWord.compareTo(temp2.getSubject()) == 0) {
              filteredMail[numberOfFilteredMails] = temp2;
              numberOfFilteredMails++;
            } else
              break;
            higher++;
          }
          found = 1;
        } else {
          if (filterWord.compareToIgnoreCase(currentSubject) < 0) {
            end = mid - 1;
            stack.push(start);
            stack.push(end);
          } else {
            start = mid + 1;
            stack.push(start);
            stack.push(end);
          }
        }
      }
    }
      else{
        switch (filterType) {
          case "By subject":
            while (start <= end) {
              if (found == 1)
                break;
              end = (int) stack.pop();
              start = (int) stack.pop();
              mid = start + (end - start) / 2;
              Mail temp = (Mail) sortedMails.get(mid);
              String currentSubject = temp.getSubject();
              if (this.filterWord.compareToIgnoreCase(currentSubject) == 0) {
                filteredMail[numberOfFilteredMails] = temp;
                numberOfFilteredMails++;
                found1 = 1;
              }
              if (found1 == 1) {
                int lower = mid - 1, higher = mid + 1;
                while (lower >= start) {
                  Mail temp2 = (Mail) sortedMails.get(lower);
                  if (filterWord.compareToIgnoreCase(temp2.getSubject()) == 0) {
                    filteredMail[numberOfFilteredMails] = temp2;
                    numberOfFilteredMails++;
                  } else
                    break;
                  lower--;
                }
                while (higher <= end) {
                  Mail temp2 = (Mail) sortedMails.get(higher);

                  if (filterWord.compareTo(temp2.getSubject()) == 0) {
                    filteredMail[numberOfFilteredMails] = temp2;
                    numberOfFilteredMails++;
                  } else
                    break;
                  higher++;
                }
                found = 1;
              } else {
                if (filterWord.compareToIgnoreCase(currentSubject) < 0) {
                  end = mid - 1;
                  stack.push(start);
                  stack.push(end);
                } else {
                  start = mid + 1;
                  stack.push(start);
                  stack.push(end);
                }
              }
            }
            break;
          case "By sender":
            while (start <= end) {
              if (found == 1)
                break;
              end = (int) stack.pop();
              start = (int) stack.pop();
              mid = start + (end - start) / 2;
              Mail temp = (Mail) sortedMails.get(mid);
              String currentSender = temp.getSender();
              if (this.filterWord.compareToIgnoreCase(currentSender) == 0) {
                filteredMail[numberOfFilteredMails] = temp;
                numberOfFilteredMails++;
                found1 = 1;
              }
              if (found1 == 1) {
                int lower = mid - 1, higher = mid + 1;
                while (lower >= start) {
                  Mail temp2 = (Mail) sortedMails.get(lower);
                  if (filterWord.compareToIgnoreCase(temp2.getSender()) == 0) {
                    filteredMail[numberOfFilteredMails] = temp2;
                    numberOfFilteredMails++;
                  } else
                    break;
                  lower--;
                }
                while (higher <= end) {
                  Mail temp2 = (Mail) sortedMails.get(higher);

                  if (filterWord.compareTo(temp2.getSender()) == 0) {
                    filteredMail[numberOfFilteredMails] = temp2;
                    numberOfFilteredMails++;
                  } else
                    break;
                  higher++;
                }
                found = 1;
              } else {
                if (filterWord.compareToIgnoreCase(currentSender) < 0) {
                  end = mid - 1;
                  stack.push(start);
                  stack.push(end);
                } else {
                  start = mid + 1;
                  stack.push(start);
                  stack.push(end);
                }
              }
            }
            break;
          default:
            for (int i = 0; i < sortedMails.size(); i++) {
              filteredMail[i] = (Mail)sortedMails.get(i);
            }
            return filteredMail;

        }
      }
    if (filteredMail.length == 0) {

    }

      return filteredMail;

    }

}

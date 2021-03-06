package classes;

import static classes.repeatedMethods.dateToString;

import application.viewMailController;
import eg.edu.alexu.csd.datastructure.linkedlist.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedlist.LinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Folder implements IFolder {



  private int numberOfPages;
  private int currentPage;
  private String username;
  private String viewedFolder;//inbox or trash or whatever
  private String path;
  public DLinkedList index;//to load index file as what was said in assignment.
  public DLinkedList selectedMails = new DLinkedList();
  public Mail[] sortedAndFilteredMails;



  public DLinkedList getSelectedMails() {
    return selectedMails;
  }

  public void setSelectedMails(DLinkedList selectedMails) {
    this.selectedMails = selectedMails;
  }

  public int getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(int numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getViewedFolder() {
    return viewedFolder;
  }

  public void setViewedFolder(String viewedFolder) {
    this.viewedFolder = viewedFolder;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Folder(String username) {
    String fileSeparator = System.getProperty("file.separator");
    this.username = username;
    this.path = "System" + fileSeparator + username;
    index = new DLinkedList();

  }

  public void readFolder(String folder) {
    //folder can be inbox or trash or any folder that contain a index file
    index.clear();
    viewedFolder = folder;
    int numberOfMails = 0;
    String fileSeparator = System.getProperty("file.separator");
    try {
      BufferedReader reader = new BufferedReader(
          new FileReader(path + fileSeparator + folder + fileSeparator + "index.txt"));
      String line;

      while ((line = reader.readLine()) != null) {
        index.add(new Mail(line));     //add each line in index file to a node in double linked list.
        numberOfMails++;
      }
      reader.close();
    } catch (Exception e) {
      System.out.println(e);
    }
    numberOfPages = numberOfMails / 10 + ((numberOfMails%10==0)?0:1);
    System.out.println(numberOfPages);
  }

  public void readFolder2(String folder) {
    //folder can be inbox or trash or any folder that contain a index file
    viewedFolder = folder;
    String fileSeparator = System.getProperty("file.separator");
    try {
      BufferedReader reader = new BufferedReader(
          new FileReader(path + fileSeparator + folder + fileSeparator + "index.txt"));
      String line;
      while ((line = reader.readLine()) != null) {
        Mail newMail = new Mail(line);
        index.add(newMail);        //add each line in index file to a node in double linked list.
      }
      reader.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public ObservableList<Integer> setPageComboBox(){
    ObservableList<Integer> listOfPages = FXCollections.observableArrayList();

    for (int i = 0; i < this.getNumberOfPages() ; i++) {
      listOfPages.add(i+1);
    }
    return listOfPages;
  }

  public DLinkedList listToDLinkedList(ObservableList<Mail> list){
    Object[] array = list.toArray();
    Mail[] array2 = new Mail[array.length];
    for (int i = 0; i < array.length; i++) {
      array2[i] = (Mail)array[i];
    }
    DLinkedList linkedList = new DLinkedList();
    for (int i = 0; i < array2.length ; i++) {
      linkedList.add(array[i]);
    }
    return linkedList;
  }

  public LinkedList listToLinkedList(ObservableList<Mail> list){
    Object[] array = list.toArray();
    Mail[] array2 = new Mail[array.length];
    for (int i = 0; i < array.length; i++) {
      array2[i] = (Mail)array[i];
    }
    LinkedList linkedList = new LinkedList();
    for (int i = 0; i < array2.length ; i++) {
      linkedList.add(array[i]);
    }
    return linkedList;
  }

//  public void stringToMail(){
//    for (int i = 0; i <index.size()   ; i++) {
//      String indexStrings = (String)index.get(i);
//      index.set(i,new Mail(indexStrings));
//    }
//  }

  public void setSMails(Mail[] arr){
    index.clear();
    if(arr==null){
      return;
    }
    for (int i = 0; i < arr.length ; i++) {
      index.add(arr[i]);
    }
  }

  public void setFMails(Mail[] arr){
    index.clear();
    if(arr==null){
      return;
    }
    for (int i = 0; i < arr.length ; i++) {
      index.add(arr[i]);
    }
  }
//  public void reverseIndex(){
//    Object temp;
//    int size = index.size;
//    for (int i = 0; i < index.size ; i++) {
//      temp = index.get(i);
//      index.set(i,index.get(size-i-1));
//      index.set(size-i-1, temp);
//    }
//  }

  public  void openMailWindows(DLinkedList linkedList,App app) throws IOException {
    String fileSeparator = System.getProperty("file.separator");
    for (int i = 0; i < linkedList.size() ; i++) {
      Mail currentMail = (Mail) linkedList.get(i);
      String file = dateToString(currentMail.getDateReceived()) ;
    try {
      BufferedReader reader = new BufferedReader(
          new FileReader(this.path + fileSeparator + this.getViewedFolder() + fileSeparator + file + fileSeparator + "component.txt"));
      String line;
      for (int j = 0; j < 4; j++) {
        line = reader.readLine();
      }
      currentMail.component = "";
      while ((line = reader.readLine()) != null) {
        currentMail.component += line;
      }
      reader.close();
    } catch (Exception e) {
      System.out.println(e);
    }




      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("/application/viewMail.fxml"));
      Scene scene = new Scene(fxmlLoader.load());
      Stage stage = new Stage();
      viewMailController controller = fxmlLoader.getController();
      controller.newInstance =  app;
//      if((currentMail.nameOfAttachements!=null)||(!currentMail.nameOfAttachements.matches(""))) {
//        ObservableList<File> attachs = FXCollections.observableArrayList();
//        String[] nameOfattachements = currentMail.nameOfAttachements.split(" ");
//        for (int j = 0; j < nameOfattachements.length; j++) {
//          attachs.add(new File(this.path + fileSeparator + this.getViewedFolder() + fileSeparator + file + fileSeparator +nameOfattachements[i]));
//        }
//        controller.setAttachements(attachs);
//      }

      controller.init(currentMail);
      stage.setTitle("Mail");
      stage.setScene(scene);
      stage.show();
    }
  }
}
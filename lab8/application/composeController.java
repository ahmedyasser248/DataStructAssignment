package application;

import classes.App;
import classes.Mail;
import classes.repeatedMethods;
import eg.edu.alexu.csd.datastructure.linkedlist.LinkedList;
import java.io.File;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
public class composeController {
  @FXML
  private TextField tf1;//recievers
  @FXML
  private TextField tf2;//subject
  @FXML
  private ChoiceBox CB;
  @FXML
  private TextField tf3;//components
  @FXML
  private ListView<File> lv;
  @FXML
  private Label lbl;
  @FXML
  private Label lbl2;

//public void initialize(URL location,ResourceBundle resources) {
//	lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//}

  public void initialize() {
    lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    CB.setValue("4");
    CB.getItems().addAll("1","2","3","4");
  }

  public void Send(ActionEvent event) {
    App obj=new App();
    Mail obj2;
    String sender="user1u";//mafrod da gayly mn controller abl kda
    String reciever=tf1.getText();
    String importance=CB.getValue().toString();
    String subject=tf2.getText();
    String components=tf3.getText();
    if(tf2.getText().trim().isEmpty()) {subject="None";}
    obj2=new Mail(sender,reciever,subject,components,importance);
    ObservableList<File> files=  lv.getItems();
    for(int j=0;j<files.size();j++) {
      obj2.attachements.add(files.get(j));
    }
    if(tf3.getText().trim().isEmpty()&&lv.getItems().isEmpty()) {
      lbl2.setText("you should add a component \n or an attachement ");
      return;
    }
    if(obj.compose(obj2)) {
      //hrg3 ll setting view w 22fel dy

    } else {
      lbl.setText("check recievers usernames");
    }
    System.out.println("i reached here");
  }

  public void addattachements(ActionEvent event) {
    LinkedList selectedFilesx=new LinkedList();
    FileChooser fc=new FileChooser();
    List<File>selectedFiles = fc.showOpenMultipleDialog(null);
    if(selectedFiles!=null) {
      for(int i=0;i<selectedFiles.size();i++) {
        selectedFilesx.add(selectedFiles.get(i));
      }
      for(int j=0;j<selectedFilesx.size();j++) {
        File temp=(File)selectedFilesx.get(j);
        lv.getItems().add(temp);
      }

    }else {return ;}

  }

  public void remove(ActionEvent event) {
    ObservableList<File> deleted=lv.getSelectionModel().getSelectedItems();
    lv.getItems().removeAll(deleted);
  }

  public void saveDrafts(ActionEvent event) {
    repeatedMethods obj=new repeatedMethods();
    Mail obj2;
    String sender="user1u";//mafrod da gayly mn controller abl kda
    String reciever=tf1.getText();
    String importance=CB.getValue().toString();
    String subject=tf2.getText();
    String components=tf3.getText();
    if(tf2.getText().trim().isEmpty()) {subject="None";}
    obj2=new Mail(sender,reciever,subject,components,importance);
    ObservableList<File> files=  lv.getItems();
    for(int j=0;j<files.size();j++) {
      obj2.attachements.add(files.get(j));
    }
    if(tf3.getText().trim().isEmpty()&&lv.getItems().isEmpty()) {
      lbl2.setText("you should add a component \n or an attachement ");
      return;
    }
    if(repeatedMethods.saveDraft(obj2)) {
      System.out.println("it worked");
    }else {
      System.out.println("it failed");


    }



  }

}
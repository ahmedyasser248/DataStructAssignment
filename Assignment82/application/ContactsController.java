package application;

import classes.App;
import classes.Contact;
import classes.contactsMethods;
import eg.edu.alexu.csd.datastructure.linkedlist.LinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
public class ContactsController implements Initializable {
  //configure the table
  @FXML
  private TableView<Contact> tableView;
  @FXML
  private TableColumn<Contact,String>firstColumn;
  @FXML
  private TableColumn<Contact,String>secondCoulmn;
  @FXML
  private TableColumn<Contact,String>thirdColumn;
  @FXML
  private TextField tf1;
  @FXML
  private Button bt1;
  @FXML
  private Label lbl;
  @FXML
  private Button bt2;
  @FXML
  private Label lb2;

  public App appinstance1 = new App();
  private ObservableList<Contact> data;
  public void initialize(URL url,ResourceBundle rb) {
    //set up column in the table
    firstColumn.setCellValueFactory(new PropertyValueFactory<Contact,String>("nameTable"));
    secondCoulmn.setCellValueFactory(new PropertyValueFactory<Contact,String>("otherEmailsTable"));
    thirdColumn.setCellValueFactory(new PropertyValueFactory<Contact,String>("userNameTable"));

    tableView.setEditable(true);
    secondCoulmn.setCellFactory(TextFieldTableCell.forTableColumn());
  }
  public void changeOtherEmailsCellEvent(CellEditEvent edditedCell) {
    Contact selectedContact = tableView.getSelectionModel().getSelectedItem();
    if(edditedCell.getNewValue().toString().trim().isEmpty()) {
      selectedContact.setOtherEmailsTable("None");
    }else {
      selectedContact.setOtherEmailsTable(edditedCell.getNewValue().toString());}
    //remove user1u and add the username.
    contactsMethods.editContact(selectedContact,appinstance1.currentFolder.getUsername());
  }

  public ObservableList<Contact> getData(){
    //here we must insert name of user
    LinkedList obj=contactsMethods.readContatcs(appinstance1.currentFolder.getUsername());
    data=FXCollections.observableArrayList();
    for(int i=0;i<obj.size();i++) {
      Contact temp=(Contact)obj.get(i);
      data.add(temp);
    }
    return data;
  }

  public void Add(ActionEvent event) {
    lbl.setText(" ");
    if(tf1.getText().trim().isEmpty()) {
      lbl.setText("Fill name !");
      return;}
    String username=tf1.getText();
    //enter name of user here
    if(!contactsMethods.addContacts(appinstance1.currentFolder.getUsername(), username)) {
      lbl.setText("user not found or duplicate");
      return;
    }
    Contact obj=contactsMethods.getContact(username);
    tableView.getItems().add(obj);
  }
  public void delete(ActionEvent event) {
    lb2.setText(" ");
    Contact obj=tableView.getSelectionModel().getSelectedItem();
    if(obj==null) {
      lb2.setText("Choose Contact");
      return;
    }//remove user1u and put username
    contactsMethods.deleteContacts(appinstance1.currentFolder.getUsername(),obj.getUserName());
    data.remove(obj);
  }

  void init(){
    tableView.setItems(getData());
  }


}
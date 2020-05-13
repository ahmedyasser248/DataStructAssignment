package application;

import static classes.Mail.setObservableMails;

import classes.App;
import classes.Filter;
import classes.Mail;
import classes.Sort;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MailListingController implements Initializable {


@FXML private TableView<Mail> tableViewInbox;
@FXML private TableColumn<Mail,String> subjectColumn;
@FXML private TableColumn<Mail,String> senderColumn;
@FXML private TableColumn<Mail, LocalDateTime> dateColumn;
@FXML private Button composeButton;
@FXML private Button refreshButton;
@FXML private Button viewButton;
@FXML private Button deleteButton;
@FXML private Button applyButton;
@FXML private Button contactsButton;
@FXML private TextField filterField;
@FXML private AnchorPane anchorPane;
@FXML private Tab InboxTab;
@FXML private Tab DraftTab;
@FXML private Tab SentTab;
@FXML private Tab TrashTab;
@FXML private TabPane tabPane;
@FXML private TableView<Mail> tableViewSent;
@FXML private TableColumn<Mail,String> subjectColumnSent;
@FXML private TableColumn<Mail,String> sentToColumnSent;
@FXML private TableColumn<Mail, LocalDateTime> dateColumnSent;

  @FXML private TableView<Mail> tableViewTrash;
  @FXML private TableColumn<Mail,String> subjectColumnTrash;
  @FXML private TableColumn<Mail,String> senderColumnTrash;
  @FXML private TableColumn<Mail, LocalDateTime> dateColumnTrash;

@FXML private ComboBox pageComboBox;
  @FXML private ComboBox sortComboBox;
public App appinstance = new App();
Boolean pageComboBoxListenerIsActive = true;


  public void init(){


    subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
    senderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<Mail,LocalDateTime>("dateReceived"));
    tableViewInbox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    subjectColumnSent.setCellValueFactory(new PropertyValueFactory<>("subject"));
    sentToColumnSent.setCellValueFactory(new PropertyValueFactory<>("receivers"));
    dateColumnSent.setCellValueFactory(new PropertyValueFactory<Mail,LocalDateTime>("dateReceived"));
    tableViewSent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    subjectColumnTrash.setCellValueFactory(new PropertyValueFactory<>("subject"));
    senderColumnTrash.setCellValueFactory(new PropertyValueFactory<>("sender"));
    dateColumnTrash.setCellValueFactory(new PropertyValueFactory<Mail,LocalDateTime>("dateReceived"));
    tableViewTrash.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    //retrieves mails in the index file;
    appinstance.currentFolder.readFolder("inbox");


    sortComboBox.setItems(appinstance.currentSort.setSortComboBox());
    sortComboBox.setValue("By date");
    pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
    pageComboBox.setValue(1);


    //activates when selecting a new page
    pageComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>()
    {
      public void changed(ObservableValue<? extends Integer> ov,
          final Integer oldvalue, final Integer newvalue)
      {
        if(newvalue==null){return;}
        if(pageComboBoxListenerIsActive==false){return;}
        newPage(newvalue-1);
      }});


    tableViewInbox.setItems(setObservableMails(appinstance.listEmails(0)));


    //activates when changing tabs
    tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {

      try {
        tabChange(newTab.getText());
      } catch (IOException e) {
        e.printStackTrace();
      }

    });

//    inboxTabPages = new Pagination(appinstance.currentFolder.getNumberOfPages());
//    inboxTabPages.setMaxPageIndicatorCount(3);
//    inboxTabPages.setPageFactory((pageIndex) -> {
//      tableViewInbox.setItems(setObservableMails(appinstance.listEmails(pageIndex)));
//      return null;
//    });
  }

  public void composeButton(ActionEvent Event) throws IOException {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("Compose.fxml"));
    Parent root = loader.load();
    composeController composeController = loader.getController();
    composeController.newInstance = this.appinstance;
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setTitle("Compose");
    stage.setScene(scene);
    stage.show();
  }

  public void refreshButton(ActionEvent Event) throws IOException {
    switch (appinstance.currentFolder.getViewedFolder()){
      case "inbox":
        tableViewInbox.setItems(setObservableMails(appinstance.listEmails(appinstance.currentFolder.getCurrentPage())));
        break;
      case "sent":
        tableViewSent.setItems(setObservableMails(appinstance.listEmails(appinstance.currentFolder.getCurrentPage())));
        break;
      case "drafts":
        break;
      case "trash":
        break;
    }
  }

  public void viewButton(ActionEvent Event) throws IOException{
    switch (appinstance.currentFolder.getViewedFolder()){
      case "inbox":
        ObservableList<Mail> viewedMailsInbox = tableViewInbox.getSelectionModel().getSelectedItems();
        appinstance.currentFolder.openMailWindows(appinstance.currentFolder.listToDLinkedList(viewedMailsInbox),appinstance);
        break;
      case "sent" :
        ObservableList<Mail> viewedMailsSent = tableViewSent.getSelectionModel().getSelectedItems();
        appinstance.currentFolder.openMailWindows(appinstance.currentFolder.listToDLinkedList(viewedMailsSent),appinstance);
        break;
      case "trash":
        ObservableList<Mail> viewedMailsTrash = tableViewTrash.getSelectionModel().getSelectedItems();
        appinstance.currentFolder.openMailWindows(appinstance.currentFolder.listToDLinkedList(viewedMailsTrash),appinstance);
        break;
    }

  }

  public void deleteButton(ActionEvent Event) throws IOException {
    switch (appinstance.currentFolder.getViewedFolder()){
      case "inbox":
        ObservableList<Mail> viewedMailsInbox = tableViewInbox.getSelectionModel().getSelectedItems();
        appinstance.deleteEmails(appinstance.currentFolder.listToLinkedList(viewedMailsInbox));
        break;
      case "sent" :
        ObservableList<Mail> viewedMailsSent = tableViewSent.getSelectionModel().getSelectedItems();
        appinstance.deleteEmails(appinstance.currentFolder.listToLinkedList(viewedMailsSent));
        break;
    }

  }

  public void contactsButton(ActionEvent Event) throws IOException{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Contacts.fxml"));
    Parent root = loader.load();
    ContactsController contactsController = loader.getController();
    contactsController.appinstance1 = this.appinstance;
    contactsController.init();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setTitle("Contacts");
    stage.setScene(scene);
    stage.show();
  }

  public void applyButton(ActionEvent Event){
    appinstance.setViewingOptions(null,new Filter(filterField.getText(),(String)sortComboBox.getSelectionModel().getSelectedItem()),new Sort((String) sortComboBox.getSelectionModel().getSelectedItem()));
    appinstance.currentFolder.readFolder(appinstance.currentFolder.getViewedFolder());
    switch (appinstance.currentFolder.getViewedFolder()){
      case "inbox" :
        //pageComboBoxListenerIsActive=false;
        appinstance.currentFolder.setCurrentPage(0);
        pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
        if(appinstance.currentFolder.getNumberOfPages()!=0){pageComboBox.setValue(1);}
        tableViewInbox.setItems(setObservableMails(appinstance.listEmails(0)));
        //pageComboBoxListenerIsActive=true;
        break;
      case "sent" :
        //pageComboBoxListenerIsActive=false;
        appinstance.currentFolder.setCurrentPage(0);
        pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
        if(appinstance.currentFolder.getNumberOfPages()!=0){pageComboBox.setValue(1);}
        tableViewSent.setItems(setObservableMails(appinstance.listEmails(0)));
        //pageComboBoxListenerIsActive=true;
        break;
      case "trash" :
        //pageComboBoxListenerIsActive=false;
        appinstance.currentFolder.setCurrentPage(0);
        pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
        if(appinstance.currentFolder.getNumberOfPages()!=0){pageComboBox.setValue(1);}
        tableViewTrash.setItems(setObservableMails(appinstance.listEmails(0)));
        //pageComboBoxListenerIsActive=true;
        break;
    }
  }

  public void tabChange(String tab) throws IOException {
    //appinstance.currentFolder.index.clear();

    if(appinstance.currentFolder != null) {
      switch (tab){
        case "Inbox" :
          //pageComboBoxListenerIsActive=false;
          appinstance.currentFolder.readFolder("inbox");
          appinstance.currentFolder.setCurrentPage(0);
          pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
          if(appinstance.currentFolder.getNumberOfPages()!=0){pageComboBox.setValue(1);}
          tableViewInbox.setItems(setObservableMails(appinstance.listEmails(0)));
          //pageComboBoxListenerIsActive=true;
          break;
        case "sent" :
          //pageComboBoxListenerIsActive=false;
          appinstance.currentFolder.readFolder("sent");
          appinstance.currentFolder.setCurrentPage(0);
          pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
          if(appinstance.currentFolder.getNumberOfPages()!=0){pageComboBox.setValue(1);}
          tableViewSent.setItems(setObservableMails(appinstance.listEmails(0)));
          //pageComboBoxListenerIsActive=true;
          break;
        case "trash" :
          pageComboBoxListenerIsActive=false;
          appinstance.currentFolder.readFolder("trash");
          appinstance.currentFolder.setCurrentPage(0);
          pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
          if(appinstance.currentFolder.getNumberOfPages()!=0){pageComboBox.setValue(1);}
          tableViewTrash.setItems(setObservableMails(appinstance.listEmails(0)));
          //pageComboBoxListenerIsActive=true;
          break;
      }

    }
  }

  public void newPage(Integer pageNumber){
    int pageNo = pageNumber.intValue();
    appinstance.currentFolder.setCurrentPage(pageNo);
    switch (appinstance.currentFolder.getViewedFolder()){
      case "inbox":
        tableViewInbox.setItems(setObservableMails(appinstance.listEmails(appinstance.currentFolder.getCurrentPage())));
        break;
      case "sent":
        tableViewSent.setItems(setObservableMails(appinstance.listEmails(appinstance.currentFolder.getCurrentPage())));
        break;
      case "trash":
        tableViewTrash.setItems(setObservableMails(appinstance.listEmails(appinstance.currentFolder.getCurrentPage())));
        break;
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}

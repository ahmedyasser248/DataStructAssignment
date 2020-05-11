package application;

import static classes.Mail.setObservableMails;

import classes.App;
import classes.Mail;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
@FXML private Pagination inboxTabPages;
@FXML private ComboBox pageComboBox;
public App appinstance = new App();


  public void init(){


    subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
    senderColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<Mail,LocalDateTime>("dateReceived"));
    tableViewInbox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    subjectColumnSent.setCellValueFactory(new PropertyValueFactory<>("subject"));
    sentToColumnSent.setCellValueFactory(new PropertyValueFactory<>("receivers"));
    dateColumnSent.setCellValueFactory(new PropertyValueFactory<Mail,LocalDateTime>("dateReceived"));
    tableViewSent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    appinstance.currentFolder.readFolder("inbox");

    pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
    pageComboBox.setValue(1);

    //activates when selecting a new page
    pageComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>()
    {
      public void changed(ObservableValue<? extends Integer> ov,
          final Integer oldvalue, final Integer newvalue)
      {
        if(newvalue==null){return;}
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
    Parent root = FXMLLoader.load(getClass().getResource("Compose.fxml"));
    Scene scene = new Scene(root);
    Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
    window.setScene(scene);
    window.show();
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
        ObservableList<Mail> viewedMails = tableViewInbox.getSelectionModel().getSelectedItems();
        appinstance.currentFolder.openMailWindows(appinstance.currentFolder.listToLinkedList(viewedMails));
        break;
    }
  }

  public void tabChange(String tab) throws IOException {
    appinstance.currentFolder.index.clear();

    if(appinstance.currentFolder != null) {
      switch (tab){
        case "Inbox" :
          appinstance.currentFolder.readFolder("inbox");
          appinstance.currentFolder.setCurrentPage(0);
          pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
          if(appinstance.currentFolder.getNumberOfPages()!=0){pageComboBox.setValue(1);}
          tableViewInbox.setItems(setObservableMails(appinstance.listEmails(0)));
          break;
        case "sent" :
          appinstance.currentFolder.readFolder("sent");
          appinstance.currentFolder.setCurrentPage(0);
          pageComboBox.setItems(appinstance.currentFolder.setPageComboBox());
          if(appinstance.currentFolder.getNumberOfPages()!=0){pageComboBox.setValue(1);}
          tableViewSent.setItems(setObservableMails(appinstance.listEmails(0)));
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

    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}

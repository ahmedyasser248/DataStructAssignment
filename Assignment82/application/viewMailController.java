package application;

import static classes.repeatedMethods.dateToString;

import classes.App;
import classes.Mail;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class viewMailController implements Initializable {

  @FXML
  Text fromText;
  @FXML
  Text toText;
  @FXML
  Text subjectText;
  @FXML
  Text dateText;
  @FXML
  TextField componentText;
  @FXML
  ListView<File> attachements;


  public App newInstance = new App();
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
  public void init(Mail viewedMail){
    subjectText.setText(viewedMail.getSubject());
    toText.setText(viewedMail.getReceivers());
    dateText.setText(dateToString(viewedMail.getDateReceived()));
    fromText.setText(viewedMail.getSender());
    componentText.setText(viewedMail.component);

  }
  public void setAttachements(ObservableList<File> attachs){
    attachements.getItems().setAll(attachs);
  }
}

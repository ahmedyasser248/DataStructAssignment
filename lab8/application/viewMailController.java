package application;

import static classes.repeatedMethods.dateToString;

import classes.Mail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
}

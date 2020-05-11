package application;

import classes.App;
import classes.Folder;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller1 {
  @FXML
  private AnchorPane rootpane;
  @FXML
  private TextField tf;
  @FXML
  private PasswordField pw;
  @FXML
  private Label lbl;

  public App appInstance = new App();

  public void signin(ActionEvent Event) throws IOException {
    String username=tf.getText();
    String password=pw.getText();

    if(appInstance.signin(username, password)) {
      appInstance.currentFolder = new Folder(username);

      FXMLLoader loader = new FXMLLoader(getClass().getResource("MailListing.fxml"));
      Parent root = loader.load();
      MailListingController mailListingController = loader.getController();
      mailListingController.appinstance = this.appInstance;
      mailListingController.init();

      Scene scene = new Scene(root);
      Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
      window.setScene(scene);
      window.show();

    }else {
      lbl.setText("wrong inputs try again");
    }

  }

  public void signUp(ActionEvent Event) throws IOException {
    System.out.println("i reached here");
    AnchorPane pane=FXMLLoader.load(getClass().getResource("signup.fxml"));
    rootpane.getChildren().setAll(pane);




  }


}
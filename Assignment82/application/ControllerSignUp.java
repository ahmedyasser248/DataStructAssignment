package application;

import classes.App;
import classes.Contact;
import classes.Filter;
import classes.Folder;
import classes.Sort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerSignUp {
  @FXML
  private TextField tf1;
  @FXML
  private PasswordField pw1;
  @FXML
  private TextField tf2;
  @FXML
  private PasswordField pw2;
  @FXML
  private TextField otherEmails;
  @FXML
  private Label lbl1;
  @FXML
  private Label lbl2;
  @FXML
  private Label lbl3;
  @FXML
  private Label lbl4;

  public App appInstance = new App();

  public void save(ActionEvent event) {
    try{App obj=new App();
      if(tf1.getText().trim().isEmpty()) {
        lbl1.setText("fill");
        return;}
      if(tf2.getText().trim().isEmpty()) {lbl2.setText("please fill");return;}
      if(pw1.getText().trim().isEmpty()) {lbl3.setText("please fill");return;}
      if(pw2.getText().trim().isEmpty()) {lbl4.setText("please fill");return;}
      if(!pw1.getText().equals(pw2.getText())) {lbl4.setText("must match password");return;}
      String emails;
      if(otherEmails.getText().trim().isEmpty()) {
        emails="none";
      } else {
        emails=otherEmails.getText();}
      Contact obj2=new Contact(tf1.getText(),tf2.getText(),pw1.getText(),emails);
      if(obj.signup(obj2)) {
        appInstance.setViewingOptions(new Folder(tf2.getText()),new Filter("",null),new Sort(null));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MailListing.fxml"));
        Parent root = loader.load();
        MailListingController mailListingController = loader.getController();
        mailListingController.appinstance = this.appInstance;
        mailListingController.init();

        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

      }else {
        lbl2.setText("choose another user name");
      }
    }catch(Exception e) {System.out.println(e);}

    //Filter trial = Class.forName("BySender").getDeclaredConstructor(String.class).newInstance();
  }


}

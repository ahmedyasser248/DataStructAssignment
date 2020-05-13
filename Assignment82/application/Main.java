package application;

import static classes.makeMails.def;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    def();
    try {
      Parent root=FXMLLoader.load(getClass().getResource("/application/signin.FXML"));
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
//  public void changeScenes(String fxmlDoc) throws IOException {
//    Parent root = FXMLLoader.load(getClass().getResource(fxmlDoc));
//    Scene scene = new Scene(root);
//    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    window.setScene(Scene);
//    window.show();
//  }

  public static void main(String[] args) {
    launch(args);
  }
}

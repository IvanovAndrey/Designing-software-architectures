package main.com.spbstu;

import main.com.spbstu.facade.Facade;
import main.com.spbstu.facade.FacadeImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main extends Application {
    private static Stage mainStage;

    public static Facade facade = (Facade) new FacadeImpl();

    public static void main(String[] args) {
        launch(args);
    }

   @Override
   public void start(Stage stage) throws Exception {
       mainStage = stage;
       mainStage.setTitle("TallantoFX");
       showStartView();
       mainStage.show();
   }

    public static void showStartView() {
       try{
        String fxmlFile = "/main/resources/fxml/startPage.fxml";
           FXMLLoader loader = new FXMLLoader();
           AnchorPane root = null;
           root = loader.load(Main.class.getResourceAsStream(fxmlFile));
        Scene scene = new Scene(root, 600, 400);
        mainStage.setScene(scene);
    } catch (
    IOException e) {
        e.printStackTrace();
    }
    }

    public static void showClientView(String login) {
        try{
            String fxmlFile = "/main/resources/fxml/clientProfilePage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = null;
            root = (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void showRegistrationView() {
        try {
            String fxmlFile = "/main/resources/fxml/registrationPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            Scene scene = new Scene(root, 500, 475);
            mainStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package main.com.spbstu;

import main.com.spbstu.controller.*;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.facade.FacadeImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

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
        try {
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

    public static void showClientView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/clientProfilePage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            clientProfilePageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void showAdminView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/adminProfilePage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            adminProfilePageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
    } catch(
    IOException e)
    {
        e.printStackTrace();
    }

}

    public static void showTeacherView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/teacherProfilePage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            teacherProfilePageController uvc = loader.getController();
            uvc.setup(login, name, status);
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


    public static void notificationClientView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/notificationsClientPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            notificationClientPageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 675, 405);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void notificationAdminView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/notificationsAdminPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            notificationAdminPageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 675, 405);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void notificationTeacherView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/notificationsTeacherPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            notificationTeacherPageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 675, 405);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void complaintView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/complaintPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(Main.class.getResourceAsStream(fxmlFile));
            complaintPageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void scheduleView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/shedulePage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            schedulePageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void requestView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/requestPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            requestPageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void createLessonView(String login, String name, String status) {
        try {
        String fxmlFile = "/main/resources/fxml/createLessonPage.fxml";
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
        createLessonPageController uvc = loader.getController();
        uvc.setup(login, name, status);
        Scene scene = new Scene(root, 600, 400);
        mainStage.setScene(scene);
    } catch(
    IOException e)
    {
        e.printStackTrace();
    }
    }
}


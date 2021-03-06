package main.com.spbstu;

import main.com.spbstu.controller.*;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.facade.FacadeImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.com.spbstu.project.ClientsOnLessons;
import main.com.spbstu.project.Lesson;
import main.com.spbstu.project.Notification;
import main.com.spbstu.storage.DataGateway;

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
            ClientProfilePageController uvc = loader.getController();
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
            AdminProfilePageController uvc = loader.getController();
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
            TeacherProfilePageController uvc = loader.getController();
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

    public static void notificationAdminView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/notificationsAdminPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            NotificationAdminPageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 675, 405);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void notificationUserView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/notificationUserPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            NotificationUserPageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 675, 405);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void complaintView(String login, String name, String status) {
        try {
            String fxmlFile = "/main/resources/fxml/complaintPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(Main.class.getResourceAsStream(fxmlFile));
            ComplaintPageController uvc = loader.getController();
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
            SchedulePageController uvc = loader.getController();
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
            RequestPageController uvc = loader.getController();
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
        CreateLessonPageController uvc = loader.getController();
        uvc.setup(login, name, status);
        Scene scene = new Scene(root, 600, 400);
        mainStage.setScene(scene);
    } catch(
    IOException e)
    {
        e.printStackTrace();
    }
    }

    public static void lessonView(String login, String name, String status, Lesson lesson) throws Exception {
        try {
            String fxmlFile = "/main/resources/fxml/lessonPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            LessonPageController uvc = loader.getController();
            uvc.setup(login, name, status, lesson);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void clientOnLessonView(String login, String name, String status, Lesson lesson, ClientsOnLessons col) throws Exception {
        try {
            String fxmlFile = "/main/resources/fxml/clientOnLessonPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            ClientOnLessonPageController uvc = loader.getController();
            uvc.setup(login, name, status, lesson, col);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }}

    public static void lessonClientView(String login, String name, String status, Lesson lesson) {
        try {
            String fxmlFile = "/main/resources/fxml/lessonPageClient.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            LessonPageClientController uvc = loader.getController();
            uvc.setup(login, name, status, lesson);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void notificationCreateView(String login, String name, String status) {
        try{
            String fxmlFile = "/main/resources/fxml/notificationCreate.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            NotificationCreatePageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
    } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void showUsersView(String login, String name, String status) {
        try{
            String fxmlFile = "/main/resources/fxml/usersPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
           UsersPageController uvc = loader.getController();
            uvc.setup(login, name, status);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void approveNotificationView(String login, String name, String status, Notification notification) {
        try{
            String fxmlFile = "/main/resources/fxml/approveNitificationPage.fxml";
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root =  (AnchorPane) loader.load(Main.class.getResourceAsStream(fxmlFile));
            ApproveNotificationPageController uvc = loader.getController();
            uvc.setup(login, name, status, notification);
            Scene scene = new Scene(root, 600, 400);
            mainStage.setScene(scene);
        } catch (Exception e)
        {
            e.printStackTrace();
        }}
}


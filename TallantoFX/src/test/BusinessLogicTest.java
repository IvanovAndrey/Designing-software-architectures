import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.IncorrectPasswordException;
import main.com.spbstu.exceptions.UserNotFoundException;
import main.com.spbstu.project.*;
import main.com.spbstu.storage.StorageRepository;
import main.com.spbstu.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static junit.framework.TestCase.*;

public class BusinessLogicTest {
    User admin;
    User client;
    User teacher;
    StorageRepository repository;

    @Before
    public void setUp() throws UserNotFoundException, DBConnectionException, IncorrectPasswordException {
        repository = new StorageRepository();
        admin = repository.getUser("admin");
        admin.signIn("pass");
        teacher = repository.getUser("teacher");
        teacher.signIn("pass");
        client = repository.getUser("client1");
        teacher.signIn("pass");
    }

    @After
    public void tearDown() throws DBConnectionException {
        repository.drop();
        repository.clear();
        repository = null;
        admin = null;
        client = null;
        teacher = null;
    }


    @Test
    public void paymentBP() throws Exception{
        int oldNotificationsSize = repository.getNotifications().size();
        int oldLessonsSize = repository.getLessons().size();
        int oldComplaintsSize = repository.getComplaints().size();


            Request request = teacher.createRequest(teacher.getId(),"ПН % ВТ", Date.valueOf("2020-06-10"));
            assertTrue("Add request", true);
            assertTrue(repository.getRequests().contains(request));

            Notification notification = admin.createNotification(admin.getId(),teacher.getId(),"NEW","Доход","Ваш заработок равен 1000");
            assertTrue("Add notification", true);
            assertEquals(oldNotificationsSize + 1, repository.getNotifications().size());
            assertTrue(repository.getNotifications().contains(notification));


            teacher.setNotificationStatus(notification.getId(),"APPROVED");
            assertTrue("Set status", true);
            notification.setStatus("APPROVED");
            assertTrue(repository.getNotifications().contains(notification));
         try {
             Lesson lesson = admin.createLesson("","testTheme","ВС", "client1-client2");
         }
        catch (Exception e) {
            assertFalse("No teacher name", false);
        }
        try {
            Lesson lesson = admin.createLesson("teacher","","ВС", "client1-client2");
        }
        catch (Exception e) {
            assertFalse("No theme", false);
        }
        try {
            Lesson lesson = admin.createLesson("teacher","testTheme","ВС", "");
        }
        catch (Exception e) {
            assertFalse("No clients", false);
        }
        try {
            Lesson lesson = admin.createLesson("admin","testTheme","ВС", "client1-client2");
        }
        catch (Exception e) {
            assertFalse("No teacher status", false);
        }
        try {
            Lesson lesson = admin.createLesson("teacher","testTheme","ВС", "client1-admin");
        }
        catch (Exception e) {
            assertFalse("No client status", false);
        }

        Lesson lesson = admin.createLesson("teacher","testTheme","ВС", "client1-client2");
        assertTrue("Create lesson", true);

        assertEquals(oldLessonsSize + 1, repository.getNotifications().size());
        assertTrue(repository.getLessons().contains(lesson));
        try {
            Lesson lesson2 = admin.createLesson("teacher","testTheme","ВС", "client1-client2");
        }
        catch (Exception e) {
            assertFalse("Second lesson on this date", false);
        }

        lesson.setTheme("New Test Theme");
        lesson.setStatus("CLOSED");
        lesson.setCommetnary("Test Commentary");
        teacher.updateLesson(lesson);
        assertTrue("Update lesson", true);
        assertTrue(repository.getLessons().contains(lesson));

        List<ClientsOnLessons> clientsOnLesson = lesson.getCol(lesson.getId());
        assertFalse("No clients on lesson",clientsOnLesson.isEmpty());

        clientsOnLesson.get(0).setStatus("Посетил");
        assertTrue("Update status first", true);
        clientsOnLesson.get(0).setCommetnary("Отлично себя вел");
        assertTrue("Update commentary first", true);
        clientsOnLesson.get(1).setStatus("Прогул");
        assertTrue("Update status second", true);
        clientsOnLesson.get(1).setCommetnary("Сволочь");
        assertTrue("Update commentary second", true);

        teacher.updateCON(clientsOnLesson.get(0));
        assertTrue("Add fiest changes to client on lesson", true);
        assertTrue(repository.getCOL().contains(clientsOnLesson.get(0)));

        teacher.updateCON(clientsOnLesson.get(1));
        assertTrue("Add second changes to client on lesson", true);
        assertTrue(repository.getCOL().contains(clientsOnLesson.get(1)));

        try {
            Complaint complaint = client.createComplaint("","testTheme","Test Complaint");
        }
        catch (Exception e) {
            assertFalse("No id incedent", false);
        }
        try {
            Complaint complaint = client.createComplaint("2","","Test Complaint");
        }
        catch (Exception e) {
            assertFalse("No theme", false);
        }
        try {
            Complaint complaint = client.createComplaint("2","testTheme","");
        }
        catch (Exception e) {
            assertFalse("No text", false);
        }
        try {
            Complaint complaint = admin.createComplaint("2","testTheme","Test Complaint");
        }
        catch (Exception e) {
            assertFalse("Incorrect id incedent", false);
        }

        Complaint complaint = client.createComplaint("2","testTheme","Test Complaint");
        assertTrue("Add complaint", true);
        assertEquals(oldComplaintsSize + 1, repository.getComplaints().size());
        assertTrue(repository.getComplaints().contains(complaint));

    }
}

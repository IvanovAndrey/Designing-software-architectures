import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.IncorrectPasswordException;
import main.com.spbstu.exceptions.UserNotFoundException;
import main.com.spbstu.project.Notification;
import main.com.spbstu.project.Request;
import main.com.spbstu.storage.StorageRepository;
import main.com.spbstu.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static junit.framework.TestCase.*;

public class BPpaymentTest {
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
        int oldRequestsSize = repository.getRequests().size();
        int oldNotificationsSize = repository.getNotifications().size();


            Request request = teacher.createRequest(teacher.getId(),"ПН % ВТ", Date.valueOf("2020-06-10"));
            assertTrue("Add request", true);
            List<Request> a = repository.getRequests();
            int b = repository.getRequests().size();
            assertTrue(repository.getRequests().contains(request));

            Notification notification = admin.createNotification(admin.getId(),teacher.getId(),"NEW","Доход","Ваш заработок равен 1000");
            assertTrue("Add notification", true);
            assertEquals(oldNotificationsSize + 1, repository.getNotifications().size());
            assertTrue(repository.getNotifications().contains(notification));


            teacher.setNotificationStatus(notification.getId(),"APPLY");
            assertTrue("Set status", true);
            notification.setStatus("APPLY");
            assertTrue(repository.getNotifications().contains(notification));
    }
}

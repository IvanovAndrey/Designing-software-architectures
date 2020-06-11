import main.com.spbstu.exceptions.IncorrectPasswordException;
import main.com.spbstu.exceptions.UserAlreadyExistsException;
import main.com.spbstu.storage.StorageRepository;
import main.com.spbstu.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class StorageRepositoryTest {

    StorageRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new StorageRepository();
    }

    @After
    public void tearDown() throws Exception {
        repository.drop();
        repository.clear();
        repository = null;
    }

    @Test
    public void addUserTest() throws Exception {

        try {
            repository.addUser("testuser", "testuser", "cilent", "pass");
            assertTrue("Added user", true);
        } catch (Exception e) {
            assertTrue("Can't add user", false);
        }
        assertNotNull("Added user not found", repository.getUser("testuser"));

        try {
            repository.addUser("testuser", "testuser", "client", "pass");
            assertTrue("Added user", false);
        } catch (UserAlreadyExistsException e) {
            assertTrue("Exception thrown", true);
        }
        try {
            repository.addUser("testuser2", "testuser2", "admin", "pass");
            assertTrue("Added user", true);
        } catch (Exception e) {
            assertTrue("Can't add user", false);
        }

        assertNotNull("Added user not found", repository.getUser("testuser"));
        assertNotNull("Added user not found", repository.getUser("testuser2"));
    }

    @Test
    public void authenticateUserTest() throws Exception {

        try {
            repository.addUser("123", "123", "admin", "123");
            assertTrue("Exception not thrown", true);
        } catch (UserAlreadyExistsException e) {
            assertTrue("Exception thrown", false);
        }

        User user = repository.getUser("123");
        assertNotNull("Added user not found", user);

        try {
            repository.authenticateUser(user.getLogin(), "pass");
            assertFalse("Authenticated with wrong password", true);
        } catch (IncorrectPasswordException e) {
            assertTrue("Exception thrown", true);
        }
        try {
            repository.authenticateUser(user.getLogin(), "123");
            assertFalse("Authenticated with correct password", false);
        } catch (IncorrectPasswordException e) {
            assertTrue("Not authenticated with correct password", false);
        }
    }


}

package sk.pocsik.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sk.pocsik.models.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {
    private static AuthService authService;
    private static SessionFactory sessionFactory;

    private static Session session;


    @BeforeAll
    public static void setup() {
        authService = new AuthService();
        Configuration conf = new Configuration();
        conf.addAnnotatedClass(User.class);
        sessionFactory = conf.buildSessionFactory();
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User("test", "123");

        session.persist(user);
        session.merge(user);
        transaction.commit();
    }

    @AfterAll
    public static void tearDown() {
        session.close();
        sessionFactory.close();
    }

    @Test
    public void loginAllFieldsEmpty() {
        String username = "";
        String password = "";

        List<String> errors = authService.checkLoginFields(username, password);

        assertEquals(2, errors.size());
        assertTrue(errors.contains("Username is required."));
        assertTrue(errors.contains("Password is required."));
    }

    @Test
    public void loginAllFieldsNull() {
        String username = null;
        String password = null;

        List<String> errors = authService.checkLoginFields(username, password);

        assertEquals(2, errors.size());
        assertTrue(errors.contains("Username is required."));
        assertTrue(errors.contains("Password is required."));
    }

    @Test
    public void loginUserNameIsNull() {
        List<String> errors = authService.checkLoginFields(null, "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username is required."));
    }

    @Test
    public void loginUserNameIsEmpty() {
        List<String> errors = authService.checkLoginFields("", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username is required."));
    }


    @Test
    void loginUsernameFieldMoreThan50() {
        String username = "a".repeat(51);

        List<String> errors = authService.checkLoginFields(username, "password");

        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username should not exceed 50 characters."));
    }

    @Test
    public void loginPasswordIsNull() {
        List<String> errors = authService.checkLoginFields("username", null);
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Password is required."));
    }

    @Test
    public void loginPasswordIsEmpty() {
        List<String> errors = authService.checkLoginFields("username", "");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Password is required."));
    }

    @Test
    public void loginValidUserNameAndPassword() {
        List<String> errors = authService.checkLoginFields("username", "password");
        assertEquals(0, errors.size());
    }

    @Test
    public void registerUserNameIsNull() {

        List<String> errors = authService.checkRegisterFields(null, "password", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username is required."));
    }

    @Test
    public void registerUserNameIsEmpty() {

        List<String> errors = authService.checkRegisterFields("", "password", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username is required."));
    }

    @Test
    public void registerUserNameExceedsMaxLength() {
        String longUsername = "a".repeat(51);
        List<String> errors = authService.checkRegisterFields(longUsername, "password", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username should not exceed 50 characters."));
    }

    @Test
    public void registerUserNameIsTaken() {
        List<String> errors = authService.checkRegisterFields("steve", "password", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username is taken."));
    }

    @Test
    public void registerPasswordIsNull() {

        List<String> errors = authService.checkRegisterFields("username", null, "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Password is required."));
    }

    @Test
    public void registerPasswordIsEmpty() {

        List<String> errors = authService.checkRegisterFields("username", "", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Password is required."));
    }

    @Test
    public void registerPasswordsDoNotMatch() {

        List<String> errors = authService.checkRegisterFields("username", "password", "differentPassword");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Passwords not match."));
    }

    @Test
    public void registerValidUserNameAndPassword() {

        List<String> errors = authService.checkRegisterFields("username", "password", "password");
        assertEquals(0, errors.size());
    }
}
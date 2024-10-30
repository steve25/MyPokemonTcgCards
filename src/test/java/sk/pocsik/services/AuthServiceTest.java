package sk.pocsik.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sk.pocsik.daos.UserDao;
import sk.pocsik.models.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceTest {
    private static AuthService authService;
    private static AuthService mockedAuthService;
    private static UserDao mockedUserDao;
    private static UserService mockedUserService;
    private static BCryptPasswordEncoder passwordEncoder;


    @BeforeAll
    public static void setup() {
        authService = new AuthService();
        mockedAuthService = mock(AuthService.class);
        mockedUserDao = mock(UserDao.class);
//        mockedUserService = new UserService(mockedUserDao);
        passwordEncoder = new BCryptPasswordEncoder();
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
        when(mockedAuthService.checkIfExists(null)).thenReturn(false);
        when(mockedAuthService.checkRegisterFields(null, "password", "password")).thenCallRealMethod();
        List<String> errors = mockedAuthService.checkRegisterFields(null, "password", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username is required."));
    }

    @Test
    public void registerUserNameIsEmpty() {
        when(mockedAuthService.checkIfExists("")).thenReturn(false);
        when(mockedAuthService.checkRegisterFields("", "password", "password")).thenCallRealMethod();
        List<String> errors = mockedAuthService.checkRegisterFields("", "password", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username is required."));
    }

    @Test
    public void registerUserNameExceedsMaxLength() {
        String longUsername = "a".repeat(51);
        when(mockedAuthService.checkIfExists(longUsername)).thenReturn(false);
        when(mockedAuthService.checkRegisterFields(longUsername, "password", "password")).thenCallRealMethod();
        List<String> errors = mockedAuthService.checkRegisterFields(longUsername, "password", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username should not exceed 50 characters."));
    }

    @Test
    public void registerUserNameIsTaken() {
        when(mockedAuthService.checkIfExists("testUser")).thenReturn(true);
        when(mockedAuthService.checkRegisterFields("testUser", "password", "password")).thenCallRealMethod();
        List<String> errors = mockedAuthService.checkRegisterFields("testUser", "password", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Username is taken."));
    }

    @Test
    public void registerPasswordIsNull() {
        when(mockedAuthService.checkIfExists("username")).thenReturn(false);
        when(mockedAuthService.checkRegisterFields("username", null, "password")).thenCallRealMethod();
        List<String> errors = mockedAuthService.checkRegisterFields("username", null, "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Password is required."));
    }

    @Test
    public void registerPasswordIsEmpty() {
        when(mockedAuthService.checkIfExists("username")).thenReturn(false);
        when(mockedAuthService.checkRegisterFields("username", "", "password")).thenCallRealMethod();
        List<String> errors = mockedAuthService.checkRegisterFields("username", "", "password");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Password is required."));
    }

    @Test
    public void registerPasswordsDoNotMatch() {
        when(mockedAuthService.checkIfExists("username")).thenReturn(false);
        when(mockedAuthService.checkRegisterFields("username", "password", "differentPassword")).thenCallRealMethod();
        List<String> errors = mockedAuthService.checkRegisterFields("username", "password", "differentPassword");
        assertEquals(1, errors.size());
        assertTrue(errors.contains("Passwords not match."));
    }

    @Test
    public void registerValidUserNameAndPassword() {
        when(mockedAuthService.checkIfExists("username")).thenReturn(false);
        when(mockedAuthService.checkRegisterFields("username", "password", "password")).thenCallRealMethod();
        List<String> errors = mockedAuthService.checkRegisterFields("username", "password", "password");
        assertEquals(0, errors.size());
    }

    @Test
    void authenticateFoundUserAndPassword() {
        User user = new User("user", passwordEncoder.encode("password"));
        when(mockedUserDao.findUserByName("user")).thenReturn(user);
//        when(mockedAuthService.authenticate("user", "password")).thenCallRealMethod();

        boolean result = mockedAuthService.authenticate("user", "password");

        assertTrue(result);
    }

    @Test
    void register() {
    }

    @Test
    void checkIfExists() {
    }

    @Test
    void logout() {
    }
}
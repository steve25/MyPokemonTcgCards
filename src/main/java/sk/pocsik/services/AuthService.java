package sk.pocsik.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sk.pocsik.daos.UserDao;
import sk.pocsik.models.User;
import sk.pocsik.utils.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private final UserDao userDao;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userDao = new UserDao();
        this.userService = new UserService(userDao);
    }

    public List<String> checkLoginFields(String userName, String password) {
        List<String> errors = new ArrayList<>();
        if (userName == null || userName.isEmpty()) {
            errors.add("Username is required.");
        }
        if (userName != null && userName.length() >= 50) {
            errors.add("Username should not exceed 50 characters.");
        }
        if (password == null || password.isEmpty()) {
            errors.add("Password is required.");
        }

        return errors;
    }

    public boolean authenticate(String username, String password) {
        User user = userService.findUserByName(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) return false;

        UserInfo.setUserInfo(user);

        return true;
    }

    public List<String> checkRegisterFields(String userName, String password, String confirmPassword) {
        List<String> errors = new ArrayList<>();
        if (userName == null || userName.isEmpty()) {
            errors.add("Username is required.");
        }
        if (userName != null && userName.length() >= 50) {
            errors.add("Username should not exceed 50 characters.");
        }
        if (this.checkIfExists(userName)) {
            errors.add("Username is taken.");
        }
        if (password == null || password.isEmpty()) {
            errors.add("Password is required.");
        }
        if (password == null || !password.equals(confirmPassword)) {
            errors.add("Passwords not match.");
        }

        return errors;
    }

    public void register(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        userService.save(user);

        UserInfo.setUserInfo(user);
    }

    private boolean checkIfExists(String username) {
        return userService.findUserByName(username) != null;
    }

    public void logout() {
        UserInfo.setUserInfo(null);
    }
}

package sk.pocsik.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sk.pocsik.daos.UserDao;
import sk.pocsik.models.User;
import sk.pocsik.utils.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    List<String> errors;

    public AuthService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        UserDao userDao = new UserDao();
        this.userService = new UserService(userDao);
        this.errors = new ArrayList<>();
    }

    public List<String> checkLoginFields(String userName, String password) {
        this.errors.clear();

        if (userName == null || userName.isEmpty()) {
            this.errors.add("Username is required.");
        }
        if (userName != null && userName.length() >= 50) {
            this.errors.add("Username should not exceed 50 characters.");
        }
        if (password == null || password.isEmpty()) {
            this.errors.add("Password is required.");
        }

        return this.errors;
    }

    public boolean authenticate(String username, String password) {
        User user = userService.findUserByName(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) return false;

        UserInfo.setUserInfo(user);

        return true;
    }

    public List<String> checkRegisterFields(String userName, String password, String confirmPassword) {
        this.errors.clear();

        if (userName == null || userName.isEmpty()) {
            this.errors.add("Username is required.");
        }
        if (userName != null && userName.length() >= 50) {
            this.errors.add("Username should not exceed 50 characters.");
        }
        if (this.checkIfExists(userName)) {
            this.errors.add("Username is taken.");
        }
        if (password == null || password.isEmpty()) {
            this.errors.add("Password is required.");
        }
        if (password != null && !password.isEmpty() && !password.equals(confirmPassword)) {
            this.errors.add("Passwords not match.");
        }

        return this.errors;
    }

    public void register(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        userService.save(user);

        UserInfo.setUserInfo(user);
    }

    public boolean checkIfExists(String username) {
        return userService.findUserByName(username) != null;
    }

    public void logout() {
        UserInfo.setUserInfo(null);
    }
}

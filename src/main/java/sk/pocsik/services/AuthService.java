package sk.pocsik.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sk.pocsik.daos.UserDao;
import sk.pocsik.models.User;
import sk.pocsik.utils.UserInfo;

public class AuthService {
    private final UserDao userDao;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.userDao = new UserDao();
        this.userService = new UserService(userDao);
    }

    public boolean authenticate(String username, String password) {
        User user = userService.findUserByName(username);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) return false;

        UserInfo.setUserInfo(user.getId(), user.getUsername());

        return true;
    }

    public void register(String username, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        userService.save(user);

        UserInfo.setUserInfo(user.getId(), user.getUsername());
    }

    public boolean checkIfExists(String username) {
        return userService.findUserByName(username) != null;
    }

    public void logout() {
        UserInfo.setUserInfo(null, null);
    }
}

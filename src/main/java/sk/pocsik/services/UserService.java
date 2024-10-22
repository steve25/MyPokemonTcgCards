package sk.pocsik.services;

import sk.pocsik.daos.UserDao;
import sk.pocsik.models.User;

import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }
}

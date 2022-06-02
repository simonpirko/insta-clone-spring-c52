package by.tms.insta.service;

import by.tms.insta.dao.UserStorage;
import by.tms.insta.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserService {
    private final UserStorage userStorage;

    public UserService(@Qualifier("hibernateUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public boolean save(User user) {
        if (userStorage.userExists(user.login)) {
            return false;
        }
        return userStorage.save(new User());
    }

    public User findUserByLogin(User user) {
        return userStorage.findUserByLogin(user.login);
    }

    public List<User> findAll() {
        return userStorage.findAll();
    }

    public boolean authUserByLoginAndPass(User user) {
        if (!userStorage.userExists(user.login)) {
            return false;
        }

        User userByLogin = findUserByLogin(user);
        if (userByLogin.password.equals(user.getPassword())) {
            return false;
        }
        return true;
    }
}

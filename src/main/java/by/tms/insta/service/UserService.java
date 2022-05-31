package by.tms.insta.service;

import by.tms.insta.dao.UserStorage;
import by.tms.insta.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserService {
    private final UserStorage userStorage;
    private final UserValidator userValidator;

    public UserService(@Qualifier("hibernateUserStorage") UserStorage userStorage, UserValidator userValidator) {
        this.userStorage = userStorage;
        this.userValidator = userValidator;
    }

    public boolean save(User user) {
        if (!userValidator.loginAndNameValidate(user.login, user.name) || !userValidator.passwordValidate(user.password)) {
            return false;
        }
        if (!userStorage.userExists(user)) {
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
        if (!userStorage.userExists(user)) {
            return false;
        }

        User userByLogin = findUserByLogin(user);
        if (userByLogin.password.equals(user.getPassword())) {
            return false;
        }
        return true;
    }
}

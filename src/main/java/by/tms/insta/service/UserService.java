package by.tms.insta.service;


import by.tms.insta.dao.UserStorage;
import by.tms.insta.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserStorage userStorage;

    public UserService(@Qualifier("hibernateUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public boolean save(User user) {
        if (userStorage.userExists(user.getLogin())) {
            return false;
        }
        return userStorage.save(user);
    }

    public User findUserByLogin(User user) {
        return userStorage.findUserByLogin(user.getLogin());
    }

    public List<User> findAll() {
        return userStorage.findAll();
    }


    public boolean userExists(String login) {
        return userStorage.userExists(login);
    }

    public boolean authUserByLoginAndPass(User user) {
        if (!userStorage.userExists(user.getLogin())) {
            return false;
        }
        User userByLogin = findUserByLogin(user);
        return userByLogin.getPassword().equals(user.getPassword());
    }

    public List<User> getFollowings(User user) {
        return userStorage.findUserByLogin(user.getLogin()).getFollowing();
    }
}
